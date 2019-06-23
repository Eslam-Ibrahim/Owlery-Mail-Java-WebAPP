/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.DataBaseModels;

import Owlery.Beans.MailBean;
import Owlery.Beans.UserBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eslam Ibrahim
 */
public class UserDataBaseModel extends DataBaseConnectionManager {

    private MailDataBaseModel mailDBobject;
    public UserDataBaseModel() {
        super();
        mailDBobject = new MailDataBaseModel();
    }

    public boolean signUp(UserBean newUser) {

        boolean retResult = false;
        // connect to DB
        connectToDB();
        // Add user record to DB
        try {
            String inserQuery = "insert into user (FIRSTNAME,LASTNAME,GENDER,MAILADDRESS,PASSWORD,DATEOFBIRTH)values (?,?,?,?,?,?)";
            pst = conn.prepareStatement(inserQuery);
            pst.setString(1, newUser.getFirstName());
            pst.setString(2, newUser.getLastName());
            pst.setString(3, newUser.getGender());
            pst.setString(4, newUser.getMailAddress() + "@owlery.com");
            pst.setString(5, newUser.getPassword());
            pst.setString(6, newUser.getDateOfBirth());
            pst.executeUpdate();
            retResult = true;
        } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();

        // Send welcome mail 
        MailBean welcomeMail = new MailBean();
        welcomeMail.setSenderAccID(1);
        welcomeMail.setReceiverAccID(getUserAccountID(newUser.getMailAddress()+ "@owlery.com"));
        welcomeMail.setSubject("Welcome TO Owlery Mail !");
        welcomeMail.setMailContent("Greetings,"
                + "\n" + "We would like to thank you for joining Owlery."
                + "And we hope you get the best mailing experince ever!" + "\n"
                + "IF you have need any technical support , contact us A.S.A.P" + "\n"
                + "Yours," + "\n" + "Owlery Development Team");
        welcomeMail.setDate(welcomeMail.calculateDate());
        welcomeMail.setTime(welcomeMail.calculateTime());
        welcomeMail.setReadFlag(0);
        mailDBobject.sendMail(welcomeMail);
        return retResult;
    }

    public UserBean logIn(String eMail, String password) {

        UserBean activeUserAccount =  null;
        // connect to DB
        connectToDB();
        // select the user if exists 
        try {

            String selectUserQuery = "select * from user where MAILADDRESS=?";
            pst = conn.prepareStatement(selectUserQuery);
            pst.setString(1, eMail);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                // user was found in system - check for password
                if (rs.getString(rs.findColumn("PASSWORD")).equals(password)) {
                    // User eMail and Password are correct
                    activeUserAccount = new UserBean();
                    activeUserAccount.setAccountID(rs.getInt("ACCOUNTID"));
                    activeUserAccount.setFirstName(rs.getString("FIRSTNAME"));
                    activeUserAccount.setLastName(rs.getString("LASTNAME"));
                    activeUserAccount.setMailAddress(eMail);
                    activeUserAccount.setDateOfBirth(rs.getString("DATEOFBIRTH"));
                }
            }
        } catch (SQLException e) {
            System.out.println("sql error" + e);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        disconnectFromDB();
        return activeUserAccount;
    }

    public boolean MailAddress_Availability(String checkMailAddress) {

        connectToDB();
        try {

            String selectUserQuery = "select MAILADDRESS from user where MAILADDRESS=?";
            pst = conn.prepareStatement(selectUserQuery);
            pst.setString(1, checkMailAddress);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("sql error" + e);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        disconnectFromDB();
        return true;

    }
    

    public int getUserAccountID(String mailAddress) {

        int retAccID = -1;
        connectToDB();
        try {

            String selectUserQuery = "select ACCOUNTID from user where MAILADDRESS=?";
            pst = conn.prepareStatement(selectUserQuery);
            pst.setString(1, mailAddress);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                retAccID = rs.getInt("ACCOUNTID");
            }
        } catch (SQLException e) {
            System.out.println("sql error" + e);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        disconnectFromDB();

        return retAccID;
    }
    
    public String getUserMail (int accID){
        String accMail = "";
        connectToDB();
        try {

            String selectUserQuery = "select MAILADDRESS from user where ACCOUNTID=?";
            pst = conn.prepareStatement(selectUserQuery);
            pst.setInt(1, accID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                accMail = rs.getString("MAILADDRESS");
            }
        } catch (SQLException e) {
            System.out.println("sql error" + e);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        disconnectFromDB();

        return accMail;
    }
    
    public boolean updatePersonalInfo(UserBean currentUser){
        
        boolean updFlag = false;
        connectToDB();
        try{
            String updQry = "update user set FIRSTNAME = ? , LASTNAME = ? ,DATEOFBIRTH = ? where ACCOUNTID = ?";
            pst = conn.prepareStatement(updQry);
            pst.setString(1, currentUser.getFirstName());
            pst.setString(2, currentUser.getLastName());
            pst.setString(3, currentUser.getDateOfBirth());
            pst.setInt(4, currentUser.getAccountID());
            pst.executeUpdate();
            updFlag = true;
            } catch (SQLException ex) {
             System.out.println("sql error" + ex);
        }
        disconnectFromDB(); 
        return updFlag;
    }
    
    public boolean updatePassword(int currentUserAccID , String newPassword){
        
          boolean updFlag = false;
        connectToDB();
        try{
            String updQry = "update user set PASSWORD = ? where ACCOUNTID = ?";
            pst = conn.prepareStatement(updQry);
            pst.setString(1,newPassword);
            pst.setInt(2, currentUserAccID);
            pst.executeUpdate();
            updFlag = true;
            } catch (SQLException ex) {
             System.out.println("sql error" + ex);
        }
        disconnectFromDB(); 
        return updFlag;
        
    }

}
