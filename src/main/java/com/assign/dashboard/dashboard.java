package com.assign.dashboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class dashboard
 */
@WebServlet("/dashboard")
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dashboard() {
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
		// doPost(request,response);
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		pw.print("Access denied ....! ");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.html");
		rd.include(request, response);

		String value = request.getParameter("actionholder");

		if (value.equals("Logout")) {
			HttpSession session = request.getSession(true);

			String s = (String) session.getAttribute("status");

			if (s == "1") {

				session.invalidate();
				response.sendRedirect("index.html");
			}
		}

		if (value.equals("Global Search")) {
			response.sendRedirect("GlobaSearch.html");
		}

		if (value.equals("Execution Console")) {
			response.sendRedirect("sqlconsole.html");
		}

		if (value.equals("Add Classes")) {
			response.sendRedirect("addclasses.html");
		}

		if (value.equals("Delete Data")) {
			response.sendRedirect("deletedata.html");
		}
		if (value.equals("Map teachers to subject")) {
			response.sendRedirect("Mapppingdata.html");
		}

		if (value.equals("Display all data")) {
			response.sendRedirect("Displayalldata.html");
		}

		if (value.equals("Add Student")) {
			response.sendRedirect("AddStudent.html");
		}

		if (value.equals("Add Teachers")) {
			response.sendRedirect("Addteachers.html");
		}

		if (value.equals("Display Subjects")) {
			response.sendRedirect("Displaysubject.html");
		}

		if (value.equals("Change password")) {
			response.sendRedirect("setpassadmin.html");
		}

		if (value.equals("Add Subjects")) {
			response.sendRedirect("addsubject.html");
		}

	}

}
