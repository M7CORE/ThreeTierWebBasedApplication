/* Name: Maria Corella
Course: CNT 4714 – Spring 2021 – Project Four
Assignment title: A Three-Tier Distributed Web-Based Application
Date: Wednesday April 28, 2021
*/

import java.sql.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project4 extends HttpServlet{
    //process get requests

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        String command = request.getParameter("command");
        String message = null;
        
        try {
            //Connect to database first
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Project4.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4?useTimezone=true&serverTimezone=UTC", "root", "root");
            DatabaseMetaData dbMetaData = connection.getMetaData();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            
            String intro = "<div class='container-fluid'><div class='row justify-content-center'><div class='table-responsive-sm-10 table-responsive-md-10 table-responsive-lg-10'><table class='table'>";
            String columnsTitle = "<thead class='thead-dark'><tr>";
            String info = "<tbody>";
            String end = "</table></div></div></div>";

            //if it is to execute query
            if (command.split("\\s+")[0].equals("select")){
                ResultSet resultSet = statement.executeQuery(command);
                ResultSetMetaData data = resultSet.getMetaData();
                //Get column title
                for (int i = 1; i <= data.getColumnCount(); i++){
                    columnsTitle += "<th scope='col'>" + data.getColumnName(i) + "</th>";
                }

                columnsTitle += "</tr></thead>"; 

                //Get info
                while (resultSet.next()){
                    info += "<tr>";
                    for (int i = 1; i <= data.getColumnCount() ; i++){
                        if (i == 1)
                            info += "<td scope'row'>" + resultSet.getString(i) + "</th>";
                        else
                            info += "<td>" + resultSet.getString(i) + "</th>";
                    }
                    info += "</tr>";
                }

                info += "</tbody>";

                message = intro + columnsTitle + info + end;
                
                HttpSession session = request.getSession();
                session.setAttribute("message", message);
                session.setAttribute("command", command);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Project4.jsp");
                dispatcher.forward(request, response);
            }

            //if its to update, application logic here too
            else {
                String command1 = "update suppliers set status = status + 5 where snum in (select snum from shipments where quantity > 99)";
                statement.executeUpdate(command);
                
                //Check to see if insert into shipment
                
                if (command.split("\\s+")[2].equals("shipments")){
                    //get the quantity
//                    int quantity = Integer.parseInt(command.split("\\s+")[7].subSequence(0, 3).toString());
                    int quantity = Integer.parseInt(command.split("\\s+")[7].replace(");", ""));
                    if (quantity >= 100){
                        //execute logic
                        statement1.executeUpdate(command1);
                        HttpSession session = request.getSession();
                        session.setAttribute("message", "Update has executed with application logic.");
                        session.setAttribute("command", command);
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Project4.jsp");
                        dispatcher.forward(request, response);
                    }
                    else {
                        HttpSession session = request.getSession();
                        session.setAttribute("message", "Update has executed without application logic.");
                        session.setAttribute("command", command);
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Project4.jsp");
                        dispatcher.forward(request, response);
                    }
                    
                }
                
                else if (command.split("\\s+")[1].equals("shipments") && command.split("\\s+")[0].equals("update")){
                    statement1.executeUpdate(command1);
                    HttpSession session = request.getSession();
                    session.setAttribute("message", "Update has executed with application logic.");
                    session.setAttribute("command", command);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Project4.jsp");
                    dispatcher.forward(request, response);
                }
                //If not an insert/update to shipment, execute normally
                else {
                    HttpSession session = request.getSession();
                    session.setAttribute("message", "Update has executed without application logic.");
                    session.setAttribute("command", command);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Project4.jsp");
                    dispatcher.forward(request, response);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Project4.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session = request.getSession();
            session.setAttribute("message", ex.getMessage());
            session.setAttribute("command", command);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Project4.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	doGet(request, response);
    }

} 

