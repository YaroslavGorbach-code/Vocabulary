package yaroslavgorbach.koropapps.vocabulary.utils.feature.billing

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.queryProductDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BillingManager(private val context: Context) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var billingClient: BillingClient? = null
    private var listener: PurchaseListener? = null

    init {
        initBillingClient()
    }

    private fun initBillingClient() {
        billingClient = BillingClient.newBuilder(context)
            .enablePendingPurchases(
                PendingPurchasesParams
                    .newBuilder()
                    .enableOneTimeProducts().build()
            )
            .setListener { billingResult, purchases ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                    purchases.forEach(::handlePurchase)
                }
            }
            .build()
    }

    fun setOnAcknowledgedListener(purchaseListener: PurchaseListener){
        listener = purchaseListener
    }

    fun showPurchasesDialog(activity: Activity) {
        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    val productList = listOf(
                        QueryProductDetailsParams.Product.newBuilder()
                            .setProductId("remove_ads")
                            .setProductType(BillingClient.ProductType.INAPP)
                            .build()
                    )

                    val params = QueryProductDetailsParams.newBuilder()
                    params.setProductList(productList)

                    scope.launch {
                        val result = billingClient?.queryProductDetails(params.build())

                        result?.productDetailsList?.firstOrNull()?.let { product ->
                            val productDetailsParamsList = listOf(
                                BillingFlowParams.ProductDetailsParams.newBuilder()
                                    .setProductDetails(product)
                                    .build()
                            )

                            val billingFlowParams = BillingFlowParams.newBuilder()
                                .setProductDetailsParamsList(productDetailsParamsList)
                                .build()

                            withContext(Dispatchers.Main) {
                                billingClient?.launchBillingFlow(activity, billingFlowParams)
                            }
                        }

                    }
                }
            }

            override fun onBillingServiceDisconnected() {
                // Retry logic or show error
            }
        })
    }

    fun queryPurchases() {
        billingClient?.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        ) { billingResult, purchasesList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                if (purchasesList.isEmpty()){
                    listener?.onUserHasNoPurchases()
                }

                purchasesList.forEach(::handlePurchase)
            }
        }
    }

    fun endConnection() {
        billingClient?.endConnection()
        scope.cancel()
    }

    private fun handlePurchase(purchase: Purchase) {
        when {
            purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged -> {
                val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()

                billingClient?.acknowledgePurchase(acknowledgePurchaseParams) {
                    listener?.onPurchaseAcknowledged(purchase)
                }
            }
        }
    }

    interface PurchaseListener {
        fun onPurchaseAcknowledged(purchase: Purchase)
        fun onUserHasNoPurchases()
    }
}