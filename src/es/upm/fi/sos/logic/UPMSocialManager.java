package es.upm.fi.sos.logic;
import java.util.List;
import es.upm.fi.sos.model.*;

public interface UPMSocialManager {
	/***OPERACIONES DE USUARIO****************************************************************************************/

	User 				getUser(int user_id);
	List<User> 			getAllUsers ();
	User 				createUser(User user);
	User 				updateUser(User user);
	boolean 			userExists(int id);
	void 				removeUser(User user);
}
