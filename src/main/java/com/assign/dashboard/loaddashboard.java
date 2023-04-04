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
 * Servlet Filter implementation class loaddashboard
 */
@WebFilter("/dashboard.html")
public class loaddashboard extends HttpFilter implements Filter {
	private Connection con;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public loaddashboard() {
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
		PreparedStatement stmt;
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		pw.print("<h1>Administrator Console</h1><br />");
		pw.print("<table><tr><td>");
		pw.print("<form action='dashboard' method='post'>\r\n"
				+ "	<input type='submit' name='actionholder' value='Change password' />\r\n" + "	<br />\r\n"
				+ "	<br />\r\n" + "\r\n" + "	<input type='submit' name='actionholder' value='Add Subjects' />\r\n"
				+ "	<br />\r\n" + "	<input type='submit' name='actionholder' value='Add Teachers' />\r\n"
				+ "	<br />\r\n" + "	<input type='submit' name='actionholder' value='Add Student' />\r\n"
				+ "	<br />\r\n" + "	<input type='submit' name='actionholder' value='Add Classes' />\r\n"
				+ "	<br />\r\n" + "	<input type='submit' name='actionholder' value='Display all data' />\r\n"
				+ "	<br />\r\n" + "	<input type='submit' name='actionholder' value='Map teachers to subject' />\r\n"
				+ "	<br />\r\n" + "	<input type='submit' name='actionholder' value='Delete Data' />\r\n"
				+ "	<br />\r\n" + "	<input type='submit' name='actionholder' value='Global Search' />\r\n"
				+ "	<br />\r\n" + "	<input type='submit' name='actionholder' value='Execution Console' />\r\n"
				+ "	<br />\r\n" + "	\r\n" + "	<br />\r\n" + "\r\n" + "	<input type='submit' name='actionholder' value='Logout' />\r\n"
				+ "	<br />\r\n" + "	\r\n" + "	</form>");

		pw.print("</td><td style='width:800px'> </td><td style='opacity: 0.8;background-color:white'>");
		// pw.print("<marquee direction='down'>");
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
		pw.print("<p style='border-radius:5px;'><center> Academy total counts</br>");

		String taecherscont = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(teachname) FROM teachers;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				taecherscont = rs.getString(1);
			}

			pw.print("Total teachers count : " + taecherscont);
			pw.print("</br>");
//			stmt = con.prepareStatement("SELECT * FROM teachers;");
//			rs = stmt.executeQuery();
//			while (rs.next()) {
//				pw.print(" "+rs.getString(1)+"</br>");
//				}
			// pw.print("</table></center>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String subjscont = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(subjectname) FROM subjects;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				subjscont = rs.getString(1);
			}

			pw.print("Total subject count : " + subjscont);
//
//			stmt = con.prepareStatement("SELECT * FROM subjects;");
//			rs = stmt.executeQuery();
//			pw.print("</br>");
//			while (rs.next()) {
//				pw.print(" "+rs.getString(1)+"</br>");
//				}
			// pw.print("</table></center>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String stucont = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(stuname) FROM students;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				stucont = rs.getString(1);
			}

			pw.print("</br>Total student count : " + stucont);

//			stmt = con.prepareStatement("SELECT * FROM students;");
//			rs = stmt.executeQuery();
//			pw.print("</br>");
//			while (rs.next()) {
//				pw.print(" "+rs.getString(1)+"</br>");
//				}
			// pw.print("</table></center>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		pw.print("</center></p>");

		pw.print("</td></tr></table>");

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
