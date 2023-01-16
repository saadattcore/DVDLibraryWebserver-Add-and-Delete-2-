import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DeleteHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
   
    System.out.println("DeleteHandler Called");
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

    DVDDao dvds = new DVDDao();

    

    try{
      // get the dvd details before we delete from the Database
      DVD deletedDVD = dvds.getDVD(ID);
      // actually delete from database;
      dvds.deleteDVD(ID);
      

     out.write(
      "<html>" +
      "<head> <title>DVD Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
      "<h1> DVD Deleted</h1>"+
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
      "    <td>"+ deletedDVD.getID() + "</td>" +
      "    <td>"+ deletedDVD.getTitle() + "</td>" +
      "    <td>"+ deletedDVD.getGenre() + "</td>" +
      "    <td>"+ deletedDVD.getYear() + "</td>" +
      "    <td>"+ deletedDVD.getRating() + "</td>" +
      "  </tr>" 
        );
      
      out.write(
      "</tbody>" +
      "</table>" +
      "<a href=\"/\">Back to Menu </a>"+
      "</body>" +
      "</html>");
     }catch(SQLException se){
      System.out.println(se.getMessage());
    }
    out.close();

}
}
