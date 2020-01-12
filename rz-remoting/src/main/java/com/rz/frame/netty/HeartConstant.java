package com.rz.frame.netty;

public interface HeartConstant {
	public interface RemotingHeader {
		public final static int LENGTH_FIELD = 10;
		public final static int DEFAULT_MAGIC_START_CODE = 0xdec1_0ade;
		public final static int DEFAULT_MAGIC_END_CODE = 0xdec1_0ade;
	}
	
	public interface Address {
		public final static String IP = "127.0.0.1";
		public final static int PORT = 9099;
	}
	
	public interface Config {
		public final static int WRITE_TIME_OUT = 6;
		public final static int READ_TIME_OUT = 6;
		public final static int HEART_TIME_OUT = 5;
	}
}
