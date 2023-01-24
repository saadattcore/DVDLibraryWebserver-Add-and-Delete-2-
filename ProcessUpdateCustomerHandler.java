import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessUpdateCustomerHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("ProcessUpdateDVD Called");
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
        String secondName = parms.get("secondname");
        String email = parms.get("email");
        String password = parms.get("password");
        String telephone = parms.get("telephonenumber");

        try {

            Customer dbCustomer =  customerDAO.getCustomer(id);

            if(dbCustomer == null)
            {
                System.out.println("Product not found");
                return;
            }

            dbCustomer.setFirstName(firstName);
            dbCustomer.setLastName(secondName);
            dbCustomer.setEmail(email);
            dbCustomer.setPassword(password);
            dbCustomer.setTelePhoneNumber(telephone);


            System.out.println("about to update customer"); // Debugging message

            System.out.println("Customer to update" + dbCustomer);

            customerDAO.updateCustomer(dbCustomer); // add to database

            out.write(
                    "<html>" +
                            "<head> <title>DVD Library</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> DVD Added</h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>FirstName</th>" +
                            "    <th>SecondName</th>" +
                            "    <th>Email</th>" +
                            "    <th>Password</th>" +
                            "    <th>Telephone</th>" +


                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");

            out.write(
                    "  <tr>"       +
                            "    <td>"+ dbCustomer.getCustomerId() + "</td>" +
                            "    <td>"+ dbCustomer.getFirstName() + "</td>" +
                            "    <td>"+ dbCustomer.getLastName() + "</td>" +
                            "    <td>"+ dbCustomer.getEmail() + "</td>" +
                            "    <td>"+ dbCustomer.getPassword() + "</td>" +
                            "    <td>"+ dbCustomer.getTelePhoneNumber() + "</td>" +
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
