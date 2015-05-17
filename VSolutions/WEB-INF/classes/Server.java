import java.io.*;
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
import javax.servlet.ServletContext;

public class Server extends HttpServlet {
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException {
       System.out.println("Get");
       response.setContentType("text/html");
       PrintWriter out = response.getWriter();
       String email = request.getParameter("email");
       String passowrd = request.getParameter("password");
       if(email == null || passowrd == null)
       {  out.write("NULLLLLL");}
       if(email.equals("") || passowrd.equals(""))
       {
           out.write("NULLLLLL");
       }
        else {
           try {

               Class.forName("com.mysql.jdbc.Driver");
               System.out.println("Driver  loaded");
             //  out.write("driver load");
           } catch (Exception ex) {
               System.out.println("Driver Not loaded azfeeeer");
           }

           try {
               System.out.println("1");
               Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vsolutions?" + "user=root&password=admin");
               //      System.out.println("Connection Success");

               ResultSet mar;
               Statement st;
               st = conn.createStatement();
               System.out.println("2");
               mar = st.executeQuery("SELECT * FROM user_info WHERE name='"+email+"' and password='"+passowrd+"'");
               int count=0;
               while (mar.next()) {count++;}
               if(count == 1)
               {
                 //  out.write("asdasd");
                   System.out.println("3");
                   ServletContext session1 = request.getServletContext();
                    //out.write(email);
                 session1.setAttribute("email",email);
                   session1.setAttribute("password",passowrd);

                   HttpSession session = request.getSession(true);
                   session.setAttribute("email", email);
                   session.setMaxInactiveInterval(5);
                   session.setAttribute("password", passowrd);
                   String dir = System.getProperty("user.dir");
                   StringTokenizer sk = new StringTokenizer(dir,"\\");
                   int tok = sk.countTokens();
                   dir = "";
                   for(int i=0;i <tok-1;++i)
                   {
                       dir += sk.nextToken();
                       dir += "\\";
                   }
                   dir += "webapps\\VSolutions\\Homepage.html";
                //   System.out.println(dir);
                   try (BufferedReader buff = new BufferedReader(new FileReader(dir))) {
                       String line;
                       while ((line = buff.readLine()) != null) {
                           out.println(line);
                       }
                   } catch (Exception Ex) {
                       out.println("<h1 align=\"center\">FILE NOT FOUND<br>Please Check your URL<h1>");
                   }
                   //RequestDispatcher rd = requestout.write(email + " Email");.getRequestDispatcher("Profile");
                    //rd.forward(request,response);
               }
               else
               {
                   out.write("oopoos");
               }


           } catch (Exception ex) {
               System.out.println(ex.getMessage());
           }
       }
   }
}