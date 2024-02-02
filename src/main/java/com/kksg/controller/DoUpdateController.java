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
 * Servlet implementation class DoUpdateController
 */
@WebServlet("/DoUpdateController")
public class DoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DoUpdateController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String idString = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(idString));
		employee.setName(name);
		employee.setEmail(email);
		
try {
			
			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Create the Connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			PreparedStatement statement = connection.prepareStatement("update emp SET name=?, email=? WHERE id=?");
			
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getEmail());
			statement.setInt(3, employee.getId());

			int result = statement.executeUpdate();

			if(result > 0) {
				//request.setAttribute("employee", employee);
				System.out.println("Name: "+employee.getName()+" And Email: "+employee.getEmail()+ " is updated");
				request.getRequestDispatcher("/display").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
