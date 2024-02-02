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
 * Servlet implementation class DeleteController
 */
@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public DeleteController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idS = request.getParameter("id");
		System.out.println("Reacived String ID from URL is : "+idS);
		int id = Integer.parseInt(idS);
		
		Employee emp1 = new Employee();
		emp1.setId(id);
		
		try {

			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Create the Connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			PreparedStatement statement = connection.prepareStatement("delete from emp where id=?");
			statement.setInt(1, id);
			
			int result = statement.executeUpdate();

			if (result > 0) {
				request.setAttribute("employee", emp1);
				System.out.println(emp1.getId() + " is deleted");
				request.getRequestDispatcher("/display").forward(request, response);
			}
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
		doGet(request, response);
	}

}
