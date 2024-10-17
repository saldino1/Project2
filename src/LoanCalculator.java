import java.util.Scanner;

public class LoanCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String option = "";
        while (!option.equalsIgnoreCase("Q")) {
            
            displayMenu();
            
            option = scanner.next();
            
            if (option.equalsIgnoreCase("P") || option.equals("T")) {
                
                //Add code here to gather user input and output payment or table
                
            }
            else if (!option.equalsIgnoreCase("Q")) {
                
                System.out.println("Invalid option");
            }
        }

        scanner.close();
    }

    public static void displayMenu() {
    
        System.out.println("Loan Calculator - Please choose an option.");
        System.out.println("");
        System.out.println("P - Payment");
        System.out.println("T - Table");
        System.out.println("Q - Quit");
        System.out.print("Option: ");
        
    }

    //Calculates and returns monthly payment amount using formula given above
    //
    //Throws an IllegalArgumentException with the message, "Invalid amount",
    //if loanAmount less than or equal to 0
    //
    //Throws an IllegalArgumentException with the message, "Invalid rate",
    //if annualInterestRate less than or equal to 0 
    //
    //Throws an IllegalArgumentException with the message, "Invalid months",
    //if numberOfMonths less than or equal to 0  
    //
    //NOTE: You must check for these error conditions in the order given above.
    public static double calculatePayment(int loanAmount, int annualInterestRate,
    int numberOfMonths) { 
        return 0;
    }

    //Returns a 2D array of doubles with a row for each month and
    //three columns. 
    //Column 0 for a row contains the portion of that row's monthly payment
    //used to pay the monthly interest on the loan balance.
    //Column 1 for a row contains the portion of that row's monthly payment 
    //used to decrease the loan balance
    //Column 2 for a row contains the remaining loan balance after the
    //monthly payment
    //
    //Throws an IllegalArgumentException with the message, "Invalid amount",
    //if loanAmount less than or equal to 0
    //
    //Throws an IllegalArgumentException with the message, "Invalid rate",
    //if annualInterestRate less than or equal to 0 
    //
    //Throws an IllegalArgumentException with the message, "Invalid months",
    //if numberOfMonths less than or equal to 0  
    //
    //NOTE: You must check for these error conditions in the order given above.
    //
    //HINT: Use the calculatePayment() method to get the monthly payment amount
    //and also check for the error conditions
    public static double[][] getTable(int loanAmount, int annualInterestRate,
    int numberOfMonths ) {
        return null;
    }

    //Returns a formatted version of the amortization table for a loan
    //that contains a String for each row followed by a newline character
    //
    //Use the toString() method below to get a formatted String for 
    //each row that contains the month number, payment amount, interest
    //portion of the monthly payment, loan portion of the monthly payment,
    //and remaining balance.
    //
    //Throws an IllegalArgumentException with the message, "Null table",
    //if table is null
    //
    //Throws an IllegalArgumentException with the message, "Empty table",
    //if the number of rows in the table is 0.
    //
    //Throws an IllegalArgumentException with the message, "Invalid rows",
    //if each row does not have exactly 3 elements.
    //
    //NOTE: You must check for these error conditions in the order given above.
    public static String toString(double payment, double[][] table) {
        return null;                
    }


    //Returns a formatted string for a row of the table and uses the absolute value
    //of the balance to handle the case where the final balance may be a very
    //small negative number.
    //
    //NOTE: Use this method in the above toString() method
    public static String toString(int number, double payment, 
    double interest, double loan, double balance) {
        return String.format("%4d %10.2f %9.2f %8.2f %9.2f", number, payment,
            interest, loan, Math.abs(balance));
    } 
}