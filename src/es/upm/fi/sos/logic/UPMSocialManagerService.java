package es.upm.fi.sos.logic;


import java.util.List;
import es.upm.fi.sos.model.*;
import es.upm.fi.sos.persistence.*;

public class UPMSocialManagerService implements UPMSocialManager{
	private UserDAO userDAO;
	private PostDAO postDAO;
	
	public UPMSocialManagerService(){
		this.userDAO = (UserDAO) DAOFactory.getInstance().createDao(DAOType.USER);
		this.postDAO = (PostDAO) DAOFactory.getInstance().createDao(DAOType.POST);	
	}
	/***OPERACIONES DE USUARIO****************************************************************************************/
	public User getUser(int userId) {
		User user = this.userDAO.findByPK(userId);				
		return user;
	}

	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	public User createUser(User usuario) {
		return userDAO.save(usuario);
		
	}

	public User updateUser(User userToUpdate) {
		User savedUser = null;
		User usuarioExistente = this.getUser(userToUpdate.getId());
		usuarioExistente.setName(userToUpdate.getName());
		usuarioExistente.setFriends(userToUpdate.getFriends());
		usuarioExistente.setSchool(userToUpdate.getSchool());
		usuarioExistente.setPhoto(userToUpdate.getPhoto());
		savedUser = userDAO.update(usuarioExistente);
		return savedUser;
	}

	public void removeUser(User user) {
		userDAO.delete(user);		
	}
	public boolean userExists(int id) {
		System.out.printf("----1>>>>>>>>>> entra en userExists() y voy a hacer un findByPK() del id = %d*****",id);
		User usuario = userDAO.findByPK(id);
		System.out.printf("----2>>>>>>>>>> entra en userExists() y esta a punto de pintarlo*****");
		String s = usuario.getName();
		System.out.printf("----3a>>>>>>>>>> user cargado = %s *************************",s);

		return usuario!=null;	
	}
	/***OPERACIONES DE POST****************************************************************************************/
	/***OPERACIONES DE FRIEND**************************************************************************************/

}
