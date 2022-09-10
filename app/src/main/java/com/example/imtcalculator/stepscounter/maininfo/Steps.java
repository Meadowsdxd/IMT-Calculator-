package com.example.imtcalculator.stepscounter.maininfo;

public class Steps {
    public String id,dates,results_total_steps,results_total_distance,results_average_speed,results_average_frequency,results_burned_calories,results_total_moving_time,
            results_jogging_steps,results_running_steps,results_walking_steps,hours,minutes,seconds;

    public Steps() {
    }

    public Steps(String id, String dates, String results_total_steps, String results_total_distance, String results_average_speed, String results_average_frequency, String results_burned_calories, String results_total_moving_time, String results_jogging_steps, String results_running_steps, String results_walking_steps, String hours, String minutes, String seconds) {
        this.id = id;
        this.dates = dates;
        this.results_total_steps = results_total_steps;
        this.results_total_distance = results_total_distance;
        this.results_average_speed = results_average_speed;
        this.results_average_frequency = results_average_frequency;
        this.results_burned_calories = results_burned_calories;
        this.results_total_moving_time = results_total_moving_time;
        this.results_jogging_steps = results_jogging_steps;
        this.results_running_steps = results_running_steps;
        this.results_walking_steps = results_walking_steps;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public  String toString(){
        return  dates+"---->"+results_total_steps;
    }
}
