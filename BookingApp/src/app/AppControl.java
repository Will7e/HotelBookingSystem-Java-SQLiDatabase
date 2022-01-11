package app;

import dataconnection.DataService;
import tableclasses.Company;
import tableclasses.Customer;
import tableclasses.Hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class AppControl {
    public String checkInDate, checkOutDate;
    public int customerId,roomId,bookingId;

    public void startMenu(Scanner scanner, DataService dataService,
                          ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Customer> customers) {
        System.out.println(" -: Booking System :-\n ");
        System.out.println("1.Book hotel\n2.Cancel Booking\n3.Search customers\n4.Add Company(Customer) \n");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                bookHotel(scanner, dataService, hotels, companies, customers);
                break;
            case 2:

                break;
            case 3:
                searchCustomers(scanner, companies, customers);
                startMenu(scanner,dataService,hotels,companies,customers);
                break;
            case 4:
                break;

        }
    }

    public boolean findAvailableRoomByCity(Scanner scanner, DataService dataService, ArrayList<Hotel> hotels) {
        hotelAvailable(hotels);
        String hotelCity = getHotelCity(scanner, hotels);
        int roomSize = getHotelRoomSize(scanner);
        System.out.println("Input check in date:");
        checkInDate = getDates(scanner);
        System.out.println("Input check out date:");
        checkOutDate = getDates(scanner);
       return dataService.findAvailableRoom(roomSize, checkOutDate, hotelCity, checkInDate);

    }

    public void hotelAvailable(ArrayList<Hotel> hotels) {
        System.out.println("Here are the list of our hotels.\n --------------");
        System.out.println("Hotel name    |        City         |    Country");
        for (Hotel hotel : hotels) {
            System.out.println("[" + hotel.name + "]       [" + hotel.city + "]             [" + hotel.country + "]");
            System.out.println();
        }

    }

    public int getHotelRoomSize(Scanner scanner) {
        int roomSize;
        System.out.println("Hotel has 3 room sizes \n[2] [3] and [4]");
        System.out.println("Which room size does customer wants?");
        roomSize = Integer.parseInt(scanner.nextLine());
        if (roomSize < 2 || roomSize > 4) {
            System.out.println("Incorrect room size. Try again.");
            getHotelRoomSize(scanner);
        }
        return roomSize;
    }

    // Get check in and check out dates
    public String getDates(Scanner scanner) {
        String date = "";
        System.out.print("Year: ");
        String year = scanner.nextLine();
        System.out.print("Month:");
        String month = scanner.nextLine();
        System.out.print("Day: ");
        String day = scanner.nextLine();
        if (!year.equalsIgnoreCase("2022")) {
            System.out.println("Current year is 2022.");
            getDates(scanner);
        } else if (!month.equalsIgnoreCase("06") && !month.equalsIgnoreCase("07")) {
            System.out.println("Our booking calendar is only available in Juni and July\n" +
                    "Error?. Months digit need to be 06 or 07.  ");
            getDates(scanner);
        } else {
            date = year + "-" + month + "-" + day;
            {
            }
        }
        return date;
    }
    //Hotel city as choice.
    public String getHotelCity(Scanner scanner, ArrayList<Hotel> hotels) {
        String hotelCity, s1;
        System.out.print("Type in hotel's city to find available room: ");
        hotelCity = scanner.nextLine();
        s1 = hotelCity.substring(0, 1).toUpperCase() + hotelCity.substring(1);

        for (int i = 0; i < hotels.size(); i++) {
            if (hotelCity.equalsIgnoreCase(hotels.get(i).city)) {
                return s1;
            }
        }
        System.out.println("\nCant find matching hotel city in database. Try again.\n");
        return getHotelCity(scanner, hotels);

    }

    public void bookHotel(Scanner scanner, DataService dataService, ArrayList<Hotel> hotels,
                          ArrayList<Company> companies, ArrayList<Customer> customers) {

       boolean check = findAvailableRoomByCity(scanner, dataService, hotels);
       if (check){
           System.out.println("Accept booking?\n1.Yes\n2.No");
           int input = Integer.parseInt(scanner.nextLine());
           switch (input) {
               case 1:
                   createBooking(dataService, scanner, companies, customers,hotels);
                   break;
               case 2:
                   startMenu(scanner, dataService, hotels, companies, customers);
                   break;
       }
        } else {
           startMenu(scanner,dataService,hotels,companies,customers);
       }
    }

    public void askCustomerInfo(DataService dataService, Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers,ArrayList<Hotel> hotels) {
        System.out.println("1.Existing customer\n2.New customer");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                searchCustomers(scanner, companies, customers);
                getCustomerIdBySearch(scanner,dataService);
                startMenu(scanner,dataService,hotels,companies,customers);
                break;
            case 2:
               int customerId = createCustomer(dataService, scanner, companies, customers);
               int calendarId = createCalendar(dataService,roomId);
               int bookingId= dataService.createBooking(customerId,calendarId);
                System.out.println(bookingId + " has been created " );
                startMenu(scanner,dataService,hotels,companies,customers);
                break;
        }

    }
    public void getCustomerIdBySearch(Scanner scanner,DataService dataService){
        System.out.println("Type in customer id for booking.");
        customerId = Integer.parseInt(scanner.nextLine());
        int calendarId = createCalendar(dataService,roomId);
        bookingId =dataService.createBooking(calendarId,customerId);
        System.out.println("A booking with " + bookingId + " has been created.");
    }

    public String addCompany(DataService dataService, Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers) {
        String name;
        System.out.print("Create company name for customer: ");
        name = scanner.nextLine();
        dataService.createCompany(name);
        return name;
    }

    public void searchCustomers(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers) {
        System.out.println("1.Search by company info \n2.Search by customer info\n");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                searchCustomerByCompany(scanner, companies, customers);
                break;
            case 2:
                searchCustomerByInfo(scanner, customers);
                break;
        }
    }

    public int searchCompany(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers) {
        System.out.print("Type in company id or name: ");
        String input = scanner.nextLine();
        for (Company company : companies) {
            if (String.valueOf(company.companyID).equalsIgnoreCase(input) || input.equalsIgnoreCase(company.companyName)) {
                return company.companyID;
            }
        }
        System.out.println("Hotel id and name does not exist in database.");

        return 0;
    }


    public void searchCustomerByCompany(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers) {
        int id = searchCompany(scanner, companies,customers);
        for (Customer customer : customers) {
            if (customer.companyID == id) {
                System.out.println(customer.customerID + " " + customer.firstName + " " + customer.lastName + " " + customer.phoneNumber + " " + customer.email);
            }
        }

    }


    public void searchCustomerByInfo(Scanner scanner, ArrayList<Customer> customers) {
        System.out.println("1.Search by name \n2. Search by phone number\n3.Search by emails \n4.Return to main");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                searchByNamePrint(scanner,customers,1);
                break;
            case 2:
                searchByNamePrint(scanner, customers,2);
                break;
            case 3:
                searchByNamePrint(scanner, customers,3);
                break;
            case 4: // Return to main menu.
        }
    }

    public int searchCustomerByName(Scanner scanner, ArrayList<Customer> customers) {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        for (Customer customer : customers) {
            if (firstName.equalsIgnoreCase(customer.firstName) && lastName.equalsIgnoreCase(customer.lastName))
                return customer.customerID;
        }
        System.out.println("Customer does not exist in database.Try again \n");
        return 0;
    }
    public void searchByNamePrint(Scanner scanner,ArrayList<Customer> customers, int printsCase){
        int customerId = 0;
        if (printsCase == 1){
            customerId=searchCustomerByName(scanner,customers);
        }else if (printsCase == 2){
            customerId=searchByPhoneNumbers(scanner,customers);
        }else if (printsCase==3){
            customerId=searchByCustomerEmail(scanner,customers);
        }
        for (Customer customer : customers){
            if (customer.customerID == customerId){
                System.out.println(customer.customerID + " " + customer.firstName + " " + customer.lastName + " " + customer.phoneNumber + " " + customer.email+"\n");
            }
        }
    }
    public int searchByPhoneNumbers(Scanner scanner,ArrayList<Customer>customers){
        System.out.print("Phone number: ");
        String input = scanner.nextLine();
        for (Customer customer: customers){
            if ( input.equalsIgnoreCase(customer.phoneNumber)){
                return customer.customerID;
            }
        }
        System.out.println("Customer does not exist in database. Try again\n");
        return 0;
    }
    public int searchByCustomerEmail(Scanner scanner, ArrayList<Customer>customers){
        System.out.print("Email address: ");
        String input = scanner.nextLine();
        for (Customer customer: customers){
            if (input.equalsIgnoreCase(customer.email)){
                return customer.customerID;
            }
        }
        System.out.println("Customer does not exist in database. Try again\n");
        return 0;
    }
    public void createBooking(DataService dataService,Scanner scanner,ArrayList<Company>companies,ArrayList<Customer>customers,ArrayList<Hotel>hotels){
        System.out.println("Type in room id above. ");
        System.out.print("Room Id: ");
        roomId = Integer.parseInt(scanner.nextLine());
        askCustomerInfo(dataService,scanner,companies,customers,hotels);
}
    public int createCalendar(DataService dataService,int roomId){
        return dataService.createCalendar(checkInDate, checkOutDate,roomId);
}

    public int createCustomer(DataService dataService,Scanner scanner,ArrayList<Company>companies,ArrayList<Customer>customers){
         customerId = 0;
        String firstName,lastName,address,city,country,phoneNumber,email,birthday;
        String name = addCompany(dataService,scanner,companies,customers);
        int companyId = dataService.createCompany(name);
        System.out.println("How many customers do you want to add? ");
        int input = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < input; i++){
            System.out.print("First name: ");
            firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            lastName= scanner.nextLine();
            System.out.print("Adress: ");
            address= scanner.nextLine();
            System.out.print("City: ");
            city= scanner.nextLine();
            System.out.print("Country: ");
            country= scanner.nextLine();
            System.out.print("Phone Number: ");
            phoneNumber= scanner.nextLine();
            System.out.print("Email: ");
            email= scanner.nextLine();
            System.out.print("Birthday: ");
            birthday= scanner.nextLine();
           customerId = dataService.createCustomer(companyId,firstName,lastName,address,city,country,phoneNumber,email,birthday);
        }
        return customerId;
}

public void deleteBooking(){

}
}





