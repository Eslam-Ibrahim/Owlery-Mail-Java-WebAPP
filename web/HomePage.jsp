<%-- 
    Document   : HomePage
    Created on : Dec 16, 2015, 11:10:46 PM
    Author     : Eslam Ibrahim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Owlery.Beans.UserBean"%>
<!DOCTYPE HTML>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Owlery Mail-HomePage</title>
        <link rel="stylesheet" type="text/css" href="css/homePagestyle.css" />
        <!-- modernizr enables HTML5 elements and feature detects -->
        <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
    </head>
        <%
             // Get Current Active User 
           UserBean currentActiveUser= (UserBean) request.getSession().getAttribute("activeUserAccount");
           // delete PayLoadMail if any
           request.getSession().removeAttribute("PayLoadMail");
        %>
    <body>
        <div id="main">
            <header>
                <div id="logo"><h1><a href="HomePage.jsp">OwleryMail</a></h1></div>
                <nav>
                    <ul class="lavaLampWithImage" id="lava_menu">
                        <li class="current"><a href="HomePage.jsp">Home</a></li>
                        <li><a href="InboxServlet">MailBox</a></li>
                        <li><a href="compose_mail.jsp">Compose Mail</a></li>
                        <li><a href="ArchiveMailBoxServlet">Archive MailBox</a></li>
                        <li><a href="settings.jsp">Settings</a></li>
                        <li><a href="UserLogoutServlet">Logout</a></li>
                    </ul>
                </nav>
            </header>
            <div id="site_content">
                <div id="sidebar_container">
                    <div class="gallery">
                        <ul class="images">
                            <li><img width="450" height="450" src="images/photo_bgs.jpg" alt="photo_two" /></li>
                            <li><img width="450" height="450" src="images/bg3.jpg" alt="photo_three" /></li>
                            <li><img width="450" height="450" src="images/bg6.jpg" alt="photo_four" /></li>
                            <li><img width="450" height="450" src="images/bg5.jpg" alt="photo_five" /></li>
                        </ul>
                    </div>
                </div>
                <div id="content">
                    <h1>Welcome Back <%=currentActiveUser.getFirstName()%> <%=currentActiveUser.getLastName()%> ,</h1>
                    <p>We are Very Pleased to see you stopping by again :)</p>
                    <h2>yours,</h2>
                    <h2>Owlery Mail Development Team</h2>
                    
                </div>
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
