/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.MailControllers;

import Owlery.Beans.MailBean;
import Owlery.DataBaseModels.MailDataBaseModel;
import Owlery.DataBaseModels.UserDataBaseModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eslam Ibrahim
 */
public class SendMailServlet extends HttpServlet {

    private MailDataBaseModel mailDBMod;
    private UserDataBaseModel userDBMod;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        userDBMod = new UserDataBaseModel();
        MailBean sendMailObj = new MailBean();
        sendMailObj.setSenderAccID(Integer.parseInt(request.getParameter("senderAccID")));
        sendMailObj.setReceiverAccID(userDBMod.getUserAccountID(request.getParameter("receiverMail")));
        sendMailObj.setSubject(request.getParameter("mailSubject"));
        sendMailObj.setMailContent(request.getParameter("mailContent"));
        sendMailObj.setDate(sendMailObj.calculateDate());
        sendMailObj.setTime(sendMailObj.calculateTime());
        mailDBMod = new MailDataBaseModel();
        boolean sendFlag = mailDBMod.sendMail(sendMailObj);
        if (sendFlag) {
              response.sendRedirect("HomePage.jsp");
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Send Failure!');");
                out.println("location='compose_mail.jsp';");
                out.println("</script>");
                
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
