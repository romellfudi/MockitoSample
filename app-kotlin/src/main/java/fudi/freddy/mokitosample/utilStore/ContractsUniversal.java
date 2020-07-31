package fudi.freddy.mokitosample.utilStore;

import fudi.freddy.mokitosample.store.SharePreferenceContact;
import fudi.freddy.mokitosample.store.StoreContract;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 2/26/17
 */

public class ContractsUniversal {

    public static StoreContract getStoreContract() {
        return new SharePreferenceContact();
    }
}
