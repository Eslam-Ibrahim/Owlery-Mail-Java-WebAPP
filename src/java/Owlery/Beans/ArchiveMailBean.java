/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.Beans;

/**
 *
 * @author Eslam Ibrahim
 */
import java.io.Serializable;

public class ArchiveMailBean extends MailBean implements Serializable{
    
    private int archMailID;
    private int archMailOwner;
    private String archDate;
    private String archTime;
    
    public ArchiveMailBean(){
        super();
        archMailID = -1;
        archMailOwner =-1;
        archDate="";
        archTime="";
    }

    public int getArchMailID() {
        return archMailID;
    }

    public void setArchMailID(int archMailID) {
        this.archMailID = archMailID;
    }

    public int getArchMailOwner() {
        return archMailOwner;
    }

    public void setArchMailOwner(int archMailOwner) {
        this.archMailOwner = archMailOwner;
    }

    public String getArchDate() {
        return archDate;
    }

    public void setArchDate(String archDate) {
        this.archDate = archDate;
    }

    public String getArchTime() {
        return archTime;
    }

    public void setArchTime(String archTime) {
        this.archTime = archTime;
    }
    
    
    
}
