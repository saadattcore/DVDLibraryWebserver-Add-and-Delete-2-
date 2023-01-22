import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class DisplayLibraryHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    ProductDAO products = new ProductDAO();
    try{
    ArrayList<Product> allDVDS = products.getAllProducts();

    out.write(
      "<html>" +
      "<head> <title>DVD Library New</title> "+
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
      "    <th>SKU</th>" +
      "    <th>Description</th>" +
      "    <th>Category</th>" +
      "    <th>Price</th>" +
      "    <th>Delete</th>" +
               "    <th>Update</th>" +
      "  </tr>" +
      "</thead>" +
      "<tbody>");

      for (Product d : allDVDS){
        out.write(
      "  <tr>"       +
      "    <td>"+ d.getID() + "</td>" +
      "    <td>"+ d.getSKU() + "</td>" +
      "    <td>"+ d.getDescription() + "</td>" +
      "    <td>"+ d.getCategory() + "</td>" +
      "    <td>"+ d.getPrice() + "</td>" +
      "    <td> <a href=\"/delete?id=" + d.getID() + "\"> delete </a> </td>" +
              "    <td> <a href=\"/update?id=" + d.getID() + "\"> update </a> </td>" +
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