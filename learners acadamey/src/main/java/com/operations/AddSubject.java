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
import com.details.SubjectDetails;
import com.util.Util;

@WebServlet("/subject")
public class AddSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getRequestDispatcher("add-subject.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("Name");
		String code = request.getParameter("code");
		String teacher = request.getParameter("teacher");
		
		try {
			SessionFactory factory = Util.buildConnection();
			
			Session session = factory.openSession();
			
			SubjectDetails subjectdetails = new SubjectDetails(name, code, teacher);
			
			Transaction tx = session.beginTransaction();
			
			session.save(subjectdetails);
			
			tx.commit();
			
			if(session != null) {
				out.println("<div align='center'><h2 style='color:green'>Subject Added Successfully</h2>"
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
