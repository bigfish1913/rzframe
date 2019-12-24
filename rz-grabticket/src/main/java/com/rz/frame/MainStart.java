package com.rz.frame;

import com.rz.frame.biz.ScanTicketImp;
import com.rz.frame.dto.QueryTicketDto;

public class MainStart {
	public static void main(String[] args) {
		QueryTicketDto queryTicketDto = new QueryTicketDto();
		queryTicketDto.setDepartStation("上海");
		queryTicketDto.setArriveStaion("松江南");
		queryTicketDto.setDepartDate("2020-01-20");
		queryTicketDto.setSeatNames("二等座");
		queryTicketDto.setTrainNos("G7391");
		ScanTicketImp scanTicketImp = new ScanTicketImp();
		String s = scanTicketImp.queryTicket(queryTicketDto);
		System.out.println(s);
	}
}
