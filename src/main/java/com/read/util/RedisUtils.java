package com.read.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class RedisUtils {
	private static final Logger log = LoggerFactory.getLogger(RedisUtils.class);
	private static final int DEFAULT_TIME_OUT = 30*60; 
	private static Jedis jedis  = null;
	static {
		try{
			jedis = new Jedis("192.168.1.178",6379);
			jedis.auth("123456");
			System.out.println("Connection to server sucessfully");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("redis 服务器连接失败");
		}
		
	}
	
	private static Jedis getJedis(){
		return jedis;
	}
	
	/**
	 * 检查sessionId在redis中是否存在
	 * @param sessionId
	 * @return
	 */
	public static boolean exist(String sessionId){
		log.info("exist");
		return jedis.exists(sessionId);
	}
	
	/**
	 * 添加一个k-v值对到sessionId的键中
	 * @param sessionId
	 * @param key
	 * @param value
	 * @return 如果key不存在，返回1，如果key存在，返回0
	 */
	public static long put(String sessionId,String key,String value ){
		expire(sessionId, DEFAULT_TIME_OUT);
		return jedis.hset(sessionId, key, value);
	}
	
	/**
	 * 从redis中取出sessionId中的key的值
	 * @param sessionId
	 * @param key
	 * @return
	 */
	public static String get(String sessionId,String key){
		return jedis.hget(sessionId, key);
	}
	
	/**
	 * 设置key的过期时间
	 * @param sessionId
	 * @param seconds
	 */
	public static long expire(String sessionId,int seconds){
		return jedis.expire(sessionId, seconds);
	}
	
	public static long ttl(String key){
		return jedis.ttl(key);
	}
	public static void main(String[] args) throws InterruptedException {
		
		
		
		//System.out.println(put("test1","name","ksir"));
		
		log.info("获取有效时间:{}s",ttl("test1"));
		/*log.info("设置超时时间:{}s",expire("test",60));
		log.info("获取有效时间:{}s",ttl("test"));
		Thread.sleep(30000l);
		log.info("获取有效时间:{}s",ttl("test"));
		log.info("设置超时时间:{}s",expire("test",100));
		log.info("获取有效时间:{}s",ttl("test"));*/
		
	}
	
	
}
