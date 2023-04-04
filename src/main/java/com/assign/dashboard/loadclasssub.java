package com.assign.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class loadclasssub
 */
//@WebFilter("/addsubject.html")
public class loadclasssub extends HttpFilter implements Filter {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static Connection con;
	PreparedStatement stmt;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public loadclasssub() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		String url = "jdbc:mysql://localhost:3306/learneracad";
		String user = "root";
		String password = "root";
		try {
			con = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		// boolean loginflag=false;

		try {
			stmt = con.prepareStatement("select * from classes ;");
			ResultSet rs = stmt.executeQuery();
			pw.println("<table>");
			while (rs.next()) {
				pw.println("<tr><td>" + rs.getString(1).toString() + "</td><td>" + rs.getString(2).toString()
						+ "</td></tr>");
			}

			pw.println("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
