package application.service;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Statement;

import application.entite.Post;

import java.sql.Connection;

public class ConnectionBDD {
	
	Connection connection;

	public ConnectionBDD() {
		this.connection = getConnection();
	}
	
	private Connection getConnection()
	{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/funny_post";
			String username = "root";
			String password = "";
			Class.forName(driver);
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connecté");
			return connection;
		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	public ArrayList<Post> getAllPost()
	{
		try {
			ArrayList<Post> lstPostRes = new ArrayList<Post>();
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from post");
			
			while(resultSet.next())
			{
				Post postAdd = new Post(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("body"));
				lstPostRes.add(postAdd);
			}
			
			return lstPostRes;			
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}		
		return null;			
	}
	
	public void addPost(Post postAdd)
	{
		try {
			PreparedStatement insertQuery = this.connection.prepareStatement("INSERT INTO post VALUES (?, ?, ?)");
			insertQuery.setInt(1, postAdd.getId());
			insertQuery.setString(2, postAdd.getTitle());
			insertQuery.setString(3, postAdd.getBody());
			insertQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}	
	}
	
	public void updatePost(Post postUpdate)
	{
		try {
			PreparedStatement updateQuery = this.connection.prepareStatement("UPDATE post SET body = ? WHERE id = ? ");
			updateQuery.setString(1, postUpdate.getBody());
			updateQuery.setInt(2, postUpdate.getId());
			updateQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
	}
	
	public void deletePost(int idDelete)
	{
		try {
			PreparedStatement deleteQuery = this.connection.prepareStatement("DELETE FROM post WHERE id = ? ");
			deleteQuery.setInt(1, idDelete);
			deleteQuery.executeUpdate();
		}
		catch (Exception e) {
			System.err.println("Erreur de connexion !!");
			System.err.println(e.getMessage());
		}
	}
	
}
