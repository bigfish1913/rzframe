package com.rz.frame;

public class DemoDubboServiceImpl implements DemoDubboService {
	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}
}
