package com.assign.dashboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.loginmod.dbconnect;

/**
 * Servlet implementation class setpassadmin
 */
@WebServlet("/setpassadmin")
public class setpassadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setpassadmin() {
		super();
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
		PrintWriter pw = response.getWriter();
		pw.print("Access denied ..!");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		PrintWriter pw = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("/setpassadmin.html");

		rd.include(request, response);
		response.setContentType("text/html");

		String pass, repass;

		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/applications.properties");
		props.load(in);

		pass = request.getParameter("pass");
		repass = request.getParameter("repass");
		Connection con = dbconnect.getConnection(props);

		if (pass.equals(repass)) {

			PreparedStatement stmt;

			try {
				stmt = con.prepareStatement("update admin set password=? where username='admin';");
				stmt.setString(1, pass);
				int s = stmt.executeUpdate();

				if (s > 0) {
					pw.print(
							"<br/><center class='loginerr' style='color:green;margin-bottom:800px;'> Password updated successfully <a href='dashboard.html'> Click here </a> to goto dashboard </center>");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			pw.print(
					"<br/><form  class='loginerr' action='none'><center style='color:red;margin-bottom:800px;'> Entered password and new password dosenot match try again ...! </center></form>");
		}

	}

}
