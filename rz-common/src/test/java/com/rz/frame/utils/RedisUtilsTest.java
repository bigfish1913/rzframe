package com.rz.frame.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedisUtilsTest {
	
	@Test
	public void get() {
		System.out.println(RedisUtils.get("abc123123"));
	}
	
	@Test
	public void get1() {
	}
	
	@Test
	public void getList() {
	}
	
	@Test
	public void set() {
		RedisUtils.set("abc123123",123645);
	}
	
	@Test
	public void set1() {
	}
	
	@Test
	public void incr() {
	}
}