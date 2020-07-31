package fudi.freddy.mokitosample.mvp_sevice

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
abstract class AbstractPresent<T : MVPView?> {
    var view: T? = null
    open fun attach(actView: T) {
        view = actView
    }

    open fun detach() {
        view = null
    }

}