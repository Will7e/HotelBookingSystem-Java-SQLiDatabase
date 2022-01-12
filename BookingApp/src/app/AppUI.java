package app;

import dataconnection.DataService;
import tableclasses.Hotel;
import tableclasses.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class AppUI {
    public void introPrint(){
        System.out.println(" -: Booking System :-\n ");
    }
    public void menuChoicePrint(){
        System.out.println("\n1.Book hotel\n2.Delete Booking\n3.Informations Search\n4.Add Company(Customer) \n");
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
        System.out.println("1.Search by customer ID\n2.Search by name \n3.Search by phone number\n4.Search by emails \n5.Return to main");
    }
    public void askRoomSizePrint(){
        System.out.println("Hotels has 3 room sizes. \n[2] [3] and [4]");
        System.out.println("Which room size does customer wants?");
    }

    public void bookingCreatedMess(int customerId,int bookingId){
        System.out.println("Congrats a booking with customer id: " + customerId + " Booking id: " + bookingId + " has been created.");
    }
    public void checkInDateText(){
        System.out.println("Input check in date:");

    }
    public void checkOutDateText(){
        System.out.println("Input check out date:");

    }
public void calendarInputErrorMess(){
    System.out.println("Our booking calendar is only available for Juni and July\n" +
            "Error?. Months digit need to be 06 or 07.  ");

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
    System.out.println("Incorrect input. [Correct "+ i +" -> " + j+"]");

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
        System.out.print("Type in customer id to find booking: ");
        customerId = Integer.parseInt(scanner.nextLine());
        dataService.viewBookingById(customerId, queryViewByCustomerId());

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
                "WHERE Hotel.Hotel_ID = 3\n" +
                "ORDER BY Room.Price "+order+"";
    }
    public void printHotelDistance(){

    }

}
