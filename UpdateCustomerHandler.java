import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Map;

public class UpdateCustomerHandler {
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
                            "<head> <title>DVD Library</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<div class=\"container\">"+
                            "<h1> Add DVD</h1>"+
                            "<form method=\"get\" action=\"/processUpdateProduct\">" +
                            "<div class=\"form-group\"> "+
                            "<label for=\"ID\">Customer ID</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\"" + " value=\""  + updateCustomer.getCustomerId() +
                            "\">" +

                            "<label for=\"title\">FirstName</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\"" + " value=\""  + updateCustomer.getFirstName() +
                            "\">" +

                            "<label for=\"genre\">SecondName</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\"" + " value=\""  + updateCustomer.getLastName() +
                            "\">"
                            +

                            "<label for=\"genre\">Email</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\"" + " value=\""  + updateCustomer.getEmail() +
                            "\">"
                            +

                            "<label for=\"genre\">Password</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\"" + " value=\""  + updateCustomer.getPassword() +
                            "\">"
                            +

                            "<label for=\"year\">Phone number</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"category\" id=\"category\"" + " value=\""  + updateCustomer.getTelePhoneNumber() +
                            "\">"
                            +

                            "<label for=\"rating\">Address ID</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\"" + " value=\""  + updateCustomer.getCustomerAddressId() +
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
