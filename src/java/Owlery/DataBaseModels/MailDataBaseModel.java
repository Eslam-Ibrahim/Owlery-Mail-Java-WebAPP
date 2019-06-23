/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.DataBaseModels;

import Owlery.Beans.ArchiveMailBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import Owlery.Beans.MailBean;
import java.util.ArrayList;

/**
 *
 * @author Eslam Ibrahim
 */
public class MailDataBaseModel extends DataBaseConnectionManager {

    private UserDataBaseModel userDB;

    public MailDataBaseModel() {
        super();
    }

    public boolean sendMail(MailBean newMail) {

        boolean sendFlag = false;
        // connect to DB
        connectToDB();
        // Try to add the new mail to DB
        try {

            String mailInsertQry = "insert into mail_inbox (SENDERACCOUNTID,RECEIVERACCOUNTID,SUBJECT,MAILCONTENT,DATE,TIME,READFLAG)values (?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(mailInsertQry);
            pst.setInt(1, newMail.getSenderAccID());
            pst.setInt(2, newMail.getReceiverAccID());
            pst.setString(3, newMail.getSubject());
            pst.setString(4, newMail.getMailContent());
            pst.setString(5, newMail.getDate());
            pst.setString(6, newMail.getTime());
            pst.setInt(7, newMail.getReadFlag());
            pst.executeUpdate();
            sendFlag = true;
        } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();
        return sendFlag;
    }
    
    protected void getMissingInfo( ArrayList<ArchiveMailBean> archMail){
        
        userDB = new UserDataBaseModel();
        connectToDB();
        try{
            for (int i = 0; i < archMail.size(); i++) {
                
                String selectQry = "select SENDERACCOUNTID,RECEIVERACCOUNTID,SUBJECT,READFLAG from mail_inbox where MAILID = ?";
                pst = conn.prepareStatement(selectQry);
                pst.setInt(1, archMail.get(i).getMailID());
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    archMail.get(i).setSenderAccID(rs.getInt("SENDERACCOUNTID"));
                    archMail.get(i).setReceiverAccID(rs.getInt("RECEIVERACCOUNTID"));
                    archMail.get(i).setSubject(rs.getString("SUBJECT"));
                    archMail.get(i).setReadFlag(rs.getInt("READFLAG"));
                }
            }
         } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();
        // get sender mail - reciver mail
        for (int i = 0; i < archMail.size(); i++) {
            archMail.get(i).setSenderMail(userDB.getUserMail(archMail.get(i).getSenderAccID()));
            archMail.get(i).setReceiverMail(userDB.getUserMail(archMail.get(i).getReceiverAccID()));
        }
    }

    public ArrayList<MailBean> Inbox(int receiverID) {

        ArrayList<MailBean> mailInbox = new ArrayList<>();
        userDB = new UserDataBaseModel();
        String recieverMail = userDB.getUserMail(receiverID);

        // connect to DB
        connectToDB();
        try {

            String mailSelectQry = "select MAILID,SENDERACCOUNTID,SUBJECT,DATE,TIME,READFLAG from mail_inbox where RECEIVERACCOUNTID = ? and ARCHFLAG = 0";
            pst = conn.prepareStatement(mailSelectQry);
            pst.setInt(1, receiverID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                MailBean tempMail = new MailBean();
                tempMail.setMailID(rs.getInt("MAILID"));
                tempMail.setSenderAccID(rs.getInt("SENDERACCOUNTID"));
                tempMail.setSubject(rs.getString("SUBJECT"));
                tempMail.setDate(rs.getString("DATE"));
                tempMail.setTime(rs.getString("TIME"));
                tempMail.setReadFlag(rs.getInt("READFLAG"));
                mailInbox.add(tempMail);
            }
        } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();
        // set Sender Mail - set ReceiverMail
        for (int i = 0; i < mailInbox.size(); i++) {
            mailInbox.get(i).setSenderMail(userDB.getUserMail(mailInbox.get(i).getSenderAccID()));
            mailInbox.get(i).setReceiverMail(recieverMail);
            mailInbox.get(i).setReceiverAccID(receiverID);
        }
        return mailInbox;
    }

    private void updateReadFlag(int mailID) {

        connectToDB();
        try {

            String updateQry = "update mail_inbox set READFLAG = 1 where MAILID = ?";
            pst = conn.prepareStatement(updateQry);
            pst.setInt(1, mailID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();
    }

    protected void updateArchFlag(int mailID) {
        connectToDB();
        try {

            String updateQry = "update mail_inbox set ARCHFLAG = 1 where MAILID = ?";
            pst = conn.prepareStatement(updateQry);
            pst.setInt(1, mailID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();

    }

    public MailBean readMail(int mailID) {

        MailBean readMailBean = new MailBean();
        // connect to DB
        connectToDB();
        userDB = new UserDataBaseModel();
        try {

            String mailSelectQry = "select* from mail_inbox where MAILID = ?";
            pst = conn.prepareStatement(mailSelectQry);
            pst.setInt(1, mailID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                readMailBean.setMailID(rs.getInt("MAILID"));
                readMailBean.setSenderAccID(rs.getInt("SENDERACCOUNTID"));
                readMailBean.setReceiverAccID(rs.getInt("RECEIVERACCOUNTID"));
                readMailBean.setSubject(rs.getString("SUBJECT"));
                readMailBean.setMailContent(rs.getString("MAILCONTENT"));
                readMailBean.setDate(rs.getString("DATE"));
                readMailBean.setTime(rs.getString("TIME"));
            }
        } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();
        // set Sender Mail - set ReceiverMail
        readMailBean.setSenderMail(userDB.getUserMail(readMailBean.getSenderAccID()));
        readMailBean.setReceiverMail(userDB.getUserMail(readMailBean.getReceiverAccID()));
        // update read flag
        updateReadFlag(mailID);
        return readMailBean;
    }

    public boolean deleteMail(int mailID) {

        boolean delFlag = false;
        connectToDB();
        try {
            String delMailQry = "delete from mail_inbox where MAILID = ?";
            pst = conn.prepareStatement(delMailQry);
            pst.setInt(1, mailID);
            pst.executeUpdate();
            delFlag = true;
        } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();
        return delFlag;
    }
}
