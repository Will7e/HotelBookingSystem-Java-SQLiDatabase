package tableclasses;

public class Hotel {
    public int hotelId;
    public String name;
    public String city;
    public String country;
    public int distanceToBeach;
    public int getDistanceToCenter;

    public Hotel(int hotelId, String name, String city, String country, int distanceToBeach, int getDistanceToCenter) {
        this.hotelId = hotelId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.distanceToBeach = distanceToBeach;
        this.getDistanceToCenter = getDistanceToCenter;
    }
}
