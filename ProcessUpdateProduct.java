import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessUpdateProduct implements HttpHandler{
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


        ProductDAO productDAO = new ProductDAO();

        System.out.println("about to get data");

        String sku = parms.get("sku");
        String description = parms.get("description");
        String category = parms.get("category");
        double price = Double.parseDouble(parms.get("price"));
        int id = Integer.parseInt(parms.get("id"));


        try {

           Product dbProduct =  productDAO.getProduct(id);

           if(dbProduct == null)
           {
               System.out.println("Product not found");
                return;
           }

           dbProduct.setSKU(sku);
           dbProduct.setCategory(category);
           dbProduct.setDescription(description);
           dbProduct.setPrice(price);


            System.out.println("about to update dvd"); // Debugging message

            System.out.println("DVD to Add" + dbProduct);

            productDAO.updateProduct(dbProduct); // add to database


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
                            "    <th>Title</th>" +
                            "    <th>Genre</th>" +
                            "    <th>Year</th>" +
                            "    <th>Rating</th>" +

                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>"       +
                            "    <td>"+ dbProduct.getID() + "</td>" +
                            "    <td>"+ dbProduct.getSKU() + "</td>" +
                            "    <td>"+ dbProduct.getDescription() + "</td>" +
                            "    <td>"+ dbProduct.getCategory() + "</td>" +
                            "    <td>"+ dbProduct.getPrice() + "</td>" +
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
