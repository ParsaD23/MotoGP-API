package com.github.parsad23.motogpapi.domain;

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
    public ConstructorStandings(String name, int position, double points) {
        super(name, position, points);
    }

    @Override
    public String toString() {
        return "ConstructorStandings{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", points=" + points +
                '}';
    }
}
