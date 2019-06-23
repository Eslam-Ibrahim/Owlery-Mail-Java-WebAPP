<%-- 
    Document   : compose_mail
    Created on : Dec 24, 2015, 9:41:32 PM
    Author     : Eslam Ibrahim
--%>

<%@page import="Owlery.Beans.MailBean"%>
<%@page import="Owlery.Beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Owlery Mail-Compose Mail</title>
        <style> 
            textarea {
                width: 100%;
                height: 300px;
                padding: 12px 20px;
                box-sizing: border-box;
                border: 2px solid #ccc;
                border-radius: 4px;
                background-color: #f8f8f8;
                font-size: 16px;
                resize: none;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="css/homePagestyle.css" />
        <!-- modernizr enables HTML5 elements and feature detects -->
        <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
    </head>

    <body>
        <%
            // Get Current Active User 
            UserBean currentActiveUser = (UserBean) request.getSession().getAttribute("activeUserAccount");
            // Get PayLoadMail if any
            MailBean PayLoadMail = (MailBean) request.getSession().getAttribute("PayLoadMail");
        %>
        <div id="main">
            <header>
                <div id="logo"><h1><a href="HomePage.jsp">OwleryMail</a></h1></div>
                <nav>
                    <ul class="lavaLampWithImage" id="lava_menu">
                        <li><a href="HomePage.jsp">Home</a></li>
                        <li><a href="InboxServlet">MailBox</a></li>
                        <li class="current"><a href="compose_mail.jsp">Compose Mail</a></li>
                        <li><a href="ArchiveMailBoxServlet">Archive MailBox</a></li>
                        <li><a href="settings.jsp">Settings</a></li>
                        <li><a href="UserLogoutServlet">Logout</a></li>
                    </ul>
                </nav>
            </header>
         
            <div id="site_content">
                <br>

                <form action="SendMailServlet" name="composeMailForm" id="composeMailForm" method="post">
                    <fieldset>
                        <legend> Mail Header </legend>
                        From: <%=currentActiveUser.getMailAddress()%>
                        <input type="hidden" name="senderAccID" value="<%=currentActiveUser.getAccountID()%>">
                        <br>
                        <br>
                        <%
                            if (PayLoadMail!=null){
                                
                        %>
                        To: <input type="text" name="receiverMail" value="<%=PayLoadMail.getReceiverMail()%>"required>
                         <br>
                         <br>
                        
                        <%
                           } else {
                        %>
                        To: <input type="text" name="receiverMail" placeholder="example@owlery.com" required>
                        <br>
                        <br>
                        <%}%>
                        
                        <%
                            if (PayLoadMail!=null){
                                
                        %>
                        Subject: <input type="text" name="mailSubject" value="<%=PayLoadMail.getSubject()%>" required>
                        <br>
                        <%
                           } else {
                        %>
                        Subject: <input type="text" name="mailSubject" required>
                        <br>
                        <%}%>
                    </fieldset>
                    <br>
                     <%
                            if (PayLoadMail!=null){
                                
                        %>
                        <textarea name="mailContent" form="composeMailForm" required><%out.println("");%><%out.println("");%><%out.println("");%><%=PayLoadMail.getMailContent()%></textarea>
                    <br>
                     <%
                           } else {
                        %>
                         <textarea name="mailContent" form="composeMailForm" placeholder="Your Mail Goes Here..." required></textarea>
                    <br>
                        <%}%>
                    <center>
                        <input type="submit" value="Send Mail Now!">
                    </center>
                </form>



            </div>
            <footer>
                <p><a href="about_owlery.jsp">About Owlery Mail</a></p>
                <p>&copy;2015 Owlery Mail | <a href="http://www.css3templates.co.uk">CSS3 design template from css3templates.co.uk</a></p>
            </footer>
        </div>
        <!-- javascript at the bottom for fast page loading -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.easing.min.js"></script>
        <script type="text/javascript" src="js/jquery.lavalamp.min.js"></script>
        <script type="text/javascript" src="js/image_fade.js"></script>
        <script type="text/javascript">
            $(function () {
                $("#lava_menu").lavaLamp({
                    fx: "backout",
                    speed: 700
                });
            });
        </script>
    </body>
</html>
