package com.github.parsad23.motogpapi.domain;

import java.util.List;

/**
 * Represents the information of a constructor in the constructor standings
 */
public class ConstructorStandings extends ElementStandings{

    /**
     * Creates a constructor
     * @param name Name of the constructor
     * @param position Position of the constructor in the standings
     * @param points Total points of the constructor in the standings
     */
    public ConstructorStandings(String name, int position, double points, List<Double> results) {
        super(name, position, points, results);
    }

    @Override
    public String toString() {
        return "ConstructorStandings" + super.toString();
    }
}
