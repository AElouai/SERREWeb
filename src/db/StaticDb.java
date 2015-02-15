
package db;

import java.sql.*;

import serre.Serre;
import serre.control.SerreStruct;
import serre.control.SerreStructArray;

/**
 * Class Static qui gre la connection a la base de données
 * @author iname
 */
public class StaticDb {
	private static String className = "org.gjt.mm.mysql.Driver";
	private static Connection con;
	@SuppressWarnings({ "unchecked", "unused" })
	private static Class driver;
	private static Statement stmt;
	private static String server;
    private static String db;
    private static String user;
    private static String pass;
   
    /**
     * 
     * @param myServer serveur Mysql choisie
     * @param myDataBase Base de données choisie
     * @param myUser utilisateur du serveur mysql
     * @param myPassword mot de passe de l'utilisateur du serveur mysql
     */
    public static void init(String myServer, String myDataBase, String myUser, String myPassword) {
    	// TODO: implement
    	server = myServer;
		db = myDataBase;
		user = myUser;
		pass = myPassword;
    }
	   
    /**
     * 
     * @return true si la connection est faite, false si non
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws NullPointerException
     */
    @SuppressWarnings("unchecked")
	public static boolean openConnection() throws ClassNotFoundException, SQLException, NullPointerException {
    	// TODO: implement
		@SuppressWarnings("unused")
		Class driverObject = Class.forName(className);
		con = DriverManager.getConnection("jdbc:mysql://"+server+"/"+db, user, pass);
		stmt = con.createStatement();
		return true;
    }
	   
    /**
     * 
     * @return true si la fermeture de la conncetion est faite, false si non 
     * @throws SQLException
     */
    public static boolean closeConnection() throws SQLException {
    	// TODO: implement
		stmt.close();
		con.close();
		return true;
	}
	   
    /**
     * 
     * @return Un ResulSet qui contien les resultats lu de la base de données pour les capteurs 
     * @throws SQLException
     */
    public static ResultSet getCapteurResult() throws SQLException{
    	// TODO: implement
    	return stmt.executeQuery("select * from capteur");
	}
    
    /**
     * 
     * @param configid ID de la configuration choisie
     * @return Un ResultSet qui contien la configuration choisie et qui a l'ID donné comme parametre
     * @throws SQLException
     */
    public static ResultSet getConfigurationResult(int configid) throws SQLException{
    	// TODO: implement
    	return stmt.executeQuery("select * from configuration where serreid = " + configid);
	}
    
    /**
     * 
     * @param serreid ID de la serre  choisie
     * @return Un ResultSet qui contien la serre choisie et qui a l'ID donné comme parametre
     * @throws SQLException
     */
    public static ResultSet getSerreResult(int serreid) throws SQLException{
    	// TODO: implement
    	return stmt.executeQuery("select * from serre where serreid = " + serreid);
	}
    
    /**
     * 
     * @return L'ID de la derniere serre crée
     * @throws SQLException
     */
    public static int getLastSerreId() throws SQLException{
    	// TODO: implement
    	ResultSet res = stmt.executeQuery("select * from serre");
    	res.last();
    	return res.getInt("serreid");
	}
    
    /**
     * 
     * @return Un SerreStructArray qui contient tous les serres de la base de données
     * @throws SQLException
     */
    public static SerreStructArray getSerres() throws SQLException
    {
    	SerreStructArray ssa = new SerreStructArray();
    	ResultSet res = stmt.executeQuery("select * from serre");
    	res.next();
    	while(!res.isAfterLast())
    	{
	    	ssa.add(new SerreStruct(res.getInt("serreid"),res.getString("label")));
	    	res.next();
    	}
		return ssa;
    }

    /**
     * 
     * @param Label Le Label de la nouvel serre a crée
     * @param dbc une instance de la class DbConfiguration qui contient la configuration de la serre a crée 
     * @throws SQLException
     */
    public static void CreatSerre(String Label,DbConfiguration dbc) throws SQLException
    {
    	stmt.executeUpdate("INSERT INTO `serre` (`SERREID` ,`LABEL`) VALUES ( NULL , '" + Label + "');");
    	
    	stmt.executeUpdate("INSERT INTO `configuration` " +
    			"(`CONFIGID` ,`SERREID` ,`TEMPMIN` ,`TEMPMAX` ,`RHMIN` ,`RHMAX` ,`LUMMIN` ,`LUMMAX` ,`CO2MIN` ,`CO2MAX` ) " +
    			"VALUES ( NULL , '" +
    			StaticDb.getLastSerreId()
    			+ "', '" +
    			dbc.getTempmin()
    			+ "', '" +
    			dbc.getTempmax()
    			+ "', '" +
    			dbc.getRhmin()
    			+ "', '" +
    			dbc.getRhmax()
    			+ "', '" +
    			dbc.getLummin()
    			+ "', '" +
    			dbc.getLummax()
    			+ "', '" +
    			dbc.getCo2min()
    			+ "', '" +
    			dbc.getCo2max()
    			+ "');");
    }
    
    public static void DeleteSerre(int idSerre) throws SQLException
    {
    	stmt.executeUpdate("DELETE FROM `configuration` WHERE `SERREID` = '" + idSerre + "' ;");
    	
    	stmt.executeUpdate("DELETE FROM `serre` WHERE `SERREID` = '" + idSerre + "' ;");
    }
}