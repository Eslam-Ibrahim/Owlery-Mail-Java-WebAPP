<%-- 
    Document   : settings
    Created on : Dec 26, 2015, 2:48:57 PM
    Author     : Eslam Ibrahim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Owlery.Beans.UserBean"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Owlery Mail-Settings</title>
        <link rel="stylesheet" type="text/css" href="css/homePagestyle.css" />
        <!-- modernizr enables HTML5 elements and feature detects -->
        <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
        <script type="text/javascript">
            function valdPass(form) {
                if (form.Password.value === form.confirmPassword.value) {
                    if (form.Password.value.length < 8) {
                        alert("Error: Password must be at least 8 chars");
                        form.Password.focus();
                        return false;
                    }
                    re = /[0-9]/;
                    if (!re.test(form.Password.value)) {
                        alert("Error: Password must be at least 1 number");
                        form.password.focus();
                        return false;
                    }
                    ch = /[A-Z]/;
                    if (!ch.test(form.Password.value)) {
                        alert("Error: Password must be at least 1 Uppercase letter");
                        form.Password.focus();
                        return false;
                    }
                    chk = /[a-z]/;
                    if (!chk.test(form.Password.value)) {
                        alert("Error: Password must be at least 1 Lowercase letter");
                        form.Password.focus();
                        return false;
                    }
                } else {
                    // password is empty
                    alert("Error: Password must not be empty and confirm password must be equal to password");
                    form.Password.focus();
                    form.confirmPassword.focus();
                    return false;
                }


            }
        </script>
    </head>

    <body>
        <%
            // Get Current Active User 
            UserBean currentActiveUser = (UserBean) request.getSession().getAttribute("activeUserAccount");
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
                        <li class="current"><a href="settings.jsp">Settings</a></li>
                        <li><a href="UserLogoutServlet">Logout</a></li>
                    </ul>
                </nav>
            </header>
            <div id="site_content">
                <br>
                <form action="UserSettingsServlet" id="settingsForm" name="settingsForm" method="post">
                     <fieldset>
           <legend>Personal Information Settings:</legend>
           <br>
           First Name:<input type="text" name="newFName" required value="<%=currentActiveUser.getFirstName()%>">
            <br>
            <br>
            Last Name:<input type="text" name="newLName" required value="<%=currentActiveUser.getLastName()%>">
            <br>
            <br>
            Date Of Birth:
            <input type="date" name="newDOB" required value="<%=currentActiveUser.getDateOfBirth()%>">
            <br>
            <br>
            </fieldset>
                    <center>
                    <input type="submit" value="Update Personal Info" name="subButton">    
                    </center>
              <br>
              <fieldset>
               <legend>Password Settings:</legend>
               <br>
               <p style="border-style:solid;border-color: blue;">
                Password Must Be:
                <br>
                -At least 8 chars 
                <br>
                -At least one uppercase letter 
                <br>
                -At least on lowercase letter 
                <br>
                -At least one number
                <br>
               </p>
               <br>
               Password:<input type="password" name="Password" >
               <br>
               <br>
               Confirm Password:<input type="password" name="confirmPassword" >
               <br>
               <br>
            </fieldset>
              <center>
                  <input type="submit" value="Change Password" name="subButton" onclick=" return valdPass(settingsForm)">
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
