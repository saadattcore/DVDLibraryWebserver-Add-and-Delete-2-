import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class UpdateHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Update Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> parms = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(parms);

        //get ID number
        int ID = Integer.parseInt(parms.get("id"));

        ProductDAO dvds = new ProductDAO();



        try{
            // get the dvd details before we delete from the Database
            Product updateProduct = dvds.getProduct(ID);

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
                            "<label for=\"ID\">ID</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\"" + " value=\""  + updateProduct.getID() +
                            "\">" +

                            "<label for=\"title\">Sku</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\"" + " value=\""  + updateProduct.getSKU() +
                            "\">" +

                            "<label for=\"genre\">Description</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\"" + " value=\""  + updateProduct.getDescription() +
                            "\">"
                            +

                            "<label for=\"year\">Category</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"category\" id=\"category\"" + " value=\""  + updateProduct.getCategory() +
                            "\">"
                            +

                            "<label for=\"rating\">Price</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\"" + " value=\""  + updateProduct.getPrice() +
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
