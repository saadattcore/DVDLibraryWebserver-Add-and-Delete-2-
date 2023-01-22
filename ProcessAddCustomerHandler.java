import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessAddCustomerHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Process Add Customer Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> parms = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(parms);

        //get ID number


        CustomerDAO customerDAO = new CustomerDAO();

        System.out.println("about to get data");

        int id = Integer.parseInt(parms.get("id"));
        String firstName = parms.get("firstname");
        String secondname = parms.get("secondname");
        String email = parms.get("email");
        String password = parms.get("password");
        String telephone = parms.get("telephone");



        System.out.println("about to create customer"); // Debugging message
        Customer customer = new Customer(id,firstName,secondname,email,password,telephone);
        System.out.println("DVD to Add" + customer);

        try {
            customerDAO.addCustomer(customer); // add to database


            out.write(
                    "<html>" +
                            "<head> <title>DVD Library</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Customer Added</h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>Title</th>" +
                            "    <th>Genre</th>" +
                            "    <th>Year</th>" +
                            "    <th>Rating</th>" +

                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>"       +
                            "    <td>"+ customer.getCustomerId() + "</td>" +
                            "    <td>"+ customer.getFirstName() + "</td>" +
                            "    <td>"+ customer.getSecondName() + "</td>" +
                            "    <td>"+ customer.getEmail() + "</td>" +
                            "    <td>"+ customer.getTelePhoneNumber() + "</td>" +
                            "    <td>"+ customer.getCustomerAddressId() + "</td>" +
                            "  </tr>"
            );

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\">Back to List </a>"+
                            "</body>" +
                            "</html>");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }

        out.close();

    }
}
