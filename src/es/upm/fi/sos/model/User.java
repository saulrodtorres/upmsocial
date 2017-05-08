package es.upm.fi.sos.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class User {
	int id;
	String name;
	String school;
	String photo;
	ArrayList<User> friends;
	/***************constructor**********************************/
	public User(){
	/*es obligatorio el constructor vacio*/	
	}
	public User(String nombre){
		this.id = 0;
		this.name = nombre;
		this.school = "ETSIINF";
		this.photo = "04095393921170";
		this.friends = null;	
	}
	/****************getters and setters*************************/
	@XmlElement(name="user_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement(name="user_friends")
	public ArrayList<User> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}
	@XmlElement(name="user_name")
	public String getName() {
		return this.name;
	}
	public void setName(String p_name) {
		this.name = p_name;
	}
	@XmlElement(name="user_school")
	public String getSchool() {
		return this.school;
	}
	public void setSchool(String p_school) {
		this.school = p_school;
	}
	@XmlElement(name="user_photo")
	public String getPhoto() {
		return this.photo;
	}
	public void setPhoto(String p_photo) {
		this.photo = p_photo;
	}
	//Faltan las operaciones especiales sobre el ArrayList de amigos
}

