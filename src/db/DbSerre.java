package db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Une Classe pour la comunication entre la classe Serre et les données lu de notre Base de données
 * 
 * @author kamal
 */
public class DbSerre {
	
	private static String label = "";

	/**
	 * 
	 * @param newSerre ID de la serre recherche 
	 * @return Une chaine de caractere qui contien le nom de la serre qui a l'ID donne comme parametre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getSerreLabel(int newSerre) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StaticDb.openConnection();
 		ResultSet res = StaticDb.getSerreResult(newSerre);
 		res.next();
 		label = res.getString("label");
 	 	StaticDb.closeConnection();
 	 	return label;
	}

	/**
	 * 
	 * @return L'ID De la derniere serre crée
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getLastSerreId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StaticDb.openConnection();
 		int id = StaticDb.getLastSerreId();
 	 	StaticDb.closeConnection();
 	 	return id;
	}
}
