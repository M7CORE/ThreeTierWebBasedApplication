// Servlet to display a JREG file with a text file description
//import javax.servlet.*;
//import javax.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class ImageContent extends HttpServlet {
  /** Process the HTTP Get request */
  public void doGet(HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String picture = request.getParameter("picture");

    out.println("<img src = \"images/" + picture + ".jpg"
      + "\" align=left>");

    // Read description from a file and send it to the browser
    BufferedReader in = new BufferedReader(new FileReader(
      "c:\\" + picture + ".txt"));

    // Text line from the text file for description
    String line;

    // Read a line from the text file and send it to the browser
    while ((line = in.readLine()) != null) {
      out.println(line);
    }

    out.close();
  }
}

