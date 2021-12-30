package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.lang.Object;
import org.junit.Assert;
import java.lang.Math;
import java.util.NoSuchElementException;

public class ClevisTest {
    Clevis clevis;
    String name1="John";
    String name2="Max";
    String name3;
    double x1=5;
    double y1=5;
    double x2=10;
    double y2=10;
    double w=25;
    double l=25;
    double r1=10;
    double move=5.0;
    String html = "temphtml";
    String txt = "temptxt";
    String[] list= new String[]{"Shape","Two"};
    int index=1;


    @Test
    public void testClevisConstructor(){
        Clevis clevis = new Clevis(name1);
        assert clevis.getId().compareTo(name1)==0;
        clevis.setId(name2);
        assert clevis.getId().compareTo(name2)==0;
    }

    @Test
    public void testCircle(){
        Circle temp = new Circle( x1,y1,r1,name1);
        assert temp.getX()==x1;
        assert temp.getY()==y1;
        assert temp.getX1()==(x1+r1);
        assert temp.getY1()==(y1+r1);
        assert temp.getRad()==r1;

        assert temp.findArea()==Math.PI * Math.pow(r1, 2);
        assert temp.findPerimeter()==Math.PI * (2 * r1);
        temp.moveX1(move);
        assert temp.getX()==x1+move;
        temp.moveY1(move);
        assert temp.getY()==y1+move;
    }

    @Test
    public void testRectangle(){
        Rectangle temp = new Rectangle(x1,y1,w,l,name1);
        assert temp.getX()==x1;
        assert temp.getY()==y1;
        assert temp.getSideAlpha()==w;
        assert temp.getSideBravo()==l;
        assert temp.getX1()==w+x1;
        assert temp.getY1()==l+y1;

        assert temp.findArea()==w*l;
        assert temp.findPerimeter()==(2*w)+(2*l);

        temp.setSideBravo(move);
        assert temp.getSideBravo()==move;
        temp.moveX(move);
        assert temp.getX()==x1+move;
        temp.moveY(move);
        assert temp.getY()==y1+move;
    }


    @Test
    public void testMyCanvas(){
        MyCanvas temp = new MyCanvas();
        temp.setCircle(x1,x2,y1,index);
        temp.setLine(x1,y1,x2,y2,index);
        temp.setRec(x1,y1,x2,y2,index);
        temp.setSquare(x1,y1,x2,index);
        //temp.paint(new Graphics(););
    }

    @Test
    public void testSquare(){
        Square temp = new Square(x1,y1,w,name2);

        assert temp.getX()==x1;
        assert temp.getY()==y1;
        assert temp.getX1()==x1+w;
        assert temp.getY1()==y1+w;
        assert temp.getSide()==w;

        assert temp.findArea()==w*w;
        assert temp.findPerimeter()==4*w;

        temp.setSideAlpha(5.0);
        assert temp.getSide()==5.0;

        temp.moveX(move);
        assert temp.getX()==x1+move;
        temp.moveY(move);
        assert temp.getY()==y1+move;

    }

    @Test
    public void testLine(){
        Line temp = new Line(x1,y1,x2,y2,name2);

        assert temp.getX1()==x1;
        assert temp.getY1()==y1;
        assert temp.getX2()==x2;
        assert temp.getY2()==y2;
        assert temp.getminX1()==x1;
        assert temp.getminY1()==y1;
        assert temp.getminX2()==x2;
        assert temp.getminY2()==y2;
        assert temp.getcorrY()==y1;
        assert temp.getLen()==Math.sqrt(Math.pow((x1 - y1), 2) + Math.pow((x2 - y2), 2));
        assert temp.findArea()==0;
        assert temp.findPerimeter()==0;

        temp.moveX1(move);
        assert temp.getX1()==x1+move;
        temp.moveY1(move);
        assert temp.getY1()==y1+move;
        temp.moveX2(move);
        assert temp.getX2()==x2+move;
        temp.moveY2(move);
        assert temp.getY2()==y2+move;

    }

    @Test
    public void testprintDocuments(){
        printDocuments temp = new printDocuments(txt,html);
        temp.writefileTXT(list);
        temp.writefileHTML(list,index);

    }

    @Test
    public void testDatabase(){
        Clevis test1 = new Clevis();
        test1.setId(name1);
        String oldName = "Daniel";
        Database temp = new Database();
        temp.add(test1);
        try {
            temp.changeID(test1.getId(), oldName);
        }catch (NoSuchElementException e){assert false;}
        assert test1.getId().compareTo(oldName)==0;
        try {
            assert test1==temp.get(oldName);
        }catch (NoSuchElementException e){assert false;}

        Database temp2 = new Database();
        try {
            temp2.changeID(oldName, oldName);
        }catch (NoSuchElementException e){assert true;}

        try{
            temp2.get(oldName);
        }catch (NoSuchElementException e){assert true;}

        temp.toString();


    }

}