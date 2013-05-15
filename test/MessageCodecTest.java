import junit.framework.Assert;

import org.junit.Test;


public class MessageCodecTest implements MetricListener {

	private String receivedCountName;
	private long receivedCountQuantity;
	private String receivedTimeName;
	private int receivedTime;
	private String receivedTimeUnit;

	@Test
	public void test() {
		new StatsDMessageCodec(this).messageReceived("gorets:1|c");
		Assert.assertEquals("gorets", this.receivedCountName);
		Assert.assertEquals(1, this.receivedCountQuantity);
	}

	@Test
	public void testSporrans() {
		new StatsDMessageCodec(this).messageReceived("sporrans:2|c");
		Assert.assertEquals("sporrans", this.receivedCountName);
		Assert.assertEquals(2, this.receivedCountQuantity);
	}
	
	@Test
	public void glork() {
		new StatsDMessageCodec(this).messageReceived("glork:320|ms");
		Assert.assertEquals("glork", this.receivedTimeName);
		Assert.assertEquals(320, this.receivedTime);
		Assert.assertEquals("ms", this.receivedTimeUnit);
	}

	@Override
	public void receivedCountMetric(String countName, long countQuantity) {
		receivedCountName = countName;
		receivedCountQuantity = countQuantity;
	}

	@Override
	public void receivedTimeMetric(String name, int time, String timeUnit) {
		receivedTimeName = name;
		receivedTime = time;
		receivedTimeUnit = timeUnit;
	}

}
