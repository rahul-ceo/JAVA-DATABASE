package com;

import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDemo {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String uname = "root";
        String pass = "Rahul@81"; // Use your real MySQL password
        Database db = null;
        Scanner scan = new Scanner(System.in);
        String table = null;

        while (true) {
            System.out.println("1.Create Database\n2.Create Table\n3.Insert\n4.Delete\n5.Update\n6.Retrieve\n7.Exit");
            int action = scan.nextInt();

            switch (action) {
                case 1:
                    db = new Database(url, uname, pass);
                    System.out.println("Database Name:");
                    db.createDatabase(scan.next());
                    break;
                case 2:
                    System.out.println("Table Name:");
                    table = scan.next();
                    db.createTable(table);
                    break;
                case 3:
                    if (table == null) {
                        System.out.println("Table not created yet.");
                        break;
                    }
                    System.out.println("Enter Name & CGPA:");
                    db.insertVar(table, new Student(scan.next(), scan.nextFloat()));
                    break;
                case 4:
                    if (table == null) {
                        System.out.println("Table not created yet.");
                        break;
                    }
                    System.out.println("Enter RollNo to delete:");
                    db.delete(table, scan.nextInt());
                    break;
                case 5:
                    if (table == null) {
                        System.out.println("Table not created yet.");
                        break;
                    }
                    System.out.println("Enter RollNo & New CGPA:");
                    db.update(table, scan.nextInt(), scan.nextFloat());
                    break;
                case 6:
                    if (table == null) {
                        System.out.println("Table not created yet.");
                        break;
                    }
                    db.retreive(table);
                    break;
                case 7:
                    System.out.println("Thank you!");
                    System.exit(0);
            }
        }
    }
}