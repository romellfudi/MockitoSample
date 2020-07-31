package fudi.freddy.mokitosample.share

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
interface Repository {
    fun save(newData: String?)
    fun load(): String?
}