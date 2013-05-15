public class StatsDMessageCodec implements MessageCodec {

	private MetricListener metricListener;

	public StatsDMessageCodec(MetricListener metricListener) {
		this.metricListener = metricListener;
	}

	@Override
	public void messageReceived(String message) {
		String name = message.substring(0, message.indexOf(":"));
		if (message.endsWith("|c")) {
			int countQuantity = Integer.parseInt(message.substring(message.indexOf(":") + 1, message.indexOf("|")));
			this.metricListener.receivedCountMetric(name, countQuantity);
		}
		else {
			 this.metricListener.receivedTimeMetric(name, Integer.parseInt(message.substring(message.indexOf(":") + 1, message.indexOf("|"))), "ms");
		}
	}
}
