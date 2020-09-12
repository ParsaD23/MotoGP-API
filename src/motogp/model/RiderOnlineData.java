package motogp.model;

public class RiderOnlineData {
	private int number;
	private String name;
	private String nationality;
	private String team;
	private int position;
	private int time;
	private int laps;

	public RiderOnlineData(int number, String name, String nationality, String team, int position, int time, int laps) {
		this.number = number;
		this.name = name;
		this.team = team;
		this.position = position;
		this.nationality = nationality;
		this.time = time;
		this.laps = laps;
	}

	public int getTime() {
		return time;
	}

	public String getNationality() {
		return nationality;
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

	public int getLaps() {
		return laps;
	}

	@Override
	public String toString() {
		return "RiderOnlineData{" +
				"number=" + number +
				", name='" + name + '\'' +
				", nationality='" + nationality + '\'' +
				", team='" + team + '\'' +
				", position=" + position +
				", time=" + time +
				", laps=" + laps +
				'}';
	}
}
