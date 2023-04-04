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
 * Servlet implementation class sqlconsole
 */
@WebServlet("/sqlconsole")
public class sqlconsole extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	String url = "jdbc:mysql://localhost:3306/learneracad";
	String user = "root";
	String password = "root";
	PreparedStatement stmt;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public sqlconsole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String querytoexe = request.getParameter("sqlquery");
		PrintWriter pw=response.getWriter();

		String key1="Drop";
		String key2="Alter";
		String key3="Create";
		if ( querytoexe.toLowerCase().indexOf(key1.toLowerCase()) != -1 || querytoexe.toLowerCase().indexOf(key2.toLowerCase()) != -1 ||querytoexe.toLowerCase().indexOf(key3.toLowerCase()) != -1 ) {
			RequestDispatcher rd = request.getRequestDispatcher("/sqlconsole.html");
			rd.include(request, response);
			response.setContentType("text/html");
			   pw.print("<br/><center class='loginerr' style='color:red;margin-bottom:800px;'> You are not allowed to drop / Alter the main frame </center>");

			} else {

				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, user, password);
					if (con != null) {
						stmt = con.prepareStatement(querytoexe);
						ResultSet rs = stmt.executeQuery();

						System.out.println(rs.getFetchSize()
								);
						RequestDispatcher rd = request.getRequestDispatcher("/sqlconsole.html");
						rd.include(request, response);
						response.setContentType("text/html");
						pw.print(" </br><table style='color:white;'>");
						while(rs.next()) {
							pw.print("<tr>");
							try {
								if(rs.getString(1)!=null) {
									pw.print("<td >"+rs.getString(1)+"</td>");
								}
								if(rs.getString(2)!=null) {
									pw.print("<td>"+rs.getString(2)+"</td>");
								}
								if(rs.getString(3)!=null) {
									pw.print("<td>"+rs.getString(3)+"</td>");
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								continue;
							}
							pw.print("</tr>");

						}
						pw.print("</table>");
						pw.print("<br/><center class='loginerr' style='color:green;margin-bottom:800px;'> Query executed successfully </center>");



					} else {
						System.out.println("failed to db");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					pw.print("<br/><center class='loginerr' style='color:red;margin-bottom:800px;'> Query execution failed </center>");

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}





	}

}
