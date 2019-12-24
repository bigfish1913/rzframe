package com.rz.frame.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryTicketDto {
	private String departStation;
	private String departStationCode;
	private String arriveStaion;
	private String arriveStaionCode;
	private String departDate;
	private String ticketType;
	//多个,分割
	private String trainNos;
	private String seatNames;
 
}
