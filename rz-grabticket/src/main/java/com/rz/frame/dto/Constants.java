package com.rz.frame.dto;

public class Constants {
	
	public class LoginInfo {
		public static final String AuthorKey = "authorKey";
		public static final String UserName = "13661862134";
	}
	
	public class LoginUrl {
		public static final String uamtk_static = "https://kyfw.12306.cn/passport/web/auth/uamtk-static";
		public static final String conf = "https://kyfw.12306.cn/otn/login/conf";
		public static final String captchaImg = "https://kyfw.12306.cn/passport/captcha/captcha-image64?login_site=E&module=login&rand=sjrand&_=%s";
		public static final String captchaCheck = "https://kyfw.12306.cn/passport/captcha/captcha-check?answer=%s&rand=sjrand&login_site=E&_=%s";
		public static final String webLogin = "https://kyfw.12306.cn/passport/web/login";
		public static final String userLogin = "https://kyfw.12306.cn/otn/login/userLogin";
		public static final String uamtk = "https://kyfw.12306.cn/passport/web/auth/uamtk";
		public static final String uamauthclient = "https://kyfw.12306.cn/otn/uamauthclient";
		
	}
	
	public class QueryUrl {
		public static final String queryUrl = "https://kyfw.12306.cn/otn/leftTicket/%s?leftTicketDTO.train_date=%s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT";
	}
	
	public class OrderUrl {
		public static final String submitUrl = "https://kyfw.12306.cn/otn/leftTicket/submitOrderRequest";
		public static final String initDcUrl = "https://kyfw.12306.cn/otn/confirmPassenger/initDc";
		public static final String passengerDTOsUrl = "https://kyfw.12306.cn/otn/confirmPassenger/getPassengerDTOs";
		public static final String checkOrderInfoUrl = "https://kyfw.12306.cn/otn/confirmPassenger/checkOrderInfo";
		public static final String getQueueCountUrl = "https://kyfw.12306.cn/otn/confirmPassenger/getQueueCount";
		public static final String confirmSingleForQueueUrl = "https://kyfw.12306.cn/otn/confirmPassenger/confirmSingleForQueue";
	}
}
