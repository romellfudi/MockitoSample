package fudi.freddy.mokitosample.store;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public interface StoreContract<T> {
    void save(T object);

    T load(String mail);

}
