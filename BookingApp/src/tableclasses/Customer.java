package tableclasses;

public class Customer {
    public int customerID;
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String country;
    public String phoneNumber;
    public String email;
    public String birthday;
    public int companyID;

    public Customer(int customerID, String firstName, String lastName, String address, String city, String country,
                    String phoneNumber, String email, String birthday, int companyID) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.companyID = companyID;
    }
}
