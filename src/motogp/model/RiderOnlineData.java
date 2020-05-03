package motogp.model;

public class RiderOnlineData {
	private int number;
	private int position;
	private Constructor constructor;

	public RiderOnlineData(int number, int position, Constructor constructor) {
		this.number = number;
		this.position = position;
		this.constructor = constructor;
	}

	public int getNumber() {
		return number;
	}

	public int getPosition() {
		return position;
	}

	public Constructor getConstructor() {
		return constructor;
	}

	@Override
	public String toString() {
		return "RiderOnlineData{" +
				"number=" + number +
				", position=" + position +
				", constructor=" + constructor +
				'}';
	}
}
