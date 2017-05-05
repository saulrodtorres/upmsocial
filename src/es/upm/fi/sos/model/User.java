package es.upm.fi.sos.model;

import java.util.ArrayList;

public class User {
	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	String name;
	String school;
	String photo;
	ArrayList<User> friends;

	public String getName() {
		return this.name;
	}

	public void setName(String p_name) {
		this.name = p_name;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String p_school) {
		this.school = p_school;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String p_photo) {
		this.photo = p_photo;
	}

	//Falta las operaciones sobre el ArrayList de amigos
	
}

