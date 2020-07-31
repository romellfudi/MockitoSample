package fudi.freddy.mokitosample.share;

/**
 * Created by romelldominguez on 2/24/17.
 */

public interface ShareContract {

    interface ShareView{
        void reLoadList();
        void clearText();
        void loadText(String text);

    }

    interface ShareUserActions{
        void saveInput(String text); // Object
        void loadInput();
        void display(String dat);
    }
}
