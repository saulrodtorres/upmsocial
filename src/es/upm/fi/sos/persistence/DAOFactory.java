package es.upm.fi.sos.persistence;

public class DAOFactory {

	private static DAOFactory instance = null;
	
	private DAOFactory() {
	}
	
	public static DAOFactory getInstance() {
		if(instance == null)
			instance = new DAOFactory();
		return instance;
	}
	
	public DAO createDao(DAOType type){
		DAO dao = null;
		switch (type) {
		
		case USER:
			dao = new UserDAOImplementation();
			break;		
		case POST:
			dao = new PostDAOImplementation();
			break;
		/*
		case FRIEND:
			dao = new UserDAOImplementation();
			break;
			*/
		}
		return dao;
	}
}
