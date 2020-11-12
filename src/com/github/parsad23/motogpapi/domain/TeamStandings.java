package com.github.parsad23.motogpapi.domain;

/**
 * Represents the information of a team in the teams' standings
 */
public class TeamStandings extends ElementStandings{

    /**
     * Creates a team
     * @param name Name of the team
     * @param position Position of the team in the standings
     * @param points Total points of the team in the standings
     */
    public TeamStandings(String name, int position, double points) {
        super(name, position, points);
    }

    @Override
    public String toString() {
        return "TeamStandings{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", points=" + points +
                '}';
    }
}
