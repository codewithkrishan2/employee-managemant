package com.kksg.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UpdateController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String stringId = request.getParameter("id");
		int id = Integer.parseInt(stringId);
		Employee employee = new Employee();
		employee.setId(id);
		System.out.println(employee.getId());

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			PreparedStatement stmt = con.prepareStatement("select * from emp WHERE id=?");
			stmt.setInt(1, employee.getId());
			
			ResultSet rs = stmt.executeQuery();

			Employee emp1 = new Employee();				
			while (rs.next()) {
				emp1.setId(rs.getInt(1));
				emp1.setName(rs.getString(2));
				emp1.setEmail(rs.getString(3));
			}
			System.out.println(emp1.getEmail()+"Email in controller");
			request.setAttribute("employee", emp1);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			
			con.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
