import java.util.Scanner;
public class PlaneManagement {
    static Scanner scanner=new Scanner(System.in);
    private static int [][] seats={{0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    // create Ticket array to save ticket information
    private static Ticket [][] ticketsold = new Ticket[52][];

    public static void Main(){
        int option;
        do {
            System.out.println("""
                    *****************************************************
                    *                    MENU OPTION                    *
                    *****************************************************
                     1) Buy a seat
                     2) Cancel a seat
                     3) Find first available seat
                     4) Show seating plan
                     5) Print tickets information and total sales
                     6) Search ticket
                     0) Quit
                    ******************************************************""");

            System.out.print("Please select an option : ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }while (option!=0);
    }
    private static void buy_seat(){
        double price;
        String name,surname,email;

        System.out.print("Enter your row letter (A,B,C,D) : ");
        char row=scanner.next().toUpperCase().charAt(0);
        // validating row
        if (row>='A'&&row<='D'){
            System.out.print("Enter your seat number : ");
            int seat_no=scanner.nextInt();
            //validating seat number
            if (seat_no <= seats[row - 'A'].length){
                // check if seat is already booked
                if (seats[row-'A'][seat_no-1]==1){
                    System.out.println("your selected seat is already booked \n");
                }else {
                    // Mark seat as booked
                    seats[row-'A'][seat_no-1]=1;

                    System.out.print("Enter your name : ");
                    name=scanner.next();
                    System.out.print("Enter your Surname : ");
                    surname=scanner.next();
                    System.out.print("Enter your Email : ");
                    email=scanner.next();

                    Person person=new Person(name,surname,email);
                    System.out.println("Seat is booked successfully \n");

                    if (seat_no<=5){
                        price=200;
                    } else if (seat_no<=9) {
                        price=150;
                    }else {
                        price=180;
                    }
                    Ticket ticket =new Ticket(Character.toString(row),seat_no,price,person);
                    Ticket.save(Character.toString(row),seat_no,name,surname,email,price);
                    //  store Ticket information
                    for (int i=0;i<ticketsold.length;i++){
                        if (ticketsold[i]==null){
                            ticketsold[i]= new Ticket[]{ticket};
                            break;
                        }
                    }
                }
            }else {
                System.out.println("Invalid seat number, Please enter a correct seat number");
            }
        }else {
            System.out.println("Invalid  row, Please enter a correct row \n");
        }
    }
    private static void cancel_seat(){
        System.out.print("Enter your row letter (A,B,C,D) : ");
        char row=scanner.next().toUpperCase().charAt(0);
        // validating row
        if (row>='A'&&row<='D'){
            System.out.print("Enter your seat number : ");
            int seat_no=scanner.nextInt();
            //validating seat number
            if (seat_no <= seats[row - 'A'].length){
                if (seats[row-'A'][seat_no-1]==0){
                    System.out.println("you selected a wrong seat the seat is not booked \n");
                }else {
                    // mae seat as available
                    seats[row-'A'][seat_no-1]=0;
                    // delete  ticket information
                    for (Ticket[] ticketinfo:ticketsold){
                        if (ticketinfo != null){
                            for (Ticket tickets:ticketinfo){
                                if (tickets!= null && tickets.getRow().equals(Character.toString(row)) && tickets.getSeatno()==seat_no){
                                    ticketinfo=null;
                                    break;
                                }
                            }
                        }
                    }
                    Ticket.delete(Character.toString(row),seat_no);
                    System.out.println(" Seat cancelled successfully \n");
                }
            }else {
                System.out.println("Invalid seat number, Please enter correct a seat number \n");
            }
        }else {
            System.out.println("Invalid  row, Please enter a correct row \n");
        }
    }
    // create method to find first available seat
    private static void find_first_available(){
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    char row = (char) ('A' + i);
                    int seat_no = j + 1;
                    System.out.println("First available seat : " + row + seat_no + "\n");
                    return; // Return once the first available seat is found
                }
            }
        }
        System.out.println("No available seats. \n");
    }
    private static void show_seating_plan(){
        seat(seats[0]);         // call seat method for print seating plan
        seat(seats[1]);
        seat(seats[2]);
        seat(seats[3]);
    }
    // create method to print sold ticket information
    private static void print_tickets_info(){
        double totalprice=0;
        for (Ticket[] ticketinfo:ticketsold){
            if (ticketinfo != null){
                for (Ticket tickets:ticketinfo){
                    totalprice+=tickets.getPrice();
                    System.out.println(tickets.getRow()+tickets.getSeatno()+" : "+"£"+tickets.getPrice());
                }
            }
        }
        System.out.println("Total sales : £"+totalprice +"\n");
    }
    // create method to search a ticket
    private static void search_ticket(){
        System.out.print("Enter your row letter (A,B,C,D) : ");
        char row=scanner.next().toUpperCase().charAt(0);
        if (row>='A'&&row<='D'){
            System.out.print("Enter your seat number : ");
            int seat_no=scanner.nextInt();
            if (seat_no <= seats[row - 'A'].length){
                if (seats[row-'A'][seat_no-1]==1){
                    for (Ticket[] ticketinfo:ticketsold){
                        if (ticketinfo != null){
                            for (Ticket tickets:ticketinfo){
                                if (tickets != null && tickets.getRow().equals(Character.toString(row)) && tickets.getSeatno()==seat_no){
                                    System.out.println("Ticket Information");
                                    System.out.println("Ticket : "+tickets.getRow()+tickets.getSeatno());
                                    System.out.println("Passenger Name : "+tickets.getPerson().getName());
                                    System.out.println("Passenger Surname : "+tickets.getPerson().getSurname());
                                    System.out.println("E-mail address : "+tickets.getPerson().getEmail()+"\n");
                                    break;
                                }
                            }

                        }
                    }
                }else {
                    System.out.println("This seat is available \n ");
                }

            }else {
                System.out.println("Invalid seat number, Please enter correct a seat number \n");
            }
        }else {
            System.out.println("Invalid  row, Please enter a correct row \n");
        }
    }
    //create this method for show seating plane
    private static void seat(int[]seat){
        for (int element:seat){
            if (element==1){
                System.out.print("X");
            }else {
                System.out.print(element);
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        System.out.println("*      Welcome To PlaneManagement Application        *");
        // call the main method
        Main();
    }
}