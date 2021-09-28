package yaroslavgorbach.koropapps.vocabulary.utils

fun <T> unsafeLazy(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)