package com.example.user.cabbooking;

/**
 * Created by User on 12/07/2018.
 */

public class User {

   private String emp_name,designation,ph_no,jobband;
 private String emp_id;

    public String getEmp_name() {
        return emp_name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPh_no() {
        return ph_no;
    }

    public String getEmp_id() {
        return emp_id;

    }
    public String getJobband(){
        return jobband;
    }

    public User(String emp_id, String emp_name, String designation, String ph_no, String jobband) {
        this.emp_name = emp_name;
        this.designation = designation;
        this.ph_no = ph_no;
        this.emp_id = emp_id;
        this.jobband=jobband;

    }
}
