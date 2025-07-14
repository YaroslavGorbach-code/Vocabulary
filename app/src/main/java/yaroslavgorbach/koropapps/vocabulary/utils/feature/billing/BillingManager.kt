package yaroslavgorbach.koropapps.vocabulary.utils.feature.billing

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.queryProductDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resumeWithException

class BillingManager(private val context: Context) {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    var billingClient: BillingClient? = null
    private var listener: PurchaseListener? = null

    companion object {
        const val IN_APP_PERMANENT_SUBSCRIPTION = "remove_ads"
    }

    init {
        initBillingClient()
    }

    suspend fun queryPermanentSubscriptionProduct(): List<ProductDetails> {
        ensureBillingConnected()

        return queryProductDetails(
            listOf(IN_APP_PERMANENT_SUBSCRIPTION),
            BillingClient.ProductType.INAPP
        )
    }


    fun setOnAcknowledgedListener(purchaseListener: PurchaseListener) {
        listener = purchaseListener
    }

    fun showOneTimeProduct(activity: Activity) {

        scope.launch {
            ensureBillingConnected()

            val products = queryProductDetails(
                listOf(IN_APP_PERMANENT_SUBSCRIPTION),
                BillingClient.ProductType.INAPP
            )
            Log.v("dasasdasd", products.map { it.productId }.joinToString())
            products.firstOrNull()?.let { productDetails ->
                launchOnTimeProductBillingFlow(activity, productDetails)
            }
        }
    }

    fun showSubscription(activity: Activity, subscriptionId: String) {
        scope.launch {
            val products = queryProductDetails(
                listOf(subscriptionId),
                BillingClient.ProductType.SUBS
            )
            products.firstOrNull()?.let { productDetails ->
                launchSubscriptionBillingFlow(activity, productDetails)
            }
        }
    }

    private fun initBillingClient() {
        billingClient = BillingClient.newBuilder(context)
            .enablePendingPurchases(
                PendingPurchasesParams
                    .newBuilder()
                    .enableOneTimeProducts()
                    .build()
            )
            .setListener { billingResult, purchases ->
                if (purchases.isNullOrEmpty()) {
                    listener?.onUserHasNoPurchases()
                }
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                    purchases.forEach(::handlePurchase)
                }
            }
            .build()
    }

    private suspend fun ensureBillingConnected() {
        if (billingClient?.isReady == true) return

        suspendCancellableCoroutine { continuation ->
            billingClient?.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(result: BillingResult) {
                    if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                        continuation.resume(Unit, {})
                    } else {
                        continuation.resumeWithException(
                            RuntimeException("Billing setup failed: ${result.debugMessage}")
                        )
                    }
                }

                override fun onBillingServiceDisconnected() {
                    continuation.resumeWithException(
                        RuntimeException("Billing service disconnected")
                    )
                }
            })
        }
    }

    private suspend fun launchOnTimeProductBillingFlow(
        activity: Activity,
        productDetails: ProductDetails
    ) {
        val params = BillingFlowParams.ProductDetailsParams.newBuilder()
            .setProductDetails(productDetails)
            .build()

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(listOf(params))
            .build()

        withContext(Dispatchers.Main) {
            billingClient?.launchBillingFlow(activity, billingFlowParams)
        }
    }

    private suspend fun launchSubscriptionBillingFlow(
        activity: Activity,
        productDetails: ProductDetails
    ) {
        val offerToken = productDetails
            .subscriptionOfferDetails
            ?.firstOrNull()
            ?.offerToken

        if (offerToken == null) {
            Log.e(
                "BillingManager",
                "No offer token found for subscription ${productDetails.productId}"
            )
            return
        }

        val productDetailsParams = BillingFlowParams.ProductDetailsParams.newBuilder()
            .setProductDetails(productDetails)
            .setOfferToken(offerToken)
            .build()

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(listOf(productDetailsParams))
            .build()

        withContext(Dispatchers.Main) {
            billingClient?.launchBillingFlow(activity, billingFlowParams)
        }
    }

    private suspend fun queryProductDetails(
        productIds: List<String>,
        productType: String
    ): List<ProductDetails> {
        ensureBillingConnected()
        Log.v("dsaasdasd", "products.joinToString()")

        if (productIds.isEmpty()) return emptyList()

        val products = productIds.map { productId ->
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(productId)
                .setProductType(productType)
                .build()
        }
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(products)
            .build()

        val result = billingClient?.queryProductDetails(params)
        Log.v("dsaasdasd", result!!.productDetailsList!!.map { it.productId }.toString())
        return result?.productDetailsList ?: emptyList()
    }

    private fun handlePurchase(purchase: Purchase) {
        when {
            purchase.purchaseState == Purchase.PurchaseState.PURCHASED &&
                    !purchase.isAcknowledged -> {
                val acknowledgeParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()

                billingClient?.acknowledgePurchase(acknowledgeParams) {
                    listener?.onPurchaseAcknowledged(purchase)
                }
            }
        }
    }

    suspend fun getAllActivePurchases(context: Context): List<Purchase> {
        ensureBillingConnected()
        val inAppDeferred = scope.async {
            suspendCancellableCoroutine<List<Purchase>> { cont ->
                billingClient?.queryPurchasesAsync(
                    QueryPurchasesParams.newBuilder()
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
                ) { billingResult, purchases ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        cont.resume(purchases, {})
                    } else {
                        cont.resume(emptyList(), {})
                    }
                }
            }
        }

        val subsDeferred = scope.async {
            suspendCancellableCoroutine<List<Purchase>> { cont ->
                billingClient?.queryPurchasesAsync(
                    QueryPurchasesParams.newBuilder()
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build()
                ) { billingResult, purchases ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        cont.resume(purchases, {})
                    } else {
                        cont.resume(emptyList(), {})
                    }
                }
            }
        }

        val inApp = inAppDeferred.await()
        val subs = subsDeferred.await()

        return inApp + subs
    }


    interface PurchaseListener {
        fun onPurchaseAcknowledged(purchase: Purchase)
        fun onUserHasNoPurchases()
    }
}