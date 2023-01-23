import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Map;

public class DeleteCustomerHandler {
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Delete Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map<String,String> parms = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(parms);

        //get ID number
        int ID = Integer.parseInt(parms.get("id"));

        CustomerDAO customers = new CustomerDAO();



        try{
            // get the dvd details before we delete from the Database
            Customer deletedCustomer = customers.getCustomer(ID);

            // actually delete from database;
            customers.deleteCustomer(ID);


            out.write(
                    "<html>" +
                            "<head> <title>DVD Library</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Customer Deleted</h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>Customer ID</th>" +
                            "    <th>FirstName</th>" +
                            "    <th>SecondName</th>" +
                            "    <th>Email</th>" +
                            "    <th>Password</th>" +
                            "    <th>Phone number</th>" +
                            "    <th>Address ID</th>" +

                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>"       +
                            "    <td>"+ deletedCustomer.getCustomerId() + "</td>" +
                            "    <td>"+ deletedCustomer.getFirstName() + "</td>" +
                            "    <td>"+ deletedCustomer.getLastName() + "</td>" +
                            "    <td>"+ deletedCustomer.getEmail() + "</td>" +
                            "    <td>"+ deletedCustomer.getPassword() + "</td>" +
                            "    <td>"+ deletedCustomer.getTelePhoneNumber() + "</td>" +
                            "    <td>"+ deletedCustomer.getCustomerAddressId() + "</td>" +
                            "  </tr>"
            );

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\">Back to Menu </a>"+
                            "</body>" +
                            "</html>");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        out.close();

    }
}
