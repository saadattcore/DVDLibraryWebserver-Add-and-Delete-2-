public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String telePhoneNumber;
    private int CustomerAddressId;

    Customer(int customerId, String firstName, String secondName, String email, String password, String telePhone){
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = secondName;
        this.email = email;
        this.password = password;
        this.telePhoneNumber = telePhone;
    }



    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelePhoneNumber() {
        return telePhoneNumber;
    }

    public void setTelePhoneNumber(String telePhoneNumber) {
        this.telePhoneNumber = telePhoneNumber;
    }

    public int getCustomerAddressId() {
        return CustomerAddressId;
    }

    public void setCustomerAddressId(int customerAddressId) {
        CustomerAddressId = customerAddressId;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    private CustomerAddress customerAddress;





}