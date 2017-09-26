package com.example.buh.foptaxpay2;
public class Item{
    private String region;
    private String reciepient;
    private String edrpou;
    private String account;
    private String bank;
    private String mfo;
    private String details;

    public Item(String region, String reciepient, String edrpou, String account, String bank, String mfo, String details) {
        this.region = region;
        this.reciepient = reciepient;
        this.edrpou = edrpou;
        this.account = account;
        this.bank = bank;
        this.mfo = mfo;
        this.details = details;
    }
    public int removeItem(){
        return 0;
    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getReciepient() {
        return reciepient;
    }

    public void setReciepient(String reciepient) {
        this.reciepient = reciepient;
    }

    public String getEdrpou() {
        return edrpou;
    }

    public void setEdrpou(String edrpou) {
        this.edrpou = edrpou;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getMfo() {
        return mfo;
    }

    public void setMfo(String mfo) {
        this.mfo = mfo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Item() {

        this.reciepient = reciepient;
        this.edrpou = edrpou;
        this.account = account;
        this.bank = bank;
        this.mfo = mfo;
        this.details = details;
    }
}
