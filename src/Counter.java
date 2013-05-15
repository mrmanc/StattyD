
public class Counter {

	private String countName;
	private long quantity;

	public Counter(String countName) {
		this.countName = countName;
		// TODO Auto-generated constructor stub
	}

	public void transmit(MetricTransmission metricTransmission) {
		metricTransmission.transmit(this.countName, this.quantity);
	}

	public void add(long countQuantity) {
		this.quantity += countQuantity;
	}

}
