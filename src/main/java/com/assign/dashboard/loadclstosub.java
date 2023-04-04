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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class loadclstosub
 */
@WebFilter("/addsubject.html")
public class loadclstosub extends HttpFilter implements Filter {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Connection con;
    /**
     * @see HttpFilter#HttpFilter()
     */
    public loadclstosub() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		System.out.println("data to load");


		PreparedStatement stmt;
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	String url = "jdbc:mysql://localhost:3306/learneracad";
		    String user = "root";
		    String password = "root";
	      con = DriverManager.getConnection(url, user, password);
	      if(con!=null) {
	    	  System.out.println("connected to db");
	      }else {
	    	  System.out.println("failed to db");
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		// boolean loginflag=false;

		try {
			stmt = con.prepareStatement("select * from classes ;");
			ResultSet rs = stmt.executeQuery();
			pw.println("<a href='dashboard.html'><input type='button' value='Go back' /></a>");
			pw.println("<form action='addsubject' method='post'>");
			pw.print("<input class='loginfld' type='text' name='subjectname' id='username-field' class='login-form-field' placeholder='Enter Subject Name'><br />");
			pw.print("<select name='classname'>");
			while (rs.next()) {
				pw.println("<option value='"+rs.getString(1).toString()+"' >"+rs.getString(1).toString()+"</option>");
				System.out.println(rs.getString(1).toString());
			}
			pw.print("</select>");
			pw.print("<input class='loginfld' type='submit' value='Add' /></form>");
			con.close();
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
