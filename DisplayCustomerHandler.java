import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class DisplayCustomerHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        CustomerDAO dvds = new CustomerDAO();
        try{
            ArrayList<Customer> allCustomers = dvds.getAllCustomers();

            out.write(
                    "<html>" +
                            "<head> <title>DVD Library</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +


                            "<div class=\"container\">" +
                            "<h1> DVDs in Stock!</h1>"+
                            "<form method=\"get\" action=\"/processProductSearch\" >" +

                            "<input type=\"text\" id=\"txtCategory\" name=\"txtCategory\" placeholder=\"category\"> &nbsp &nbsp <input type=\"text\" id=\"txtDesc\" name=\"txtDesc\" placeholder=\"description\">&nbsp &nbsp <input type=\"submit\" id=\"txtSearch\" name=\"txtSearch\" class=\"btn btn-primary\" >"  +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>FirstName</th>" +
                            "    <th>SecondName</th>" +
                            "    <th>Email</th>" +
                            "    <th>TelephoneNumber</th>" +
                            "    <th>AddressId</th>" +
                            "    <th>Delete</th>" +
                            "    <th>Update</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");

            for (Customer d : allCustomers){
                out.write(
                        "  <tr>"       +
                                "    <td>"+ d.getCustomerId() + "</td>" +
                                "    <td>"+ d.getFirstName() + "</td>" +
                                "    <td>"+ d.getSecondName() + "</td>" +
                                "    <td>"+ d.getEmail() + "</td>" +
                                "    <td>"+ d.getCustomerId() + "</td>" +
                                "    <td> <a href=\"/delete?id=" + d.getCustomerId() + "\"> delete </a> </td>" +
                                "    <td> <a href=\"/update?id=" + d.getCustomerId() + "\"> update </a> </td>" +
                                "  </tr>"
                );
            }
            out.write(
                    "</tbody>" +
                            "</table>" +
                            "</form>"+
                            "<a href=\"/\">Back to Menu </a>"+
                            "</div>" +

                            "</body>" +
                            "</html>");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        out.close();

    }

}