import java.io.*;
import java.io.PrintWriter;
import java.lang.Exception;
import java.lang.Override;
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
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;



public class Filt implements Filter
{

    public void init(FilterConfig fConfig) throws ServletException {
     //   this.context = fConfig.getServletContext();
        //this.context.log("RequestLoggingFilter initialized");
    }


    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)
    {
       try{
        ServletContext session = request.getServletContext();
           PrintWriter out = response.getWriter();
        String  username = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
           HttpServletRequest req=(HttpServletRequest)request;
           HttpSession session111 = req.getSession();// don't create if it doesn't exist
           if(session111 != null && !session111.isNew()) {
               chain.doFilter(request, response);
           } else {

            System.out.print(System.getProperty("user.dir"));
            String dir = System.getProperty("user.dir");
            StringTokenizer sk = new StringTokenizer(dir,"\\");
            int tok = sk.countTokens();
            dir = "";
            for(int i=0;i <tok-1;++i)
            {
                dir += sk.nextToken();
                dir += "\\";
            }
            dir += "webapps\\lab11\\index.html";
            try (BufferedReader buff = new BufferedReader(new FileReader(dir))) {
                String line;
                while ((line = buff.readLine()) != null) {
                    out.println(line);
                }
            } catch (Exception Ex) {
                out.println("<h1 align=\"center\">FILE NOT FOUND<br>Please Check your URL<h1>");
            }
        }

       }
       catch (Exception ex)
       {
           System.out.print(ex.getMessage());
       }
    }


    public void destroy() {
        //we can close resources here
    }
}

