package com.kksg.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddController
 */
@WebServlet("/AddController")
public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public AddController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Employee emp1 = new Employee();
		emp1.setName(name);
		emp1.setEmail(email);
		
		try {
			
			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Create the Connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			PreparedStatement statement = connection.prepareStatement("insert into emp(name,email) values(?,?)");

			statement.setString(1, emp1.getName());
			statement.setString(2, emp1.getEmail());

			int result = statement.executeUpdate();

			if(result > 0) {
				request.setAttribute("employee", emp1);
				System.out.println(emp1.getName()+" And "+emp1.getEmail()+ " is inserted");
				request.getRequestDispatcher("add.jsp").forward(request, response);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
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
