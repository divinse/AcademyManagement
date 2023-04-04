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
 * Servlet implementation class addclass
 */
@WebServlet("/addclass")
public class addclass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addclass() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
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
		PrintWriter pw = response.getWriter();
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/applications.properties");
		props.load(in);
		Connection con = dbconnect.getConnection(props);

		String clasname;
		boolean flag = true;
		clasname = request.getParameter("clasname");

		PreparedStatement stmt, stmt1;
		try {

			stmt1 = con.prepareStatement("select * from classes;");

			ResultSet rs = stmt1.executeQuery();

			while (rs.next()) {

				if (rs.getString(1).equals(clasname)) {
					flag = false;
				}

			}

			if (flag) {
				stmt = con.prepareStatement("insert into classes(classname) values(?);");
				stmt.setString(1, clasname);
				stmt.executeUpdate();

				RequestDispatcher rd = request.getRequestDispatcher("/addclasses.html");
				rd.include(request, response);
				response.setContentType("text/html");
				pw.print(
						"<br/><center class='loginerr' style='color:green;margin-bottom:800px;'> Added Successfully </center>");

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/addclasses.html");
				rd.include(request, response);
				response.setContentType("text/html");
				pw.print(
						"<br/><center class='loginerr' style='color:red;margin-bottom:800px;'> Data already available </center>");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
