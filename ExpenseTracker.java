import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {

    public static void main(String[] args) {
        // Created a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Created a list to store expense objects
        List<Expense> expenses = new ArrayList<>();

        // Created a map to store category-wise total spending
        Map<String, Double> categoryTotals = new HashMap<>();

        while (true) {
            // Display the main menu 
            System.out.println();
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add an expense");
            System.out.println("2. View expenses");
            System.out.println("3. View category-wise spending");
            System.out.println("4. View total spending");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Option 1: Add an expense
                    System.out.print("Enter the expense description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter the expense amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter the expense category: ");
                    String category = scanner.nextLine();
                    
                    // Created a new Expense object and add it to the expenses list
                    expenses.add(new Expense(description, amount, category));

                    // Updated the category totals with the new expense
                    categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);

                    System.out.println("Expense added successfully.");
                    break;
                case 2:
                    // Option 2: View expenses
                    System.out.println("Expenses:");
                    for (Expense expense : expenses) {
                        // Display each expense
                        System.out.println(expense);
                    }
                    break;
                case 3:
                    // Option 3: View category-wise spending
                    System.out.println("Category-wise spending:");
                    for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
                        // Display each category and its total spending
                        System.out.println(entry.getKey() + ": $" + entry.getValue());
                    }
                    break;
                case 4:
                    // Option 4: View total spending
                    double totalSpending = expenses.stream().mapToDouble(Expense::getAmount).sum();
                    System.out.println("Total spending: $" + totalSpending);
                    break;
                case 5:
                    // Option 5: Exit the program
                    System.out.println("Exiting Expense Tracker. Goodbye!"); 
                    System.out.println();
                    System.exit(0);
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again."); 
                    scanner.close();

            }
        }
    }
}

class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Amount: $" + amount + ", Category: " + category;
    }
}
