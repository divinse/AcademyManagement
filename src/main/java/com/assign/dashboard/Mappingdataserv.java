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
 * Servlet implementation class Mappingdataserv
 */
@WebServlet("/Mappingdataserv")
public class Mappingdataserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Mappingdataserv() {
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
		// doGet(request, response);

		String teachername = request.getParameter("teachername");
		String mappsub = request.getParameter("subname");
		String[] newstr = mappsub.split("\\|");
		System.out.println(newstr[0] + "....      . " + newstr[1]);
		PrintWriter pw = response.getWriter();
		Properties props = new Properties();

		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/applications.properties");
		props.load(in);
		Connection con = dbconnect.getConnection(props);
		if (con != null) {
			System.out.println("Database connected successful - mapdata");
		}
		boolean flag = false;
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("select * from mapdata;");
			rs = stmt.executeQuery();

			while (rs.next()) {

				if (rs.getString(1).equals(newstr[1].toString()) && rs.getString(2).equals(teachername)
						&& rs.getString(3).equals(newstr[0].toString())) {

					flag = true;
					break;
				}
			}

			if (flag) {

				RequestDispatcher rd = request.getRequestDispatcher("/Mapppingdata.html");
				rd.include(request, response);
				response.setContentType("text/html");
				pw.print(
						"<br/><form  class='loginerr' action='none'><center style='color:red;margin-bottom:800px;'> Data Available <a href='Mapppingdata.html'>Click here </a> to try again ...! </center></form>");

			} else {
				stmt = con.prepareStatement("Select * from learneracad.mapdata where subjectname=? and clasname=?;");

				stmt.setString(1, newstr[0].toString());
				stmt.setString(2, newstr[1].toString());
				ResultSet s = stmt.executeQuery();
				int count = 0;
				String mppedtechname = "";
				while (s.next()) {
					count++;
					mppedtechname = s.getString(2).toString();
				}
				if (count > 0) {
					pw.print(
							"<br/><form  class='loginerr' action='none'><center style='color:red;margin-bottom:800px;'>"
									+ "Already teacher with name <strong>" + mppedtechname
									+ "</strong>mapped for <strong>" + newstr[1].toString() + "</strong> for <strong>"
									+ newstr[0].toString()
									+ " </strong><br/><a href='Mapppingdata.html'>Click here </a> to continue </center></form>");

				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/Mapppingdata.html");
					rd.include(request, response);
					response.setContentType("text/html");
					stmt = con.prepareStatement("insert into mapdata(clasname,teachname,subjectname) values(?,?,?)");
					stmt.setString(1, newstr[1].toString());
					stmt.setString(2, teachername);
					stmt.setString(3, newstr[0].toString());
					int stats;
					stats = stmt.executeUpdate();

					if (stats == 1) {
						pw.print(
								"<br/><form  class='loginerr' action='none'><center style='color:green;margin-bottom:800px;'> Map Completed succesfully <a href='Mapppingdata.html'>Click here </a> to continue</center></form>");
					}
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
