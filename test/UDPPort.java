import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
import org.junit.Assert;


public class UDPPort {

	private int portNumber;
	private MessageCodec statistics;
	
	public UDPPort(int portNumber, MessageCodec statistics) {
		this.portNumber = portNumber;
		this.statistics = statistics;
	}

	Handler bind() {
		ChannelFactory factory = new NioDatagramChannelFactory(Executors.newCachedThreadPool());
	
		ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap(factory);
	
		Handler handler = new Handler(this.statistics);
		bootstrap.getPipeline().addLast("MyHandler", handler);
	
		try {
			bootstrap.bind(new InetSocketAddress(InetAddress.getByName("localhost"), portNumber));
		}
		catch (UnknownHostException e1) {
			Assert.fail("Could not bind to port. " + e1.getMessage());
		}
		catch (ChannelException e1) {
			Assert.fail("Could not bind to port. " + e1.getMessage());
		}
		return handler;
	}

}
