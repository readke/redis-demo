package com.read.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.read.util.RedisUtils;

@WebServlet(urlPatterns="/test")
public class MyServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MyServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(req.getRequestedSessionId());
		String session_id = req.getRequestedSessionId();
		if(session_id == null){
			session_id = req.getSession().getId();
		}
		if(RedisUtils.exist(session_id)){
			RedisUtils.put(session_id, "user", "ksir");
		}
		
		log.info(req.getServletContext().getRealPath("/"));
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
}
