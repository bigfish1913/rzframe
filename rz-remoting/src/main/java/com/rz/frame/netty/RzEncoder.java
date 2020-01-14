package com.rz.frame.netty;

import com.rz.frame.utils.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class RzEncoder extends MessageToByteEncoder<Message> {
	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		// 写入开头的标志
		out.writeInt(msg.getHeader().getHeadData());
		// 写入包的的长度
		out.writeInt(msg.getContent().length);
		/**
		 * token定长50个字节
		 *  第一个参数 原数组
		 *  第二个参数 原数组位置
		 *  第三个参数 目标数组
		 *  第四个参数 目标数组位置
		 *  第五个参数 copy多少个长度
		 */
		byte[] indexByte = msg.getHeader().getToken().getBytes();
		writeByte(out, indexByte, 50);
		
		
		byte[] createTimeByte = msg.getHeader().getCreateDate().toString().getBytes();
		writeByte(out, createTimeByte, 50);
		
		byte[] idByte = msg.getHeader().getMessageId().getBytes();
		writeByte(out, idByte, 50);
		
		byte[] msgType = new byte[]{msg.getHeader().getMessageType().getValue()};
		out.writeBytes(msgType);
		byte[] contentType = new byte[]{msg.getHeader().getContentType().getValue()};
		out.writeBytes(contentType);
	 
		
		out.writeBytes(msg.getContent());
		
	}
	
	private void writeByte(ByteBuf out, byte[] bytes, int length) {
		byte[] writeArr = new byte[length];
		/**
		 *
		 *  第一个参数 原数组
		 *  第二个参数 原数组位置
		 *  第三个参数 目标数组
		 *  第四个参数 目标数组位置
		 *  第五个参数 copy多少个长度
		 */
		System.arraycopy(bytes, 0, writeArr, 0, bytes.length > writeArr.length ? writeArr.length : bytes.length);
		out.writeBytes(writeArr);
	}
	
	private void writeByte(ByteBuf out, String content, int length) {
		if (StringUtils.isEmpty(content)) {
			content = "";
		}
		writeByte(out, content.getBytes(), length);
	}
	
}
