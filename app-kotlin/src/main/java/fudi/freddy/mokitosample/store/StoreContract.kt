package fudi.freddy.mokitosample.store

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
interface StoreContract<T> {
    fun save(`object`: T)
    fun load(mail: String): T
}