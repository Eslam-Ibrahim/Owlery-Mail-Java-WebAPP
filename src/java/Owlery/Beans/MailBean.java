/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.Beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eslam Ibrahim
 */
public class MailBean implements Serializable {

    protected int mailID;
    protected int senderAccID;
    protected int receiverAccID;
    protected String subject;
    protected String mailContent;
    protected String date;
    protected String time;
    protected int readFlag;
    protected String senderMail;
    protected String receiverMail;

    public MailBean() {
        mailID = -1;
        senderAccID = -1;
        receiverAccID = -1;
        subject = "";
        mailContent = "";
        date = "";
        time = "";
        readFlag = 0;

    }

    public int getMailID() {
        return mailID;
    }

    public void setMailID(int mailID) {
        this.mailID = mailID;
    }

    public int getSenderAccID() {
        return senderAccID;
    }

    public void setSenderAccID(int senderAccID) {
        this.senderAccID = senderAccID;
    }

    public int getReceiverAccID() {
        return receiverAccID;
    }

    public void setReceiverAccID(int receiverAccID) {
        this.receiverAccID = receiverAccID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }

    public String getSenderMail() {
        return senderMail;
    }

    public void setSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }

    public String getReceiverMail() {
        return receiverMail;
    }

    public void setReceiverMail(String receiverMail) {
        this.receiverMail = receiverMail;
    }

    public String calculateDate() {

        DateFormat newDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date newDate = new Date();
        newDateFormat.format(newDate);
        int dateInt = newDate.getDate();
        int month = newDate.getMonth() + 1;
        int year = newDate.getYear() + 1900;
        String sqlDate = dateInt + "/" + month + "/" + year;
        return sqlDate;
    }

    public String calculateTime() {
        DateFormat newDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date newDate = new Date();
        newDateFormat.format(newDate);
        int hours = newDate.getHours();
        int minutes = newDate.getMinutes();
        String sqlTime = hours + " : " + minutes;
        return sqlTime;
    }

    @Override
    public String toString() {
        return "MailBean{" + "mailID=" + mailID + ", senderAccID=" + senderAccID + ", receiverAccID=" + receiverAccID + ", subject=" + subject + ", mailContent=" + mailContent + ", date=" + date + ", time=" + time + ", readFlag=" + readFlag + ", senderMail=" + senderMail + ", receiverMail=" + receiverMail + '}';
    }
    

}
