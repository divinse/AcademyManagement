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
 * Servlet implementation class addteachers
 */
@WebServlet("/addteachers")
public class addteachers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addteachers() {
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
		PrintWriter pw = response.getWriter();
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/applications.properties");
		props.load(in);
		Connection con = dbconnect.getConnection(props);

		String teachername;
		teachername = request.getParameter("teachername");
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("insert into teachers(teachname) values(?);");
			stmt.setString(1, teachername);
			int res = stmt.executeUpdate();
			if (res == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("/Addteachers.html");
				rd.include(request, response);
				response.setContentType("text/html");
				pw.print(
						"<br/><center class='loginerr' style='color:green;margin-bottom:800px;'> Added Successfully </center>");

			} else {

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
