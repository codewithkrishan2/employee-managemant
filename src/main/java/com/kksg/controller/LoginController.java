package com.kksg.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username"); 
		String password = request.getParameter("password");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		try {
			
			//Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Create the Connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM emp WHERE name=? AND email=?");
			
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				String name = result.getString(2);
				String email = result.getString(3);
				
				System.out.println(name);
				System.out.println(email);
				
//				boolean validate = user.validate(name, email);
				if (username.equalsIgnoreCase(name) && password.equalsIgnoreCase(email)) {
					request.setAttribute("u", user);
					RequestDispatcher rd = request.getRequestDispatcher("loginsuccess.jsp");
					rd.forward(request, response);
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("loginerror.jsp");
					rd.forward(request, response);
				}
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
