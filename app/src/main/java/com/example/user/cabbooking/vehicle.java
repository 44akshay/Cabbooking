package com.example.user.cabbooking;

/**
 * Created by User on 16/07/2018.
 */

public class vehicle {
    String carname;
    String carno;
    String cardriverno;

    public vehicle(String carname, String carno, String cardriverno, String remarks) {
        this.carname = carname;
        this.carno = carno;
        this.cardriverno = cardriverno;
        this.remarks = remarks;
    }

    public String getCarname() {
        return carname;
    }

    public String getCarno() {
        return carno;
    }

    public String getCardriverno() {
        return cardriverno;
    }

    public String getRemarks() {
        return remarks;
    }

    String remarks;
    public vehicle() {
    }
}
