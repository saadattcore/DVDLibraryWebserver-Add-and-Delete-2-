import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


class Main {
  static final private int PORT = 8090;

  public static void main(String[] args) throws IOException {
    //  System.out.println("Hello world!");

    HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
    server.createContext("/", new RootHandler() );
    server.createContext("/dvds", new DisplayLibraryHandler() );
    server.createContext("/delete", new DeleteHandler() );
    server.createContext("/add", new AddHandler());
    server.createContext("/processAddDVD", new ProcessAddDVDHandler());
    server.createContext("/update", new UpdateHandler());
    server.createContext("/processUpdateProduct", new ProcessUpdateProduct());
    server.createContext("/processProductSearch", new ProcessProductSearch());

    server.setExecutor(null);
    server.start();
    System.out.println("The server is listening on port " + PORT);

    Scanner in = new Scanner(System.in);
    ProductDAO product = new ProductDAO();
    String productSelection;

    CustomerDAO customer = new CustomerDAO();
    String customerSelection = null;

    String mainSelection;

    do {
      System.out.println("--------------------");
      System.out.println("The Everything Store");
      System.out.println("Choose from these options");
      System.out.println("Main menu");
      System.out.println("--------------------");

      System.out.println("[1] Controls for products");
      System.out.println("[2] Controls for customers");
      System.out.println("[3] Exit");

      mainSelection = in.nextLine();

      switch(mainSelection) {
        case "1":
          do {
            System.out.println("--------------------");
            System.out.println("The Everything Store");
            System.out.println("Choose from these options");
            System.out.println("--------------------");

            System.out.println("[1] List all Products");
            System.out.println("[2] Search Products by ID");
            System.out.println("[3] Add a new Product");
            System.out.println("[4] Update a Product by ID");
            System.out.println("[5] Delete a Product by ID");
            System.out.println("[6] Exit ");
            System.out.println();

            productSelection = in.nextLine();

            try {
              switch (productSelection) {
                case "1":
                  ArrayList<Product> allProduct = product.getAllProducts();
                  for (int i = 0; i < allProduct.size(); i++){
                    System.out.println(allProduct.get(i));
                  }
                  System.out.println();
                  break;

                case "2":
                  System.out.println("\nEnter Product ID to get details  ");
                  int ID = Integer.parseInt(in.nextLine());
                  System.out.println(product.getProduct(ID));
                  System.out.println();
                  break;

                case "3":
                  System.out.println("Add a new Product");
                  Product newProduct = createProduct();
                  product.addProduct(newProduct);
                  System.out.println();
                  break;

                case "4":
                  System.out.println("Update a new Product");
                  System.out.println("\nEnter Product ID to update ");
                  int uID = Integer.parseInt(in.nextLine());
                  System.out.println(product.getProduct(uID));
                  Product updatedProduct = updateProduct(product.getProduct(uID));
                  product.updateProduct(updatedProduct);
                  break;

                case "5":
                  System.out.println("Delete Product");
                  System.out.println("Enter ID of Product to delete");
                  int dID = Integer.parseInt(in.nextLine());
                  product.deleteProduct(dID);
                  break;

                case "6":
                  System.out.println("Exiting");
                  break;
                default:
                  System.out.println("Invalid Selection");
              }
            }
            catch (SQLException ex){
              System.out.println(ex.getMessage());
            }
          } while (!productSelection.equals("6"));

        case "2":
          do{
            System.out.println("[1] List all Customers");
            System.out.println("[2] Search Customers by ID");
            System.out.println("[3] Add a new Customer");
            System.out.println("[4] Update a Customer by ID");
            System.out.println("[5] Delete a Customer by ID");
            System.out.println("[6] Exit");
            System.out.println();

            customerSelection = in.nextLine();

            try {
              switch (customerSelection) {
                case "1":
                  ArrayList<Customer> allCustomer = customer.getAllCustomers();
                  for (int i = 0; i < allCustomer.size(); i++){
                    System.out.println(allCustomer.get(i));
                  }
                  System.out.println();
                  break;

                case "2":
                  System.out.println("\nEnter Customer ID to get details  ");
                  int ID = Integer.parseInt(in.nextLine());
                  System.out.println(customer.getCustomer(ID));
                  System.out.println();
                  break;

                case "3":
                  System.out.println("Add a new Customer");
                  Customer newCustomer = createCustomer();
                  customer.addCustomer(newCustomer);
                  System.out.println();
                  break;

                case "4":
                  System.out.println("Update a new Customer");
                  System.out.println("\nEnter Customer ID to update ");
                  int uID = Integer.parseInt(in.nextLine());
                  System.out.println(customer.getCustomer(uID));
                  Customer updatedCustomer = updateCustomer(customer.getCustomer(uID));
                  customer.updateCustomer(updatedCustomer);
                  break;

                case "5":
                  System.out.println("Delete Customer");
                  System.out.println("Enter ID of Customer to delete");
                  int dID = Integer.parseInt(in.nextLine());
                  customer.deleteCustomer(dID);
                  break;

                case "6":
                  System.out.println("Exiting");
                  break;
                default:
                  System.out.println("Invalid Selection");
              }
            }
            catch (SQLException ex){
              System.out.println(ex.getMessage());
            }
          } while (!customerSelection.equals("6"));

        case "3":
          System.out.println("Exiting");
          break;
        default:
          System.out.println("Invalid selection");
      }
    } while (!mainSelection.equals("3"));

  }

  private static Customer createCustomer() {
    int id;
    String firstName;
    String lastName;
    String email;
    String password;
    String number;

    Scanner in = new Scanner(System.in);
    System.out.println("Please enter ID");
    id = Integer.parseInt(in.nextLine());

    System.out.println("Please enter first name");
    firstName = in.nextLine();

    System.out.println("Please enter last name");
    lastName = in.nextLine();

    System.out.println("Please enter email");
    email = in.nextLine();

    System.out.println("Please enter password");
    password = in.nextLine();

    System.out.println("Please enter contact number");
    number = in.nextLine();

    return new Customer(id, firstName, lastName, email, password, number);
  }
  private static Customer updateCustomer(Customer customer){

    String firstName;
    String lastName;
    String email;
    String password;
    String telephoneNumber;

    Scanner in = new Scanner(System.in);
    System.out.println("Updating customer with ID:" + customer.getCustomerId());
    System.out.print("Please enter first name");
    firstName = in.nextLine();
    if (firstName.equals(""))
      firstName = customer.getFirstName();

    System.out.println("Please enter last name");
    lastName = in.nextLine();
    if (lastName.equals(""))
      lastName = customer.getLastName();

    System.out.println("Please enter email");
    email = in.nextLine();
    if (email.equals(""))
      email = customer.getEmail();

    System.out.println("Please enter password");
    password = in.nextLine();
    if (password.equals(""))
      password = customer.getPassword();

    System.out.println("Please enter contact number");
    telephoneNumber = in.nextLine();
    if (telephoneNumber.equals(""))
      telephoneNumber = customer.getTelePhoneNumber();

    return new Customer(customer.getCustomerId(), firstName, lastName, email, password, telephoneNumber);
  }
  private static Product createProduct() {
    // TODO Auto-generated method stub
    int id;
    String sku;
    String description;
    double price;
    String category;

    Scanner in = new Scanner(System.in);
    System.out.println("Please enter ID");
    id = Integer.parseInt(in.nextLine());

    System.out.println("Please enter SKU");
    sku = in.nextLine();

    System.out.println("Please enter description");
    description = in.nextLine();

    System.out.println("Please enter category");
    category = in.nextLine();

    System.out.println("Please enter price");
    price = in.nextDouble();
    //    price = Integer.parseInt(in.nextLine());

    return new Product(id, sku, description, price, category);
  }
  private static Product updateProduct(Product product) {
    // TODO Auto-generated method stub
    String sku;
    String description;
    double price;
    String category;

    Scanner in = new Scanner(System.in);
    System.out.println("Updating Product with ID:" + product.getID());
    System.out.println("Please enter SKU");
    sku = in.nextLine();
    if (sku.equals(""))
      sku = product.getSKU();

    System.out.println("Please enter new description");
    description = in.nextLine();
    if (description.equals(""))
      description = product.getDescription();

    System.out.println("Please enter price");
    String strPrice = in.nextLine();
    if (strPrice.equals(""))
      price = product.getPrice();
    else
      price = Integer.parseInt(strPrice);

    System.out.println("Please enter category");
    String strCategory = in.nextLine();
    if (strCategory.equals(""))
      category = product.getCategory();
    else
      category = strCategory;

    return new Product(product.getID(), sku, description, price, category);
  }

}