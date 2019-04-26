package com.komlan.lab.kafka.model;

public class Customer {
    private int CustomerID;
    private String customerName;

    public Customer(int ID, String name) {
        this.setCustomerName(name);
        this.setCustomerID(ID);
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.CustomerID = customerID;
    }

    public String toString(){
        return "Customer(ID: " + this.CustomerID + ", name: " + this.customerName + ")";
    }
}