import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DVDDao {
	
	public DVDDao() {}
	
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		try {
			String dbURL = "jdbc:sqlite:dvd.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return dbConnection;
		}
	
	public ArrayList<DVD> getAllDVDs() throws SQLException {
		System.out.println("Retrieving all dvds ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM Product;";
		ArrayList<DVD> dvds = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {

				int id = result.getInt("Id");
				String sku = result.getString("SKU");
				String description = result.getString("Description");
				String category = result.getString("Category");
				double price = result.getDouble("Price");
				dvds.add(new DVD(id,sku,description,category,price));
			}
		} catch(Exception e) {
			System.out.println("get all dvds: "+e);
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return dvds;
	}

	public DVD getDVD(int film_id) throws SQLException {

		DVD temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT * FROM Product WHERE ID =" + film_id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);

			while (result.next()) {


				int id = result.getInt("ID");
				String sku = result.getString("SKU");
				String description = result.getString("Description");
				String category = result.getString("Category");
				double price = result.getDouble("Price");
				temp = new DVD(id,sku,description,category,price);

			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}

	public Boolean deleteDVD(int film_id) throws SQLException {
		System.out.println("Deleting dvd");
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM Product WHERE ID = " + film_id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			result = statement.executeUpdate(query);
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean updateDVD(DVD dvd) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "UPDATE Product " + "SET Id = '" + dvd.getID() + "'," + "Sku = '"
				+ dvd.getSKU() + "'," + "Description= '" + dvd.getDescription() + "'," + "Category= '" + dvd.getCategory()
				+ "Price = '" + dvd.getPrice()
				+" WHERE ID = " + dvd.getID()
				+ ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL update
			statement.executeUpdate(query);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;

		} finally {

			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}

	public boolean addDVD(DVD in) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		
		String update = "INSERT INTO Product (ID, Sku, Description, Category, Price) VALUES ("+in.getID()+",'"+in.getSKU()+"','"+in.getDescription()+"','"+in.getCategory()+ "',"+in.getPrice()+");";
		boolean ok = false;
			try {
					dbConnection = getDBConnection();
					statement = dbConnection.createStatement();
					System.out.println(update);
		// execute SQL query
					statement.executeUpdate(update);
					ok = true;
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					if (statement != null) {
						statement.close();
					}
					if (dbConnection != null) {
						dbConnection.close();
					}
					
				}
			return ok;
	}
	}
