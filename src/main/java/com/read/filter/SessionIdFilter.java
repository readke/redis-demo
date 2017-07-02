package com.read.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.read.Test;

import redis.clients.jedis.Jedis;
@WebFilter(urlPatterns={"/*"})
public class SessionIdFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		Jedis jedis = Test.getJedis();
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		String session_id = req.getRequestedSessionId();
		if(session_id != null){
			if(jedis.exists(session_id)){
				System.out.println("session_id:"+session_id+"�Ѵ���");
				System.out.println("session_id:"+session_id+"--name:"+jedis.hget("session_id", "name"));;
			}else{
				try {
					throw new Exception("ϵͳ�쳣");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else{
			System.out.println("û��session_id,��ŵ�redis");
			
			session_id = req.getSession().getId();
			System.out.println(session_id);
			System.out.println(jedis.hset(session_id, "name", "ksir"));
			
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init.....");
	}
	
}
