package com.read;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Test {
	private static Jedis jedis  = new Jedis("192.168.1.59",6379);
	static {
		jedis.auth("123456");
		System.out.println("Connection to server sucessfully");
	}
	private Test(){
		
	}
	public static Jedis getJedis(){
		return jedis;
		
	}
	public static void main(String[] args) {
		Test.getJedis();
	}
}
