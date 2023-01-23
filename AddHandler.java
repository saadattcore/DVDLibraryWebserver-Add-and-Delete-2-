import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

public class AddHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {

    System.out.println("Add Handler Called");
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(
            new OutputStreamWriter(he.getResponseBody() ));

    ProductDAO dvds = new ProductDAO();



    out.write(
            "<html>" +
                    "<head> <title>DVD Library</title> "+
                    "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                    "</head>" +
                    "<body>" +
                    "<div class=\"container\">"+
                    "<h1> Add DVD</h1>"+
                    "<form method=\"get\" action=\"/processAddDVD\">" +
                    "<div class=\"form-group\"> "+
                    "<label for=\"ID\">ID</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\"> " +

                    "<label for=\"sku\">Sku</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\"> " +

                    "<label for=\"description\">Description</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\"> " +

                    "<label for=\"type 1\">Type 1</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\"> " +

                    "<label for=\"type 2\">Type 2</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\"> " +

                    "<label for=\"category\">Category</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"category\" id=\"category\"> " +

                    "<label for=\"price\">Price</label> " +
                    "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\" >" +
                    "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
                    "</div>" +
                    "</form>" +
                    "<a href=\"/\">Back to List </a>"+
                    "</div>" +
                    "</body>" +
                    "</html>");

    out.close();

  }
}
