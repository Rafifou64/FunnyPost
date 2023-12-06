package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.entite.Post;
import application.service.Modele;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class IndexController implements Initializable {
	
	@FXML
	private ListView<String> listViewPost;
	
	@FXML
	private Button btnInit;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private TextField textDelete;
	
	private Modele modele;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.modele = new Modele();
		this.loadListView(this.modele.getAllPostInBDD());
	}
	
	public void loadListView(ArrayList<Post> lstPost)
	{
		this.listViewPost.getItems().clear();
		for(int i = 0 ; i < lstPost.size(); i++)
		{
			this.listViewPost.getItems().add(lstPost.get(i).getDisplayPost());
		}
	}
	
	public void initializePost() throws IOException
	{
		this.modele.fetchPostAPI();
		this.loadListView(this.modele.getAllPostInBDD());
	}
	
	public void deletePost()
	{	
		int idPost = Integer.parseInt(this.textDelete.getText());
		this.modele.deletePost(idPost);
		
		//this.listViewPost.getItems().remove(o)
	}

}
