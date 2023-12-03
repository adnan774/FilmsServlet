package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;


@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		FilmDAO dao = new FilmDAO();
		
		Film f = dao.getFilmByID(id);
		request.setAttribute("films", f);
		request.getRequestDispatcher("update.jsp").include(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FilmDAO dao = new FilmDAO();
		Film f = new Film();
		
		
		f.setId(Integer.valueOf(request.getParameter("id")));
		f.setTitle(request.getParameter("title"));
		f.setYear(Integer.valueOf(request.getParameter("year")));
		f.setDirector(request.getParameter("director"));
		f.setStars(request.getParameter("stars"));
		f.setReview(request.getParameter("review"));
		
		try {
			dao.updateFilm(f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
		response.sendRedirect(request.getContextPath() + "/films");

		
	}

}