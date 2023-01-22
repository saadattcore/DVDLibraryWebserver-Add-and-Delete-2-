public class Customer {
    private int customerId;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String telePhoneNumber;
    private int CustomerAddressId;

    Customer(int customerId,String firstName, String secondName,String email, String passowrd,String telePhone){
        this.customerId = customerId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = passowrd;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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