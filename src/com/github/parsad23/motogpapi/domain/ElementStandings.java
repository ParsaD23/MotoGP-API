package com.github.parsad23.motogpapi.domain;

import java.util.List;

public abstract class ElementStandings {

    String name;
    int position;
    double points;
    List<Double> results;

    public ElementStandings(String name, int position, double points, List<Double> results) {
        this.name = name;
        this.position = position;
        this.points = points;
        this.results = results;
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

    public List<Double> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", points=" + points +
                ", results=" + results +
                '}';
    }
}
