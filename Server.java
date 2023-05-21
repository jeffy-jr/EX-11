
package com.jfr;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.System.*;

/**
 *
 * @author frsed
 */
@WebService(serviceName = "jfw")
public class jfw  extends HttpServlet {

    /**
     * This is a sample web service operation
     * 
     */
    @WebMethod(operationName = "fetch")
     public String[][] fetch() {                 //@WebParam(name = "name") String txt
         
         String[][] str = new String[4][4];
        HttpServletResponse response = null;
       String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3307/food";

        // Database credentials
        String USER = "root";
        String PASS = "";

        String title = "Database Result";

        System.out.println(title);
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM shows;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(" <style> th, td { ");
            System.out.println("padding-top: 10px;");
            System.out.println(" padding-bottom: 20px;");
            System.out.println("padding-left: 30px;");
            System.out.println("padding-right: 40px;");
            System.out.println("} </style><center><div><table border = 1 >");
            System.out.println("<tr><td> Name </td>'");
            System.out.println("<td> cuisine </td>");
            System.out.println("<td> price </td>");
            System.out.println("<td> feedback </td></tr>");
 
            
            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                String Name = rs.getString("Name");
                String cuisine = rs.getString("cuisine");
                String price = rs.getString("price");
                String feedback = rs.getString("feedback");

                // Display values
                System.out.println("<tr> <td>" + Name + " </td>");
                System.out.println("<td>" + cuisine + "</td>");
                System.out.println("<td>" + price + "</td>");
                System.out.println("<td >" + feedback + "</td>");
                System.out.println(
                        "<td > ");
                System.out.println("</td></tr> ");
                int j = 0;
                
                str[i][j] = Name;
                str[i][j+1] = cuisine;
                str[i][j+2] = price;
                str[i][j+3] = feedback;
                i++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Do not connect to DB - Error:" + e);
        }
        return str;
    }

       
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}

    