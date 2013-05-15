import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;


public class Handler extends SimpleChannelUpstreamHandler {
	private MessageCodec messageCodec;

	public Handler(MessageCodec statistics) {
		this.messageCodec = statistics;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) throws Exception {
		BigEndianHeapChannelBuffer message = (BigEndianHeapChannelBuffer) event.getMessage();
		messageCodec.messageReceived(new String(message.array()));
	}
}
