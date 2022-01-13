package app;

import dataconnection.DataService;
import tableclasses.Customer;
import tableclasses.Hotel;
import tableclasses.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class AppUI {
    public void introPrint(){
        System.out.println(" -: Booking System :-\n ");
    }
    public void menuChoicePrint(){
        System.out.println("[1].Book Hotel\n[2].Delete Booking\n[3].Informations Search\n" +
                "[4].Change Customer Infos\n[5].Add Company(Customer)\n[6].Edit Company\n[7].View/Add/Edit Booking's Options\n[8].Exit\n");
    }

    public void hotelAvailablePrint(ArrayList<Hotel> hotels, ArrayList<Room>rooms){
        System.out.println("Here are the list of our hotels.\n --------------");
        System.out.println("Hotel ID  |  Hotel name    |        City         |    Country   ");
        for (Hotel hotel : hotels) {
            System.out.println("  ["+hotel.hotelId+"]       [" + hotel.name + "]          [" + hotel.city + "]            [" + hotel.country + "]");
            System.out.println();
        }
    }
    public void searchCustomerChoicePrint(){
        System.out.println("[1]Search by customer ID\n[2].Search by name \n[3].Search by phone number\n[4].Search by emails \n[5].Return to main display\n");
    }
    public void askRoomSizePrint(){
        System.out.println("Hotels has 3 room sizes. \n[2] [3] and [4]");
        System.out.println("Which room size does customer wants?\n");
    }

    public void bookingCreatedMess(int customerId,int bookingId,ArrayList<Customer> customers,DataService dataService){
        System.out.println("Thank you for booking. Here is your booking informations:\n");
        dataService.viewBookingById(bookingId,queryViewByBookingId());
    }
    public void checkInDateText(){
        System.out.println("Check in date ->:");

    }
    public void checkOutDateText(){
        System.out.println("Check out date ->:");

    }
public void calendarInputErrorMess(){
    System.out.println("Our booking calendar is only available for Juni and July\n" +
            "Error?. Months digit need to be 06 or 07.\n");

}
public int fyllCustomerInfo(int i, DataService dataService, Scanner scanner,int companyId){
    System.out.println("Customer: " + (i+1));
    System.out.print("First name: ");
    String firstName = scanner.nextLine();
    System.out.print("Last Name: ");
    String lastName = scanner.nextLine();
    System.out.print("Adress: ");
    String address = scanner.nextLine();
    System.out.print("City: ");
    String city = scanner.nextLine();
    System.out.print("Country: ");
    String country = scanner.nextLine();
    System.out.print("Phone Number: ");
    String phoneNumber = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("Birthday: ");
    String birthday = scanner.nextLine();
    return dataService.createCustomer(companyId, firstName, lastName, address, city, country, phoneNumber, email, birthday);
}

public void inCorrectInput(int i, int j){
    System.out.println("Incorrect input. [Correct "+ i +" -> " + j+"]\n");

}
public String checkInAndOut(Scanner scanner){
    String date;
    System.out.print("Year: ");
    String year = scanner.nextLine();
    System.out.print("Month:");
    String month = scanner.nextLine();
    System.out.print("Day: ");
    String day = scanner.nextLine();
    if (!year.equalsIgnoreCase("2022")) {
        System.out.println("Current year is 2022.");
        checkInAndOut(scanner);
        return "";
    } else if (!month.equalsIgnoreCase("06") && !month.equalsIgnoreCase("07")) {
        calendarInputErrorMess();
        checkInAndOut(scanner);
        return "";
    } else {
        date = year + "-" + month + "-" + day;
        {
        }
    }
    return date;
}
    public void viewBookingByCustomerId(Scanner scanner,DataService dataService){
        int customerId;
        System.out.print("[Find booking infos] Customer ID ->: ");
        customerId = Integer.parseInt(scanner.nextLine());
       boolean check = dataService.viewBookingById(customerId, queryViewByCustomerId());
        System.out.println();
       if (check){
           System.out.println("Booking not found by this customer ID\n");
       }

    }

    public String queryViewByBookingId(){
        return "SELECT Customer.First_Name, Customer.Last_Name, Booking.Booking_ID, Calendar.Calendar_ID, " +
                "Calendar.Room_ID, Room.Room_Name, Hotel.Hotel_Name, Calendar.CheckIn_Date, Calendar.CheckOut_Date \n" +
                "FROM Booking \n" +
                "LEFT JOIN Customer ON Customer.Customer_ID = Booking.Customer_ID \n" +
                "INNER JOIN Calendar ON Calendar.Calendar_ID = Booking.Calendar_ID \n" +
                "INNER JOIN Room ON Room.Room_ID = Calendar.Room_ID \n" +
                "INNER JOIN Hotel ON Hotel.Hotel_ID = Room.Hotel_ID \n" +
                "WHERE Booking.Booking_ID = ?";
    }
    public String queryViewByCustomerId(){
        return "SELECT Customer.First_Name, Customer.Last_Name, Booking.Booking_ID, Calendar.Calendar_ID," +
                "Calendar.Room_ID, Room.Room_Name, Hotel.Hotel_Name, Calendar.CheckIn_Date, Calendar.CheckOut_Date\n" +
                "FROM Booking \n" +
                "INNER JOIN Customer ON Customer.Customer_ID = Booking.Customer_ID \n" +
                "INNER JOIN Calendar ON Calendar.Calendar_ID = Booking.Calendar_ID \n" +
                "INNER JOIN Room ON Room.Room_ID = Calendar.Room_ID \n" +
                "INNER JOIN Hotel ON Hotel.Hotel_ID = Room.Hotel_ID \n" +
                "WHERE Customer.Customer_ID = ?";
    }
    public String viewPriceQuery(String order){
        return "SELECT Hotel.Hotel_ID,Hotel.Hotel_Name,Room.Room_ID,Room.Room_Size,Room.Price FROM Hotel\n" +
                "LEFT JOIN Room ON Room.Hotel_ID = Hotel.Hotel_ID\n" +
                "WHERE Hotel.Hotel_ID = ? \n" +
                "ORDER BY Room.Price "+order+"";
    }
    public String getCustomerNameById(ArrayList<Customer> customers, int customerId){
        String name,firstName,lastName;
        for (Customer customer: customers){
            if (customer.customerID == customerId){
                firstName = customer.firstName;
                lastName = customer.lastName;
                name = firstName + " " +lastName;
                return name;
            }
        }
        return "";
    }

    public void searchCustomerMenuChoice(){
        System.out.println("[1].Search booking by customer ID\n[2].Search by booking ID\n[3].Search by company infos \n[4].Search by customer infos\n[5].Return main display\n");
    }
    public void changeName(Scanner scanner,DataService dataService){
        System.out.print("Customer ID ->: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New first name: ");
        String firstName = scanner.nextLine();
        System.out.print("New last name: ");
        String lastName = scanner.nextLine();
        dataService.updateName(firstName,lastName,id);
    }
    public void changeAdress(Scanner scanner,DataService dataService){
        System.out.print("Customer ID ->: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New Adress: ");
        String adress = scanner.nextLine();
        System.out.print("New City: ");
        String city = scanner.nextLine();
        System.out.print("New Country: ");
        String country = scanner.nextLine();
        dataService.updateAdress(adress,city,country,id);

    }
    public void changePhoneOrEmail(Scanner scanner,DataService dataService,String co){
        System.out.print("Customer ID ->: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New "+co+": ");
        String adress = scanner.nextLine();
        dataService.updatePhoneNumbersOrEmail(co,adress,id);


    }
    public int getCustomerId(Scanner scanner){
        System.out.print("[Delete] Customer ID ->: ");
        return Integer.parseInt(scanner.nextLine());
    }
    public void optionsChoice(){
        System.out.println("[1].Extra bed\n[2].Add 2 meals\n[3].Add 3 meals\n");
    }
}





