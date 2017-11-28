package com.timbuchalka;

/**
 * Created by AhmedKhaled on 11/19/17.
 */
public class Instruction {

    String name,label,op1,op2,format,address;

    public Instruction(String address, String name, String label, String op1, String op2, String format) {
        this.address = address;
        this.name = name;
        this.label = label;
        this.op1 = op1;
        this.op2 = op2;
        this.format = format;
    }

    @Override
    public String toString() {
        return  (label+ "  "+name+"  "+op1+"  "+op2+" "+address);
    }
    public Instruction(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


}
