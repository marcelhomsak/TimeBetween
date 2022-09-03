package com.marcelhomsak.timebetween;

public class Time implements Comparable<Time> {

    private int differenceInSeconds;
    private String startingTime;
    private String endingTime;

    // properties for tableView column
    private String date;
    private String differenceTime;
    private String representation;

    public Time(String startingTime, String endingTime, String date) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.date = date;
        this.representation = startingTime + " - " + endingTime;
        this.differenceInSeconds = calculateDifferenceInSeconds(startingTime, endingTime);
        this.differenceTime = getDifferenceTime();
    }

    public Time(String startingTime, String endingTime) {
        this(startingTime, endingTime, "");
    }

    public static String secondsToTime(int seconds) {
        return String.format("%02d:%02d:%02d",
                seconds / 3600,
                seconds % 3600 / 60,
                seconds % 60);
    }

    private int calculateDifferenceInSeconds(String startingTime, String endingTime) {
        String[] partsStart = startingTime.split(":");
        String[] partsEnd = endingTime.split(":");

        int secondsFromMidnightStart = Integer.parseInt(partsStart[0]) * 3600 +
                Integer.parseInt(partsStart[1]) * 60 +
                Integer.parseInt(partsStart[2]);
        int secondsFromMidnightEnd = Integer.parseInt(partsEnd[0]) * 3600 +
                Integer.parseInt(partsEnd[1]) * 60 +
                Integer.parseInt(partsEnd[2]);
        return secondsFromMidnightEnd - secondsFromMidnightStart;
    }

    public int getDifferenceInSeconds() {
        return this.differenceInSeconds;
    }

    public String getDifference() {
        return String.format("%02d:%02d:%02d",
                this.differenceInSeconds / 3600,
                this.differenceInSeconds % 3600 / 60,
                this.differenceInSeconds % 60);
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    @Override
    public String toString() {
        return representation;
    }

    @Override
    public int compareTo(Time t) {
        return Long.compare(this.differenceInSeconds, t.differenceInSeconds);
    }

    // getters needed for retrieving property in PropertyValueFactory for tableView in ListOfTimesWindowController.java
    public String getDate() {
        return this.date;
    }

    public String getDifferenceTime() {
        return differenceTime;
    }

    public String getRepresentation() {
        return this.representation;
    }


}
