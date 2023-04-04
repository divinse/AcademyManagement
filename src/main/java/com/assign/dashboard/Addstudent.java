package com.assign.dashboard;

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

import com.assign.loginmod.dbconnect;

/**
 * Servlet implementation class Addstudent
 */
@WebServlet("/Addstudent")
public class Addstudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Addstudent() {
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
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/applications.properties");
		props.load(in);
		Connection con = dbconnect.getConnection(props);

		String studentname, classname;
		studentname = request.getParameter("studentname");
		classname = request.getParameter("classname");
		boolean flag = false;
		PreparedStatement stmt;

		try {

			stmt = con.prepareStatement("select * from students;");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).toString().equals(studentname) && rs.getString(2).toString().equals(classname)) {
					flag = true;
					break;
				}
			}

			PrintWriter pw = response.getWriter();
			if (flag) {
				RequestDispatcher rd = request.getRequestDispatcher("/AddStudent.html");
				rd.include(request, response);

				response.setContentType("text/html");
				pw.print("<a href='dashboard.html'><input type='button' value='Go to dashboard' /></a>");
				pw.print(
						"<br/><form  class='loginerr' action='none'><center style='color:red;margin-bottom:900px;'> Student already registered cannot be registered again <a href='AddStudent.html'>Click here</a> to retry </center></form>");

			} else {

				stmt = con.prepareStatement("Insert into students(stuname,class) values (?,?)");
				stmt.setString(1, studentname);
				stmt.setString(2, classname);

				int stats = stmt.executeUpdate();
				if (stats == 1) {
					System.out.println("data entry completed - add student");
					RequestDispatcher rd = request.getRequestDispatcher("/AddStudent.html");
					rd.include(request, response);
					pw.print("<a href='dashboard.html'><input type='button' value='Go to dashboard' /></a>");
					pw.print(
							"<br/><form  class='loginerr' action='none'><center style='color:green;margin-bottom:900px;'> Student registered successful <a href='AddStudent.html'>Click here</a> to continue </center></form>");

				} else {
					System.out.println("data entry failed - add student");
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
