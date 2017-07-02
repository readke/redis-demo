package com.read;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Test {
	private static Jedis jedis  = new Jedis("192.168.1.117",6379);
	static {
		jedis.auth("123456");
		System.out.println("Connection to server sucessfully");
	}
	private Test(){
		
	}
	public static Jedis getJedis(){
		return jedis;
		
	   /* System.out.println(jedis.set("name", "ksir"));
	    System.out.println(jedis.get("name"));
	    jedis.rpush("lista", "namer");
	    jedis.lpush("lista", "namel");
	    jedis.del("lista");
	    List<String> l = jedis.lrange("lista", 0, -1);
	    
	    for(String s : l){
	    	System.out.println(s);
	    }
	    
	    jedis.hset("session_id", "name", "ksir");
	    System.out.println(jedis.hget("session_id","name"));*/
	    
	}
	public static void main(String[] args) {
		Test.getJedis();
	}
}
