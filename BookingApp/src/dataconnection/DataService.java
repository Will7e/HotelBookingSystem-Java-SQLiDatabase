package dataconnection;

import tableclasses.Company;
import tableclasses.Customer;
import tableclasses.Hotel;
import tableclasses.Room;

import java.sql.*;
import java.util.ArrayList;

public class DataService {
    Connection con = null;
    String url = "jdbc:sqlite:C:\\Users\\WillE\\Desktop\\Ny mapp (2)\\HotelBookingSystem\\BookingApp\\src\\HotelBooking.db";

    public void connect() {
        try {
            con = DriverManager.getConnection(url);
            System.out.println(" Hotel Database Connected... \n ------------------");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    // Add data to company table. Return id
    public int createCompany(String companyName) {
        int createdId = 0;
        String query = "INSERT INTO Company(Company_Name) VALUES(?)";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, companyName);
            // commit to database
            statement.executeUpdate();
            // get result
            ResultSet keys = statement.getGeneratedKeys();

            while (keys.next()) {
                createdId = keys.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return createdId;
    }

    // Get a view on what amenities the hotel has.
    public void viewAmenities(int hotelId) {
        String query = "SELECT Amenity_Info.Amenity_ID, Hotel.Hotel_ID, Hotel.Hotel_Name, Description FROM Amenity_Info\n" +
                "INNER JOIN Hotel ON Hotel.Hotel_ID = Amenity.Hotel_ID\n" +
                "INNER JOIN Amenity ON Amenity.Amenity_ID = Amenity_Info.Amenity_ID\n" +
                "WHERE Hotel.Hotel_ID = '" + hotelId + "'\n" +
                "GROUP BY Amenity_Info.Description ";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("Hotel ID      Hotel Name     Amenity ID      Description");
            while (resultSet.next()) {
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                String title = resultSet.getString("Hotel_Name");
                int amenityId = resultSet.getInt("Amenity_ID");
                String description = resultSet.getString("Description");
                System.out.println(hotelId + "             " + title + "         " + amenityId + "        " + description);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Create customer in database
    public int createCustomer(int companyID, String fistName, String lastName, String address,
                              String city, String country, String phoneNumber, String email,
                              String birthday) {
        int customerId = 0;
        String query = "INSERT INTO Customer(First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, fistName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, city);
            statement.setString(5, country);
            statement.setString(6, phoneNumber);
            statement.setString(7, email);
            statement.setString(8, birthday);
            statement.setInt(9, companyID);
            // commit to database
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            while (keys.next()) {
                customerId = keys.getInt(1);
            }
            System.out.println("[Customer ADDED!]. Customer ID: " + customerId + " .Customer name: " + fistName+" "+lastName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerId;
    }

    // Add company id and name to arraylist
    public ArrayList<Company> getCompany() {
        ArrayList<Company> companies = new ArrayList<>();
        String query = "SELECT * FROM Company";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                int id = resultSet.getInt("Company_ID");
                String name = resultSet.getString("Company_Name");
                companies.add(new Company(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return companies;
    }

    // Find available room by room size and check in check out date
    public boolean findAvailableRoom(int roomSize, String checkInDate, int hotelId, String checkOutDate) {
        boolean isEmpty = true;
        String query = "SELECT Room.Room_ID, Room.Room_Name, Room.Room_Size, Hotel.Hotel_ID, Hotel.Hotel_Name, " +
                "Hotel.City, Hotel.Country,Room.Price FROM Room \n" +
                "LEFT JOIN Calendar ON Room.Room_ID = Calendar.Room_ID \n" +
                "INNER JOIN Hotel ON Hotel.Hotel_ID = Room.Hotel_ID\n" +
                "WHERE  Room.Room_Size = ? AND Hotel.Hotel_ID = ? AND Calendar.CheckOut_Date <= ?\n" +
                "OR Room.Room_Size = ? AND Hotel.Hotel_ID = ? AND Calendar.CheckIn_Date >= ?\n" +
                "OR Room.Room_Size = ? AND Hotel.Hotel_ID = ? AND Calendar.Calendar_ID IS NULL\n" +
                "GROUP BY Room.Room_ID\n" +
                "ORDER BY Room.Price ASC";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            // ? parameter id
            statement.setInt(1, roomSize);
            statement.setInt(2, hotelId);
            statement.setString(3, checkOutDate);
            statement.setInt(4, roomSize);
            statement.setInt(5, hotelId);
            statement.setString(6, checkInDate);
            statement.setInt(7, roomSize);
            statement.setInt(8, hotelId);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                System.out.println("RoomId   RoomName   RoomSize    HotelId      HotelName        City       Country     Price");
                isEmpty = false;
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                int roomID = resultSet.getInt("Room_ID");
                String roomName = resultSet.getString("Room_Name");
                int roomSize1 = resultSet.getInt("Room_Size");
                String hotelID = resultSet.getString("Hotel_ID");
                String hotelName = resultSet.getString("Hotel_Name");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");
                int price = resultSet.getInt("Price");
                System.out.println(roomID + "          " + roomName + "        " + roomSize1 + "            " + hotelID + "        " +
                        "" + hotelName + "      " + city + "      " + country + "       " + price);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (isEmpty) {
            System.out.println("No room available.");
            return false;
        }

        return true;
    }

    // Create booking on sqlite database. Return booking id
    public int createBooking(int customerId, int calendarId) {
        int bookingId = 0;
        String query = "INSERT INTO Booking(Customer_ID,Calendar_ID) VALUES(?,?)";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, customerId);
            statement.setInt(2, calendarId);
            // commit to database
            statement.executeUpdate();
            // get result
            ResultSet keys = statement.getGeneratedKeys();

            while (keys.next()) {
                bookingId = keys.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingId;
    }


    // Create a calendar on sqlite databas. Return table id.
    public int createCalendar(String checkInDate, String checkoutDate, int roomId) {
        int calendarId = 0;
        String query = "INSERT INTO Calendar(CheckIn_Date,CheckOut_Date,Room_ID) VALUES(?,?,?)";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, checkInDate);
            statement.setString(2, checkoutDate);
            statement.setInt(3, roomId);

            // commit to database
            statement.executeUpdate();
            // get result
            ResultSet keys = statement.getGeneratedKeys();

            while (keys.next()) {
                calendarId = keys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendarId;
    }

    // Add data from customer table to array
    public ArrayList<Customer> getCustomer() {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                int id = resultSet.getInt("Customer_ID");
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                String birthday = resultSet.getString("Birthday");
                int companyId = resultSet.getInt("Company_ID");
                customers.add(new Customer(id, firstName, lastName, address, city, country, phoneNumber, email, birthday, companyId));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    // Add data from hotel table to array
    public ArrayList<Hotel> getHotel() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotel";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                int id = resultSet.getInt("Hotel_ID");
                String hotelName = resultSet.getString("Hotel_Name");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");
                int distanceToBeach = resultSet.getInt("Distance_To_Beach");
                int distanceToCenter = resultSet.getInt("Distance_To_Center");
                hotels.add(new Hotel(id, hotelName, city, country, distanceToBeach, distanceToCenter));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotels;
    }

    // Add data from room table to arraylist.
    public ArrayList<Room> getRoom() {
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Room";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                int roomId = resultSet.getInt("Room_ID");
                int roomNumber = resultSet.getInt("Room_Name");
                int roomSize = resultSet.getInt("Room_Size");
                int hotelId = resultSet.getInt("Hotel_Id");
                int price = resultSet.getInt("Price");
                rooms.add(new Room(roomId, roomNumber, roomSize, hotelId, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rooms;
    }

    public boolean viewBookingById(int id, String appUiQuery) {
        int bookingId, calendarId;

        try {
            PreparedStatement statement = con.prepareStatement(appUiQuery);
            statement.setInt(1, id);
            // commit to database
            // get result
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("First Name     Last Name      BookingID    CalendarID    RoomID" +
                        "    Room Name    Hotel Name      Check In       Check Out   ");
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                bookingId = resultSet.getInt("Booking_ID");
                calendarId = resultSet.getInt("Calendar_ID");
                int roomId = resultSet.getInt("Room_ID");
                int roomName = resultSet.getInt("Room_Name");
                String hotelName = resultSet.getString("Hotel_Name");
                String checkIn = resultSet.getString("CheckIn_Date");
                String checkOut = resultSet.getString("CheckOut_Date");


                System.out.println("  "+firstName + "       "  + lastName + "           "
                        + bookingId + "               " + calendarId + "        "  + roomId + "         " + roomName + "         " + hotelName + "    " +
                        checkIn + "    " + checkOut+"\n");

                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void deleteBooking(int bookingId, int calendarId) {
        String query = "DELETE FROM Booking WHERE Booking.Booking_ID = ?";
        String query2 = "DELETE FROM Calendar WHERE Calendar.Calendar_ID = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, bookingId);
            PreparedStatement statement2 = con.prepareStatement(query2);
            statement2.setInt(1, calendarId);
            // ? parameter id
            statement.executeUpdate();
            statement2.executeUpdate();
            System.out.println("Booking with BookingID : " + bookingId + " has been canceled.\n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void viewHotelPrice(int hotelId, String appUiQuery) {
        try {
            PreparedStatement statement = con.prepareStatement(appUiQuery);
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Hotel ID     Hotel Name      Room ID     Room Size     Price");
            while (resultSet.next()) {
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                String hotelName = resultSet.getString("Hotel_Name");
                int roomId = resultSet.getInt("Room_ID");
                int roomSize = resultSet.getInt("Room_Size");
                int price = resultSet.getInt("Price");

                System.out.println(hotelId + "           " + hotelName + "        " + roomId + "             " + roomSize + "         " + price);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println();

    }

    public void addOption(int optionId, int bookingId) {
        String query = "INSERT INTO Option(Option_ID,Booking_ID) VALUES(?,?)";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, optionId);
            statement.setInt(2, bookingId);
            // commit to database
            statement.executeUpdate();
            // get result
            String description = getOptionDescription(optionId);
            System.out.println("Option : " + description +" has added to Booking ID: "+ bookingId+".\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateName(String firstName, String lastName, int customerId) {
        String query = "UPDATE Customer\n" +
                "Set First_Name = ?,\n" +
                "Last_Name = ?\n" +
                "WHERE Customer_ID = ?";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, customerId);
            // commit to database
            statement.executeUpdate();
            System.out.println("Name updated: " + firstName + " " + lastName + "\n");
            // get result
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateAdress(String address, String city, String country, int customerId) {
        String query = "UPDATE Customer\n" +
                "Set Address = ?,\n" +
                "City =?,\n" +
                "Country = ?\n" +
                "WHERE Customer_ID = ?";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, address);
            statement.setString(2, city);
            statement.setString(3, country);
            statement.setInt(4, customerId);
            // commit to database
            statement.executeUpdate();
            // get result
            System.out.println("Address updated: " + address + " " + city + " " + country + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updatePhoneNumbersOrEmail(String co, String info, int customerId) {
        String query = "UPDATE Customer\n" +
                "Set '" + co + "' = ?\n" +
                "WHERE Customer_ID = ?\n" +
                "\n";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, info);
            statement.setInt(2, customerId);
            // commit to database
            statement.executeUpdate();
            System.out.println(co + " updated: " + info + "\n");
            // get result
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void countCustomerInCompany(int companyId) {
        String query = "SELECT COUNT(Company.Company_ID) as Count_Customer,Company.Company_Name FROM Company\n" +
                "INNER JOIN Customer ON Company.Company_ID = Customer.Company_ID\n" +
                "WHERE Company.Company_ID = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, companyId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                int count = resultSet.getInt("Count_Customer");
                String companyName = resultSet.getString("Company_Name");
                System.out.println("Company name: " + companyName + " has " + count + " customers.\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteCustomer(int customerId) {
        String query = "DELETE FROM Customer\n" +
                "WHERE Customer.Customer_ID = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean viewBookingOptions(int bookingId) {
        String query = "SELECT Option.Option_ID,Booking.Booking_ID,Booking.Customer_ID,Booking.Calendar_ID,Option_Info.Description FROM Option\n" +
                "INNER JOIN Booking ON Booking.Booking_ID = Option.Booking_ID\n" +
                "INNER JOIN Option_Info ON Option.Option_ID = Option_Info.Option_ID\n" +
                "WHERE Booking.Booking_ID = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, bookingId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Option ID    Booking ID    Description");
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                int optionID = resultSet.getInt("Option_ID");
                int bookingID = resultSet.getInt("Booking_ID");
                String description = resultSet.getString("Description");
                System.out.println("   " + optionID + "             " + bookingID + "          " + description);

            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }

    public void updateOption(int updateOptionId, int optionId) {
        String query = "UPDATE Option\n" +
                "SET \n" +
                "Option_ID = ?\n" +
                "WHERE Option_ID = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, updateOptionId);
            statement.setInt(2, optionId);
            // commit to database
            statement.executeUpdate();
            String updateDescription = getOptionDescription(updateOptionId);
            String description = getOptionDescription(optionId);
            System.out.println("Option updated from: [" + description +"] to [" + updateDescription+"]\n");
            // get result
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOptionDescription(int optionId) {
        String description;
        String query = "SELECT * FROM Option_Info\n" +
                "WHERE Option_Info.Option_ID = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, optionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // vi behöver nu hämta ut värde från kolumn i raden:
                description = resultSet.getString("Description");
                return description;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }
}














