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
 * Servlet implementation class deletedata
 */
@WebServlet("/deletedata")
public class deletedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	String url = "jdbc:mysql://localhost:3306/learneracad";
	String user = "root";
	String password = "root";
	PreparedStatement stmt;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deletedata() {
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
		response.setContentType("text/html");

		String action = request.getParameter("initiate");
		String clasdel = request.getParameter("clasdel");
		String subdel = request.getParameter("subdel");
		String teachdel = request.getParameter("teachdel");

		if (clasdel != null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
				if (con != null) {
					stmt = con.prepareStatement("Delete FROM classes where classname=?;");
					stmt.setString(1, request.getParameter("cname"));
					int rs = stmt.executeUpdate();
					PreparedStatement stmt1 = con.prepareStatement("Delete FROM mapdata where clasname=?;");
					stmt1.setString(1, request.getParameter("cname"));
					stmt.executeUpdate();
					if (rs != 0) {

						RequestDispatcher rd = request.getRequestDispatcher("/deletedata.html");
						rd.include(request, response);
						response.setContentType("text/html");

						pw.print(
								"<br/><center class='loginerr' style='color:green;margin-bottom:800px;'> Data removed Successfully </center>");

					}

				} else {
					System.out.println("failed to db");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (subdel != null) {
			System.out.println("delet class function invoke ");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
				if (con != null) {
					stmt = con.prepareStatement("Delete FROM subjects where subjectname=?;");
					stmt.setString(1, request.getParameter("sname"));
					int rs = stmt.executeUpdate();

					PreparedStatement stmt1 = con.prepareStatement("Delete FROM mapdata where subjectname=?;");
					stmt1.setString(1, request.getParameter("sname"));
					stmt.executeUpdate();

					if (rs != 0) {
						RequestDispatcher rd = request.getRequestDispatcher("/deletedata.html");
						rd.include(request, response);
						response.setContentType("text/html");

						pw.print(
								"<br/><center class='loginerr' style='color:green;margin-bottom:800px;'> Data removed Successfully </center>");

					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (teachdel != null) {
			System.out.println("delet class function invoke ");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
				if (con != null) {
					stmt = con.prepareStatement("Delete FROM teachers where teachname=?;");
					stmt.setString(1, request.getParameter("tname"));
					int rs = stmt.executeUpdate();
					PreparedStatement stmt1 = con.prepareStatement("Delete FROM mapdata where teachname=?;");
					stmt1.setString(1, request.getParameter("tname"));
					stmt1.executeUpdate();

					if (rs != 0) {
						RequestDispatcher rd = request.getRequestDispatcher("/deletedata.html");
						rd.include(request, response);
						response.setContentType("text/html");

						pw.print(
								"<br/><center class='loginerr' style='color:green;margin-bottom:800px;'> Data removed Successfully </center>");

					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (action != null) {
			String perfomer = request.getParameter("tabname");
			System.out.println(perfomer);

			RequestDispatcher rd = request.getRequestDispatcher("/deletedata.html");
			rd.include(request, response);
			response.setContentType("text/html");

			if (perfomer.equals("Class")) {
				pw.print("Please Select Class to remove </br>");

				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, user, password);
					if (con != null) {
						stmt = con.prepareStatement("SELECT * FROM classes;");
						ResultSet rs = stmt.executeQuery();
						pw.print("<form method='post' action='deletedata'>");
						pw.print("<select name='cname'>");
						while (rs.next()) {

							pw.print("<option >" + rs.getString(1) + "</option>");
							System.out.println(rs.getString(1));

						}
						pw.print("</select>");
						pw.print("<button type='submit' name='clasdel' >Delete</button>");

					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (perfomer.equals("Subjects")) {
				pw.print("Please Select Subject to remove </br>");

				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, user, password);
					if (con != null) {
						stmt = con.prepareStatement("SELECT * FROM subjects;");
						ResultSet rs = stmt.executeQuery();
						pw.print("<form method='post' action='deletedata'>");
						pw.print("<select name='sname'>");
						while (rs.next()) {

							pw.print("<option >" + rs.getString(1) + "</option>");
							System.out.println(rs.getString(1));

						}
						pw.print("</select>");
						pw.print("<button type='submit' name='subdel' >Delete</button>");

					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (perfomer.equals("Teachers")) {
				pw.print("Please Select teacher to remove </br>");

				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, user, password);
					if (con != null) {
						stmt = con.prepareStatement("SELECT * FROM teachers;");
						ResultSet rs = stmt.executeQuery();
						pw.print("<form method='post' action='deletedata'>");
						pw.print("<select name='tname'>");
						while (rs.next()) {

							pw.print("<option >" + rs.getString(1) + "</option>");
							System.out.println(rs.getString(1));

						}
						pw.print("</select>");
						pw.print("<button type='submit' name='teachdel' >Delete</button>");

					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (perfomer.equals("Students")) {
				pw.print("Please Select teacher to remove </br>");

				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, user, password);
					if (con != null) {
						stmt = con.prepareStatement("SELECT * FROM students;");
						ResultSet rs = stmt.executeQuery();
						pw.print("<form method='post' action='deletedata'>");
						pw.print("<select name='sname'>");
						while (rs.next()) {

							pw.print("<option >" + rs.getString(1) + "</option>");
							System.out.println(rs.getString(1));

						}
						pw.print("</select>");
						pw.print("<button type='submit' name='studel' >Delete</button>");

					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
