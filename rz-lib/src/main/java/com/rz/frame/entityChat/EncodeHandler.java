package com.rz.frame.entityChat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.text.SimpleDateFormat;

public class EncodeHandler extends MessageToByteEncoder<Message> {
 @Override
 protected void encode(ChannelHandlerContext channelHandlerContext, Message msg, ByteBuf out) throws Exception {
  // TODO Auto-generated method stub
  // 写入开头的标志
  out.writeInt(msg.getHead().getHeadData());
  // 写入包的的长度
  out.writeInt(msg.getContent().length);
  byte[] tokenByte = new byte[50];
  /**
   * token定长50个字节
   *  第一个参数 原数组
   *  第二个参数 原数组位置
   *  第三个参数 目标数组
   *  第四个参数 目标数组位置
   *  第五个参数 copy多少个长度
   */
  byte[] indexByte = msg.getHead().getToken().getBytes();
  System.arraycopy(indexByte, 0, tokenByte, 0, indexByte.length > tokenByte.length ? tokenByte.length : indexByte.length);
  
		
		byte[] createTimeByte = new byte[50];
		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format0.format(msg.getHead().getCreateDate());
		indexByte = time.getBytes();
		System.arraycopy(indexByte, 0, createTimeByte, 0, indexByte.length > createTimeByte.length ? createTimeByte.length : indexByte.length);
		
		byte[] idByte = new byte[50];
		indexByte = msg.getHead().getMessageId().getBytes();
		System.arraycopy(indexByte, 0, idByte, 0, indexByte.length > idByte.length ? idByte.length : indexByte.length);
		
		byte[] msgType = new byte[50];
		indexByte = msg.getHead().getMessageType().toString().getBytes();
		System.arraycopy(indexByte, 0, msgType, 0, indexByte.length > msgType.length ? msgType.length : indexByte.length);
		
		//写入令牌
		out.writeBytes(tokenByte);
		//写入令牌生成时间
		out.writeBytes(createTimeByte);
		//写入令牌
		out.writeBytes(idByte);
		//写入令牌生成时间
		out.writeBytes(msgType);
		// 写入消息主体
		out.writeBytes(msg.getContent());
	}
}
