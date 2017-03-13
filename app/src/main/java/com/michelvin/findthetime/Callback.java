//@Authors Owen Galvin and Daniel Michelin
//@Date 3/6/2017

package com.michelvin.findthetime;

public class Callback implements OnTaskCompleted{
    MainActivity mainActivity;

    Callback(){}

    Callback(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onTaskCompleted(String result) {
        mainActivity.startNextActivity(result);
    }
}
