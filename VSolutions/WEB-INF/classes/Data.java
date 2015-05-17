import java.io.*;
import java.lang.Exception;
import java.lang.String;
import java.lang.System;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.math.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class Data extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        String username = "";
        HttpSession session ;
                String password = "";
        PrintWriter out = response.getWriter();
      //  out.write("data asda");
          session=   request.getSession();
           username =  session.getAttribute("ID").toString();
     //   out.write("data asda");

            try {

                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver  loaded");
            } catch (Exception ex) {
                System.out.println("Driver Not loaded");
            }

            try {

                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vsolutions?" + "user=root&password=admin");
                ResultSet mar;
                Statement st;
                st = conn.createStatement();
                mar = st.executeQuery("SELECT * FROM user_info.comments WHERE ID='" + ID  + "'");
                int count = 0;
                while (mar.next())
                {
                    out.write("<H1>ID: "+mar.getInt(1));
                    out.write("<br>Post: "+mar.getString(3));
                    out.write("<br>Comment1: "+mar.getString(4));
                    out.write("<br>Comment2:: "+mar.getString(5));
                    out.write("</h1>");

                }
                out.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }

    }
}