package hk.edu.polyu.comp.comp2021.clevis.model;

import javax.swing.JComponent; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@SuppressWarnings("ALL")
public class MyCanvas extends JComponent {

	int x, y, w, h;
	int record;
    public void setRec(double x, double y, double w, double h, int record) {
        this.x=(int) Math.round(x); this.y=(int) Math.round(y); this.w=(int) Math.round(w); this.h=(int) Math.round(h); this.record=record;
        repaint();//mark this component to be repainted
    }

    public void setLine(double x, double y, double w, double h, int record) {
        this.x=(int) Math.round(x); this.y=(int) Math.round(y); this.w=(int) Math.round(w); this.h=(int) Math.round(h); this.record=record;
        repaint();
    }

    public void setCircle(double x, double y, double w, int record) {
        this.x=(int) Math.round(x); this.y=(int) Math.round(y); this.w=(int) Math.round(w); this.record=record;
        repaint();
    }
    public void setSquare(double x, double y, double w, int record) {
        this.x=(int) Math.round(x); this.y=(int) Math.round(y); this.w=(int) Math.round(w); this.record=record;
        repaint();
    }

    @Override
	public void paint(Graphics g) {
 
	  super.paint(g);
		
	  Graphics2D g2d = (Graphics2D)g;
	  
	  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	  

	  /**
	    * The coordinate system of a graphics context is such that the origin is at the 
	    * northwest corner and x-axis increases toward the right while the y-axis increases 
	    * toward the bottom.
	    */
	  
	  switch(record) {
	  	case 0:
		  g2d.drawRect(x, y, w, h);
		  break;
	  	case 1:
	  		g2d.drawLine(x, y, w, h);
	  		break;
	  	case 2:
	  		g2d.drawOval(x-w, y-w, w*2, w*2);
	  		break;
	  	case 3:
			  g2d.drawRect(x, y, w, w);
			  break;
	  	default:
	  		System.out.println("Command not recognised.");
	  }
	 	 
    }
 
 }

