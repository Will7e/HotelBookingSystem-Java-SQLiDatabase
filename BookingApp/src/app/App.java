package app;

import dataconnection.DataService;
import tableclasses.Company;
import tableclasses.Customer;
import tableclasses.Hotel;
import tableclasses.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner scanner;
    DataService dataService;
    ArrayList<Company> companies;
    ArrayList<Customer> customers;
    AppControl appUI;
    ArrayList<Hotel> hotels;
    ArrayList<Room> rooms;


    public App() {
        dataService = new DataService();
        dataService.connect();
        appUI = new AppControl();
        scanner = new Scanner(System.in);
        companies = dataService.getCompany();
        customers = dataService.getCustomer();
        hotels = dataService.getHotel();
        rooms = dataService.getRoom();
        appUI.startMenu(scanner,dataService,hotels,companies,customers);

    }





}
