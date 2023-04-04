package com.assign.dashboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.loginmod.dbconnect;

/**
 * Servlet implementation class Searchdata
 */
@WebServlet("/Searchdata")
public class Searchdata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Searchdata() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ArrayList<String> searchdata = new ArrayList<>();
		Properties props = new Properties();
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/applications.properties");
		props.load(in);
		Connection con = dbconnect.getConnection(props);

		String searchkey;
		searchkey = request.getParameter("searchkey").toLowerCase();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("select * from subjects;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String match1 = rs.getString(1);
				String match2 = rs.getString(2);
				if (match1.contains(searchkey) || match2.contains(searchkey)) {
					searchdata.add(searchkey + " matched with Subjects " + match1);
				}
			}
			stmt = con.prepareStatement("select * from classes;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String match = rs.getString(1).toLowerCase();

				if (match.contains(searchkey)) {
					searchdata.add(match + " matched from Classes ");
				}
			}

			stmt = con.prepareStatement("select * from students;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String match1 = rs.getString(1).toLowerCase();
				String match2 = rs.getString(2).toLowerCase();
				if (match1.contains(searchkey) || match2.contains(searchkey)) {
					searchdata.add(searchkey + " matched from Students " + match1);
				}
			}
			stmt = con.prepareStatement("select * from teachers;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String match = rs.getString(1).toLowerCase();

				if (match.contains(searchkey)) {
					searchdata.add(match + " matched from teachers ");
				}
			}
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher("/GlobaSearch.html");
			rd.include(request, response);

			if (searchdata.size() == 0) {
				pw.print("<center><p style='color:white;'>No data found for " + searchkey
						+ " search keyword</p></center></br>");
			} else {
				pw.print("<table style='color:white;'>");

				for (String x : searchdata) {

					pw.print("<tr><td>" + x + "</td></tr>");
				}

				pw.print("<tr><td></br>Total search found " + searchdata.size() + "</td></tr></table></br> ");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
