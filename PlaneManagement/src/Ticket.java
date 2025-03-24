import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private String Row;
    private int Seatno;
    private double Price;
    private Person person;

    public Ticket(String row, int seatno, double price, Person person) {
        this.Row = row;
        this.Seatno = seatno;
        this.Price = price;
        this.person = person;
    }

    public String getRow() {
        return Row;
    }

    public void setRow(String row) {
        this.Row = row;
    }

    public int getSeatno() {
        return Seatno;
    }

    public void setSeatno(int seatno) {
        this.Seatno = seatno;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static void save(String rowletter, int seatno, String name, String surname, String email, double price){
        File file = new File(rowletter+seatno+".txt");
        boolean file_created = false;
        try {
            file_created = file.createNewFile();
        } catch (IOException ex) {
            System.out.println("ERROR: File could not be created.");
        }
        if (file_created) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(rowletter+seatno+".txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                writer.write("Row : "+rowletter+"\n"+"Seat number : "+seatno+"\n"+"\n"+"Price : £"+price+"\n"+"First name : "+name+"\n"+"Surname : "+surname+"\n"+"E-mail : "+email);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void delete(String rowletter,int seatno){
        File file = new File(rowletter+seatno+".txt");
        if (file.exists()){file.delete();
            System.out.println("File deleted.");
        }else{
            System.out.println("File does not exist.");
        }
    }
}
