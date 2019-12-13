package com.example.lab5;

public class Country {
    private String country;
    private String capital;
    private int flag;
    private String alphabet;

    Country() {

    }

    Country (String country, String capital) {
        this.country = country;
        this.capital = capital;
    }

    Country (String country, String capital, int flag) {
        this.country = country;
        this.capital = capital;
        this.flag = flag;
    }

    Country (String country, String capital, int flag, String alphabet) {
        this.country = country;
        this.capital = capital;
        this.flag = flag;
        this.alphabet = alphabet;
    }

    public int getFlag() {
        return flag;
    }

    public String getCapital() {
        return capital;
    }

    public String getCountry() {
        return country;
    }

    public String getAlphabet() {
        return alphabet;
    }
}
