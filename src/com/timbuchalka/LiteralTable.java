package com.timbuchalka;

/**
 * Created by AhmedKhaled on 11/19/17.
 */
public class LiteralTable {
    String litreal,value,address;
    int length,pole;
    public LiteralTable(){}
    public LiteralTable(int length, String litreal, String value, int pole, String address) {
        this.length = length;
        this.litreal = litreal;
        this.value = value;
        this.pole = pole;
        this.address = address;
    }
}
