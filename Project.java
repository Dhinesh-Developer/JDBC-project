package Adv_Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Project {

    private static Connection connection;
    private static PreparedStatement statement;

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbcclasses";
        String username = "root";
        String password = "root@dk";

        Scanner in = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection(url, username, password);

            String choice;

            do {
                System.out.println("Choose an operation!");
                System.out.println("1. Insert a row");
                System.out.println("2. Update a row");
                System.out.println("3. Delete a row");

                int operation = in.nextInt();

                switch (operation) {
                    case 1:
                        String insertSQL = "INSERT INTO employee (id, name, email, dept, salary) VALUES (?, ?, ?, ?, ?)";
                        try {
                            statement = connection.prepareStatement(insertSQL);
                            System.out.println("Enter the id value: ");
                            int id = in.nextInt();
                            System.out.println("Enter the name: ");
                            String name = in.next();
                            System.out.println("Enter the email: ");
                            String email = in.next();
                            System.out.println("Enter the dept: ");
                            String dept = in.next();
                            System.out.println("Enter the salary: ");
                            int salary = in.nextInt();

                            statement.setInt(1, id);
                            statement.setString(2, name);
                            statement.setString(3, email);
                            statement.setString(4, dept);
                            statement.setInt(5, salary);

                            int res = statement.executeUpdate();
                            System.out.println(res + " row(s) inserted.");
                        } finally {
                            if (statement != null) {
                                statement.close();
                            }
                        }
                        break;

                    case 2:
                        String updateSQL = "UPDATE employee SET name = ?, email = ?, dept = ?, salary = ? WHERE id = ?";
                        try {
                            statement = connection.prepareStatement(updateSQL);
                            System.out.println("Enter the id value: ");
                            int id = in.nextInt();
                            System.out.println("Enter the name: ");
                            String name = in.next();
                            System.out.println("Enter the email: ");
                            String email = in.next();
                            System.out.println("Enter the dept: ");
                            String dept = in.next();
                            System.out.println("Enter the salary: ");
                            int salary = in.nextInt();

                            statement.setString(1, name);
                            statement.setString(2, email);
                            statement.setString(3, dept);
                            statement.setInt(4, salary);
                            statement.setInt(5, id);

                            int res = statement.executeUpdate();
                            System.out.println(res + " row(s) updated.");
                        } finally {
                            if (statement != null) {
                                statement.close();
                            }
                        }
                        break;

                    case 3:
                        String deleteSQL = "DELETE FROM employee WHERE id = ?";
                        try {
                            statement = connection.prepareStatement(deleteSQL);
                            System.out.println("Enter the id value to delete: ");
                            int id = in.nextInt();

                            statement.setInt(1, id);

                            int res = statement.executeUpdate();
                            System.out.println(res + " row(s) deleted.");
                        } finally {
                            if (statement != null) {
                                statement.close();
                            }
                        }
                        break;

                    default:
                        System.out.println("Invalid choice, please enter a number between 1 and 3.");
                        break;
                }

                System.out.println("Do you want to perform another operation (yes/no)?");
                choice = in.next();

            } while (choice.equalsIgnoreCase("yes"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close parting
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            in.close();
        }
    }
}
