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
                "INNER JOIN Hotel \n" +
                "INNER JOIN Amenity \n" +
                "WHERE Hotel.Hotel_ID = '" + hotelId + "'\n" +
                "GROUP BY Hotel.Hotel_Name ";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                String title = resultSet.getString("Hotel_Name");
                int amenityId = resultSet.getInt("Amenity_ID");
                String description = resultSet.getString("Description");
                System.out.println(hotelId + " || " + title + " || " + amenityId + " || " + description);
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
            System.out.println("Customer " + fistName + " " + lastName + " " + customerId + " added.");
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
    public boolean findAvailableRoom(int roomSize, String checkInDate,String hotelCity, String checkOutDate) {
            boolean isEmpty = true;
            String query = "SELECT Room.Room_ID, Room.Room_Name, Room.Room_Size, Hotel.Hotel_ID, Hotel.Hotel_Name, Hotel.City, Hotel.Country FROM Room \n" +
                    "LEFT JOIN Calendar ON Room.Room_ID = Calendar.Room_ID \n" +
                    "INNER JOIN Hotel ON Hotel.Hotel_ID = Room.Hotel_ID\n" +
                    "WHERE  Room.Room_Size = ? AND Hotel.City = ? AND Calendar.CheckOut_Date <= ?\n" +
                    "OR Room.Room_Size = ? AND Hotel.City = ? AND Calendar.CheckIn_Date >= ?\n" +
                    "OR Room.Room_Size = ? AND Hotel.City = ? AND Calendar.Calendar_ID IS NULL\n \n";
            try {
                PreparedStatement statement = con.prepareStatement(query);
                // ? parameter id
                statement.setInt(1, roomSize);
                statement.setString(2, hotelCity);
                statement.setString(3, checkOutDate);
                statement.setInt(4, roomSize);
                statement.setString(5, hotelCity);
                statement.setString(6, checkInDate);
                statement.setInt(7,roomSize);
                statement.setString(8,hotelCity);
                ResultSet resultSet = statement.executeQuery();


                while (resultSet.next()) {
                    System.out.println("RoomId   RoomName   RoomSize    HotelId      HotelName        City       Country");
                    isEmpty = false;
                    // vi behöver nu hämta ut varje värde från varje kolumn i raden:
                    int roomID = resultSet.getInt("Room_ID");
                    String roomName = resultSet.getString("Room_Name");
                    int roomSize1 = resultSet.getInt("Room_Size");
                    String hotelID = resultSet.getString("Hotel_ID");
                    String hotelName = resultSet.getString("Hotel_Name");
                    String city = resultSet.getString("City");
                    String country = resultSet.getString("Country");
                    System.out.println(roomID + "          " + roomName + "        " + roomSize1 + "            " + hotelID + "        " +
                            "" + hotelName + "      " + city + "      " + country +"\n");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (isEmpty){
                System.out.println("No room available.");
                return false;
            }

        return true;
    }

    // Create booking on sqlite database. Return booking id
    public int createBooking(int calendarId, int customerId) {
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

    public void viewBooking(int customerId) {
        int bookingId, calendarId;
        String query = "SELECT Customer.First_Name, Customer.Last_Name, Booking.Booking_ID, Calendar.Calendar_ID, Calendar.Room_ID, Room.Room_Name, Hotel.Hotel_Name, Calendar.CheckIn_Date, Calendar.CheckOut_Date \n" +
                "FROM Booking \n" +
                "INNER JOIN Customer ON Customer.Customer_ID = Booking.Customer_ID \n" +
                "INNER JOIN Calendar ON Calendar.Calendar_ID = Booking.Calendar_ID \n" +
                "INNER JOIN Room ON Room.Room_ID = Calendar.Room_ID \n" +
                "INNER JOIN Hotel ON Hotel.Hotel_ID = Room.Hotel_ID \n" +
                "WHERE Customer.Customer_ID = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, customerId);
            // commit to database
            // get result
            ResultSet resultSet = statement.executeQuery();
            System.out.println("First Name     Last Name      BookingID    CalendarID    RoomID" +
                    "    Room Name    Hotel Name      Check In    Check Out   ");

            while (resultSet.next()) {
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                bookingId = resultSet.getInt("Booking_ID");
                calendarId = resultSet.getInt("Calendar_ID");
                int roomId = resultSet.getInt("Room_ID");
                int roomName = resultSet.getInt("Room_Name");
                String hotelName = resultSet.getString("Hotel_Name");
                String checkIn = resultSet.getString("CheckIn_Date");
                String checkOut = resultSet.getString("CheckOut_Date");


                System.out.println(firstName + "" +
                        "       " +
                        "  " + lastName + "    " +
                        "       " + bookingId + "    " + calendarId + "    " +
                        "" + roomId + "    " + roomName + "    " + hotelName + "" +
                        " " + checkIn + " " + checkOut);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteBooking(int bookingId, int calendarId) {
        String query = "DELETE FROM Booking WHERE Booking.Booking_ID = ?";
        String query2 = "DELETE FROM Calendar WHERE Calendar.Calendar_ID = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,bookingId);
            PreparedStatement statement2 = con.prepareStatement(query2);
            statement2.setInt(1,calendarId);
            // ? parameter id
            statement.executeUpdate();
            statement2.executeUpdate();

            System.out.println("Delete succeed");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}








