package com.waiwaiwai.mydesign.observable.template;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcreteSubject implements Subject {

    private List<Observable> observables = new ArrayList<>();

    @Override
    public void registerObserver(Observable observable) {
        observables.add(observable);
    }

    @Override
    public void removeObserver(Observable observable) {
        observables.remove(observable);
    }

    @Override
    public void notifyObservers(String message) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (Observable observable : observables) {
            executorService.execute(() -> observable.update(message));
        }
    }
}
