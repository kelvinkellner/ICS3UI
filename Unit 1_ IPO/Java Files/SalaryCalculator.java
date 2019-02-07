// Kelvin Kellner
// This program will calculate a person's annual salary based on their wage and hours worked
// Tuesday, 13 February, 2018
// Mrs. Cooper

public class SalaryCalculator
{
  public static void main(String[] args)
  {
    // Declare variables to store the worker's information
    double hours = 30; // store how many hours per week the worker works
    double rate = 10.75; // store the worker's hourly wage
    
    // Create variables that calculate and store the worker's pay per different period of time
    double weeklyPay = hours * rate; // calculate the worker's weekly pay
    double monthlyPay = weeklyPay * 52 / 12; // calculate the worker's monthly pay, by finding and diving the annual pay by the number of months
    double annualPay = weeklyPay * 52; // calculate annual pay
    
    // Create variables that calculate and store deductions that will be removed from the gross annual pay
    double incomeTax = annualPay * 0.15; // income tax is 15% of annual gross pay
    double eI = annualPay * 0.017; // EI is calculated and stored as well, at 1.7%
    double cPP = annualPay * 0.049; // CPP is stored at 4.9%
    
    // Calculate and store the net annual pay by subtracting all the deductions from the gross annual pay
    double netAnnualPay = annualPay - incomeTax - eI - cPP;
    
    // Output the worker's gross salary information to the user
    System.out.println("By earning $" + rate + " per hour, and working " + hours + " hours per week..."); // state the worker's information
    System.out.println("Your weekly gross pay would be: $" + weeklyPay); // tell the user how much they would make per time wwek
    System.out.println("Your monthly gross pay would be: $" + monthlyPay); // pay per month
    System.out.println("Your annual gross pay would be: $" + annualPay); // pay per year
    
    // Account for deductions, and print net salary
    System.out.println("\nWe also can't forget to account for deductions..."); // simply a heading for the next section
    System.out.println("Income tax: $" + incomeTax + " (15% of annual gross)"); // tell the user the monetary value that is deducted from income tax, as well as the percentage of the total grossing
    System.out.println("EI: $" + eI + " (1.7% of annual gross)"); // the same goes for EI
    System.out.println("CPP: $" + cPP + " (4.9% of annual gross)"); // and CPP
    System.out.println("After accounting for deductions, your net salary will be: $" + netAnnualPay); // tell the user what the net salary will be after deductions
  } // end main
} // end class