package motogp.model;

public class RiderOnlineData {
	private int number;
	private String name;
	private String team;
	private int position;

	public RiderOnlineData(int number, String name, String team, int position) {
		this.number = number;
		this.name = name;
		this.team = team;
		this.position = position;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "RiderOnlineData{" +
				"number=" + number +
				", name='" + name + '\'' +
				", team='" + team + '\'' +
				", position=" + position +
				'}';
	}
}
