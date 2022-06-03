import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        startUI(scanner);
    }

    private static void startUI(Scanner scanner) {
        System.out.print("Enter the number of rows:\n> ");
        int numberOfRows = scanner.nextInt();

        System.out.print("Enter the number of seats in each row:\n> ");
        int numberOfSeats = scanner.nextInt();
        int numTickets=0,income=0,totalIncome;
        String[][] cinema = createCinemaRoom(numberOfRows, numberOfSeats);

        while(true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            System.out.print("> ");
            int op = scanner.nextInt();
            System.out.println();
            if (op == 1) {
                showCinemaSeats(numberOfRows, numberOfSeats, cinema);
            } else if (op == 2) {
                numTickets++;

                System.out.print("Enter a row number:\n> ");
                int rowNumber = scanner.nextInt();
                System.out.print("Enter a seat number in that row:\n> \n");
                int seatNumber = scanner.nextInt();
                if(rowNumber>numberOfRows||seatNumber>numberOfSeats){
                    System.out.println("\nWrong input!\n");
                    System.out.print("Enter a row number:\n> ");
                    int rowNumber1 = scanner.nextInt();
                    System.out.print("Enter a seat number in that row:\n> \n");
                    int seatNumber1 = scanner.nextInt();
                    cinema[rowNumber1 - 1][seatNumber1 - 1] = " B";
                    System.out.println("Ticket price: $" + calculateTicket(numberOfRows, numberOfSeats, rowNumber1));
                }else if(cinema[rowNumber - 1][seatNumber - 1] == " B"){
                    System.out.println("That ticket has already been purchased!\n");
                    System.out.print("Enter a row number:\n> ");
                    int rowNumber1 = scanner.nextInt();
                    System.out.print("Enter a seat number in that row:\n> \n");
                    int seatNumber1 = scanner.nextInt();
                    cinema[rowNumber1 - 1][seatNumber1 - 1] = " B";
                    System.out.println("Ticket price: $" + calculateTicket(numberOfRows, numberOfSeats, rowNumber1));

                }
                else {
                    cinema[rowNumber - 1][seatNumber - 1] = " B";
                    System.out.println("Ticket price: $" + calculateTicket(numberOfRows, numberOfSeats, rowNumber));

                }
                income+=calculateTicket(numberOfRows,numberOfSeats,rowNumber);
            }  else if (op == 3) {
                System.out.println("Number of purchased tickets: "+numTickets);
                System.out.println("Current income: $"+income);
                System.out.println("Total income: $"+totalIn(numberOfRows,numberOfSeats));

            } else if (op!=1&&op!=2&&op!=3) {
                break;
            }
        }
    }

    private static void showCinemaSeats(int numberOfRows, int numberOfSeats, String[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  ");

        for (int i = 1; i <= numberOfSeats; i++) {
            System.out.print(i + " ");
        }

        System.out.println();
        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(i + 1);

            for (int j = 0; j < numberOfSeats; j++) {
                System.out.print(cinema[i][j]);
            }

            System.out.println();
        }
    }

    private static String[][] createCinemaRoom(int numberOfRows, int numberOfSeats) {
        String[][] cinema = new String[numberOfRows][numberOfSeats];

        for (String[] strings : cinema) {
            Arrays.fill(strings, " S");
        }

        return cinema;
    }

    private static int calculateTicket(int numberOfRows, int numberOfSeats, int rowNumber) {
        int firstHalf = numberOfRows / 2;
        int secondHalf = firstHalf + 1;
        int calculateTicket = rowNumber <= (numberOfRows % 2 == 0 ? secondHalf : firstHalf) ? 10 : 8;
        return numberOfRows * numberOfSeats < 60 ? 10 : calculateTicket;
    }
    public static  int totalIn(int numberOfRows, int numberOfSeats){
        int firstHalf = numberOfRows / 2;
        int secondHalf = firstHalf + 1;
        return numberOfRows * numberOfSeats < 60 ? numberOfRows * numberOfSeats*10 : (firstHalf*8+secondHalf*10);
    }
}