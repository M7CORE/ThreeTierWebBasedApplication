<!--//Project 4 JSP-->
<!DOCTYPE html>
<%--start scriptlet --%>
<% 
    String command = (String) session.getAttribute("command");
    if (command == null)
        command = "";
    String message = (String) session.getAttribute("message");
    if (message == null)
        message = "";
%>
<html lan = "en">
    <head>
        <title>Project4</title>
        <style type = "text/css">
            <!--
                body {background-color:black; color:lime; font-family: verdana, arial, sans-serif; font-size: 24pt; text-align: center;}
<!--                .size1 {color: red; font-weight: bold; font-size: 24pt; text-decoration: underline;} -->
<!--                .size2 {color: yellow; font-weight: bold; font-size: 16pt;} -->
            -->
        </style>
    </head>

    <!-- body section of the document, print table here-->
    <body>
        <form action="/Project4/Project4" method = "post">
            <p><label>
                <h1 style = "font-size: 32px; text-align: center;">Spring 2021 Project 4</h1>
                <h1 style = "font-size: 32px; text-align: center;">A Servlet/JSP-based Multi-tiered Enterprise Application Utilizing a Tomcat Container</h1>
                <p style = "font-size: 12px; text-align: center;">Developed by: Maria Corella</p>
                <br><br>
                <p style = "color: white; font-size: 24px;">You are connected to the database as administrator. Enter sql command in the space below, then click button to execute.</p>
                <div style = "text-align: center;">
                    <textarea name = "command" class = "form-control" id = "command" rows = "20" cols = "100"><%=command %></textarea>
                </div>
                <br><br>
                <input type = "submit" value = "Execute Command" /><label>
                <input type = "button" value = "Clear Command" onClick = "javascript:clearContent();"/><label>
            </p></label>
        </form>
        <h2 style = "font-size: 12pt; text-align: center;">
            <p style = "text-align: center;">Database Results:</p>
            <p style = "text-align: center;">
                <%=message%>
            </p>
        </h2>
        <script>
            function clearContent() {
                document.getElementsById("command").value = "";
            }
        </script>
    </body>
</html>