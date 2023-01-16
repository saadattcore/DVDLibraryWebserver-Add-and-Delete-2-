import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;


class Main {
  static final private int PORT = 8080;

  public static void main(String[] args) throws IOException {
    System.out.println("Hello world!");

    HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
    server.createContext("/", new RootHandler() ); 
    server.createContext("/dvds", new DisplayLibraryHandler() );
    server.createContext("/delete", new DeleteHandler() ); 
    server.createContext("/add", new AddHandler());
    server.createContext("/processAddDVD", new ProcessAddDVDHandler());
    
    server.setExecutor(null);
    server.start();
    System.out.println("The server is listening on port " + PORT);



    
  }




}