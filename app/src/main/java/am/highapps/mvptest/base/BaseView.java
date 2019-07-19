package am.highapps.mvptest.base;


public interface BaseView<T> {

    void setPresenter(T presenter);

    void showProgress();

    void hideProgress();

}
