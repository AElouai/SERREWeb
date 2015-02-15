package db;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe de Connection entre les capteurs et la base de données
 * @author kamal
 *
 */
public class DbCapteur {
	
	private double ctemp;
	private double chumid;
	private double cco2;
	private double clumin;
	private ResultSet res;
	
	/**
	 * 
	 * @return La Temperature Capter
	 */
	public double getCtemp() {
		return ctemp;
	}

	/**
	 * 
	 * @return Le poursentage d l'Humidité Capter
	 */
	public double getChumid() {
		return chumid;
	}

	/**
	 * 
	 * @return Les ppm CO2 Capter
	 */
	public double getCco2() {
		return cco2;
	}

	/**
	 * 
	 * @return La Luminosité Capter
	 */
	public double getClumin() {
		return clumin;
	}
	
	/**
	 * Connection a la base de données 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void openConnection() throws ClassNotFoundException, SQLException
	{
		StaticDb.openConnection();
		res = StaticDb.getCapteurResult();
		res.next();
	}
	
	/**
	 * Recharge les valeures capter
	 * @throws SQLException
	 */
	public void charge() throws SQLException
	{
		this.cco2 = res.getDouble("co2");
		this.chumid = res.getDouble("humid");
		this.clumin = res.getDouble("lumin");
		this.ctemp =  res.getDouble("temp");
		res.next();
		if (res.isAfterLast()) res.first();
	}

	/**
	 * Fermeture de la conection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void closeConnection() throws ClassNotFoundException, SQLException
	{
		StaticDb.closeConnection();
	}
}