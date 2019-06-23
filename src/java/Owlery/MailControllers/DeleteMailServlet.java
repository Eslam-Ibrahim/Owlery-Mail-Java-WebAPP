/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.MailControllers;

import Owlery.DataBaseModels.MailDataBaseModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eslam Ibrahim
 */
public class DeleteMailServlet extends HttpServlet {

  private MailDataBaseModel mailDBModel;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String submitter = request.getParameter("subButton");
        System.out.println(submitter);
        if (submitter.equals("Archive Mail(s)")){
            
            String url = "/ArchiveMailServlet";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        boolean delFlag = false; 
       mailDBModel = new MailDataBaseModel();
       String[] mailIDs = request.getParameterValues("mailChecked");
        if (mailIDs!=null){
            for (int i = 0; i < mailIDs.length; i++) {
            delFlag = mailDBModel.deleteMail(Integer.parseInt(mailIDs[i]));
        }
        }
        if(delFlag){
             response.sendRedirect("HomePage.jsp");
            
         } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Deletion Failure!');");
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
