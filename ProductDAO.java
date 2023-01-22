import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProductDAO {

	public ProductDAO() {}

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

	public ArrayList<Product> getAllProducts() throws SQLException {
		System.out.println("Retrieving all products ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM Product;";
		ArrayList<Product> products = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//	System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {

				int id = result.getInt("ID");
				String sku = result.getString("SKU");
				String description = result.getString("Description");
				String type1 = result.getString("Type1");
//				System.out.println(type1);
				String type2 = result.getString("Type2");
//				System.out.println(type2);
				String category = result.getString("Category");
				double price = result.getDouble("Price");
				products.add(new Product(id,sku,description,type1,type2,price,category));
			}
		} catch(Exception e) {
			System.out.println("get all products: "+e);
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
		return products;
	}

	public Product getProduct(int product_id) throws SQLException {

		Product temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT * FROM Product WHERE ID =" + product_id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//	System.out.println("DBQuery: " + query);
			//	execute SQL query
			result = statement.executeQuery(query);

			while (result.next()) {


				int id = result.getInt("ID");
				String sku = result.getString("SKU");
				String description = result.getString("Description");
				String type1 = result.getString("Type1");
				String type2 = result.getString("Type2");
				double price = result.getDouble("Price");
				String category = result.getString("Category");

				temp = new Product(id,sku,description,type1,type2,price,category);

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

	public Boolean deleteProduct(int product_id) throws SQLException {
		System.out.println("Deleting product");
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM Product WHERE ID = " + product_id + ";";
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

	public Boolean updateProduct(Product product) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "UPDATE Product " + "SET ID = '" + product.getID() + "'," + "Sku = '"
				+ product.getSKU() + "', " + "Description = '" + product.getDescription() + "', " + "Type1 = '" + product.getType1() + "', " + "Type2 = '" + product.getType2() + "', " + "Category = '" + product.getCategory()
				+ "', " + "Price = " + product.getPrice()
				+" WHERE ID = " + product.getID()
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

	public boolean addProduct(Product in) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;

		String update = "INSERT INTO Product (ID, Sku, Description, Type1, Type2, Category, Price) VALUES ("+in.getID()+",'"+in.getSKU()+"','"+in.getDescription()+"','"+in.getType1()+"','"+in.getType2()+"','"+in.getCategory()+ "',"+in.getPrice()+");";
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
