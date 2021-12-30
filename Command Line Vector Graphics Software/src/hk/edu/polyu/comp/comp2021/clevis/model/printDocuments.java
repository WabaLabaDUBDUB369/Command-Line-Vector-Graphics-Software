package hk.edu.polyu.comp.comp2021.clevis.model;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;

@SuppressWarnings("ALL")
public class printDocuments {
    String name;
    String name2;
    public printDocuments(String name,String name2){this.name=name;
        this.name2=name2;
        this.clearFiles();
        createfileTXT();
        createfileHTML();
    }
    public void createfileTXT(){ //should never be called outside of class
        try{
            File obj = new File(""+name+".txt");
            name=name+".txt";
            if(!obj.createNewFile()){
                System.out.println("File Name already used");
            }
        }catch (IOException e){System.out.println("Error Occured");}
    }
    public void createfileHTML(){//should never be called outside of class
        try{
            File obj = new File(""+name2+".html");
            name2=name2+".html";
            if(!obj.createNewFile()){
                System.out.println("File Name already used");
                return;
            }
            BufferedWriter output = new BufferedWriter(new FileWriter(name2,true));
            output.append("<TABLE BORDER><TR><TH>Operation Index<TH>Operation Command</TR>");
            output.close();
        }catch (IOException e){System.out.println("Error Occured");}
    }
    public void writefileTXT(String[] info){ //info is the string of information required by req 1
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(name,true));
            String fin="";
            for(int x=0;x<info.length;x++){
                fin+=info[x]+" ";
            }
            output.append(fin);
            output.newLine();
            output.close();
        }catch (IOException e){return;}
    }
    public void writefileHTML(String[] info,int index){ //info is the string of information required by req 1
        try {
            PrintWriter output = new PrintWriter(new FileWriter(name2,true));
            String fin="";
            for(int x=0;x<info.length;x++) {
                fin += info[x] + " ";
            }
            output.println("<TR><TD>"+index+"<TD>"+fin);
            output.close();
        }catch (IOException e){return;}
    }

    public void clearFiles(){//should never be called outside of class
        try{
            Files.deleteIfExists(Paths.get(name+".txt"));
            Files.deleteIfExists(Paths.get(name2+".html"));
        } catch (IOException e){System.out.println("Error Deleting");}
    }
}
