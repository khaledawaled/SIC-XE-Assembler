package com.timbuchalka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static ArrayList instArray= new ArrayList<Instruction>();
    public static ArrayList symboltab = new ArrayList<SymbolTable>();
    public static ArrayList literaltab= new ArrayList<SymbolTable>();
    public static  String StrInst ;
    public static HashMap<String,Boolean> litVisited = new HashMap<>();
    public static HashMap<String,InstructionSet> instructionMap = new HashMap<String,InstructionSet>();
    public static void generateHashMap(){
    	
    	
    	instructionMap.put( "START",new InstructionSet(0,"000000"));
        instructionMap.put( "BASE",new InstructionSet(0,"000000"));
        instructionMap.put( "END",new InstructionSet(0,"000000"));

        instructionMap.put( "FIX",new InstructionSet(1,"C4"));
        instructionMap.put("FLOAT",new InstructionSet(1,"C0"));
        instructionMap.put("HIO",new InstructionSet(1,"F4"));
        instructionMap.put("NORM",new InstructionSet(1,"C8"));
        instructionMap.put("SIO",new InstructionSet(1,"F0"));
        instructionMap.put("TIO",new InstructionSet(1,"F8"));

        instructionMap.put("ADDR",new InstructionSet(2,"90"));
        instructionMap.put( "CLEAR",new InstructionSet(2,"B4"));
        instructionMap.put( "COMPR",new InstructionSet(2,"A0"));
        instructionMap.put( "SUBR",new InstructionSet(2,"94"));
        instructionMap.put( "TIXR",new InstructionSet(2,"B8"));
        instructionMap.put("DIVR",new InstructionSet(2,"9C"));
        instructionMap.put("MULR",new InstructionSet(2,"98"));
        instructionMap.put("RMO",new InstructionSet(2,"AC"));
        instructionMap.put("SHIFTL",new InstructionSet(2,"A4"));
        instructionMap.put("SHIFTR",new InstructionSet(2,"A8"));
        instructionMap.put("SVC",new InstructionSet(2,"B0"));

        instructionMap.put("ADD",new InstructionSet(3,"18"));
        instructionMap.put( "AND",new InstructionSet(3,"40"));
        instructionMap.put( "COMP",new InstructionSet(3,"28"));
        instructionMap.put( "DIV",new InstructionSet(3,"24"));
        instructionMap.put( "J",new InstructionSet(3,"3C"));
        instructionMap.put( "JEQ",new InstructionSet(3,"30"));
        instructionMap.put( "JSUB",new InstructionSet(3,"48"));
        instructionMap.put( "LDA",new InstructionSet(3,"00"));
        instructionMap.put( "LDX",new InstructionSet(3,"04"));
        instructionMap.put( "LDB",new InstructionSet(3,"68"));
        instructionMap.put( "LDCH",new InstructionSet(3,"50"));
        instructionMap.put( "LDT",new InstructionSet(3,"74"));
        instructionMap.put( "RSUB",new InstructionSet(3,"4C"));
        instructionMap.put( "STA",new InstructionSet(3,"0C"));
        instructionMap.put( "STCH",new InstructionSet(3,"54"));
        instructionMap.put( "STL",new InstructionSet(3,"14"));
        instructionMap.put( "STX",new InstructionSet(3,"10"));
        instructionMap.put( "SUB",new InstructionSet(3,"1C"));
        instructionMap.put( "TD",new InstructionSet(3,"E0"));
        instructionMap.put( "TIX",new InstructionSet(3,"2C"));
        instructionMap.put( "ADD",new InstructionSet(3,"18"));
        instructionMap.put( "RD",new InstructionSet(3,"D8"));
        instructionMap.put("ADDF",new InstructionSet(3,"58"));
        instructionMap.put("COMPF",new InstructionSet(3,"88"));
        instructionMap.put("DIVF",new InstructionSet(3,"64"));
        instructionMap.put("JGT",new InstructionSet(3,"34"));
        instructionMap.put("JLT",new InstructionSet(3,"38"));
        instructionMap.put("LDF",new InstructionSet(3,"70"));
        instructionMap.put("LDL",new InstructionSet(3,"08"));
        instructionMap.put("LDS",new InstructionSet(3,"6C"));
        instructionMap.put("MUL",new InstructionSet(3,"20"));
        instructionMap.put("MULF",new InstructionSet(3,"60"));
        instructionMap.put("OR",new InstructionSet(3,"44"));
        instructionMap.put("STB",new InstructionSet(3,"78"));
        instructionMap.put("STF",new InstructionSet(3,"80"));
        instructionMap.put("STI",new InstructionSet(3,"D4"));
        instructionMap.put("STS",new InstructionSet(3,"7C"));
        instructionMap.put("STSW",new InstructionSet(3,"E8"));
        instructionMap.put("STT",new InstructionSet(3,"84"));
        instructionMap.put("SUBF",new InstructionSet(3,"5C"));
        instructionMap.put("WD",new InstructionSet(3,"DC"));
        
        instructionMap.put("+ADD",new InstructionSet(4,"18"));
        instructionMap.put( "+AND",new InstructionSet(4,"40"));
        instructionMap.put( "+COMP",new InstructionSet(4,"28"));
        instructionMap.put( "+DIV",new InstructionSet(4,"24"));
        instructionMap.put( "+J",new InstructionSet(4,"3C"));
        instructionMap.put( "+JEQ",new InstructionSet(4,"30"));
        instructionMap.put( "+JSUB",new InstructionSet(4,"48"));
        instructionMap.put( "+LDA",new InstructionSet(4,"00"));
        instructionMap.put( "+LDX",new InstructionSet(4,"04"));
        instructionMap.put( "+LDB",new InstructionSet(4,"68"));
        instructionMap.put( "+LDCH",new InstructionSet(4,"50"));
        instructionMap.put( "+LDT",new InstructionSet(4,"74"));
        instructionMap.put( "+RSUB",new InstructionSet(4,"4C"));
        instructionMap.put( "+STA",new InstructionSet(4,"0C"));
        instructionMap.put( "+STCH",new InstructionSet(4,"54"));
        instructionMap.put( "+STL",new InstructionSet(4,"14"));
        instructionMap.put( "+STX",new InstructionSet(4,"10"));
        instructionMap.put( "+SUB",new InstructionSet(4,"1C"));
        instructionMap.put( "+TD",new InstructionSet(4,"E0"));
        instructionMap.put( "+TIX",new InstructionSet(4,"2C"));
        instructionMap.put( "+ADD",new InstructionSet(4,"18"));
        instructionMap.put( "+RD",new InstructionSet(4,"D8"));
        instructionMap.put("+ADDF",new InstructionSet(4,"58"));
        instructionMap.put("+COMPF",new InstructionSet(4,"88"));
        instructionMap.put("+DIVF",new InstructionSet(4,"64"));
        instructionMap.put("+JGT",new InstructionSet(4,"34"));
        instructionMap.put("+JLT",new InstructionSet(4,"38"));
        instructionMap.put("+LDF",new InstructionSet(4,"70"));
        instructionMap.put("+LDL",new InstructionSet(4,"08"));
        instructionMap.put("+LDS",new InstructionSet(4,"6C"));
        instructionMap.put("+MUL",new InstructionSet(4,"20"));
        instructionMap.put("+MULF",new InstructionSet(4,"60"));
        instructionMap.put("+OR",new InstructionSet(4,"44"));
        instructionMap.put("+STB",new InstructionSet(4,"78"));
        instructionMap.put("+STF",new InstructionSet(4,"80"));
        instructionMap.put("+STI",new InstructionSet(4,"D4"));
        instructionMap.put("+STS",new InstructionSet(4,"7C"));
        instructionMap.put("+STSW",new InstructionSet(4,"E8"));
        instructionMap.put("+STT",new InstructionSet(4,"84"));
        instructionMap.put("+SUBF",new InstructionSet(4,"5C"));
        instructionMap.put("+WD",new InstructionSet(4,"DC"));


        instructionMap.put( "BYTE",new InstructionSet(1,""));
        instructionMap.put( "WORD",new InstructionSet(3,""));
        instructionMap.put( "RESB",new InstructionSet(5,""));
        instructionMap.put( "RESW",new InstructionSet(5,""));
    	
    	
    }
    
    
    public static int getWord(int idx,String s)
    {
        while(idx < StrInst.length() && StrInst.charAt(idx) !=' ' && ( StrInst.charAt(idx) !=',' || s.equals("WORD")) )
            ++idx;
        return idx;
    }
    public static void parse(String str)
    {
        Instruction inst = new Instruction();
        int end;
        String temp ="";
        for(int i = 0 ; i < str.length() ; i++)
        {
            if (StrInst.charAt(i)== ' ' || (StrInst.charAt(i) ==','))
                continue; //add index
            end = getWord(i,inst.name);
            temp = StrInst.substring(i,end);
            if(instructionMap.containsKey(temp) == true){
                inst.name=(temp);
                inst.format = Integer.toString(instructionMap.get(temp).format);
            }
            else if(i == 0)
                inst.setLabel(temp);
            else if(inst.getOp1() == null)
                inst.setOp1(temp);
            else
                inst.setOp2(temp);

            i=end;
        }
        instArray.add(inst);
    }
    static String addHex(String x , String y)
    {
        int a = Integer.parseInt(x, 16);
        int b = Integer.parseInt(y,16);
        int sum = a + b ;
        String z = Integer.toHexString(sum);
        return z ;
    }
    static String toAscii(String s)
    {
        int x ;
        String ret ="";
        for(int idx = 3 ; idx < s.length() - 1; idx++)
        {
            x =  (int)s.charAt(idx);
            ret += Integer.toHexString(x);
        }
        return ret ;
    }
    static LiteralTable ParseLitTable(String s , int poleCounter)
    {
        int len;
        String val;

        len = s.length()-4;
        if (s.charAt(1)=='X')
        {
            len=(len+1)/2;
        }
        val = toAscii(s);
        LiteralTable temp = new LiteralTable(len,s,val,poleCounter,"0");
        return temp;

    }
    static String GenerateLitAdd(int poleCounter , String locationCounter)
    {
        for(int idx = 0 ; idx < literaltab.size() ; idx++)
        {
            LiteralTable temp = (LiteralTable) literaltab.get(idx);
            System.out.println(temp.pole+" "+poleCounter);
            if(temp.pole == poleCounter)
            {
            	
                temp.address = locationCounter;
                locationCounter = addHex(locationCounter,Integer.toString(temp.length));
            }
        }
        return locationCounter;
    }
    static String AddressByte(String s)
    {
    	int len = s.length()-3;
    	if(s.charAt(0) =='X')
    		len=(len+1)/2;
    	return Integer.toHexString(len);
    	
    }
    static String AddressWord(String s)
    {
    	int len = 1;
    	for(int idx = 0 ; idx < s.length() ; idx++)
    	{
    		if(s.charAt(idx) == ',')
    			++len;
    	}
    	len*=3;
    	return Integer.toHexString(len);
    }
    static void passOne()
    {
    	Instruction temp= (Instruction) instArray.get(0) ;
    	temp.address = temp.op1;
    	LiteralTable lit ;
        int poleCounter = 1;
        String locationCounter = temp.op1 ;
        for(int i = 0 ; i <instArray.size() ; i++)
        {
            temp = (Instruction) instArray.get(i);
            temp.address = locationCounter;
            if(temp.label != null) {
	            if( temp.label.equals("LTORG") || (temp.name!=null&&temp.name.equals("END"))) {										//LTORG
	               locationCounter=GenerateLitAdd(poleCounter,locationCounter);
	               ++poleCounter;
	               continue;
	            }
	            else
	            	symboltab.add(new SymbolTable(temp.label,temp.address));
            }
            if(temp.op1!= null) {
                if(temp.op1.charAt(0) == '=')											//LITERAL
                {
                    if(litVisited.containsKey(temp.op1)==false) {

                        litVisited.put(temp.op1, true);
                        lit = ParseLitTable(temp.op1, poleCounter);
                        literaltab.add(lit);
                    }
                }
            }
            	if(temp.name !=null) {
	                if(temp.name.equals("WORD")  )
		            {
		            	locationCounter=addHex(locationCounter,AddressWord(temp.op1));
		            	continue;
		            }
		            else if(temp.name.equals("BYTE"))
		            {
		            	locationCounter=addHex(locationCounter,AddressByte(temp.op1));
		            	continue;
		            }
		            else if(temp.name.equals("RESB")) {											//RESB
	                    locationCounter = addHex(Integer.toHexString(Integer.parseInt(temp.op1)), locationCounter);
	                }
		            else if(temp.name.equals("RESW"))											//RESB
		            {
		                int num = Integer.parseInt(temp.op1) * 3;
		
		                locationCounter = addHex( Integer.toHexString(num),locationCounter);
		            }
		            else{
		                locationCounter=addHex(locationCounter,temp.format);
		            }  
            	}     
        }
    }
    public static void toPrint(String s)throws IOException {
    	if(s=="inst") {
    		BufferedWriter writer = new BufferedWriter(new FileWriter("Instructions.txt"));
	    	for(int i = 0 ; i <instArray.size() ; i++)
	        {
	    		Instruction inst;
	            inst = (Instruction) instArray.get(i);
	            writer.write((inst.label + " " +inst.name+" "+inst.op1+" "+inst.op2+" "+inst.address.toUpperCase()+"\n"));
	        }
	    	writer.close();
    	}
    	else if(s=="sym") {
    		BufferedWriter writer = new BufferedWriter(new FileWriter("SymbolTable.txt"));
	        for (int i =0;i<symboltab.size();i++){
	            SymbolTable x;
	            x= (SymbolTable) symboltab.get(i);
	            writer.write((x.label+" "+x.address.toUpperCase()+"\n"));
	        }
	        writer.close();
    	}
    	else if(s=="lit") {
    		BufferedWriter writer = new BufferedWriter(new FileWriter("LiteralTable.txt"));
	        for (int i =0;i<literaltab.size();i++){
	            LiteralTable x;
	            x= (LiteralTable) literaltab.get(i);
	            writer.write((x.litreal+" "+ x.length+" "+x.value.toUpperCase()+" "+x.address.toUpperCase()
	            +" "+x.pole+"\n"));
	        }
	        writer.close();
    	}    
        
    }
    public static void main(String[] args) throws IOException {
    	
        generateHashMap();
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
            while((StrInst = br.readLine()) !=null)
            {
                parse(StrInst);
            }
        }
        
        passOne();
        toPrint("inst");
        toPrint("sym");
        toPrint("lit");
        
    }

}
