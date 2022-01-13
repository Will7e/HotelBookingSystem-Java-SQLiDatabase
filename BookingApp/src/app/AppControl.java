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
    public int customerId, roomId, bookingId, id;

    public void startMenu(Scanner scanner, DataService dataService,
                          ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Customer> customers,
                          AppUI appUI, ArrayList<Room> rooms) {
        appUI.menuChoicePrint();
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 8) {
            appUI.inCorrectInput(1, 8);
            startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
        } else {
            switch (input) {
                case 1:
                    bookHotel(scanner, dataService, hotels, companies, customers, appUI, rooms);
                    break;
                case 2:
                    deleteBooking(dataService, scanner, companies, customers, appUI, hotels, rooms);
                    break;
                case 3:
                    searchCustomers(scanner, companies, customers, appUI, dataService, hotels, rooms);
                    break;
                case 4:
                    changeInfo(scanner, companies, customers, dataService, hotels, appUI, rooms);
                    break;
                case 5:
                    addCustomerMenu(scanner, dataService, companies, customers, appUI);
                    break;
                case 6:
                    editCompanyById(scanner, companies, customers, dataService, hotels, appUI, rooms);
                    break;
                case 7:
                    viewBookingOption(scanner, dataService, hotels, companies, customers, appUI, rooms);
                    break;
                case 8:System.exit(0);
                    break;

            }
            startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
        }


    }

    public boolean findAvailableRoom(Scanner scanner, DataService dataService,
                                     ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Customer> customers,
                                     AppUI appUI, ArrayList<Room> rooms) {
        hotelAvailable(hotels, appUI, rooms);
        viewAmenities(scanner, dataService, hotels, companies, customers, appUI, rooms);
        int hotelCity = getHotelId(scanner, hotels);
        int roomSize = getHotelRoomSize(scanner, appUI);
        appUI.checkInDateText();
        checkInDate = getDates(scanner, appUI);
        appUI.checkOutDateText();
        checkOutDate = getDates(scanner, appUI);
        return dataService.findAvailableRoom(roomSize, checkOutDate, hotelCity, checkInDate);
    }

    public void hotelAvailable(ArrayList<Hotel> hotels, AppUI appUI, ArrayList<Room> rooms) {
        appUI.hotelAvailablePrint(hotels, rooms);
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
                          ArrayList<Company> companies, ArrayList<Customer> customers, AppUI appUI, ArrayList<Room> rooms) {

        boolean check = findAvailableRoom(scanner, dataService, hotels, companies, customers, appUI, rooms);
        System.out.println();
        if (check) {
            System.out.println("Accept booking...\n[1].Yes\n[2].No\n");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    createBooking(dataService, scanner, companies, customers, hotels, appUI, rooms);
                    break;
                case 2:
                    startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
                    break;
            }
        } else {
            startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
        }
    }

    public void askCustomerInfo(DataService dataService, Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers,
                                ArrayList<Hotel> hotels, AppUI appUI, ArrayList<Room> rooms) {
        System.out.println("[1].Existing customer\n[2].New customer\n");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                searchCustomersForBook(scanner, companies, customers, appUI, dataService, hotels, rooms);
                getCustomerIdBySearch(scanner, dataService, appUI, customers);
                startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
                break;
            case 2:
                createBookingWithNewCustomer(dataService, scanner, companies, customers, appUI);
                startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
                break;
        }

    }

    public void getCustomerIdBySearch(Scanner scanner, DataService dataService, AppUI appUI, ArrayList<Customer> customers) {
        System.out.print("[Booking]...\nRoom ID->: ");
        customerId = Integer.parseInt(scanner.nextLine());
        int calendarId = createCalendar(dataService, roomId);
        bookingId = dataService.createBooking(calendarId, customerId);
        addBookingOptions(bookingId, scanner, dataService);
        appUI.bookingCreatedMess(customerId, bookingId, customers,dataService);

    }

    public String addCompany(DataService dataService, Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers) {
        String name;
        System.out.print("Create customers company name: ");
        name = scanner.nextLine();
        dataService.createCompany(name);
        return name;
    }

    public void searchCustomers(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers, AppUI appUI,
                                DataService dataService, ArrayList<Hotel> hotels, ArrayList<Room> rooms) {
        appUI.searchCustomerMenuChoice();
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 4) {
            appUI.inCorrectInput(1, 4);
            searchCustomers(scanner, companies, customers, appUI, dataService, hotels, rooms);
        } else {
            switch (input) {
                case 1:appUI.viewBookingByCustomerId(scanner,dataService);
                    break;
                case 2:
                    viewBookingByBookingId(scanner, dataService, appUI, companies, customers, hotels, rooms);
                    break;
                case 3:
                    searchCustomerByCompany(scanner, companies, customers, dataService, hotels, appUI, rooms);
                    break;
                case 4:
                    searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies, rooms);
                    break;
                case 5:
                    break;
            }

        }


    }

    public int searchCompany(Scanner scanner, ArrayList<Company> companies, AppUI appUI,
                             DataService dataService, ArrayList<Hotel> hotels, ArrayList<Room> rooms, ArrayList<Customer> customers, int cases) {
        System.out.print("Company ID or Name ->: ");
        String input = scanner.nextLine();
        for (Company company : companies) {
            if (String.valueOf(company.companyID).equalsIgnoreCase(input) || input.equalsIgnoreCase(company.companyName)) {
                return company.companyID;
            } else {
                if (cases == 1) {
                    System.out.println("Company ID or Name does not exist in system.");
                    searchCustomers(scanner, companies, customers, appUI, dataService, hotels, rooms);
                } else if (cases == 2) {
                    System.out.println("Hotel ID or Name does not exist in system.");
                    searchCustomersForBook(scanner, companies, customers, appUI, dataService, hotels, rooms);
                }
                return 0;
            }
        }
        System.out.println();
        return 0;
    }


    public void searchCustomerByCompany(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers,
                                        DataService dataService, ArrayList<Hotel> hotels, AppUI appUI, ArrayList<Room> rooms) {
        id = searchCompany(scanner, companies, appUI, dataService, hotels, rooms, customers, 1);
        for (Customer customer : customers) {
            if (customer.companyID == id) {
                System.out.println("CustomerID      Name           Phone Number          Birthday            Email");
                System.out.println("   " + customer.customerID + "     |     " + customer.firstName + "  "
                        + customer.lastName + " |  " + customer.phoneNumber + "  |  " + customer.birthday + "    |   " + customer.email + "");
            }
        }
        System.out.println();
    }


    public void searchCustomerByInfo(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                     DataService dataService, ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Room> rooms) {
        appUI.searchCustomerChoicePrint();
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 4) {
            appUI.inCorrectInput(1, 4);
            searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies, rooms);
        } else {
            switch (input) {
                case 1:
                    searchByNamePrint(scanner, customers, 1, dataService, hotels, companies, appUI, rooms);
                    break;
                case 2:
                    searchByNamePrint(scanner, customers, 2, dataService, hotels, companies, appUI, rooms);
                    break;
                case 3:
                    searchByNamePrint(scanner, customers, 3, dataService, hotels, companies, appUI, rooms);
                    break;
                case 4:
                    searchByNamePrint(scanner, customers, 4, dataService, hotels, companies, appUI, rooms);
                    break;
                case 5:
                    startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
                    break;
            }
        }
    }

    public int searchCustomerByName(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                    DataService dataService, ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Room> rooms) {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        for (Customer customer : customers) {
            if (firstName.equalsIgnoreCase(customer.firstName) && lastName.equalsIgnoreCase(customer.lastName)) {
                System.out.println("CustomerID    Name             Phone Number         Birthday        Email");
                return customer.customerID;
            }
        }
        System.out.println("Customer does not exist in database.\n");
        searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies, rooms);
        return 0;
    }


    public void searchByNamePrint(Scanner scanner, ArrayList<Customer> customers, int printsCase, DataService dataService,
                                  ArrayList<Hotel> hotels, ArrayList<Company> companies, AppUI appUI, ArrayList<Room> rooms) {
        int customerId = 0;
        if (printsCase == 1) {
            customerId = searchByCustomerId(scanner, customers, appUI, dataService, hotels, companies, rooms);
        } else if (printsCase == 2) {
            customerId = searchCustomerByName(scanner, customers, appUI, dataService, hotels, companies, rooms);
        } else if (printsCase == 3) {
            customerId = searchByPhoneNumbers(scanner, customers, appUI, dataService, hotels, companies, rooms);
        } else if (printsCase == 4) {
            customerId = searchByCustomerEmail(scanner, customers, appUI, dataService, hotels, companies, rooms);
        }
        for (Customer customer : customers) {
            if (customer.customerID == customerId) {
                System.out.println("    " + customer.customerID + "      " + customer.firstName + " " + customer.lastName +
                        "     " + customer.phoneNumber + "       " + customer.birthday + "     " + customer.email + "\n");
            }
        }

    }


    public int searchByCustomerId(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                  DataService dataService, ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Room> rooms) {
        System.out.print("Customer ID: ");
        String input = scanner.nextLine();
        for (Customer customer : customers) {
            if (input.equalsIgnoreCase(String.valueOf(customer.customerID))) {
                System.out.println("CustomerID    Name             Phone Number         Birthday        Email");
                return customer.customerID;
            }
        }
        System.out.println("Customer ID does not exist in database.\n");
        searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies, rooms);
        return 0;
    }

    public int searchByPhoneNumbers(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                    DataService dataService, ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Room> rooms) {
        System.out.print("Phone numbers: ");
        String input = scanner.nextLine();
        for (Customer customer : customers) {
            if (input.equalsIgnoreCase(customer.phoneNumber)) {
                System.out.println("CustomerID    Name             Phone Number         Birthday        Email");
                return customer.customerID;
            }
        }
        System.out.println("Phone numbers does not exist in database.\n");
        searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies, rooms);
        return 0;
    }

    public int searchByCustomerEmail(Scanner scanner, ArrayList<Customer> customers, AppUI appUI,
                                     DataService dataService, ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Room> rooms) {
        System.out.print("Email address: ");
        String input = scanner.nextLine();
        for (Customer customer : customers) {
            if (input.equalsIgnoreCase(customer.email)) {
                System.out.println("CustomerID    Name             Phone Number         Birthday        Email");
                return customer.customerID;
            }
        }
        System.out.println("Email address does not exist in database.\n");
        searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies, rooms);
        return 0;
    }

    public void createBooking(DataService dataService, Scanner scanner, ArrayList<Company> companies,
                              ArrayList<Customer> customers, ArrayList<Hotel> hotels, AppUI appUI, ArrayList<Room> rooms) {
        System.out.print("[Pick your room]\nRoom ID ->: ");
        roomId = Integer.parseInt(scanner.nextLine());
        System.out.println();
        askCustomerInfo(dataService, scanner, companies, customers, hotels, appUI, rooms);
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
                              ArrayList<Customer> customers, AppUI appUI, ArrayList<Hotel> hotels, ArrayList<Room> rooms) {
        searchCustomers(scanner, companies, customers, appUI, dataService, hotels, rooms);
        System.out.print("[Cancel booking]\nCalendar ID -> : ");
        int calendarId = Integer.parseInt(scanner.nextLine());
        System.out.print("Booking ID -> : ");
        int bookingId = Integer.parseInt(scanner.nextLine());
        System.out.println("[1].Yes[Cancel Booking]\n[2].Return main display\n");
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 2) {
            appUI.inCorrectInput(1, 2);
            deleteBooking(dataService, scanner, companies, customers, appUI, hotels, rooms);
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
        System.out.println("[1].Add customers\n[2].Return main display\n");
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

        addBookingOptions(bookingId, scanner, dataService);

        appUI.bookingCreatedMess(customerId, bookingId, customers,dataService);
    }


    public void viewBookingByBookingId(Scanner scanner, DataService dataService, AppUI appUI,
                                       ArrayList<Company> companies, ArrayList<Customer> customers,
                                       ArrayList<Hotel> hotels, ArrayList<Room> rooms) {
        int bookingId;
        System.out.print("Booking ID -> : ");
        bookingId = Integer.parseInt(scanner.nextLine());
        boolean check = dataService.viewBookingById(bookingId, appUI.queryViewByBookingId());
        if (check) {
            System.out.println("No booking found with this Booking ID. Return main display...\n");
            startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
        }
    }

    public void viewAmenities(Scanner scanner, DataService dataService,
                              ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Customer> customers,
                              AppUI appUI, ArrayList<Room> rooms) {
        int hotelId, input;
        System.out.println("[1].Continue to book\n[2].Check Hotel amenities\n[3].Check prices\n[4].Search by distance\n[5].Return main display");
        input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 5) {
            appUI.inCorrectInput(1, 5);
            viewAmenities(scanner, dataService, hotels, companies, customers, appUI, rooms);
        }
        switch (input) {
            case 1:
                break;
            case 2:
                hotelId = askForHotelId(scanner);
                dataService.viewAmenities(hotelId);
                viewAmenities(scanner, dataService, hotels, companies, customers, appUI, rooms);
                break;
            case 3:
                hotelId = askForHotelId(scanner);
                priceOrder(scanner, dataService, appUI, hotelId);
                viewAmenities(scanner, dataService, hotels, companies, customers, appUI, rooms);
                break;
            case 4:
                hotelDistance(hotels, scanner, appUI);
                viewAmenities(scanner, dataService, hotels, companies, customers, appUI, rooms);
                break;
            case 5:
                startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
                break;
        }
    }

    public void priceOrder(Scanner scanner, DataService dataService, AppUI appUI, int hotelId) {
        String lowToHigh = "ASC";
        String highToLow = "DESC";
        System.out.println("[1].Price low to high \n[2].Price high to low\n");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                dataService.viewHotelPrice(hotelId, appUI.viewPriceQuery(lowToHigh));
                break;
            case 2:
                dataService.viewHotelPrice(hotelId, appUI.viewPriceQuery(highToLow));
                break;
        }
    }

    public void hotelDistance(ArrayList<Hotel> hotels, Scanner scanner, AppUI appUI) {
        System.out.println("[1].Distance to beach\n[2].Distance to center\n");
        int input = Integer.parseInt(scanner.nextLine());
        System.out.print("Distance (meter) -> :");
        int distance = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 2) {
            appUI.inCorrectInput(1, 2);
            hotelDistance(hotels, scanner, appUI);
        }
        switch (input) {
            case 1:
                printHotelDistance(hotels, distance, 1);
                break;
            case 2:
                printHotelDistance(hotels, distance, 2);
                break;
        }

    }

    public void printHotelDistance(ArrayList<Hotel> hotels, int distance, int i) {
        System.out.println();
        System.out.println("Hotels available within " + distance + " meters.");
        System.out.println("Hotel ID    Hotel Name      Distance");
        for (Hotel hotel : hotels) {
            if (distance >= hotel.distanceToBeach && i == 1) {

                System.out.println(" " + hotel.hotelId + "         " + hotel.name + "    " + hotel.distanceToBeach + " meters to beach.");
            } else if (distance >= hotel.distanceToCenter && i == 2) {
                System.out.println(hotel.hotelId + "         " + hotel.name + "     " + hotel.distanceToCenter + " meters to center.");
            }
        }
        System.out.println();
        System.out.println("[ONLY IF no result] -> No hotels available within the distance of beach or center]\n");
    }

    public int askForHotelId(Scanner scanner) {
        System.out.print("Hotel ID ->: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void addBookingOptions(int bookingId, Scanner scanner, DataService dataService) {
        {
            System.out.println("Extra booking's options:\n[1].Add extra bed\n[2].No\n");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    dataService.addOption(1, bookingId);
                    addMeals(bookingId, scanner, dataService);
                    break;
                case 2:
                    addMeals(bookingId, scanner, dataService);
                    break;
            }
        }

    }

    public void addMeals(int bookingId, Scanner scanner, DataService dataService) {
        {
            System.out.println("[1].Add 2 meals\n[2].Add 3 meals\n[3].No\n");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    dataService.addOption(2, bookingId);
                    break;
                case 2:
                    dataService.addOption(3, bookingId);
                    break;
                case 3:
                    break;
            }
        }

    }

    public void searchCustomersForBook(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers, AppUI appUI,
                                       DataService dataService, ArrayList<Hotel> hotels, ArrayList<Room> rooms) {
        System.out.println("[1].Search by company info \n[2].Search by customer info\n[3].Return main display\n");
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 3) {
            appUI.inCorrectInput(1, 3);
            searchCustomersForBook(scanner, companies, customers, appUI, dataService, hotels, rooms);
        } else {
            switch (input) {
                case 1:
                    searchCustomerByCompanyForBook(scanner, companies, customers, dataService, hotels, appUI, rooms);
                    createBookingWithExistingCustomer(dataService, scanner, companies, customers, appUI, hotels, rooms);
                    break;
                case 2:
                    searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies, rooms);
                    createBookingWithExistingCustomer(dataService, scanner, companies, customers, appUI, hotels, rooms);
                    break;
                case 3:
                    startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
                    break;

            }

        }

    }

    public void createBookingWithExistingCustomer(DataService dataService, Scanner scanner, ArrayList<Company> companies,
                                                  ArrayList<Customer> customers, AppUI appUI, ArrayList<Hotel> hotels, ArrayList<Room> rooms) {
        System.out.println("\n[Booking...]\nCustomer ID ->:");
        int customerId = Integer.parseInt(scanner.nextLine());

        int calendarId = createCalendar(dataService, roomId);
        int bookingId = dataService.createBooking(customerId, calendarId);

        addBookingOptions(bookingId, scanner, dataService);
        appUI.bookingCreatedMess(customerId, bookingId, customers,dataService);
        startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
    }

    public void searchCustomerByCompanyForBook(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers,
                                               DataService dataService, ArrayList<Hotel> hotels, AppUI appUI, ArrayList<Room> rooms) {
        int id = searchCompany(scanner, companies, appUI, dataService, hotels, rooms, customers, 2);
        System.out.println("CustomerID      Name           Phone Number          Birthday            Email");
        for (Customer customer : customers) {
            if (customer.companyID == id) {
                System.out.println("   " + customer.customerID + "     |     " + customer.firstName + "  "
                        + customer.lastName + " |  " + customer.phoneNumber + "  |  " + customer.birthday + "    |   " + customer.email);
            }
        }
    }

    public void changeInfo(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers,
                           DataService dataService, ArrayList<Hotel> hotels, AppUI appUI, ArrayList<Room> rooms) {
        System.out.println("Customer infos change:");
        searchCustomerByInfo(scanner, customers, appUI, dataService, hotels, companies, rooms);
        System.out.println("[1].Name\n[2].Phone numbers\n[3].Address,City,Country\n[4].Email\n[5].Return main display\n");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                appUI.changeName(scanner, dataService);
                break;
            case 2:
                appUI.changePhoneOrEmail(scanner, dataService, "PhoneNumber");
                break;
            case 3:
                appUI.changeAdress(scanner, dataService);
                break;
            case 4:
                appUI.changePhoneOrEmail(scanner, dataService, "Email");
                break;
            case 5:
                startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
                break;
        }
        changeInfo(scanner, companies, customers, dataService, hotels, appUI, rooms);
    }


    public void editCompanyById(Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers,
                                DataService dataService, ArrayList<Hotel> hotels, AppUI appUI, ArrayList<Room> rooms) {
        searchCustomerByCompany(scanner, companies, customers, dataService, hotels, appUI, rooms);
        dataService.countCustomerInCompany(id);
        System.out.println("Delete customer from company:\n[1].Yes\n[2].No\n");
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 2) {
            appUI.inCorrectInput(1, 2);
            editCompanyById(scanner, companies, customers, dataService, hotels, appUI, rooms);
        } else {
            switch (input) {
                case 1:
                    int customerId = appUI.getCustomerId(scanner);
                    dataService.deleteCustomer(customerId);
                    String name = appUI.getCustomerNameById(customers, customerId);
                    System.out.println(name + " has been deleted. ");
                    break;
                case 2:
                    startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
                    break;
            }
        }
    }

    public void viewBookingOption(Scanner scanner, DataService dataService,
                                  ArrayList<Hotel> hotels, ArrayList<Company> companies, ArrayList<Customer> customers,
                                  AppUI appUI, ArrayList<Room> rooms) {
        System.out.println("[Check booking options]\nBooking ID ->:");
        int bookingId = Integer.parseInt(scanner.nextLine());
        boolean check = dataService.viewBookingOptions(bookingId);
        if (!check) {
            System.out.println("[1].Edit option\n[2].Add Options\n[3].Return to main display");
            int input = Integer.parseInt(scanner.nextLine());
            if (input < 1 || input > 3) {
                appUI.inCorrectInput(1, 2);
                viewBookingOption(scanner, dataService, hotels, companies, customers, appUI, rooms);
            } else {
                switch (input) {
                    case 1:
                        updateOption(dataService, scanner, appUI);
                        break;
                    case 2:addOption(bookingId,appUI,dataService,scanner);
                        break;
                    case 3:
                        break;
                }
            }
            startMenu(scanner, dataService, hotels, companies, customers, appUI, rooms);
        }
    }

    public int getOptionId(Scanner scanner) {
        System.out.println("[Option to edit]\nOption ID ->:");
        return Integer.parseInt(scanner.nextLine());
    }

    public void updateOption(DataService dataService, Scanner scanner, AppUI appUI) {
        int optionId = getOptionId(scanner);
        appUI.optionsChoice();
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 3) {
            appUI.inCorrectInput(1, 3);
            updateOption(dataService, scanner, appUI);
        } else {

            switch (input) {
                case 1:
                    dataService.updateOption(1, optionId);
                    break;
                case 2:
                    dataService.updateOption(2, optionId);
                    break;
                case 3:
                    dataService.updateOption(3, optionId);
                    break;
            }
        }
    }

    public void addOption(int bookingId, AppUI appUI, DataService dataService, Scanner scanner) {
        appUI.optionsChoice();
        int input = Integer.parseInt(scanner.nextLine());
        if (input < 1 || input > 3) {
            appUI.inCorrectInput(1, 3);
            updateOption(dataService, scanner, appUI);
        } else {

            switch (input) {
                case 1:
                    dataService.addOption(1,bookingId);
                    break;
                case 2:
                    dataService.addOption(2, bookingId);
                    break;
                case 3:
                    dataService.addOption(3, bookingId);
                    break;
            }
        }
    }
}









