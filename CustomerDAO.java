import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CustomerDAO {

    public CustomerDAO() {}

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

    public ArrayList<Customer> getAllCustomers() throws SQLException {
        System.out.println("Retrieving all customers ...");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM Customer INNER JOIN CustomerAddress WHERE Customer.AddressId = CustomerAddress.AddressId;";
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {

                int id = result.getInt("customerId");
                String firstName = result.getString("FirstName");
                String secondName = result.getString("SecondName");
                String email = result.getString("Email");
                String password = result.getString("Password");
                String telephone = result.getString("TelephoneNumber");
                int addressId = result.getInt("AddressId");
                String addressLine1 = result.getString("AddressLine1");
                String addressLine2 = result.getString("AddressLine2");
                String country = result.getString("Country");
                String postCode = result.getString("PostCode");
                Customer customer = new Customer(id,firstName,secondName,email,password,telephone);
                CustomerAddress address = new CustomerAddress(addressId,addressLine1,addressLine2,country,postCode);

                customer.setCustomerAddress(address);
                customers.add(customer);
            }
        } catch(Exception e) {
            System.out.println("get all customers: "+e);
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
        return customers;
    }

    public Customer getCustomer(int customerId) throws SQLException {

        Customer temp = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        String query = "SELECT * FROM Customer WHERE CustomerId =" + customerId + ";";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery: " + query);
            // execute SQL query
            result = statement.executeQuery(query);

            while (result.next()) {


                int id = result.getInt("CustomerId");
                String firstName = result.getString("FirstName");
                String secondName = result.getString("SecondName");
                String email = result.getString("Email");
                String password = result.getString("Password");
                String telephoneNumber = result.getString("TelephoneNumber");
                temp = new Customer(id,firstName,secondName,email,password,telephoneNumber);

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

    public Boolean deleteCustomer(int cutsomerId) throws SQLException {
        System.out.println("Deleting dvd");
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;

       Customer customer =  getCustomer(cutsomerId);

        String query = "DELETE FROM Customer WHERE CustomerId = " + cutsomerId + ";";
        String deleteAddressQuery = "DELETE FROM CustomerAddress WHERE AddressId =" + customer.getCustomerAddressId() + ";";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(query);
            // execute SQL query
            result = statement.executeUpdate(query);
            result = statement.executeUpdate(deleteAddressQuery);
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

    public Boolean updateCustomer(Customer customer) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE Customer " +  "FirstName = '"
                + customer.getFirstName() + "'," + "SecondName= '" + customer.getSecondName() + "'," + "Email= '" + customer.getEmail()
                + "'," + "Password= '" + customer.getPassword() + "'"
                + "TelephoneNumber = " + customer.getTelePhoneNumber()
                +" WHERE ID = " + customer.getCustomerId()
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

    public boolean addCustomer(Customer in) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String update = "INSERT INTO Customer (FirstName, SecondName, Email, Password, TelephoNumber) VALUES ("+in.getFirstName()+",'"+in.getSecondName()+"','"+in.getEmail()+"','"+in.getPassword()+ "',"+in.getTelePhoneNumber()+");";
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
