--
-- File generated with SQLiteStudio v3.3.3 on tor jan 13 10:27:40 2022
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Amenity
CREATE TABLE Amenity (Amenity_ID INT REFERENCES Amenity_Info (Amenity_ID), Hotel_ID INT REFERENCES Hotel (Hotel_ID));
INSERT INTO Amenity (Amenity_ID, Hotel_ID) VALUES (1, 1);
INSERT INTO Amenity (Amenity_ID, Hotel_ID) VALUES (2, 1);
INSERT INTO Amenity (Amenity_ID, Hotel_ID) VALUES (2, 3);
INSERT INTO Amenity (Amenity_ID, Hotel_ID) VALUES (2, 5);
INSERT INTO Amenity (Amenity_ID, Hotel_ID) VALUES (3, 4);
INSERT INTO Amenity (Amenity_ID, Hotel_ID) VALUES (1, 1);
INSERT INTO Amenity (Amenity_ID, Hotel_ID) VALUES (4, 2);
INSERT INTO Amenity (Amenity_ID, Hotel_ID) VALUES (4, 4);

-- Table: Amenity_Info
CREATE TABLE Amenity_Info (Amenity_ID INTEGER PRIMARY KEY NOT NULL, Description VARCHAR);
INSERT INTO Amenity_Info (Amenity_ID, Description) VALUES (1, 'Pool');
INSERT INTO Amenity_Info (Amenity_ID, Description) VALUES (2, 'Evening Entertainent');
INSERT INTO Amenity_Info (Amenity_ID, Description) VALUES (3, 'Kids club');
INSERT INTO Amenity_Info (Amenity_ID, Description) VALUES (4, 'Restaurant');

-- Table: Booking
CREATE TABLE Booking (Booking_ID INTEGER PRIMARY KEY NOT NULL, Customer_ID INT REFERENCES Customer (Customer_ID), Calendar_ID INT REFERENCES Calendar (Calendar_ID));
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (1, 11, 25);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (2, 23, 9);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (3, 24, 6);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (4, 33, 30);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (6, 9, 38);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (7, 48, 44);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (8, 36, 31);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (9, 50, 20);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (11, 18, 21);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (12, 37, 28);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (13, 27, 50);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (14, 48, 3);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (15, 48, 47);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (16, 3, 6);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (17, 48, 46);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (18, 24, 37);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (19, 9, 21);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (20, 38, 42);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (21, 8, 16);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (22, 26, 21);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (23, 42, 34);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (24, 37, 19);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (25, 47, 2);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (26, 7, 46);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (27, 44, 35);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (28, 50, 14);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (29, 30, 8);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (30, 31, 14);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (31, 34, 16);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (32, 30, 14);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (33, 45, 2);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (34, 39, 27);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (36, 38, 4);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (37, 42, 39);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (38, 27, 47);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (40, 32, 24);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (41, 22, 27);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (42, 24, 2);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (43, 19, 18);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (44, 44, 14);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (45, 50, 50);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (46, 15, 23);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (47, 43, 25);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (48, 27, 46);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (49, 50, 55);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (51, 57, 53);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (52, 23, 54);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (53, 3, 55);
INSERT INTO Booking (Booking_ID, Customer_ID, Calendar_ID) VALUES (54, 52, 56);

-- Table: Calendar
CREATE TABLE Calendar (Calendar_ID INTEGER PRIMARY KEY NOT NULL, CheckIn_Date DATE, CheckOut_Date DATE, Room_ID INT REFERENCES Room (Room_ID));
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (1, '2022-06-29', '2022-07-09', 16);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (2, '2022-06-14', '2022-06-28', 27);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (3, '2022-06-01', '2022-06-10', 24);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (4, '2022-07-11', '2022-07-20', 30);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (5, '2022-07-25', '2022-07-31', 30);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (6, '2022-06-01', '2022-06-04', 18);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (7, '2022-06-01', '2022-06-10', 1);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (8, '2022-07-03', '2022-07-08', 21);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (9, '2022-07-19', '2022-07-26', 22);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (10, '2022-06-29', '2022-07-05', 8);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (11, '2022-07-04', '2022-07-16', 22);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (12, '2022-06-06', '2022-06-12', 18);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (13, '2022-07-09', '2022-07-12', 3);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (14, '2022-06-04', '2022-06-10', 9);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (15, '2022-06-17', '2022-06-27', 8);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (16, '2022-06-27', '2022-07-11', 7);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (17, '2022-06-22', '2022-06-27', 10);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (18, '2022-07-04', '2022-07-11', 17);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (20, '2022-06-21', '2022-07-15', 1);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (22, '2022-07-07', '2022-07-14', 23);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (23, '2022-06-26', '2022-07-09', 3);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (24, '2022-06-21', '2022-06-28', 13);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (25, '2022-06-13', '2022-07-05', 13);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (26, '2022-06-03', '2022-06-14', 25);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (27, '2022-06-27', '2022-07-02', 26);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (28, '2022-06-25', '2022-07-02', 28);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (29, '2022-07-27', '2022-07-31', 16);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (30, '2022-06-29', '2022-07-05', 27);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (31, '2022-06-18', '2022-06-22', 22);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (32, '2022-06-07', '2022-07-13', 22);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (33, '2022-06-25', '2022-06-27', 5);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (34, '2022-07-02', '2022-07-09', 16);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (35, '2022-06-26', '2022-06-30', 25);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (36, '2022-06-04', '2022-06-12', 3);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (37, '2022-07-16', '2022-07-22', 1);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (38, '2022-07-08', '2022-07-16', 5);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (39, '2022-07-13', '2022-07-16', 28);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (41, '2022-06-19', '2022-07-01', 13);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (42, '2022-06-15', '2022-07-17', 22);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (43, '2022-06-04', '2022-06-15', 20);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (44, '2022-07-03', '2022-07-13', 20);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (45, '2022-06-23', '2022-06-29', 30);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (46, '2022-07-26', '2022-07-29', 6);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (47, '2022-06-16', '2022-06-20', 9);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (48, '2022-07-08', '2022-07-12', 11);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (49, '2022-06-06', '2022-06-11', 8);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (50, '2022-07-11', '2022-07-19', 21);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (51, '2022-06-12', '2022-06-31', 14);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (53, '2022-06-12', '2022-06-15', 11);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (54, '2022-06-21', '2022-06-25', 7);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (55, '2022-06-12', '2022-06-14', 24);
INSERT INTO Calendar (Calendar_ID, CheckIn_Date, CheckOut_Date, Room_ID) VALUES (56, '2022-06-21', '2022-07-29', 24);

-- Table: Company
CREATE TABLE Company (Company_ID INTEGER PRIMARY KEY NOT NULL, Company_Name VARCHAR);
INSERT INTO Company (Company_ID, Company_Name) VALUES (2, 'Treeflex');
INSERT INTO Company (Company_ID, Company_Name) VALUES (3, 'Y-Solowarm');
INSERT INTO Company (Company_ID, Company_Name) VALUES (4, 'Frank');
INSERT INTO Company (Company_ID, Company_Name) VALUES (5, 'Regrant');
INSERT INTO Company (Company_ID, Company_Name) VALUES (6, 'Namfix');
INSERT INTO Company (Company_ID, Company_Name) VALUES (7, 'Zontrax');
INSERT INTO Company (Company_ID, Company_Name) VALUES (8, 'Mio');
INSERT INTO Company (Company_ID, Company_Name) VALUES (9, 'Home Ing');
INSERT INTO Company (Company_ID, Company_Name) VALUES (10, 'Subin');
INSERT INTO Company (Company_ID, Company_Name) VALUES (11, 'Zamit');
INSERT INTO Company (Company_ID, Company_Name) VALUES (12, 'Bitchip');
INSERT INTO Company (Company_ID, Company_Name) VALUES (13, 'Veribet');
INSERT INTO Company (Company_ID, Company_Name) VALUES (14, 'Crusher');
INSERT INTO Company (Company_ID, Company_Name) VALUES (15, 'Bamity');
INSERT INTO Company (Company_ID, Company_Name) VALUES (16, 'Wille');
INSERT INTO Company (Company_ID, Company_Name) VALUES (17, 'Netflix');
INSERT INTO Company (Company_ID, Company_Name) VALUES (18, 'Amanzon');
INSERT INTO Company (Company_ID, Company_Name) VALUES (19, 'FaceBook');
INSERT INTO Company (Company_ID, Company_Name) VALUES (20, 'Tesla');
INSERT INTO Company (Company_ID, Company_Name) VALUES (21, 'Google');
INSERT INTO Company (Company_ID, Company_Name) VALUES (22, 'Snap');
INSERT INTO Company (Company_ID, Company_Name) VALUES (23, 'Funny');
INSERT INTO Company (Company_ID, Company_Name) VALUES (24, 'Party');
INSERT INTO Company (Company_ID, Company_Name) VALUES (25, 'hello');
INSERT INTO Company (Company_ID, Company_Name) VALUES (26, 'hello');

-- Table: Customer
CREATE TABLE Customer (Customer_ID INTEGER PRIMARY KEY NOT NULL, First_Name VARCHAR, Last_Name VARCHAR, Address VARCHAR, City VARCHAR, Country VARCHAR, PhoneNumber INT, Email VARCHAR, Birthday DATE, Company_ID INT REFERENCES Company (Company_ID));
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (1, 'Dillie', 'Curless', '1264 Hauk Road', 'Lyon', 'France', '936-451-4270', 'dcurless0@eventbrite.com', '12/23/2021', 13);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (3, 'Beckie', 'Stallan', '1533 Lakeland Park', 'Miyata', 'Japan', '888-215-4725', 'bstallan2@hp.com', '1/10/2020', 10);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (4, 'Linnet', 'Lindman', '175 Orin Way', 'Purísima', 'Colombia', '821-766-4830', 'llindman3@sourceforge.net', '4/9/2020', 1);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (6, 'Waylin', 'Batistelli', '81960 Sunbrook Hill', 'Passagem', 'Portugal', '360-943-2357', 'wbatistelli5@scientificamerican.com', '3/21/2021', 10);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (7, 'Rey', 'Heathcott', '6 Vermont Alley', 'Zielonki', 'Poland', '686-752-4063', 'rheathcott6@walmart.com', '10/11/2020', 10);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (8, 'Read', 'Ivison', '422 Comanche Trail', 'Khoroshëvo-Mnevniki', 'Russia', '807-436-1433', 'rivison7@unblog.fr', '11/24/2021', 3);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (9, 'Cookie', 'Behnke', '65768 Di Loreto Alley', 'Summerland', 'Canada', '138-352-2158', 'cbehnke8@networksolutions.com', '3/1/2020', 5);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (10, 'Dalt', 'Tomaszek', '3 Amoth Trail', 'Nanhai', 'China', '928-689-0367', 'dtomaszek9@vimeo.com', '3/27/2020', 14);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (11, 'Hagan', 'Schoular', '277 Eggendart Point', 'Awgu', 'Nigeria', '378-760-8071', 'hschoulara@chronoengine.com', '3/13/2021', 9);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (12, 'Ilse', 'Ellaway', '76 Stoughton Center', 'Rudolfov', 'Czech Republic', '149-291-1351', 'iellawayb@google.com.hk', '9/27/2021', 6);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (13, 'Matelda', 'Jacquemot', '98634 Straubel Trail', 'Rodatychi', 'Ukraine', '891-815-3812', 'mjacquemotc@mapy.cz', '2/3/2020', 7);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (14, 'Shirlene', 'Ginman', '3 Armistice Junction', 'Bagu', 'Indonesia', '713-647-6336', 'sginmand@delicious.com', '11/28/2020', 14);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (15, 'Chaddie', 'Barney', '01 School Plaza', 'Sokol', 'Russia', '671-514-3428', 'cbarneye@cdbaby.com', '9/3/2021', 5);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (16, 'Onofredo', 'Hawtry', '000 Caliangt Terrace', 'Nsok', 'Equatorial Guinea', '576-541-8095', 'ohawtryf@google.ca', '1/2/2021', 7);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (17, 'Nancy', 'Caslane', '624 Comanche Lane', 'Ko Samui', 'Thailand', '601-157-2990', 'ncaslaneg@cloudflare.com', '9/29/2021', 6);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (18, 'Elisabeth', 'Bottini', '840 Lawn Lane', 'Arue', 'French Polynesia', '255-469-2337', 'ebottinih@deliciousdays.com', '3/17/2020', 3);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (19, 'Weider', 'Huggens', '243 Clyde Gallagher Parkway', 'Xintai', 'China', '125-536-7792', 'whuggensi@ocn.ne.jp', '6/13/2021', 4);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (20, 'Griffie', 'Corris', '96 Moose Avenue', 'Verkhniy Baskunchak', 'Russia', '704-789-4706', 'gcorrisj@furl.net', '9/17/2021', 9);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (21, 'Lorant', 'Carp', '442 High Crossing Junction', 'Ciro Redondo', 'Cuba', '704-182-6898', 'lcarpk@ucoz.com', '10/8/2020', 11);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (22, 'Alanah', 'Comben', '0 Ridgeview Alley', 'Linamon', 'Philippines', '308-332-0348', 'acombenl@histats.com', '12/2/2020', 10);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (24, 'Leland', 'Goldbourn', '409 Arizona Hill', 'Kertanegla', 'Indonesia', '258-863-4748', 'lgoldbournn@abc.net.au', '12/14/2021', 3);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (25, 'Hillier', 'Robathon', '75 Walton Road', 'Kudinovo', 'Russia', '237-721-3791', 'hrobathono@themeforest.net', '8/12/2020', 15);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (26, 'Millie', 'Ramlot', '66260 Kensington Court', 'Muang Sam Sip', 'Thailand', '405-245-5239', 'mramlotp@ebay.co.uk', '11/16/2020', 7);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (27, 'Phil', 'Bottlestone', '0926 Annamark Terrace', 'San Juan Ermita', 'Guatemala', '532-479-4130', 'pbottlestoneq@last.fm', '5/15/2020', 14);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (28, 'Wilfred', 'Blaske', '5112 Vera Drive', 'Dayapan', 'Philippines', '117-178-7757', 'wblasker@wikimedia.org', '6/18/2021', 14);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (29, 'Nadean', 'Attwood', '0193 David Road', 'Lenchwe Le Tau', 'Botswana', '982-427-3128', 'nattwoods@redcross.org', '4/11/2021', 10);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (30, 'Cash', 'Wilcinskis', '74 Summerview Alley', 'Värmdö', 'Sweden', '205-200-7942', 'cwilcinskist@scientificamerican.com', '3/30/2020', 13);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (31, 'Geordie', 'McGilvra', '6 Eagan Center', 'Chirpan', 'Bulgaria', '285-495-9007', 'gmcgilvrau@histats.com', '5/23/2020', 4);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (32, 'Lionel', 'Vaggers', '1946 Charing Cross Hill', 'Honolulu', 'United States', '808-459-9657', 'lvaggersv@wsj.com', '7/31/2020', 13);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (33, 'Tarrance', 'Abdy', '1 Boyd Pass', 'Tangjian', 'China', '193-552-5251', 'tabdyw@phpbb.com', '5/24/2021', 4);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (34, 'Dorian', 'Heritege', '7 Spaight Drive', 'Ilam', 'Iran', '232-371-6947', 'dheritegex@wikia.com', '9/17/2021', 5);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (36, 'Norina', 'Grinyakin', '15 Valley Edge Park', 'Ya’erya', 'China', '177-489-9443', 'ngrinyakinz@oakley.com', '12/9/2020', 4);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (37, 'Felike', 'Routh', '88298 Valley Edge Way', 'Tenri', 'Japan', '499-910-7126', 'frouth10@nsw.gov.au', '9/8/2021', 2);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (38, 'Tabitha', 'Bree', '728 Carpenter Circle', 'Fais', 'Micronesia', '904-277-5221', 'tbree11@foxnews.com', '3/21/2020', 15);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (39, 'Baby', 'Driver', '6 Utah Plaza', 'Bayan Hot', 'China', '997-914-4856', 'babydriver@gmail.com', '7/5/2021', 14);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (40, 'Jacinda', 'Freeborne', '290 Fisk Crossing', 'La Gomera', 'Guatemala', '350-169-7910', 'jfreeborne13@yolasite.com', '4/9/2021', 11);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (41, 'Griselda', 'Lezemere', '21621 Hoffman Crossing', 'Ternovka', 'Russia', '791-581-5458', 'glezemere14@furl.net', '5/27/2021', 5);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (42, 'Marice', 'Bedson', '4069 Fremont Hill', 'Sambong', 'Indonesia', '571-890-0944', 'mbedson15@seesaa.net', '2/3/2020', 13);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (43, 'Alvan', 'Deverille', '6362 Northridge Place', 'Weixin', 'China', '878-148-4470', 'adeverille16@redcross.org', '10/29/2021', 10);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (44, 'Sukey', 'Pygott', '69 Spenser Center', 'Senhor da Serra', 'Portugal', '796-219-3350', 'spygott17@so-net.ne.jp', '7/15/2021', 11);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (45, 'Becky', 'Danihelka', '35 Summerview Center', 'Chejiazhuang', 'China', '240-253-4656', 'bdanihelka18@guardian.co.uk', '3/27/2020', 8);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (46, 'Geoff', 'Ruf', 'Halollan Street 24', 'Hola', 'Mexico', '369-352-5071', 'gruf19@independent.co.uk', '8/22/2020', 2);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (47, 'Kimberley', 'Nottingham', '1 Vidon Hill', 'Rowokangkung', 'Indonesia', '870-892-8997', 'knottingham1a@fastcompany.com', '1/29/2021', 12);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (48, 'Cristine', 'Simenon', '8 Pond Point', 'Uchkulan', 'Russia', '299-346-7223', 'csimenon1b@yale.edu', '9/19/2020', 12);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (49, 'Ailyn', 'Adriani', '71 Thierer Circle', 'Novovoronezh', 'Russia', '450-625-0524', 'aadriani1c@sina.com.cn', '1/8/2021', 10);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (50, 'Bordie', 'Stiffkins', '73 Milwaukee Drive', 'Yanqian', 'China', '986-929-6649', 'bstiffkins1d@123-reg.co.uk', '1/1/2020', 14);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (51, 'Baby', 'Love', '21 Red Strett', 'Hima', 'Japan', '333-333-333', 'love33@gmail.com', '1/2/1995', 24);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (52, 'Evenlina', 'Kai', '23 Lucystheizer', 'Berlin', 'Germany', '123-123-123', 'kailina@hotbabe.com', '22/2/1994', 24);
INSERT INTO Customer (Customer_ID, First_Name, Last_Name, Address, City, Country, PhoneNumber, Email, Birthday, Company_ID) VALUES (53, 'Will', 'Onia', '88 Lucky', 'Gothenburg', 'Sweden', '888-888-888', 'Onia.lucy@lucky.com', '22/5/1995', 26);

-- Table: Hotel
CREATE TABLE Hotel (Hotel_ID INTEGER PRIMARY KEY NOT NULL, Hotel_Name VARCHAR, City VARCHAR, Country VARCHAR, Distance_To_Center INT, Distance_To_Beach INT);
INSERT INTO Hotel (Hotel_ID, Hotel_Name, City, Country, Distance_To_Center, Distance_To_Beach) VALUES (1, 'Wizatis Inc', 'Angana', 'India', 2453, 183);
INSERT INTO Hotel (Hotel_ID, Hotel_Name, City, Country, Distance_To_Center, Distance_To_Beach) VALUES (2, 'Simonis Inc', 'Kalmar', 'Sweden', 3673, 266);
INSERT INTO Hotel (Hotel_ID, Hotel_Name, City, Country, Distance_To_Center, Distance_To_Beach) VALUES (3, 'Smitham Inc', 'Sanjia', 'China', 3114, 768);
INSERT INTO Hotel (Hotel_ID, Hotel_Name, City, Country, Distance_To_Center, Distance_To_Beach) VALUES (4, 'Jerdele Inc', 'Staril', 'Serbia', 3544, 195);
INSERT INTO Hotel (Hotel_ID, Hotel_Name, City, Country, Distance_To_Center, Distance_To_Beach) VALUES (5, 'Volkman Inc', 'Peking', 'China', 4968, 417);

-- Table: Option
CREATE TABLE Option (Option_ID REFERENCES Option_Info (Option_ID), Booking_ID REFERENCES Booking);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (2, 2);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (2, 3);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (3, 1);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (2, 4);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (2, 5);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (3, 2);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (2, 1);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (2, 53);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (2, 2);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (3, 55);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (2, 55);
INSERT INTO Option (Option_ID, Booking_ID) VALUES (3, 55);

-- Table: Option_Info
CREATE TABLE Option_Info (Option_ID INTEGER PRIMARY KEY NOT NULL, Description VARCHAR);
INSERT INTO Option_Info (Option_ID, Description) VALUES (1, 'Extra bed');
INSERT INTO Option_Info (Option_ID, Description) VALUES (2, '2 Meals Included');
INSERT INTO Option_Info (Option_ID, Description) VALUES (3, '3 Meals Incluced');

-- Table: Review
CREATE TABLE Review (Review_ID INT REFERENCES Review_Info (Review_ID), Hotel_ID INT REFERENCES Hotel (Hotel_ID));
INSERT INTO Review (Review_ID, Hotel_ID) VALUES (1, 1);
INSERT INTO Review (Review_ID, Hotel_ID) VALUES (2, 4);
INSERT INTO Review (Review_ID, Hotel_ID) VALUES (3, 5);
INSERT INTO Review (Review_ID, Hotel_ID) VALUES (4, 2);
INSERT INTO Review (Review_ID, Hotel_ID) VALUES (4, 5);
INSERT INTO Review (Review_ID, Hotel_ID) VALUES (1, 2);

-- Table: Review_Info
CREATE TABLE Review_Info (Review_ID INTEGER PRIMARY KEY NOT NULL, Review VARCHAR);
INSERT INTO Review_Info (Review_ID, Review) VALUES (1, 'Very Bad');
INSERT INTO Review_Info (Review_ID, Review) VALUES (2, 'Bad');
INSERT INTO Review_Info (Review_ID, Review) VALUES (3, 'Neutral');
INSERT INTO Review_Info (Review_ID, Review) VALUES (4, 'Good');
INSERT INTO Review_Info (Review_ID, Review) VALUES (5, 'Very Good');

-- Table: Room
CREATE TABLE Room (Room_ID INT PRIMARY KEY, Room_Name INT, Room_Size INT, Hotel_ID REFERENCES Hotel (Hotel_ID), Price INT);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (1, 27, 3, 3, 1718);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (2, 28, 4, 4, 1821);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (3, 17, 3, 5, 2027);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (4, 3, 3, 4, 1290);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (5, 22, 3, 5, 1794);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (6, 12, 2, 5, 2474);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (7, 27, 2, 3, 2894);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (8, 11, 3, 4, 1786);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (9, 18, 3, 3, 1095);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (10, 7, 2, 2, 2093);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (11, 26, 4, 2, 2596);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (12, 26, 2, 5, 2130);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (13, 25, 4, 4, 1222);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (14, 22, 3, 2, 1893);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (15, 14, 2, 1, 935);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (16, 27, 4, 4, 2200);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (17, 25, 2, 2, 1049);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (18, 11, 3, 3, 2206);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (19, 3, 3, 3, 1867);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (20, 30, 2, 1, 1600);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (21, 10, 2, 4, 2503);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (22, 2, 4, 5, 2020);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (23, 22, 4, 1, 2103);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (24, 19, 3, 5, 1782);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (25, 6, 2, 4, 2509);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (26, 1, 3, 1, 1097);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (27, 11, 4, 2, 2729);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (28, 9, 2, 1, 2266);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (29, 14, 2, 1, 1800);
INSERT INTO Room (Room_ID, Room_Name, Room_Size, Hotel_ID, Price) VALUES (30, 13, 3, 1, 1416);

-- View: Search for option description by option id
CREATE VIEW "Search for option description by option id" AS SELECT * FROM Option_Info
WHERE Option_Info.Option_ID = 2;

-- View: view booking info with option by customer id
CREATE VIEW "view booking info with option by customer id" AS SELECT Customer.First_Name, Customer.Last_Name,  Calendar.Calendar_ID, Calendar.Room_ID, Room.Room_Name, Hotel.Hotel_Name, Calendar.CheckIn_Date, Calendar.CheckOut_Date, Option.Option_ID, Option_Info.Description FROM Booking
INNER JOIN Customer ON Customer.Customer_ID = Booking.Customer_ID
INNER JOIN Calendar ON Calendar.Calendar_ID = Booking.Calendar_ID
INNER JOIN Option ON Option.Booking_ID = Booking.Booking_ID
INNER JOIN Option_Info ON Option_Info.Option_ID = Option.Option_ID
INNER JOIN Room ON Room.Room_ID = Calendar.Room_ID
INNER JOIN Hotel ON Hotel.Hotel_ID = Room.Hotel_ID
WHERE Customer.Customer_ID = 33;

-- View: view booking options
CREATE VIEW "view booking options" AS SELECT Option.Option_ID,Booking.Booking_ID,Booking.Customer_ID,Booking.Calendar_ID,Option_Info.Description FROM Option
INNER JOIN Booking ON Booking.Booking_ID = Option.Booking_ID
INNER JOIN Option_Info ON Option.Option_ID = Option_Info.Option_ID
WHERE Booking.Booking_ID = 1;

-- View: view booking options by booking id
CREATE VIEW "view booking options by booking id" AS SELECT Option.Option_ID,Booking.Booking_ID,Booking.Customer_ID,Booking.Calendar_ID,Option_Info.Description FROM Option
                INNER JOIN Booking ON Booking.Booking_ID = Option.Booking_ID
                INNER JOIN Option_Info ON Option.Option_ID = Option_Info.Option_ID
                WHERE Booking.Booking_ID = 2;

-- View: view company
CREATE VIEW "view company" AS SELECT * FROM Company;

-- View: view customer
CREATE VIEW "view customer" AS SELECT * FROM Customer;

-- View: view hotel
CREATE VIEW "view hotel" AS SELECT * FROM Hotel;

-- View: view hotel amenities description
CREATE VIEW "view hotel amenities description" AS SELECT Amenity_Info.Amenity_ID, Hotel.Hotel_ID, Hotel.Hotel_Name, Description FROM Amenity_Info
                INNER JOIN Hotel ON Hotel.Hotel_ID = Amenity.Hotel_ID
                INNER JOIN Amenity ON Amenity.Amenity_ID = Amenity_Info.Amenity_ID
                WHERE Hotel.Hotel_ID = 2
                GROUP BY Amenity_Info.Description;

-- View: view room and price available by check in check out day
CREATE VIEW "view room and price available by check in check out day" AS SELECT Room.Room_ID, Room.Room_Name, Room.Room_Size, Hotel.Hotel_ID, Hotel.Hotel_Name, Hotel.City, Hotel.Country,Calendar.CheckIn_Date as Check_In,Calendar.CheckOut_Date as Check_Out,Room.Price FROM Room 
LEFT JOIN Calendar ON Room.Room_ID = Calendar.Room_ID
INNER JOIN Hotel ON Hotel.Hotel_ID = Room.Hotel_ID
WHERE  Room.Room_Size = 2 AND Hotel.Hotel_ID = 1 AND Calendar.CheckOut_Date  <= '2022-06-21'
OR Room.Room_Size = 2 AND Hotel.Hotel_ID = 1 AND Calendar.CheckIn_Date >= '2022-06-25'
OR Room.Room_Size = 2 AND Hotel.Hotel_ID = 1 AND Calendar.Calendar_ID IS  NULL
GROUP BY Room.Room_ID  
ORDER BY Room.Price ASC;

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
