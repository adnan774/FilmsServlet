package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import models.Film;


@WebServlet("/films")
public class FilmsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();
		
		for(Film f : allFilms) {
			System.out.println(f.getTitle());
		}
		
		
		request.setAttribute("films", allFilms);
		RequestDispatcher rd = request.getRequestDispatcher("films.jsp");
		rd.include(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FilmDAO dao = new FilmDAO();
		Film f = new Film();
		
		f.setTitle(request.getParameter("title"));
		f.setYear(Integer.valueOf(request.getParameter("year")));
		f.setDirector(request.getParameter("director"));
		f.setStars(request.getParameter("stars"));
		f.setReview(request.getParameter("review"));
		
		try {
			dao.insertFilm(f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
		
	}

}