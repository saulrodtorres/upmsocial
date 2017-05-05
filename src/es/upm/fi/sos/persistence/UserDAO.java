package es.upm.fi.sos.persistence;

import java.util.List;
import es.upm.fi.sos.model.*;

public interface UserDAO extends DAO {
	public List<User> findAll();
	public User findByPK(int id);
	public User save(User usuario);
	public User update(User usuario);
	public void delete(User usuario);
}
