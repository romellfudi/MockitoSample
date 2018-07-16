package fudi.freddy.mokitosample.share;

import android.support.annotation.VisibleForTesting;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class SharePresent implements ShareContract.ShareUserActions {

    private ShareContract.ShareView shareView;
    private Repository repository = RepositoryShare.getInstance();


    public SharePresent(ShareContract.ShareView shareView) {
        this.shareView = shareView;
    }

    @VisibleForTesting
    void setShareView(ShareContract.ShareView shareView) {
        this.shareView = shareView;
    }

    @Override
    public void saveInput(String string) {
        repository.save(string);
        shareView.clearText();
        shareView.reLoadList();
    }

    @Override
    public void loadInput() {
        String result = repository.load();
        shareView.loadText(result);
    }

    @Override
    public void display(String dat) {
        shareView.loadText(dat);
    }
}
