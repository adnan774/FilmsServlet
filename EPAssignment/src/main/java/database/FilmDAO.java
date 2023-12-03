package database;
import java.util.ArrayList;

import models.Film;

import java.sql.*;


public class FilmDAO {
	
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "root";
    String password = "Password1";
    // Note none default port used, 6306 not 3306
    String url = "jdbc:mysql://filmdb.csi4e99wibpj.eu-west-2.rds.amazonaws.com:3306/db";

	public FilmDAO() {}

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { 
			System.out.println(se); 
			}

	   return allFilms;
   }

   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }
   
   
   
   /**
	 * Insert Film into database
	 * 
	 * @param f Film Object
	 * @return True if inserted
	 * @throws SQLException Any error message thrown
	 */
	public boolean insertFilm(Film f) throws SQLException {

		openConnection();
		boolean b = false;
		try {
			String sql = "insert into films (title, year, director, stars, review) values ('" + f.getTitle() + "','" + f.getYear() + "','" + f.getDirector() + "','" + f.getStars() + "','" + f.getReview() + "');";
			System.out.println(sql);
			b = stmt.execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException s) {
			throw new SQLException("Film Not Added");
		}
		return b;
	}
	
	public boolean updateFilm(Film f) throws SQLException {

		openConnection();
		boolean b = false;
		try {	
			String sql = "update films set title = '" + f.getTitle() + "' , year = '" + f.getYear() +  "' , director = '" + f.getDirector() +  "' , stars = '" + f.getStars() +  "' , review = '" + f.getReview() + "' WHERE id = " + f.getId() + ";";
			System.out.println(sql);
			b = stmt.execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException s) {
			throw new SQLException("Film Not Added");
		}
		return b;
	}
	
	public boolean deleteFilm(int id) throws SQLException {
		
		openConnection();
		boolean b = false;
		try {	
			String sql = "delete from films WHERE id = "+id;
			System.out.println(sql);
			//ResultSet rs1 = stmt.executeQuery(selectSQL);
			stmt.executeUpdate(sql);
			//stmt.close();
			closeConnection();
			b = true;
		} catch (SQLException s) {
			throw new SQLException("Film Not Added");
		}
		return b;
	}
	
	public ArrayList<Film> searchFilms(String searchStr) {
		
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try {
			String sql = "select * from films where title like'%"+searchStr+"%'";
			ResultSet rs1 = stmt.executeQuery(sql);
			// Retrieve the results
			while(rs1.next()) {
				oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
			}
			stmt.close();
		    closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allFilms;
		
	}
	
}
   
   
   
