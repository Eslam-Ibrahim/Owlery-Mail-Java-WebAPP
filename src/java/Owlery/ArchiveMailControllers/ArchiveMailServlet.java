/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
 
package Owlery.ArchiveMailControllers;

import Owlery.Beans.ArchiveMailBean;
import Owlery.Beans.UserBean;
import Owlery.DataBaseModels.ArchivedMailDataBaseModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eslam Ibrahim
 */
public class ArchiveMailServlet extends HttpServlet {

    ArchivedMailDataBaseModel archMailDB;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("activeUserAccount");
        response.setContentType("text/html;charset=UTF-8");
         boolean archFlag = false;
         archMailDB = new ArchivedMailDataBaseModel();
         String[] mailIDs = request.getParameterValues("mailChecked");
         System.out.println(Arrays.toString(mailIDs));
          if (mailIDs!=null){
            for (int i = 0; i < mailIDs.length; i++) {
                ArchiveMailBean tempArchMail = new ArchiveMailBean();
                tempArchMail.setArchMailOwner(currentUser.getAccountID());
                tempArchMail.setMailID(Integer.parseInt(mailIDs[i]));
                tempArchMail.setArchDate(tempArchMail.calculateDate());
                tempArchMail.setArchTime(tempArchMail.calculateTime());
                archFlag = archMailDB.archiveMail(tempArchMail);
               }
        }
         if(archFlag){
            
              response.sendRedirect("HomePage.jsp");
            
         } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Archiving Failure!');");
                out.println("location='inbox.jsp';");
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
