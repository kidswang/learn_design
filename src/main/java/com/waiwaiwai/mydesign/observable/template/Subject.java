package com.waiwaiwai.mydesign.observable.template;


public interface Subject {
    void registerObserver(Observable observable);

    void removeObserver(Observable observable);

    void notifyObservers(String message);
}
