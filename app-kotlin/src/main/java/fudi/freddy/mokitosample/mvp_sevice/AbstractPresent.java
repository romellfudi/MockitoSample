package fudi.freddy.mokitosample.mvp_sevice;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public abstract class AbstractPresent<T extends MVPView> {

    T mvpView;

    public AbstractPresent(){

    }

    public void attach(T actView){
        mvpView = actView;
    }

    public void detach(){
        mvpView = null;
    }

    public T getView(){
        return mvpView;
    }

}
