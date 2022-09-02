package com.example.imtcalculator.stepscounter.maininfo;

public class Steps {
    public String id;
    public  String results_total_steps;
    public String results_total_distance;
    public String results_average_speed;
    public String results_average_frequency;
    public String results_burned_calories;
    public String results_total_moving_time;

    public Steps(String id, String results_total_steps, String results_total_distance, String results_average_speed, String results_average_frequency, String results_burned_calories, String results_total_moving_time) {
        this.id = id;
        this.results_total_steps = results_total_steps;
        this.results_total_distance = results_total_distance;
        this.results_average_speed = results_average_speed;
        this.results_average_frequency = results_average_frequency;
        this.results_burned_calories = results_burned_calories;
        this.results_total_moving_time = results_total_moving_time;
    }
}
