/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.DataBaseModels;

import Owlery.Beans.ArchiveMailBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eslam Ibrahim
 */
public class ArchivedMailDataBaseModel extends MailDataBaseModel{
    
    public ArchivedMailDataBaseModel(){
        super();
    }
    
    public boolean archiveMail(ArchiveMailBean newArchMail){
        
        boolean archFlag = false;
        connectToDB();
        try{
            String archMailQry = "insert into archive_mailbox (ARCHMAILOWNERID , MAILID , ARCHDATE , ARCHTIME) values(?,?,?,?)";
            pst = conn.prepareStatement(archMailQry);
            pst.setInt(1, newArchMail.getArchMailOwner());
            pst.setInt(2, newArchMail.getMailID());
            pst.setString(3, newArchMail.getArchDate());
            pst.setString(4, newArchMail.getArchTime());
            pst.executeUpdate();
            archFlag = true;
         } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();
        
        // update mail arch flag
        updateArchFlag(newArchMail.getMailID());
        
        return archFlag;
    }
    
    public ArrayList<ArchiveMailBean> archiveMailBox (int archOwnerID){
        
        ArrayList <ArchiveMailBean> archMails = new ArrayList<>();
        
        connectToDB();
        try{
            String selectQry = "select MAILID , ARCHDATE , ARCHTIME from archive_mailbox where ARCHMAILOWNERID = ?";
            pst = conn.prepareStatement(selectQry);
            pst.setInt(1, archOwnerID);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                ArchiveMailBean tempMail = new ArchiveMailBean();
                tempMail.setMailID(rs.getInt("MAILID"));
                tempMail.setArchDate(rs.getString("ARCHDATE"));
                tempMail.setArchTime(rs.getString("ARCHTIME"));
                archMails.add(tempMail);
            }
            
        } catch (SQLException ex) {
            System.out.println("sql error" + ex);
        }
        disconnectFromDB();
        // get rest of missing info
        if (!archMails.isEmpty()){
            getMissingInfo(archMails);
        }
        return archMails;
    }
    public boolean deleteArchiveMail(int mailID) {

        boolean delFlag = false;
        connectToDB();
        try {
            String delMailQry = "delete from archive_mailbox where MAILID = ?";
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
