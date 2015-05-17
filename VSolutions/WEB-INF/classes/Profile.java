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

public class Profile extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        String username = "";
        String password = "";
        HttpSession session ;
        PrintWriter out = response.getWriter();

            // HttpSession session = request.getSession();
         session  = request.getSession();
            // ServletContext context = request.getSession().getServletContext();
             username = (String) session.getAttribute("email");
             password = (String) session.getAttribute("password");


        if(username.equals("") || password.equals(""))
        {
            out.write("Not Logged IN");
        }
        else
        {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver  loaded");
            } catch (Exception ex) {
                System.out.println("Driver Not loaded");
            }

            try {

                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/socials?" + "user=root&password=admin");
                //      System.out.println("Connection Success");

                ResultSet mar;
                Statement st;
                st = conn.createStatement();
                mar = st.executeQuery("SELECT * FROM socials.member WHERE Email='" + username + "' and Password='" + password + "'");
                int count = 0;
                while (mar.next())
                {
                    out.write("<H1>ID: "+mar.getInt(1));
                    out.write("<br>Name: "+mar.getString(2));
                    session.setAttribute("ID", mar.getInt(1));
                    out.write("<br>Email: "+mar.getString(3));
                    out.write("<br>Address: "+mar.getString(5));
                    out.write("<br>Phone: "+mar.getString(6));
                    out.write("<br>Qualification: "+mar.getString(7));
                    out.write("</h1>");
                }
                RequestDispatcher rd = request.getRequestDispatcher("Data");
                rd.include(request,response);
                //out.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}