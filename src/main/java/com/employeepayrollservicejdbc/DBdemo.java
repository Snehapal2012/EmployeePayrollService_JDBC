package com.employeepayrollservicejdbc;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.util.Enumeration;
public class DBdemo {
    public static void main(String[] args) {
        String jdbcURL="jdbc:mysql://localhost:3306/paroll_service?useSSl=false";
        String userName="root";
        String password="MySQL@123";
        Connection con;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        }catch (ClassNotFoundException e){
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        listDriver();
        try{
            System.out.println("Connecting to Database:"+jdbcURL);
            con= DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Connection is successful!!!!!"+con);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void listDriver(){
        Enumeration<Driver> driverList=DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass=(Driver) driverList.nextElement();
            System.out.println("  "+driverClass.getClass().getName());
        }
    }
}
