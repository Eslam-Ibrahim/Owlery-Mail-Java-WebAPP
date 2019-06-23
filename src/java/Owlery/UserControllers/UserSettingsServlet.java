/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.UserControllers;

import Owlery.Beans.UserBean;
import Owlery.DataBaseModels.UserDataBaseModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eslam Ibrahim
 */
public class UserSettingsServlet extends HttpServlet {

   private UserDataBaseModel userDBobject;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        userDBobject = new UserDataBaseModel();
        String submitter = request.getParameter("subButton");
         System.out.println(submitter);
         if (submitter.equals("Update Personal Info")){
             
             // Update Personal info Zone
             HttpSession session = request.getSession();
             UserBean activeUserAccount = (UserBean) session.getAttribute("activeUserAccount");
             activeUserAccount.setFirstName(request.getParameter("newFName"));
             activeUserAccount.setLastName(request.getParameter("newLName"));
             activeUserAccount.setDateOfBirth(request.getParameter("newDOB"));
            boolean updFlag = userDBobject.updatePersonalInfo(activeUserAccount);
            if(updFlag){
                // update activeUserAccount in session
                session.setAttribute("activeUserAccount", activeUserAccount);
                try (PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('User Info Updated Successfuly');");
                out.println("location='settings.jsp';");
                out.println("</script>");
            }
         }else{
                try (PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('User Info Update Failed!');");
                out.println("location='settings.jsp';");
                out.println("</script>");
            }
          }

        }else{
             // Change Password Zone
             HttpSession session = request.getSession();
             UserBean activeUserAccount = (UserBean) session.getAttribute("activeUserAccount");
            boolean updFlag = userDBobject.updatePassword(activeUserAccount.getAccountID(), request.getParameter("Password"));
             if(updFlag){
                try (PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Password Has Been Updated Successfully , On your next visit please remember to use the new password =D');");
                out.println("location='settings.jsp';");
                out.println("</script>");
            }
         }else{
                try (PrintWriter out = response.getWriter()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Password Update Failed!');");
                out.println("location='settings.jsp';");
                out.println("</script>");
            }
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
