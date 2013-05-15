import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Assert;
import org.junit.Test;

public class UDPTest implements MessageCodec {

	private static final String MESSAGE = "gorets:1|c";
	static final int PORT = 8125;
	private String messageReceived;

	@Test
	public void sendUDP() throws InterruptedException {
		new UDPPort(PORT, this).bind();

		sendUDPPacket(MESSAGE);
		waitABit();
		Assert.assertEquals(MESSAGE, this.messageReceived);
	}

	private void waitABit() throws InterruptedException {
		synchronized (this) {
			this.wait(20);
		}
	}

	private void sendUDPPacket(String message) {
		try {

			byte[] payload = message.getBytes();

			InetAddress address = InetAddress.getByName("127.0.0.1");

			DatagramPacket packet = new DatagramPacket(payload, payload.length, address, PORT);

			DatagramSocket dsocket = new DatagramSocket();
			dsocket.send(packet);
			dsocket.close();
		}
		catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public void messageReceived(String messageReceived) {
		this.messageReceived = messageReceived;
	}


}
