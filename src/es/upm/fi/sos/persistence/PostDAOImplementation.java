package es.upm.fi.sos.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;



import es.upm.fi.sos.model.Post;
import es.upm.fi.sos.model.User;

public class PostDAOImplementation extends AbstractDAO implements PostDAO{

	
	public Post findByPK(int id) {
		Post entidad = null;
		String sqlFindByPK = "select * from Post where post_id = ?";
		UserDAO dao = (UserDAO) DAOFactory.getInstance().createDao(DAOType.USER);
		try{
			this.openConnection();
			PreparedStatement ps = this.getConnection().prepareStatement(sqlFindByPK);
			ps.setInt(1, id);
			
			ResultSet result = ps.executeQuery();
			result.next();
			
			entidad = new Post();
			
			entidad.setId(id);
			entidad.setDate(result.getDate("post_datetime").toInstant());
			entidad.setTexto(result.getString("post_text"));
			int id_user = result.getInt("user_id");
			User userAuthor = dao.findByPK(id_user);
			entidad.setUserAuthor(userAuthor);
			entidad.setFoto(result.getString("post_photo"));
			
			result.close();
			ps.close();
			this.closeConnection();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return entidad;
	}
	public List<Post> findAll() {
		List<Post> entidades = new ArrayList<Post>();
		String sqlFindAll = "select * from Post";
		try {
			this.openConnection();
			
			Statement st = this.getConnection().createStatement();
			UserDAO dao = (UserDAO) DAOFactory.getInstance().createDao(DAOType.USER);
			ResultSet result = st.executeQuery(sqlFindAll);
			while(result.next()){
				Post entidad = new Post();

				entidad.setId(result.getInt("post_id"));
				entidad.setDate(result.getDate("post_datetime").toInstant());
				entidad.setTexto(result.getString("post_text"));
				int id_user = result.getInt("user_id");
				
				User userAuthor = dao.findByPK(id_user);
				entidad.setUserAuthor(userAuthor);
				entidad.setFoto(result.getString("post_photo"));
				
				entidades.add(entidad);
			}
			
			
			result.close();
			st.close();
			this.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entidades;	
	}





	public Post save(Post post) {
		String insertSql ="INSERT INTO post"+
				"(post_id,"
				+ "post_datetime,"
				+ "user_id,"
				+ "post_text,"
				+ "post_photo) VALUES (?,?,?,?,?)";
		Post savedpost = null;
		try {
			this.openConnection();
			PreparedStatement ps = getConnection().prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, post.getId());
			Instant instant = post.getDate();
			ps.setDate(2, (Date) Date.from(instant));			
			ps.setInt(3, post.getUserAuthor().getId());
			ps.setString(4, post.getTexto());
			ps.setString(5,post.getFoto());
			ps.executeUpdate();
			
			ResultSet result = ps.getGeneratedKeys();
			if(result.next()){
				savedpost = this.findByPK(result.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedpost;
	}


	public Post update(Post post) {
		Post updatedPost = null;
		String updateSql = "UPDATE post SET "
				+ "post_id = ?,"
				+ "post_datetime = ?,"
				+ "user_id = ?,"
				+ "post_text = ? "
				+ "post_photo = ?";//AQUI HE QUITADO UN WHERE EN LO DE POST_ID
		try {
			this.openConnection();
			PreparedStatement ps = getConnection().prepareStatement(updateSql);
			ps.setInt(1, post.getId());
			Instant instant = post.getDate();
			ps.setDate(2, (Date) Date.from(instant));
			ps.setInt(3, post.getUserAuthor().getId());
			ps.setString(4, post.getTexto());
			ps.setString(5,post.getFoto());
			
			ps.executeUpdate();
			
			ps.close();
			closeConnection();
			
			updatedPost = this.findByPK(post.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updatedPost;
	}


	public void delete(Post post) {
		String sqldelete = "delete from post where post_id = ?";
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection().prepareStatement(sqldelete);
			ps.setLong(1, post.getId());
			ps.executeUpdate();
			ps.close();
			this.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
