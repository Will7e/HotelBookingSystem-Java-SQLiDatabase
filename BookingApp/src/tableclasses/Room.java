package tableclasses;

public class Room {
    public int roomId;
    public int roomNumber;
    public int roomSize;
    public int hotelId;
    public int price;

    public Room(int roomId, int roomNumber, int roomSize,int hotelId, int price){
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
        this.hotelId = hotelId;
        this.price = price;

    }
}
