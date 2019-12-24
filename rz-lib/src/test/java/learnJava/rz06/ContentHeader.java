package learnJava.rz06;

public class ContentHeader {
	
	public static final int DEFAULT_MAGIC_CODE = 0xdec1_0ade;
	
	public static final short VERSION = 1;
	private int magicCode = DEFAULT_MAGIC_CODE;//4
	private short version = 1;//2
	private long msgLength;//8
	
	public int getMagicCode() {
		return magicCode;
	}
	
	public short getVersion() {
		return version;
	}
	
	public long getMsgLength() {
		return msgLength;
	}
	
	public void setMsgLength(long msgLength) {
		this.msgLength = msgLength;
	}
}
