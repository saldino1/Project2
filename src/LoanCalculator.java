import java.util.Scanner;

/**
 * This class represents a loan calculator
 * @author Amelia Saldino
 * @version 1.0
 */

public class LoanCalculator {

    /** Minimum loan amount accepted by program */
    public static final int MIN_LOAN_AMOUNT = 1000;

    /** Maximum loan amount accepted by program */
    public static final int MAX_LOAN_AMOUNT = 1000000;

    /** Minimum interest rate accepted by program */
    public static final int MIN_INTEREST_RATE = 1;

    /** Maximum interest rate accepted by program */
    public static final int MAX_INTEREST_RATE = 10;

    /** Minimum number of months to pay back loan */
    public static final int MIN_MONTHS = 2;

    /** Maximum number of months of pay back loan */
    public static final int MAX_MONTHS = 360;

    /** Number of months in year */
    public static final double MONTHS_IN_YEAR = 12.0;

    /** Used to convert between a number and its percent */
    public static final double PERCENT = 100.0;

    /** Desired number of columns in the amortization table */
    public static final int COLUMNS_IN_TABLE = 3;

    /**
     * The entry point of the application.
     * @param args Command-line arguments provided to the application.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String option = "";

        while (!option.equalsIgnoreCase("Q")) {
            
            displayMenu();
            
            option = scanner.next();
            
            if (option.equalsIgnoreCase("P") || option.equalsIgnoreCase("T")) {
                
                // Loan entering + error handling
                System.out.printf("Loan Amount(%i - %i): ", MIN_LOAN_AMOUNT, MAX_LOAN_AMOUNT);
                if(!scanner.hasNextInt()){
                    System.out.println("Invalid Amount");
                    continue;
                }
                int loanAmount = scanner.nextInt();
                if(loanAmount < MIN_LOAN_AMOUNT || loanAmount > MAX_LOAN_AMOUNT){
                    System.out.println("Invalid Amount");
                    continue;
                }

                // Rate entering + error handling
                System.out.printf("Interest Rate(1 - %i): ", MAX_INTEREST_RATE);
                if(!scanner.hasNextInt()){
                    System.out.println("Invalid Rate");
                    continue;
                }
                int interestRate = scanner.nextInt();
                if(interestRate < MIN_INTEREST_RATE || interestRate > MAX_INTEREST_RATE){
                    System.out.println("Invalid Rate");
                    continue;
                }

                // Months entering + error handling
                System.out.print("Number of Months(2 - 360): ");
                if(!scanner.hasNextInt()){
                    System.out.println("Invalid months");
                    continue;
                }
                int monthsNum = scanner.nextInt();
                if(monthsNum < MIN_MONTHS || monthsNum > MAX_MONTHS){
                    System.out.println("Invalid months");
                    continue;
                }
                
                if(option.equalsIgnoreCase("P")) {
                    System.out.printf("Monthly Payment: $%.2f\n", 
                        calculatePayment(loanAmount, interestRate, monthsNum));
                }
                if(option.equalsIgnoreCase("T")){
                    System.out.print(
                        "Number  Payment  Interest     Loan   Balance\n" 
                        + "--------------------------------------------\n" 
                        + toString(calculatePayment(loanAmount, interestRate, monthsNum), 
                        getTable(loanAmount, interestRate, monthsNum)));
                }
            }
            else if (!option.equalsIgnoreCase("Q")) {
                
                System.out.println("Invalid option");
            }
        }

        scanner.close();
    }

    /**
     * Prints out display menu
     */
    public static void displayMenu() {
        System.out.println("Loan Calculator - Please choose an option.");
        System.out.println("");
        System.out.println("P - Payment");
        System.out.println("T - Table");
        System.out.println("Q - Quit");
        System.out.print("Option: ");
    }

    /**
     * Gets and returns the montly payment using loan formula
     * 
     * @param loanAmount amount of money loaned
     * @param annualInterestRate annual interest rate as int from 1 - 10
     * @param numberOfMonths number of months planned to repay the loan
     * @return the calculated montly payment
     * @throws IllegalArgumentException if loanAmount, 
     * annualInterestRate, or numberOfMonths is less than or equal to 0
     */
    public static double calculatePayment(int loanAmount, int annualInterestRate,
        int numberOfMonths) { 

        if(loanAmount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if(annualInterestRate <= 0) {
            throw new IllegalArgumentException("Invalid rate");
        }

        if(numberOfMonths <= 0) {
            throw new IllegalArgumentException("Invalid months");
        }

        double monthlyInterest = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        if (monthlyInterest == 0) {
            return loanAmount / (double) numberOfMonths;    
        }

        double numerator = monthlyInterest * Math.pow((1 + monthlyInterest), numberOfMonths);
        double denominator = Math.pow((1 + monthlyInterest), numberOfMonths) - 1;
        double fraction = numerator / denominator;

        return fraction * loanAmount;
    }

    /**
     * Gets and returns an amortization table of double with interest payed,
     * payment to decrese and remaining loan balance
     * @param loanAmount total loan amount
     * @param annualInterestRate annual interest rate as int
     * @param numberOfMonths number of months planned to pay back
     * @return 2D array of doubles representing an amortization table
     * @throws IllegalArgumentException if loanAmount, 
     * annualInterestRate, or numberOfMonths is less than or equal to 0
     */
    public static double[][] getTable(int loanAmount, int annualInterestRate,
        int numberOfMonths ) {
        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if (annualInterestRate <= 0) {
            throw new IllegalArgumentException("Invalid rate");
        }

        if (numberOfMonths <= 0) {
            throw new IllegalArgumentException("Invalid months");
        }

        double[][] table = new double[numberOfMonths][COLUMNS_IN_TABLE];
        double remainingAmount = loanAmount;
        double payment = calculatePayment(loanAmount, annualInterestRate, numberOfMonths);

        for(int i = 0; i < numberOfMonths; ++i) {
            table[i][0] = remainingAmount * ((annualInterestRate / PERCENT) / MONTHS_IN_YEAR);
            table[i][1] = payment - table[i][0];
            remainingAmount -= table[i][1];
            table[i][2] = remainingAmount;
            if (remainingAmount < 0) {
                remainingAmount = 0;
            }
        }

        return table;
    }

    /**
     * Returns a formatted version of the amortization table for a loan
     * @param payment monthly payment
     * @param table generated amortization table
     * @return String formatted correctly to print of amortization table
     * @throws IllegalArgumentException if the table is null, the number of rows is 0, 
     * or any of the rows have no columns
     */
    public static String toString(double payment, double[][] table) {
        if (table == null) {
            throw new IllegalArgumentException("Null table");
        }

        if (table.length == 0) {
            throw new IllegalArgumentException("Empty table");
        }

        for (double[] row : table) {
            if (row.length != COLUMNS_IN_TABLE) {
                throw new IllegalArgumentException("Invalid rows");
            }
        }

        String output = "";
        for(int i = 0; i < table.length; ++i){
            output = output + 
                toString(i + 1, payment, table[i][0], table[i][1], table[i][2]) 
                + "\n";
        }
        
        return output;                
    }

    /**
     * Returns a formatted string for a row of the table and uses the absolute value 
     * of the balance to handle the case where the final balance may be a very 
     * small negative number.
     * @param number row number
     * @param payment monthly payment
     * @param interest interest payment monthly
     * @param loan loan amount
     * @param balance balance left
     * @return String version of one row in the amortization table
     */
    public static String toString(int number, double payment, 
        double interest, double loan, double balance) {
        return String.format("%4d %10.2f %9.2f %8.2f %9.2f", number, payment,
            interest, loan, Math.abs(balance));
    } 
}