import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessAddDVDHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        System.out.println("ProcessAddProduct Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> parms = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(parms);

        //get ID number


        ProductDAO products = new ProductDAO();

        System.out.println("about to get data");

        String sku = parms.get("sku");
        String description = parms.get("description");
        String type1 = parms.get("type1");
        String type2 = parms.get("type2");
        String category = parms.get("category");
        double price = Double.parseDouble(parms.get("price"));
        int id = Integer.parseInt(parms.get("id"));


        System.out.println("about to create product"); // Debugging message
        Product product = new Product(id,sku,description,type1,type2,price,category);
        System.out.println("Product to Add" + product);

        try {
            products.addProduct(product); // add to database


            out.write(
                    "<html>" +
                            "<head> <title>Product Library</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Product Added</h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID</th>" +
                            "    <th>SKU</th>" +
                            "    <th>Description</th>" +
                            "    <th>Type 1</th>" +
                            "    <th>Type 2</th>" +
                            "    <th>Category</th>" +
                            "    <th>Price</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>"       +
                            "    <td>"+ product.getID() + "</td>" +
                            "    <td>"+ product.getSKU() + "</td>" +
                            "    <td>"+ product.getDescription() + "</td>" +
                            "    <td>"+ product.getType1() + "</td>" +
                            "    <td>"+ product.getType2() + "</td>" +
                            "    <td>"+ product.getCategory() + "</td>" +
                            "    <td>"+ product.getPrice() + "</td>" +
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
