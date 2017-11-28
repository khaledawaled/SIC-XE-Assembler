package com.timbuchalka;

/**
 * Created by AhmedKhaled on 11/19/17.
 */
public class InstructionSet {
    String opcode=null;
    int format;
    InstructionSet( int format , String opcode )
    {

        this.format = format;
        this.opcode = opcode;
    }

}
