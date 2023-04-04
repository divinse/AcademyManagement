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
 * Servlet Filter implementation class loaddisplaydata
 */
@WebFilter("/Displayalldata.html")
public class loaddisplaydata extends HttpFilter implements Filter {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private Connection con;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public loaddisplaydata() {
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
	/**
	 *
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
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		PreparedStatement stmt;

		try {
			stmt = con.prepareStatement("select * from classes ;");
			ResultSet rs = stmt.executeQuery();
			pw.print("<a href='dashboard.html'><input type='button' value='Go to dashboard' /></a>");
			pw.print("<form action='Displayalldata' method='post'>");
			pw.print("<center>Please select Class to load data<br/>");
			pw.print("<select name='classname'>");
			while (rs.next()) {
				pw.println("<option value='" + rs.getString(1).toString() + "' >" + rs.getString(1).toString()
						+ "</option>");

			}
			pw.print("</select><br/>");
			pw.print("<input class='loginfld' type='submit' value='Get Data' /></form>");

			pw.print("</table></form>");
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
