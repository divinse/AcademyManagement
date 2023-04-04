package com.assign.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Displayalldata
 */
@WebServlet("/Displayalldata")
public class Displayalldata extends HttpServlet {
	private Connection con;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Displayalldata() {
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
		String clasn = request.getParameter("classname");
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
		RequestDispatcher rd = request.getRequestDispatcher("/Displayalldata.html");
		rd.include(request, response);
		pw.print("<a href='dashboard.html'><input type='button' value='Go to dashboard ' /></a></br>");

		pw.print("<a href='Displayalldata.html'><input type='button' value='Go back' /></a></br>");
		// boolean loginflag=false;

		pw.print("<p style='color:white;' >");
		try {
			stmt = con.prepareStatement("select * from subjects where clasname=?;");
			stmt.setString(1, clasn);
			ResultSet rs = stmt.executeQuery();
			pw.print("<center style='color:white;'>Subject and students available for " + clasn);
			pw.print("<br/>");
			pw.print("<table style='color:white;'><tr><td>");
			int count1 = 0, count2 = 0;
			while (rs.next()) {

				pw.print("Subject : " + rs.getString(1).toString() + "</br>");
				count1++;
			}

			if (count1 == 0) {
				pw.print("No subjects available to display");
			}
			pw.print("</td><td>");
			stmt = con.prepareStatement("select * from students where class=?;");
			stmt.setString(1, clasn);
			ResultSet rs1 = stmt.executeQuery();

			while (rs1.next()) {
				count2++;
				pw.print("Student : " + rs1.getString(1).toString() + "</br>");
			}
			if (count2 == 0) {
				pw.print("No students available to display");
			}
			pw.print("</td></tr>");

			pw.print("</table></form>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		pw.print("</p>");
	}

}
