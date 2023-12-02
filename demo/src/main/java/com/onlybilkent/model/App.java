package com.example;
import java.sql.*;

public class App {
   
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();

        // Open the connection
        if (dataSource.open()) {
            try {
                // Create the database tables
                dataSource.createDB();

                // Add a user (example)
                //dataSource.addUser("anila@mail.com", "Anil", "123", 1, "HI");
                //dataSource.addUser("anil.altuncu@ug.bilkent.edu.tr", "Anil Altuncu", "123", 1, "HI");
                /**
                 * dataSource.checkMail("anila@hotmail.com");
                dataSource.checkMail("anila@mail.com");
                dataSource.checkMail("anil.altuncu@ug.bilkent.edu.tr");
                dataSource.checkPassword("anil.altuncu@ug.bilkent.edu.tr", "1234");
                dataSource.checkPassword("anil.altuncu@ug.bilkent.edu.tr", "123");
                System.out.println("User added successfully.");
                 */
                

                 
            } finally {
                // Close the connection in a finally block to ensure it is closed even if an exception occurs
                dataSource.close();
            }
        } else {
            System.out.println("Failed to open the database connection.");
        }
    }

}
