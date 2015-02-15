package db;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Une Classe pour la comunication entre la classe Configurationet les données lu de notre Base de données
 * 
 * @author kamal
 * 
 */
public class DbConfiguration {
	private double configrationID;
	private double tempmin;
	private double tempmax;
 	private double rhmin;
 	private double rhmax;
 	private double lummin;
 	private double lummax;
 	private double co2min;
 	private double co2max;
 	private ResultSet res;
 	
 	
 	/**
 	 * Constructeur par defaut
 	 * @param SerreID ID de la serre Qui est configuré avec cette configuration
 	 * @throws ClassNotFoundException
 	 * @throws SQLException
 	 */
 	public DbConfiguration(int SerreID) throws ClassNotFoundException, SQLException
 	{
 		StaticDb.openConnection();
 		res = StaticDb.getConfigurationResult(SerreID);
 		res.next();
 		configrationID = res.getDouble("configid");;
 		tempmin = res.getDouble("tempmin");
 		tempmax = res.getDouble("tempmax");
 	 	rhmin = res.getDouble("rhmin");
 	 	rhmax = res.getDouble("rhmax");
 	 	lummin = res.getDouble("lummin");
 	 	lummax = res.getDouble("lummax");
 	 	co2min = res.getDouble("co2min");
 	 	co2max = res.getDouble("co2max");
 	 	StaticDb.closeConnection();
 	}

 	/**
 	 * Constructeur pour la creation d'une configuration dans la base de données
 	 * @param tempmin Le Minimum de la temperature
 	 * @param tempmax Le Maximum `` ``     ``
 	 * @param rhmin Le Minimum de l'humidité
 	 * @param rhmax Le Maximum `` ``     ``
 	 * @param lummin Le Minimum de la luminosité
 	 * @param lummax Le Maximum `` ``     ``
 	 * @param co2min Le Minimum du CO2
 	 * @param co2max Le Maximum `` ``
 	 */
	public DbConfiguration(double tempmin, double tempmax, double rhmin,
			double rhmax, double lummin, double lummax, double co2min,
			double co2max) {
		this.tempmin = tempmin;
		this.tempmax = tempmax;
		this.rhmin = rhmin;
		this.rhmax = rhmax;
		this.lummin = lummin;
		this.lummax = lummax;
		this.co2min = co2min;
		this.co2max = co2max;
	}


	/**
	 * 
	 * @return L'ID De la configuration
	 */
	public double getConfigrationID() {
		return configrationID;
	}

	/**
	 * 
	 * @return Le Minimum de la temperature
	 */
	public double getTempmin() {
		return tempmin;
	}

	/**
	 * 
	 * @return Le Maximum de la temperature
	 */
	public double getTempmax() {
		return tempmax;
	}

	/**
	 * 
	 * @return Le Minimum de la Humidité
	 */
	public double getRhmin() {
		return rhmin;
	}

	/**
	 * 
	 * @return Le Maximum de la Humidité
	 */
	public double getRhmax() {
		return rhmax;
	}

	/**
	 * 
	 * @return Le Minimum de la Luminosité
	 */
	public double getLummin() {
		return lummin;
	}

	/**
	 * 
	 * @return Le Maximum de la Luminosité
	 */
	public double getLummax() {
		return lummax;
	}

	/**
	 * 
	 * @return Le Minimum du CO2
	 */
	public double getCo2min() {
		return co2min;
	}

	/**
	 * 
	 * @return Le Maximum du CO2
	 */
	public double getCo2max() {
		return co2max;
	}
 	
}
