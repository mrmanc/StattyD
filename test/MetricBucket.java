import java.util.Map;
import java.util.TreeMap;


public class MetricBucket implements MetricListener {

	private MetricTransmission metricTransmission;
	private Map<String, Counter> counters;

	public MetricBucket(MetricTransmission metricTransmission) {
		this.metricTransmission = metricTransmission;
		this.counters = new TreeMap<String, Counter>();
	}

	@Override
	public void receivedCountMetric(String countName, long countQuantity) {
		Counter counter = new Counter(countName);
		counter.add(countQuantity);
		this.counters.put(countName, counter);
	}

	@Override
	public void receivedTimeMetric(String name, int time, String timeUnit) {
		// TODO Auto-generated method stub

	}

	public void flush() {
		for (Counter counter : counters.values()) {
			counter.transmit(this.metricTransmission);
		}
	}

}
