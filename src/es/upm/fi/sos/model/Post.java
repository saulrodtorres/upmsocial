package es.upm.fi.sos.model;

import java.time.Instant;
import java.time.LocalDate;

public class Post {
	int id;
	Instant date;
	String texto;
	String foto;
	User userAuthor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Instant getDate() {
		return date;
	}
	public void setDate(Instant date) {
		this.date = date;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public User getUserAuthor() {
		return userAuthor;
	}
	public void setUserAuthor(User userAuthor) {
		this.userAuthor = userAuthor;
	}

}
