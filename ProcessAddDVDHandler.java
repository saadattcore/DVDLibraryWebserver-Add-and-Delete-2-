import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessAddDVDHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
   
    System.out.println("ProcessAddDVD Called");
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    // Get param from URL
    Map <String,String> parms = Util.requestStringToMap
    (he.getRequestURI().getQuery());

    // print the params for debugging 
    System.out.println(parms);

    //get ID number
  

    DVDDao dvds = new DVDDao();

    System.out.println("about to get data");

    String title = parms.get("title");
    String genre = parms.get("genre");
    int rating = Integer.parseInt(parms.get("rating"));
    int year = Integer.parseInt(parms.get("year"));
    int ID = Integer.parseInt(parms.get("id"));
   
    System.out.println("about to create dvd"); // Debugging message 
    DVD dvd = new DVD(ID,title,genre,year,rating);
    System.out.println("DVD to Add" + dvd);

    try {  
    dvds.addDVD(dvd); // add to database 
      

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
      "    <td>"+ dvd.getID() + "</td>" +
      "    <td>"+ dvd.getTitle() + "</td>" +
      "    <td>"+ dvd.getGenre() + "</td>" +
      "    <td>"+ dvd.getYear() + "</td>" +
      "    <td>"+ dvd.getRating() + "</td>" +
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
