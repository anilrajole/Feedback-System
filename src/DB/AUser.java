package DB;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Anil on 12/03/2018
 */
public class AUser {
    private int uid;
    private String name;
    private String password;
    private int role;
    private String id;
    private java.time.LocalDate date;
    private java.time.LocalTime time;
    private int lcount;
    private String ques;
    private String ans;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getLcount() {
        return lcount;
    }

    public void setLcount(int lcount) {
        this.lcount = lcount;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }
}
