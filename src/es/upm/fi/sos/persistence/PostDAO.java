package es.upm.fi.sos.persistence;

import java.util.List;
import es.upm.fi.sos.model.*;
public interface PostDAO extends DAO{
		public List<Post> findAll();
		public Post findByPK(int id);
		public Post save(Post cliente);
		public Post update(Post cliente);
		public void delete(Post cliente);
		
}
