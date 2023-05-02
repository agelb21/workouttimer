package com.example.task41p;

import android.content.Intent;

public class MyDataModel {

    String taskName, timer, timer2;




    public MyDataModel(String taskName, String timer, String timer2) {
        this.taskName = taskName;
        this.timer = timer;
        this.timer2 = timer2;

    }

    public String getTaskName() {
        return taskName;
    }

    public String getTimer() {
        return timer;
    }
    public String getTimer2() {
        return timer2;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public void setTimer2(String timer2) {
        this.timer2 = timer2;
    }

    public long getTimerDurationInMillis() {

        Long milliseconds =0L;

        try{
            milliseconds = Long.parseLong(this.getTimer())*60000 + Long.parseLong(this.getTimer2())*1000;


        }catch(NumberFormatException e){
            try{
                milliseconds = Long.parseLong(this.getTimer())*60000;


            }catch(NumberFormatException b){
                try{
                    milliseconds = Long.parseLong(this.getTimer2())*1000;


                }catch(NumberFormatException c){

                }

            }

        }
        return milliseconds;

    }
}
