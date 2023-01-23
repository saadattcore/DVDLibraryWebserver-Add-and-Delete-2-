import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class RootHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));



    out.write(
      "<html>" +
              "<head> <title>DVD Library</title> "+
              "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
              "</head>" +
              "<body>" +
              "<div class=\"container\">" +
              "<a href=\"/dvds\">Display DVDs</a> " +
              "<br>" +
              "<a href=\"/add\">Add DVD</a> " +
              "<div class=\"container\">" +
              "<a href=\"/customers\">Display Customers</a> " +
              "<br>" +
              "<a href=\"/addCustomers\">Add Customers</a> " +
              "</div>" +
              "</body>" +
              "</html>");
   
    out.close();

  }

}