import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Map;

public class UpdateCustomerHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Update Customer Handler Called");
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
            Customer updateCustomer = customers.getCustomer(ID);

            out.write(
                    "<html>" +
                            "<head> <title>Customer Update</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">"+
                            "<h1> Update Customer</h1>"+
                            "<form method=\"get\" action=\"/processCustomerUpdate\">" +
                            "<div class=\"form-group\"> "+
                            "<label for=\"ID\">Customer ID</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\"" + " value=\""  + updateCustomer.getCustomerId() +
                            "\">" +

                            "<label for=\"firstname\">FirstName</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"firstname\" id=\"firstname\"" + " value=\""  + updateCustomer.getFirstName() +
                            "\">" +

                            "<label for=\"secondname\">SecondName</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"secondname\" id=\"secondname\"" + " value=\""  + updateCustomer.getLastName() +
                            "\">"
                            +

                            "<label for=\"email\">Email</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"email\" id=\"email\"" + " value=\""  + updateCustomer.getEmail() +
                            "\">"
                            +

                            "<label for=\"password\">Password</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"password\" id=\"password\"" + " value=\""  + updateCustomer.getPassword() +
                            "\">"
                            +

                            "<label for=\"telephonenumber\">Phone number</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"telephonenumber\" id=\"telephonenumber\"" + " value=\""  + updateCustomer.getTelePhoneNumber() +
                            "\">"

                            +
                            "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
                            "</div>" +
                            "</form>" +
                            "<a href=\"/\">Back to List </a>"+
                            "</div>" +
                            "</body>" +
                            "</html>");

            out.close();


        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        out.close();

    }
}
