
public interface MetricListener {

	void receivedCountMetric(String countName, long countQuantity);

	void receivedTimeMetric(String name, int time, String timeUnit);


}
