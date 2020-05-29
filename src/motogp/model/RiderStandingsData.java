package motogp.model;

public class RiderStandingsData {
	String name;
	int position;
	double points;

	public RiderStandingsData(String name, int position, double points) {
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

	@Override
	public String toString() {
		return "RiderStandingsData{" +
				"name='" + name + '\'' +
				", position=" + position +
				", points=" + points +
				'}';
	}
}
