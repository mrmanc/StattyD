import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class MetricBucketTest implements MetricTransmission {

	private int transmissionsSent;
	private String nameOfMetricTransmitted;
	private long metricValue;

	@Test
	public void onlySentOneMetric() {
		assertEquals(1, transmissionsSent);
	}


	@Test
	public void metricName() {
		assertEquals("counts-sent", this.nameOfMetricTransmitted);
	}


	@Test
	public void metricValue() {
		assertEquals(1, this.metricValue);
	}

	@Before
	public void before() {
		MetricBucket metricBucket = new MetricBucket(this);
		metricBucket.receivedCountMetric("counts-sent", 1);
		metricBucket.flush();
	}

	@Override
	public void transmit(String countName, long quantity) {
		nameOfMetricTransmitted = countName;
		metricValue = quantity;
		this.transmissionsSent ++;
	}


}
