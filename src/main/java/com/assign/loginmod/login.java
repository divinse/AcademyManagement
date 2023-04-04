package com.assign.loginmod;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * Default constructor.
	 */
	public login() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname, pass;

		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/applications.properties");

		props.load(in);

		uname = request.getParameter("username");
		pass = request.getParameter("password");
		Connection con = dbconnect.getConnection(props);

		PreparedStatement stmt;
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		// boolean loginflag=false;

		try {
			stmt = con.prepareStatement("select * from admin where username=? ");
			stmt.setString(1, uname);
			ResultSet rs = stmt.executeQuery();

			String userdbhold = null, passdbhold = null;

			while (rs.next()) {
				userdbhold = rs.getString(1);
				passdbhold = rs.getString(2);
			}

			if (uname.equals(userdbhold) && pass.equals(passdbhold)) {
				response.setContentType("text/html");
				pw.print("<br/><center style='color:red;margin-bottom:800px;'> Login Successful </center>");
				HttpSession session = request.getSession(true);
				session.setAttribute("status", "1");
				response.sendRedirect("dashboard.html");
				// break;
			} else {

				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.include(request, response);
				response.setContentType("text/html");
				pw.print(
						"<br/><form  class='loginerr' action='none'><center style='color:red;margin-bottom:800px;'> Invalid Credentials try again ...! </center></form>");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
