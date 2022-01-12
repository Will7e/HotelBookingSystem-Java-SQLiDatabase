package app;

import dataconnection.DataService;
import tableclasses.Company;
import tableclasses.Customer;
import tableclasses.Hotel;
import tableclasses.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class AppControl {
    public String checkInDate, checkOutDate;
    public int customerId, roomId, bookingId;

    public void startMenu(Scanner scanner, DataService dataService,
                          ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Customer> customers, AppUI appUI,ArrayList<Room >rooms) {
        appUI.menuChoicePrint();
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 4) {
            appUI.inCorrectInput(1, 4);
            startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
        } else {
            switch (input) {
                case 1:
                    bookHotel(scanner, dataService, hotels, companies, customers, appUI,rooms);
                    startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
                    break;
                case 2:
                    deleteBooking(dataService, scanner, companies, customers, appUI, hotels,rooms);
                    startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
                    break;
                case 3:
                    searchCustomers(scanner, companies, customers, appUI, dataService, hotels,rooms);
                    startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
                    break;
                case 4:
                    addCustomerMenu(scanner, dataService, companies, customers, appUI);
                    startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
                    break;

            }
        }

    }

    public boolean findAvailableRoom(Scanner scanner, DataService dataService, ArrayList<Hotel> hotels, AppUI appUI,ArrayList<Room> rooms) {
        hotelAvailable(hotels, appUI,rooms);
        viewAmenities(dataService,scanner,appUI,hotels);
        int hotelCity = getHotelId(scanner, hotels);
        int roomSize = getHotelRoomSize(scanner, appUI);
        appUI.checkInDateText();
        checkInDate = getDates(scanner, appUI);
        appUI.checkOutDateText();
        checkOutDate = getDates(scanner, appUI);
        return dataService.findAvailableRoom(roomSize, checkOutDate, hotelCity, checkInDate);
    }
    public void hotelAvailable(ArrayList<Hotel> hotels, AppUI appUI, ArrayList<Room> rooms) {
        appUI.hotelAvailablePrint(hotels,rooms);
    }

    public int getHotelRoomSize(Scanner scanner, AppUI appUI) {
        int roomSize;
        appUI.askRoomSizePrint();
        roomSize = Integer.parseInt(scanner.nextLine());
        if (roomSize < 2 || roomSize > 4) {
            appUI.inCorrectInput(2, 4);
            getHotelRoomSize(scanner, appUI);
            return 0;
        }
        return roomSize;
    }

    // Get check in and check out dates
    public String getDates(Scanner scanner, AppUI appUI) {
        return appUI.checkInAndOut(scanner);
    }

    //Hotel city as choice.
    public int getHotelId(Scanner scanner, ArrayList<Hotel> hotels) {
        int hotelId;
        System.out.print("[Find available room] Hotel ID ->: ");
        hotelId = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < hotels.size(); i++) {
            if (hotelId == hotels.get(i).hotelId) {
                return hotelId;
            }
        }
        System.out.println("\nCant find matching hotel city in database. Try again.\n");
        return getHotelId(scanner, hotels);

    }

    public void bookHotel(Scanner scanner, DataService dataService, ArrayList<Hotel> hotels,
                          ArrayList<Company> companies, ArrayList<Customer> customers, AppUI appUI,ArrayList<Room> rooms) {

        boolean check = findAvailableRoom(scanner, dataService, hotels, appUI,rooms);
        if (check) {
            // Can add option method here...

            System.out.println("Continue to book?\n1.Yes\n2.No\n");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    createBooking(dataService, scanner, companies, customers, hotels, appUI,rooms);
                    break;
                case 2:
                    startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
                    break;
            }
        } else {
            startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
        }
    }

    public void askCustomerInfo(DataService dataService, Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers,
                                ArrayList<Hotel> hotels, AppUI appUI,ArrayList<Room >rooms) {
        System.out.println("1.Existing customer\n2.New customer\n");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                searchCustomers(scanner, companies, customers, appUI, dataService, hotels,rooms);
                getCustomerIdBySearch(scanner, dataService, appUI);
                startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
                break;
            case 2:
                createBookingWithNewCustomer(dataService, scanner, companies, customers, appUI);
                startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
                break;
        }

    }

    public void getCustomerIdBySearch(Scanner scanner, DataService dataService, AppUI appUI) {
        System.out.print("Type in customer id for booking: ");
        customerId = Integer.parseInt(scanner.nextLine());
        int calendarId = createCalendar(dataService, roomId);
        bookingId = dataService.createBooking(calendarId, customerId);
        appUI.bookingCreatedMess(customerId, bookingId);

    }

    public String addCompany(DataService dataService, Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers) {
        String name;
        System.out.print("Create company name for customer: ");
        name = scanner.nextLine();
        dataService.createCompany(name);
        return name;
    }

    public void searchCustomers(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers, AppUI appUI,
                                DataService dataService, ArrayList<Hotel> hotels,ArrayList<Room >rooms) {
        System.out.println("1.Search by bookingId\n2.Search by company info \n3.Search by customer info\n4.Return main menu");
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 4) {
            appUI.inCorrectInput(1, 4);
            searchCustomers(scanner, companies, customers, appUI, dataService, hotels,rooms);
        } else {
            switch (input) {
                case 1:
                    viewBookingByBookingId(scanner, dataService, appUI, companies, customers, hotels,rooms);
                    break;
                case 2:
                    searchCustomerByCompany(scanner, companies, customers);
                    appUI.viewBookingByCustomerId(scanner, dataService);
                    break;
                case 3:
                    searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies,rooms);
                    appUI.viewBookingByCustomerId(scanner, dataService);
                    break;
                case 4:
                    break;
            }

        }


    }

    public int searchCompany(Scanner scanner, ArrayList<Company> companies) {
        System.out.print("Company ID or Name ->: ");
        String input = scanner.nextLine();
        for (Company company : companies) {
            if (String.valueOf(company.companyID).equalsIgnoreCase(input) || input.equalsIgnoreCase(company.companyName)) {
                return company.companyID;
            } else {
                System.out.println("Hotel ID or Name does not exist in system.");
            }
        }
        return 0;
    }


    public void searchCustomerByCompany(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers) {
        int id = searchCompany(scanner, companies);
        System.out.println("CustomerID      Name           Phone Number          Email            Birthday");
        for (Customer customer : customers) {
            if (customer.companyID == id) {
                System.out.println("   " + customer.customerID + "     |     " + customer.firstName + "  "
                        + customer.lastName + " |  " + customer.phoneNumber + "  |  " + customer.email +"    |   " + "    |    ");
            }
        }

    }


    public void searchCustomerByInfo(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                     DataService dataService, ArrayList<Hotel> hotels, ArrayList<Company> companies,ArrayList<Room >rooms) {
        appUI.searchCustomerChoicePrint();
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 4) {
            appUI.inCorrectInput(1, 4);
            searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies,rooms);
        } else {
            switch (input) {
                case 1:searchByNamePrint(scanner, customers, 1,dataService, hotels, companies, appUI,rooms);
                    break;
                case 2:
                    searchByNamePrint(scanner, customers, 2, dataService, hotels, companies, appUI,rooms);
                    break;
                case 3:
                    searchByNamePrint(scanner, customers, 3, dataService, hotels, companies, appUI,rooms);
                    break;
                case 4:
                    searchByNamePrint(scanner, customers, 4, dataService, hotels, companies, appUI,rooms);
                    break;
                case 5:
                    startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
                    break;
            }
        }
    }

    public int searchCustomerByName(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                    DataService dataService, ArrayList<Hotel> hotels, ArrayList<Company> companies,ArrayList<Room >rooms) {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        for (Customer customer : customers) {
            if (firstName.equalsIgnoreCase(customer.firstName) && lastName.equalsIgnoreCase(customer.lastName))
                return customer.customerID;
        }
        System.out.println("Customer does not exist in database.\n");
        searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies,rooms);
        return 0;
    }


    public void searchByNamePrint(Scanner scanner, ArrayList<Customer> customers, int printsCase, DataService dataService,
                                  ArrayList<Hotel> hotels, ArrayList<Company> companies, AppUI appUI,ArrayList<Room >rooms) {
        int customerId = 0;
        if (printsCase == 1) {
            customerId = searchByCustomerId(scanner, customers, appUI, dataService, hotels, companies,rooms);
        }
        else if (printsCase == 2) {
            customerId = searchCustomerByName(scanner, customers, appUI, dataService, hotels, companies,rooms);
        } else if (printsCase == 3) {
            customerId = searchByPhoneNumbers(scanner, customers, appUI, dataService, hotels, companies,rooms);
        } else if (printsCase == 4) {
            customerId = searchByCustomerEmail(scanner, customers, appUI, dataService, hotels, companies,rooms);
        }
        for (Customer customer : customers) {
            if (customer.customerID == customerId) {
                System.out.println(customer.customerID + " " + customer.firstName + " " + customer.lastName + " " + customer.phoneNumber + " " + customer.email + "\n");
            }
        }
        startMenu(scanner, dataService, hotels, companies, customers, appUI,rooms);
    }


    public int searchByCustomerId(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                    DataService dataService, ArrayList<Hotel> hotels, ArrayList<Company> companies,ArrayList<Room >rooms) {
        System.out.print("Customer ID: ");
        String input = scanner.nextLine();
        for (Customer customer : customers) {
            if (input.equalsIgnoreCase(String.valueOf(customer.customerID))) {
                return customer.customerID;
            }
        }
        System.out.println("Customer ID does not exist in database.\n");
        searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies,rooms);
        return 0;
    }

    public int searchByPhoneNumbers(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                    DataService dataService,ArrayList<Hotel> hotels, ArrayList<Company> companies,ArrayList<Room >rooms) {
        System.out.print("Phone numbers: ");
        String input = scanner.nextLine();
        for (Customer customer : customers) {
            if (input.equalsIgnoreCase(customer.phoneNumber)) {
                return customer.customerID;
            }
        }
        System.out.println("Phone numbers does not exist in database.\n");
        searchCustomerByInfo(scanner, customers, appUI, dataService,hotels,companies,rooms);
        return 0;
    }

    public int searchByCustomerEmail(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                     DataService dataService,ArrayList<Hotel> hotels, ArrayList<Company> companies,ArrayList<Room >rooms) {
        System.out.print("Email address: ");
        String input = scanner.nextLine();
        for (Customer customer : customers) {
            if (input.equalsIgnoreCase(customer.email)) {
                return customer.customerID;
            }
        }
        System.out.println("Email address does not exist in database.\n");
        searchCustomerByInfo(scanner, customers, appUI, dataService,hotels,companies,rooms);
        return 0;
    }

    public void createBooking(DataService dataService, Scanner scanner, ArrayList<Company> companies,
                              ArrayList<Customer> customers, ArrayList<Hotel> hotels, AppUI appUI,ArrayList<Room >rooms) {
        System.out.println("Type in room id above. ");
        System.out.print("Room Id: ");
        roomId = Integer.parseInt(scanner.nextLine());
        //Or can add option here...

        askCustomerInfo(dataService, scanner, companies, customers, hotels, appUI,rooms);
    }

    public int createCalendar(DataService dataService, int roomId) {
        return dataService.createCalendar(checkInDate, checkOutDate, roomId);
    }

    public int createCustomer(DataService dataService, Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers, AppUI appUI) {
        customerId = 0;
        String name = addCompany(dataService, scanner, companies, customers);
        int companyId = dataService.createCompany(name);
        System.out.println("How many customers do you want to add? ");
        int input = Integer.parseInt(scanner.nextLine());
        System.out.println();
        for (int i = 0; i < input; i++) {
            customerId = appUI.fyllCustomerInfo(i, dataService, scanner, companyId);
            System.out.println();
        }
        return customerId;
    }


    public void deleteBooking(DataService dataService, Scanner scanner, ArrayList<Company> companies,
                              ArrayList<Customer> customers, AppUI appUI, ArrayList<Hotel> hotels,ArrayList<Room >rooms) {
        searchCustomers(scanner, companies, customers, appUI, dataService, hotels,rooms);
        System.out.print("Calendar ID -> : ");
        int calendarId = Integer.parseInt(scanner.nextLine());
        System.out.print("Booking ID -> : ");
        int bookingId = Integer.parseInt(scanner.nextLine());
        System.out.println("1.Yes[Cancel Booking]\n2.Return main menu ");
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 2) {
            appUI.inCorrectInput(1, 2);
            deleteBooking(dataService, scanner, companies, customers, appUI, hotels,rooms);
        } else {
            switch (input) {
                case 1:
                    dataService.deleteBooking(bookingId, calendarId);
                    break;
                case 2:
                    break;
            }
        }

    }


    public void addCustomerMenu(Scanner scanner, DataService dataService, ArrayList<Company> companies, ArrayList<Customer> customers, AppUI appUI) {
        System.out.println("1.Add customers\n2.Return main menu.");
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 2) {
            appUI.inCorrectInput(1, 2);
            addCustomerMenu(scanner, dataService, companies, customers, appUI);
        } else {
            switch (input) {
                case 1:
                    createCustomer(dataService, scanner, companies, customers, appUI);
                    break;
                case 2:
                    break;
            }
        }

    }


    public void createBookingWithNewCustomer(DataService dataService, Scanner scanner, ArrayList<Company> companies,
                                             ArrayList<Customer> customers, AppUI appUI) {
        int customerId = createCustomer(dataService, scanner, companies, customers, appUI);
        int calendarId = createCalendar(dataService, roomId);
        int bookingId = dataService.createBooking(customerId, calendarId);
        appUI.bookingCreatedMess(customerId, bookingId);
    }


    public void viewBookingByBookingId(Scanner scanner, DataService dataService, AppUI appUI,
                                       ArrayList<Company> companies, ArrayList<Customer> customers,
                                       ArrayList<Hotel> hotels,ArrayList<Room >rooms) {
        int bookingId;
        System.out.print("Booking ID -> : ");
        bookingId = Integer.parseInt(scanner.nextLine());
        boolean check = dataService.viewBookingById(bookingId, appUI.queryViewByBookingId());
        if (check) {
            System.out.println("No booking found with this Booking ID. Return main menu...");
            startMenu(scanner,dataService,hotels,companies,customers,appUI,rooms);
        }
    }

    public void viewAmenities(DataService dataService,Scanner scanner,AppUI appUI,ArrayList<Hotel>hotels) {
        int hotelId,input;
        System.out.println("1.Continue to book\n2.Check Hotel amenities\n3.Check prices\n4.Search by distans\n");
        input= Integer.parseInt(scanner.nextLine());
        if (input<1||input>4){
            appUI.inCorrectInput(1,4);
            viewAmenities(dataService,scanner,appUI,hotels);
        }
        switch (input){
            case 1:
                break;
            case 2:hotelId = askForHotelId(scanner);
                dataService.viewAmenities(hotelId);
                viewAmenities(dataService,scanner,appUI,hotels);
                break;
            case 3:hotelId = askForHotelId(scanner);
                priceOrder(scanner,dataService,appUI,hotelId);
                viewAmenities(dataService,scanner,appUI,hotels);
                break;
            case 4:hotelDistance(hotels,scanner,appUI);
                viewAmenities(dataService,scanner,appUI,hotels);
                break;
        }
    }
    public void priceOrder(Scanner scanner,DataService dataService,AppUI appUI,int hotelId){
        String lowToHigh = "ASC";
        String highToLow = "DESC";
        System.out.println("1.Price low to high \n2.Price high to low");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input){
            case 1:dataService.viewHotelPrice(hotelId,appUI.viewPriceQuery(lowToHigh));
            break;
            case 2:dataService.viewHotelPrice(hotelId,appUI.viewPriceQuery(highToLow));
            break;
        }
    }

    public void hotelDistance(ArrayList<Hotel>hotels,Scanner scanner,AppUI appUI){
        System.out.println("1.Distance to beach\n2.Distance to center");
        int input = Integer.parseInt(scanner.nextLine());
        System.out.print("Distance (meter) -> :");
        int distance = Integer.parseInt(scanner.nextLine());
        if (input<1 || input >2){
            appUI.inCorrectInput(1,2);
            hotelDistance(hotels,scanner,appUI);
        }
        switch (input){
            case 1:printHotelDistance(hotels,distance,1);
                break;
            case 2:printHotelDistance(hotels,distance,2);
                break;
        }

    }
    public void printHotelDistance(ArrayList<Hotel>hotels,int distance,int i){
        System.out.println();
        System.out.println("Hotels available within " + distance + " meters.");
        System.out.println("Hotel ID    Hotel Name      Distance");
        for (Hotel hotel:hotels){
            if (distance >= hotel.distanceToBeach && i ==1 ){

                System.out.println(" "+hotel.hotelId + "         " + hotel.name + "    " + hotel.distanceToBeach + " meters to beach.");
            }else if (distance >= hotel.distanceToCenter && i==2){
                System.out.println(hotel.hotelId + "         " + hotel.name + "     " + hotel.distanceToCenter +" meters to center.");
            }
        }
        System.out.println();
        System.out.println("[ONLY IF no result] -> No hotels available within the distance of beach or center]\n");
    }

    public int askForHotelId(Scanner scanner){
        System.out.print("Hotel ID ->: ");
        return Integer.parseInt(scanner.nextLine());
    }
}





