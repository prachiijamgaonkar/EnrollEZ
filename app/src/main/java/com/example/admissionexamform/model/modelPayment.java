package com.example.admissionexamform.model;

public class modelPayment {

    String ModeofPayment;
    String TransactionID;
    String PaymentDate;
    String Amount;

    public modelPayment() {
    }

    public String getModeofPayment() {
        return ModeofPayment;
    }

    public void setModeofPayment(String modeofPayment) {
        ModeofPayment = modeofPayment;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
