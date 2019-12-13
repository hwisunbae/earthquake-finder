package com.example.assgn3;

public class Team {
    private String city;
    private String name;
    private String sport;
    private String mvp;
    private String stadium;

    Team() {
    }

    Team(String city, String name, String sport, String mvp, String stadium) {
        this.city = city;
        this.name = name;
        this.sport = sport;
        this.mvp = mvp;
        this.stadium = stadium;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getSport() {
        return sport;
    }

    public String getMvp() {
        return mvp;
    }

    public String getStadium() {
        return stadium;
    }
}
