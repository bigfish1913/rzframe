package com.rz.frame.utils;

import org.junit.Test;

import java.time.LocalDateTime;

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

		boolean abc = RedisUtils.setnx("abcd123", 1231, LocalDateTime.now().plusSeconds(1));
		System.out.println(abc);
		abc = RedisUtils.setnx("abcd123", 1231, LocalDateTime.now().plusSeconds(1));
		System.out.println(abc);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		abc = RedisUtils.setnx("abcd123", 1231, LocalDateTime.now().plusSeconds(1));
		System.out.println(abc);
		RedisUtils.set("abc123123",123645);
	}
	
	@Test
	public void set1() {
	}
	
	@Test
	public void incr() {
	}
}