import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProcessProductSearch implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));
        try {

            System.out.println("ProcessSearchDVD Called");
            he.sendResponseHeaders(200,0);

            URI uri =  he.getRequestURI();
            String query = uri.getQuery();
            String[] queryParts =  query.split("&");

            String category = queryParts[0].split("=").length == 2 ?  queryParts[0].split("=")[1] : "";

            String description = queryParts[1].split("=").length == 2 ?  queryParts[1].split("=")[1] : "";


            //get ID number


            ProductDAO productDAO = new ProductDAO();

            System.out.println("about to get data");

            ArrayList<Product> dbProducts =  productDAO.getAllProducts();

            ArrayList<Product> filterDBProducts = new ArrayList<Product>() {
            };

            if ((description != null && description.equals("")) && (category != null && category.equals(""))){
                filterDBProducts =  (ArrayList<Product>) dbProducts.stream()
                        .filter(p -> p.getDescription().equals(description) && p.getCategory().equals(category))
                        .collect(Collectors.toList());
            }
            else  if(description != null && !description.equals("") )
            {
                filterDBProducts =  (ArrayList<Product>) dbProducts.stream().filter(p -> p.getDescription().toLowerCase()
                        .contains(description) ).collect(Collectors.toList());
            }else
            {
                filterDBProducts =  (ArrayList<Product>) dbProducts.stream().filter(p -> p.getCategory().toLowerCase().contains(category) )
                        .collect(Collectors.toList());
            }





            System.out.println("about to update dvd"); // Debugging message

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
                            "    <th>SKU</th>" +
                            "    <th>Description</th>" +
                            "    <th>Type 1</th>" +
                            "    <th>Type 2</th>" +
                            "    <th>Category</th>" +
                            "    <th>Price</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");
            for (Product product:filterDBProducts ) {
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
            }

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\">Back to List </a>"+
                            "</body>" +
                            "</html>");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            out.close();
        }
    }
}
