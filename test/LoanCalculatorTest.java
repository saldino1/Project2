import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Program to test LoanCalculator methods
 * @author Suzanne Balik
 * @author Amelia Saldino
 */
public class LoanCalculatorTest {
    
    /** Constant for margin of error when comparing doubles */
    public static final double DELTA = 0.01;

    /**
     * Tests the calculation of monthly payments for a loan of 1000, 
     * interest rate of 5%, and period of 12 months
     */
    @Test
    public void testCalculatePayment1000Interest5Months12() {

        assertEquals(85.61, LoanCalculator.calculatePayment(1000, 5, 12), DELTA, 
                     "LoanCalculator.calculatePayment(1000,5,12)");
    }

    /**
     * Tests the calculation of monthly payments for a loan of 1450, 
     * interest rate of 8%, and period of 2 months
     */
    @Test
    public void testCalculatePayment1450Interest8Months2() {

        assertEquals(732.26, LoanCalculator.calculatePayment(1450, 8, 2), DELTA, 
                     "LoanCalculator.calculatePayment(1450,8,2)");

    }   

    /**
     * Tests the calculation of monthly payments for a loan of 2000, 
     * interest rate of 1%, and period of 4 months
     */
    @Test
    public void testCalculatePayment2000Interest1Months4() {

        assertEquals(501.04, LoanCalculator.calculatePayment(2000, 1, 4), DELTA, 
                     "LoanCalculator.calculatePayment(2000,1,4)");

    }  

    /**
     * Tests the calculation of monthly payments for a loan of 10000, 
     * interest rate of 7%, and period of 3 months
     */
    @Test
    public void testCalculatePayment10000Interest7Months3() {

        assertEquals(3372.30, LoanCalculator.calculatePayment(10000, 7, 3), DELTA, 
                     "LoanCalculator.calculatePayment(10000,7,3)");

    }

    /**
     * Tests the creation of an amortization table for a loan of 1000 
     * interest rate of 5%, and a period of 12 months
     */
    @Test
    public void testGetTable1000Interest5Months12() {

        double[][] expected =  {{4.17, 81.44, 918.56},
                                {3.83, 81.78, 836.78},
                                {3.49, 82.12, 754.66},
                                {3.14, 82.46, 672.20},
                                {2.80, 82.81, 589.39},
                                {2.46, 83.15, 506.24},
                                {2.11, 83.50, 422.74},
                                {1.76, 83.85, 338.89},
                                {1.41, 84.20, 254.70},
                                {1.06, 84.55, 170.15},
                                {0.71, 84.90,  85.25},
                                {0.36, 85.25,   0.00}};
        double[][] actual = LoanCalculator.getTable(1000, 5, 12);
        assertEquals(expected.length, actual.length, "Correct length for first dimension");
        testTwoDArrays(expected, actual);
    }
    
    /**
     * Tests the creation of an amortization table for a loan of 1450 
     * interest rate of 8%, and a period of 2 months
     */
    @Test
    public void testGetTable1450Interest8Months2() {
        double[][] expected = {{9.67, 722.59, 727.41},
                               {4.85, 727.41,   0.00}};
        double[][] actual = LoanCalculator.getTable(1450, 8, 2);
        assertEquals(expected.length, actual.length, "Correct length for first dimension");
        testTwoDArrays(expected, actual);
    }
    
    /**
     * Tests the creation of an amortization table for a loan of 2000 
     * interest rate of 1%, and a period of 4 months
     */
    @Test
    public void testGetTable2000Interest1Months4() {
        double[][] expected = {{1.67, 499.38, 1500.62},
                               {1.25, 499.79, 1000.83},
                               {0.83, 500.21, 500.62},
                               {0.42, 500.62, 0.00}};
        double[][] actual = LoanCalculator.getTable(2000, 1, 4);
        assertEquals(expected.length, actual.length, "Correct length for first dimension");
        testTwoDArrays(expected, actual);
    }

    /**
     * Tests the creation of an amortization table for a loan of 10000 
     * interest rate of 7%, and a period of 3 months
     */
    @Test
    public void testGetTable10000Interest7Months3() {
        double[][] expected = {{58.33, 3313.96, 6686.04},
                               {39.00, 3333.30, 3352.74},
                               {19.56, 3352.74, 0.00}};
        double[][] actual = LoanCalculator.getTable(10000, 7, 3);
        assertEquals(expected.length, actual.length, "Correct length for first dimension");
        testTwoDArrays(expected, actual);
    }

    /**
     * Tests creating a string out of amortization table for a loan of 1450 
     * interest rate of 8%, and a period of 2 months
     */
    @Test
    public void testToString1450Interest8Months2() {
        String expected = "   1     732.26      9.67   722.59    727.41\n" + 
                          "   2     732.26      4.85   727.41      0.00\n";
        double[][] table = {{9.666666666666668, 722.5913621262495, 727.4086378737505},
                            {4.8493909191583375, 727.4086378737578, -7.275957614183426E-12}};
        double payment = 732.2580287929161;
                            
        String actual = LoanCalculator.toString(payment, table);
        assertEquals(expected, actual, "LoanCalculator.toString(payment, table)");
        
        double[][] copy = {{9.666666666666668, 722.5913621262495, 727.4086378737505},
                           {4.8493909191583375, 727.4086378737578, -7.275957614183426E-12}};
        testTwoDArrays(copy, table);
    }
    
    /**
     * Tests that expected and actual are considered equal based on DELTA
     * 
     * @param expected expected array
     * @param actual array to compare to expected
     */
    public void testTwoDArrays(double[][] expected, double[][] actual) {
        for(int i = 0; i < expected.length; i++){
            assertArrayEquals(expected[i], actual[i], DELTA, "Correct values for row " + i);
        } 
    }
    
    /**
     * Test the LoanCalculator methods with invalid values
     */
    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.calculatePayment(0,-1,-2), 
                  "LoanCalculator.calculatePayment(0,-1,-2)");
        assertEquals("Invalid amount", exception.getMessage(),
                     "Testing LoanCalculator.calculatePayment(0,-1,-2) - exception message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.calculatePayment(100,-1,-2), 
                  "LoanCalculator.calculatePayment(100,-1,-2)");
        assertEquals("Invalid rate", exception.getMessage(),
                     "Testing LoanCalculator.calculatePayment(100,-1,-2) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.calculatePayment(100,1,-2), 
                  "LoanCalculator.calculatePayment(100,1,-2)");
        assertEquals("Invalid months", exception.getMessage(),
                     "Testing LoanCalculator.calculatePayment(100,1,-2) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.getTable(0,-1,-2), 
                  "LoanCalculator.getTable(0,-1,-2)");
        assertEquals("Invalid amount", exception.getMessage(),
                     "Testing LoanCalculator.getTable(0,-1,-2) - exception message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.getTable(100,-1,-2), 
                  "LoanCalculator.getTable(100,-1,-2)");
        assertEquals("Invalid rate", exception.getMessage(),
                     "Testing LoanCalculator.getTable(100,-1,-2) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.getTable(100,1,-2), 
                  "LoanCalculator.getTable(100,1,-2)");
        assertEquals("Invalid months", exception.getMessage(),
                     "Testing LoanCalculator.getTable(100,1,-2) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.toString(100,null), "LoanCalculator.toString(100,null)");
        assertEquals("Null table", exception.getMessage(),
                     "Testing LoanCalculator.toString(100,null) - exception message");
                     
        double[][] empty = new double[0][0];
        exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.toString(100,empty), "LoanCalculator.toString(100,empty)");
        assertEquals("Empty table", exception.getMessage(),
                     "Testing LoanCalculator.toString(100,empty) - exception message");
                     
        double[][] invalidRows = {{1, 2, 3},
                                  {4, 5},
                                  {6, 7, 8},
                                  {9, 10, 11, 12}};
        exception = assertThrows(IllegalArgumentException.class,
            () -> LoanCalculator.toString(100,invalidRows), 
                                          "LoanCalculator.toString(100,invalidRows)");
        assertEquals("Invalid rows", exception.getMessage(),
                     "Testing LoanCalculator.toString(100,invalidRows) - exception message");
    }
}