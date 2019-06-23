<%-- 
    Document   : about_owlery
    Created on : Dec 26, 2015, 12:03:29 AM
    Author     : Eslam Ibrahim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Owlery Mail-About Owlery Mail</title>
        <link rel="stylesheet" type="text/css" href="css/homePagestyle.css" />
        <!-- modernizr enables HTML5 elements and feature detects -->
        <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
    </head>
    <%
        // delete PayLoadMail if any
        request.getSession().removeAttribute("PayLoadMail");
    %>
    <body>
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
                <div id="sidebar_container">
                    <div class="gallery">
                        <ul class="images">
                            <li><img width="450" height="450" src="images/ab1.jpg" alt="photo_1" /></li>
                            <li><img width="450" height="450" src="images/ab3.jpg" alt="photo_2" /></li>
                            <li><img width="450" height="450" src="images/ab4.jpg" alt="photo_3" /></li>
                        </ul>
                    </div>
                </div>
                <div id="content">
                    <h1>About Owlery Mail</h1>
                    <h2>Owlery Mail is a closed community mailing web application that gives you
                    a reliable mailing services at the simplest way! </h2>
                    <br>
                    <h3> Pros of Owlery Mail:</h3>
                    <ul>
                        <li>You Will never ever get newsletters and random adds mails :)</li>
                        <li>Owlery Mail doesn't contain Spam or viruses!</li>
                        <li>Supports Google Chrome , Microsoft Edge , Safari ...etc.</li>
                        <li>Owlery Mail is Free!</li>
                    </ul>
                    <br>                     
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
