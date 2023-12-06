package application.service;

import java.io.IOException;
import java.util.ArrayList;

import application.entite.Post;

public class Modele {
	
	private ConnectionBDD connectionBDD;
	private Webservice webservice;

	public Modele() {
		this.connectionBDD = new ConnectionBDD();
		this.webservice = new Webservice();
	}
	
	public ArrayList<Post> getAllPostInBDD()
	{
		return this.connectionBDD.getAllPost();
	}
	
	
	public void fetchPostAPI() throws IOException
	{
		ArrayList<Post> lstPost = this.webservice.getPostAPI();
		
		for(int i = 0 ; i < lstPost.size(); i++)
		{
			this.connectionBDD.addPost(lstPost.get(i));
		}
	}
	
	public void deletePost(int idPost)
	{
		this.connectionBDD.deletePost(idPost);
	}

}
