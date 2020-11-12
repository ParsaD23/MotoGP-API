package com.github.parsad23.motogpapi.domain;

public abstract class ElementStandings {

    String name;
    int position;
    double points;

    public ElementStandings(String name, int position, double points) {
        this.name = name;
        this.position = position;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public double getPoints() {
        return points;
    }
}
