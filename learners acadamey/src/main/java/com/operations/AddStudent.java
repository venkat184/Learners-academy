package com.operations;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.details.StudentDetails;
import com.util.Util;

@WebServlet("/student")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getRequestDispatcher("add-student.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("Name");
		String RollNo = request.getParameter("rollno");
		String Class = request.getParameter("class");
		
		try {
			SessionFactory factory = Util.buildConnection();
			
			Session session = factory.openSession();
			
			StudentDetails studentdetails = new StudentDetails(name, RollNo, Class);
			
			Transaction tx = session.beginTransaction();
			
			session.save(studentdetails);
			
			tx.commit();
			
			if(session != null) {
				out.println("<div align='center'><h2 style='color:green'>Student Added Successfully</h2>"
						+ "<a href='admin-page.html'>Back To MainMenu</a>"
						+ "</div>");
			}
			session.close();
		} catch (Exception e) {
			out.println("<div align='center'><h2 style='color:red'>Operation Failed</h2>"
					+ "<a href='admin-page.html'>Back To MainMenu</a>"
					+ "</div>");
		}
	}

}
