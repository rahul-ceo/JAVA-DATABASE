package com;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;


public class Database {
    String url, uname, pass, db;
    String query;
    Connection con;

    public Database(String url, String uname, String pass) throws SQLException {
        this.url = url;
        this.uname = uname;
        this.pass = pass;
        con = DriverManager.getConnection(url, uname, pass);
    }

    public void createDatabase(String db) throws SQLException {
        this.db = db;
        query = "create database if not exists " + this.db;
        Statement st = con.createStatement();
        st.executeUpdate(query);
        System.out.println(this.db + " database created.");
        con.close();
    }

    public void createTable(String table) throws SQLException {
        url += db;
        con = DriverManager.getConnection(url, uname, pass);
        query = "create table if not exists " + table + "(" +
                "rollno integer primary key," +
                "name varchar(40)," +
                "cgpa float," +
                "college varchar(20))";
        Statement st = con.createStatement();
        st.executeUpdate(query);
        System.out.println(table + " table created");
    }

    public void insertVar(String table, Student stu) throws SQLException {
        this.query = "insert into " + table + " values(?, ?, ?, ?)";
        con = DriverManager.getConnection(url, uname, pass);
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, stu.rollNo);
        st.setString(2, stu.name);
        st.setFloat(3, stu.cgpa);
        st.setString(4, Student.college);
        int row = st.executeUpdate();
        System.out.println(row + " row/s inserted.");
        con.close();
    }

    public void delete(String table, int rollNo) throws SQLException {
        con = DriverManager.getConnection(url, uname, pass);
        this.query = "delete from " + table + " where rollno=" + rollNo;
        Statement st = con.createStatement();
        int row = st.executeUpdate(query);
        System.out.println(row + " row/s deleted.");
        con.close();
    }

    public void update(String table, int rollNo, float cgpa) throws SQLException {
        con = DriverManager.getConnection(url, uname, pass);
        this.query = "update " + table + " set cgpa=? where rollNo=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setFloat(1, cgpa);
        st.setInt(2, rollNo);
        int row = st.executeUpdate();
        System.out.println(row + " row/s updated.");
        con.close();
    }

    public void retreive(String table) throws SQLException {
        con = DriverManager.getConnection(url, uname, pass);
        this.query = "select * from " + table;
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        while (result.next()) {
            System.out.print(result.getInt(1) + " ");
            System.out.print(result.getString(2) + " ");
            System.out.print(result.getFloat(3) + " ");
            System.out.println(result.getString(4));
        }
        con.close();
    }
}