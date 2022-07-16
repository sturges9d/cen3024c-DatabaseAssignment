package cen3024c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class adds database connectivity for the WordOccurrences program.
 * @author Stephen Sturges Jr
 * @version 07/13/2022
 */
public class WordOccurrencesJDBC {
	
	/**
	 * Establishes a connection to a hard coded MySQL server.
	 * @return Returns an established connection.
	 */
	public static Connection establishConnection() {
		Connection connection;
		String serverURL = "jdbc:mysql://localhost/word_occurrences";
		String username = "wordOccurrencesUser";
		String password = "wordOccurrencesUserPW";
		try {
			connection = DriverManager.getConnection(serverURL, username, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Database connection failed.");
		return null;
	} // End of establishConnection method.
	
	/**
	 * 
	 * @param connection
	 * @return
	 */
	public static String getValues(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM word ORDER BY count DESC";
			ResultSet queryResult = statement.executeQuery(sql);
			String result = "";
			int i = 0;
			while (queryResult.next() && i < 20){
				i++;
				result += i + ". " + queryResult.getString("word") + ", " + queryResult.getInt("count") + "\n";
			} // End of while loop.
			return result;
		} catch (SQLException e) {
			System.out.println("getValues failed.");
			e.printStackTrace();
		} // End of try-catch block.
		return null;
	} // End of getValues method.
	
	/**
	 * Inserts word and number of occurrence values into the database where the connection is established.
	 * @param connection The connection established, by the establishConnection method, to the database.
	 * @param word String, word to be entered into the database.
	 * @param count int, number of occurrences of the associated word.
	 * @return int, number of rows affected by the insert statement.
	 */
	public static int insertValues(Connection connection, String word, int count) {
		int rowsAffected = 0;
		try {
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO word VALUES ('" + word + "'," + count + ")";
			rowsAffected = statement.executeUpdate(sql);
			return rowsAffected;
		} catch (SQLException e) {
			e.printStackTrace();
		} // End of try-catch block.
		return rowsAffected;
	} // End of insertValues statement.
	
} // End of WordOccurrencedJDBC class.