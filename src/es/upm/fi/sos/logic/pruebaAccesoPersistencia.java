package es.upm.fi.sos.logic;

import es.upm.fi.sos.persistence.DAOFactory;
import es.upm.fi.sos.persistence.DAOType;
import es.upm.fi.sos.persistence.PostDAO;
import es.upm.fi.sos.persistence.UserDAO;
import es.upm.fi.sos.model.*;
public class pruebaAccesoPersistencia {

	public static void main(String[] args) {
		UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDao(DAOType.USER);
		PostDAO postDAO = (PostDAO) DAOFactory.getInstance().createDao(DAOType.POST);
		User saul = userDAO.findByPK(1);
		System.out.printf("El usuario con id=1 es: %s y estudia en la %s",saul.getName(),saul.getSchool());
		User giulio = userDAO.findByPK(2);
		System.out.printf("El usuario con id=2 es: %s y estudia en la %s",giulio.getName(),giulio.getSchool());
	}

}
