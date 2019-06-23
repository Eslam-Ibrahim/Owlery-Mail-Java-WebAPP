<%-- 
    Document   : inbox
    Created on : Dec 22, 2015, 11:55:50 AM
    Author     : Eslam Ibrahim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Owlery.Beans.MailBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            #inbox {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #inbox td, #inbox th {
                border: 1px solid #ddd;
                text-align: left;
                padding: 5px;
            }
            
            #inbox th {
                padding-top: 12px;
                padding-bottom: 12px;
                background-color: #4CAF50;
                color: white;
            }
        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Owlery Mail-Inbox</title>
        <link rel="stylesheet" type="text/css" href="css/homePagestyle.css" />
        <!-- modernizr enables HTML5 elements and feature detects -->
        <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
    </head>

    <body>
        <%
            // Get Mail Inbox 
            ArrayList<MailBean> mailInbox = (ArrayList<MailBean>) session.getAttribute("mailInbox");
             // delete PayLoadMail if any
           request.getSession().removeAttribute("PayLoadMail");
        %>
        <div id="main">
            <header>
                <div id="logo"><h1><a href="HomePage.jsp">OwleryMail</a></h1></div>
                <nav>
                    <ul class="lavaLampWithImage" id="lava_menu">
                        <li><a href="HomePage.jsp">Home</a></li>
                        <li class="current"><a href="InboxServlet">MailBox</a></li>
                        <li><a href="compose_mail.jsp">Compose Mail</a></li>
                        <li><a href="ArchiveMailBoxServlet">Archive MailBox</a></li>
                        <li><a href="settings.jsp">Settings</a></li>
                        <li><a href="UserLogoutServlet">Logout</a></li>
                    </ul>
                </nav>
            </header>
            <div id="site_content">
               
                    <%--
            Inbox Table --> Sender Mail + Subject + date 
            Loop on ArrayList 
            bold readflag
                    --%>
                    <table id="inbox">
                        <tr>
                            <th>Sender Mail</th>
                            <th>Subject</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>CHK</th>
                        </tr>

                        <%
                          for (int i=0; i<mailInbox.size(); ++i){    
                        %>
                        <tr>
                            <td>
                                <%=mailInbox.get(i).getSenderMail()%>
                            </td>
                            <td>
                                <%
                                  // handel bold subject
                                   if (mailInbox.get(i).getReadFlag()==0){
                                %>
                                <a href="MailViewServlet?mailID=<%=mailInbox.get(i).getMailID()%>"> <b> <%=mailInbox.get(i).getSubject()%></b> 
                                </a>
                                <%
                                   } else{
                                %>
                                <a href="MailViewServlet?mailID=<%=mailInbox.get(i).getMailID()%>"> <%=mailInbox.get(i).getSubject()%> 
                                </a>
                                <% } %>
                            </td>
                            <td>
                                <%=mailInbox.get(i).getDate()%>
                            </td>
                            <td>
                                <%=mailInbox.get(i).getTime()%>
                            </td>
                            <td>
                                <input type="checkbox" form="deleteForm" name="mailChecked" value="<%= mailInbox.get(i).getMailID()%>">
                            </td>
                        </tr>
                        <%
                          }
                        %>
                    </table>
                    <br>
                    <form name="deleteForm" id="deleteForm" action="DeleteMailServlet" method="post">
                        <input type="submit" value="Delete Mail(s)" name="subButton"> | <input type="submit" value="Archive Mail(s)" name="subButton">
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
