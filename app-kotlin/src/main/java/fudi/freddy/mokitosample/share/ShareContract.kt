package fudi.freddy.mokitosample.share

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
interface ShareContract {
    interface ShareView {
        fun reLoadList()
        fun clearText()
        fun loadText(text: String?)
    }

    interface ShareUserActions {
        fun saveInput(text: String?) // Object
        fun loadInput()
        fun display(dat: String?)
    }
}