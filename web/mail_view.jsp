<%-- 
    Document   : mail_view
    Created on : Dec 23, 2015, 6:58:51 PM
    Author     : Eslam Ibrahim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Owlery.Beans.MailBean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Owlery Mail-Mail Thread</title>
        <style> 
            textarea {
                width: 100%;
                height: 275px;
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
            // get mail object
            MailBean selectedMail = (MailBean) session.getAttribute("selectedMail");
            // delete PayLoadMail if any
            request.getSession().removeAttribute("PayLoadMail");
        %>
        <div id="main">
            <header>
                <div id="logo"><h1><a href="HomePage.jsp">OwleryMail</a></h1></div>
                <nav>
                    <ul class="lavaLampWithImage" id="lava_menu">
                        <li><a href="HomePage.jsp">Home</a></li>
                        <li><a href="InboxServlet">MailBox</a></li>
                        <li><a href="compose_mail.jsp">Compose Mail</a></li>
                        <li><a href="ArchiveMailBoxServlet">Archive MailBox</a></li>
                        <li><a href="settings.jsp">Settings</a></li>
                        <li><a href="UserLogoutServlet">Logout</a></li>
                    </ul>
                </nav>
            </header>

            <div id="site_content">
                <br>

                <fieldset>
                    <legend> Mail Header </legend>
                    From: <%= selectedMail.getSenderMail()%>
                    <br>
                    To: <%=selectedMail.getReceiverMail()%>
                    <br>
                    Subject: <%=selectedMail.getSubject()%>
                    <br>
                    Date:<%=selectedMail.getDate()%>
                    <br>
                    Time:<%=selectedMail.getTime()%>
                    <br>
                </fieldset>
                <br>
                <textarea readonly><%=selectedMail.getMailContent()%></textarea>

                <%--
                Reply - Forward 
                --%>
                <center>
                    <form action="ReplyMailServlet" method="post">
                        <input type="submit" value="Reply">
                    </form>
                    <br>
                    <form action="ForwardMailServlet" method="post">
                        <input type="submit" value="Forward">
                    </form>
                </center>
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
