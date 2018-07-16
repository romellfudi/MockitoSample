package fudi.freddy.mokitosample.share;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public interface Repository {

    void save(String newData);
    String load();
}
