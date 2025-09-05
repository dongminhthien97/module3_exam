package com.example.module3_exam.model;

import java.time.LocalDate;

public class Order {
    private int oID;
    private int cID;
    private int sID;
    private String oPayBy;
    private LocalDate oDateOrder;
    private LocalDate oDateDelivery;
    private String oAddressDelivery;

    public Order() {
    }

    public Order(int oID, int cID, int sID, String oPayBy, LocalDate oDateOrder, LocalDate oDateDelivery, String oAddressDelivery) {
        this.oID = oID;
        this.cID = cID;
        this.sID = sID;
        this.oPayBy = oPayBy;
        this.oDateOrder = oDateOrder;
        this.oDateDelivery = oDateDelivery;
        this.oAddressDelivery = oAddressDelivery;
    }

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getoPayBy() {
        return oPayBy;
    }

    public void setoPayBy(String oPayBy) {
        this.oPayBy = oPayBy;
    }

    public LocalDate getoDateOrder() {
        return oDateOrder;
    }

    public void setoDateOrder(LocalDate oDateOrder) {
        this.oDateOrder = oDateOrder;
    }

    public LocalDate getoDateDelivery() {
        return oDateDelivery;
    }

    public void setoDateDelivery(LocalDate oDateDelivery) {
        this.oDateDelivery = oDateDelivery;
    }

    public String getoAddressDelivery() {
        return oAddressDelivery;
    }

    public void setoAddressDelivery(String oAddressDelivery) {
        this.oAddressDelivery = oAddressDelivery;
    }
}
