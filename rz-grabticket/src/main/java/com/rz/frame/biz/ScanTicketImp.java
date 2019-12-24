package com.rz.frame.biz;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rz.frame.HttpTicketUtils;
import com.rz.frame.dto.Constants;
import com.rz.frame.dto.QueryTicketDto;
import com.rz.frame.dto.SeatLevelEnum;
import com.rz.frame.dto.TicketModel;
import com.rz.frame.utils.CollectionUtils;
import com.rz.frame.utils.JsonUtils;
import com.rz.frame.utils.RzLogger;
import com.rz.frame.utils.StringUtils;
import org.apache.http.impl.client.HttpClients;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.rz.frame.dto.Constants.QueryUrl.queryUrl;

public class ScanTicketImp {
	public String queryTicket(QueryTicketDto queryTicketDto) {
		String defalutUrl = queryUrl();
		if (StringUtils.isEmpty(defalutUrl)) {
			RzLogger.info("queryTicket", "查询12306余票Url失败");
			return null;
		}
		HttpTicketUtils httpTicketUtils = HttpTicketUtils.getHttpClient(Constants.LoginInfo.UserName);
		String queryResult = httpTicketUtils.doGet(String.format(queryUrl, defalutUrl, queryTicketDto.getDepartDate(), queryTicketDto.getDepartStationCode(), queryTicketDto.getArriveStaionCode(),queryTicketDto.getDepartStation(),queryTicketDto.getArriveStaion()));
		if (StringUtils.isEmpty(queryResult)) {
			RzLogger.info("queryTicket", "查询12306余票失败");
			return null;
		}
		JSONObject queryObj = JsonUtils.toBean(queryResult);
		if (queryObj.getInteger("httpstatus") != 200) {
			RzLogger.info("queryTicket", "查询12306余票失败,返回code:{}", queryObj.getInteger("httpstatus"));
			return null;
		}
		if (StringUtils.isNotEmpty(queryTicketDto.getTrainNos())) {
			RzLogger.info("queryTicket", "需要查询的车次：{}", queryTicketDto.getTrainNos());
		}
		JSONObject data = queryObj.getJSONObject("data");
		JSONArray result = data.getJSONArray("result");
		List<TicketModel> ticketModels = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			TicketModel ticketModel = new TicketModel();
			ticketModel.setInfo(result.get(i).toString().split("\\|"));
			ticketModel.setTrainDate(queryTicketDto.getDepartDate());
			ticketModels.add(ticketModel);
		}
		RzLogger.info("queryTicket", "查询到的余票数量：%s", ticketModels.size());
		ticketModels = ticketModels.stream().filter(x -> StringUtils.isContain(queryTicketDto.getTrainNos(), x.getTrainNumber())).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(ticketModels)) {
			RzLogger.info("queryTicket", "未查询到符合的车票");
			return null;
		}
//		List<String> seatNameList = Arrays.stream(queryTicketDto.getSeatNames().split(",")).map(SeatLevelEnum.valueOf());
//		ticketModels.stream().filter(x-> seatNameList.retainAll(x.getSeat().stream().map(x->x.getSeatLevel().getName())))
		RzLogger.info("queryTicket", "查询到余票数据，正在尝试出票");
		
		return "";
	}
	
	private String queryUrl() {
		RzLogger.info("查询扫余票的url");
		String defalutUrl = "queryA";
		HttpTicketUtils httpTicketUtils = HttpTicketUtils.getHttpClient("13661862134");
		String queryResult = httpTicketUtils.doGet(String.format(queryUrl, defalutUrl, LocalDate.now().plusDays(1).toString(), "SHH", "IMH"));
		if (StringUtils.isEmpty(queryResult)) {
			RzLogger.info("获得查余票的Url出错");
			return null;
		}
		JSONObject queryJson = JsonUtils.toBean(queryResult);
		Boolean status = queryJson.getBoolean("status");
		if (!status) {
			String c_url = queryJson.getString("c_url");
			String[] urls = c_url.split("/");
			defalutUrl = urls[1];
		}
		RzLogger.info("获得查余票的Url:", defalutUrl);
		return defalutUrl;
		
	}
}
