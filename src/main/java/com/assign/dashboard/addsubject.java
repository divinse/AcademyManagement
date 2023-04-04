package com.assign.dashboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.CallableStatement;
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
 * Servlet implementation class addsubject
 */
@WebServlet("/addsubject")
public class addsubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addsubject() {
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

		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/applications.properties");
		props.load(in);
		Connection con = dbconnect.getConnection(props);

		String subname, classname;
		subname = request.getParameter("subjectname");
		classname = request.getParameter("classname");

		boolean flag = false;
		PreparedStatement stmt;
		CallableStatement stmt1;
		response.setContentType("text/html");

		try {
			stmt = con.prepareStatement("select * from subjects;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).toString().equals(subname) && rs.getString(2).toString().equals(classname)) {
					flag = true;
					break;
				}
			}

			PrintWriter pw = response.getWriter();
			if (flag) {

				RequestDispatcher rd = request.getRequestDispatcher("/addsubject.html");
				rd.include(request, response);
				response.flushBuffer();
				pw.print(
						"<br/><form  class='loginerr' action='none'><center style='color:red;margin-bottom:800px;'> Subject already availale for mentioned class <a href='addsubject.html'>Click Here </a> to retry </center></form>");
			} else {
				stmt1 = con.prepareCall("insert into subjects(subjectname,clasname) values(?,?);");

				stmt1.setString(1, subname);
				stmt1.setString(2, classname);

				int res = stmt1.executeUpdate();
				System.out.println(res + " - - -- -  -- -");
				RequestDispatcher rd = request.getRequestDispatcher("/addsubject.html");
				rd.include(request, response);
				response.flushBuffer();
				pw.print("<a href='dashboard.html'><input type='button' value='Go to dashboard' /></a>");
				pw.print(
						"<br/><form  class='loginerr' action='none'><center style='color:green;margin-bottom:800px;'> Subject entry successful<br/> <a href='addsubject.html'>Click Here </a> to add more subjects <br/> </center>  </form>");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
