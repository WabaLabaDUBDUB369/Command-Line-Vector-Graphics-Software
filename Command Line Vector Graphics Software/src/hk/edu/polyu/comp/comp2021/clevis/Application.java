package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.*;

import javax.swing.JFrame;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Area;

/**
 *
 */
public final class Application {


	/**
	 * @param args an array of command-line arguments for the application
	 */
	@SuppressWarnings("ChainOfInstanceofChecks")
	public static void main(String[] args) {

		//Object that will be storing all the records.
		Database database = new Database();

		// Create a frame
		JFrame frame = new JFrame("Graphical Interface");
		
		//Creating object of a class which will help in checking for group shapes, swapping elements of an ArrayList and printing the bounding box details
		print_swap_CheckGrpShape T = new print_swap_CheckGrpShape();


		// Add a component with a custom paint method
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MyCanvas canvas = new MyCanvas();
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    Scanner in = new Scanner(System.in);
	    
		final int frameWidth = 400;

		final int frameHeight = 400;
		
		boolean inMenu = true;

		System.out.println("\nHello There! Welcome to our CLEVIS!\n");
		System.out.println("===========================================");
		System.out.println("The purpose of this Program is to create and manipulate vector graphics containing shapes like lines, circles, rectangles, and squares \n");
		System.out.println("It also includes methods to determine advanced properties of a geometric shape (the area, perimeter, " +
				"surface area, volume).");
		System.out.println("===========================================");
		
		ArrayList<String> database1 = new ArrayList<String>();

		HashMap<String, Integer> map = new HashMap<String, Integer>();

		int starting_index = 0;
		boolean redo = false;
		String command[] = null;
		String[] copy = null;

		ArrayList <Clevis> undo_delete = new ArrayList<>();
		int undo_index=0, start=0, getStartI=0, getStartnum=0, Index=1;
		Clevis Shape_moved=null;
		String undo_string="";

		printDocuments print = new printDocuments("-txt log", "-html log");

		while (inMenu) {
			double sideA, sideB, sideC, sideD;
			double sideLength;
			boolean tracking = false, check;
			Clevis Y;

			System.out.println("Please continue with valid command:");

			if(redo == false) {
				if(command != null) {
					copy = command.clone();
				}
		        String temp = null;
		        String[] str = null;
		        try {
		            temp = br.readLine();
		            str = temp.trim().split("\\s+");
		        } catch (IOException e) {
		            e.printStackTrace();
		            System.err.println("IO Exception happened when trying to listen for input.");
		        }
				command = str;
			}else {
				command = copy.clone();
				redo = false;
			}

			switch (command[0].toUpperCase()) {

				case "RECTANGLE":
					if(command.length != 6) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					for(Clevis x: database) {
						if(x.getId().equals(command[1])) {
							System.out.println("A rectangle has already been created with a similar name.\nPlease use a different name!\n");
							System.out.println("===========================================");
							tracking = true;
							break;
						}
					}

					if(tracking == true) {
						break;
					}

					try {
						Double.parseDouble(command[2]);
						Double.parseDouble(command[3]);
						Double.parseDouble(command[4]);
						Double.parseDouble(command[5]);
					}
					catch(NumberFormatException e){
						System.err.println("Please input a double value. eg: " + ThreadLocalRandom.current().nextDouble(0, 100 + 1));
						System.out.println("===========================================");
						break;
					}

					sideA = Double.parseDouble(command[2]);
					sideB = Double.parseDouble(command[3]);
					sideC = Double.parseDouble(command[4]);
					sideD = Double.parseDouble(command[5]);

					database.insert(new Rectangle(sideA, sideB, sideC, sideD, command[1]));
					T.creationDonePrompt("Rectangle");


					// Display the frame
					frame.setSize(frameWidth, frameHeight);

					frame.setVisible(true);

					canvas.setRec(sideA, sideB, sideC, sideD, 0);
					frame.getContentPane().add(canvas);
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "SQUARE":
					if(command.length != 5) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					for(Clevis x:database) {
						if(x.getId().equals(command[1])) {
							System.out.println("A square has already been created with a similar name.\nPlease use a different name!\n");
							System.out.println("===========================================");
							tracking = true;
							break;
						}
					}

					if(tracking == true) {
						break;
					}

					try {
						Double.parseDouble(command[2]);
						Double.parseDouble(command[3]);
						Double.parseDouble(command[4]);
					}
					catch(NumberFormatException e){
						System.err.println("Please input a double value. eg: " + ThreadLocalRandom.current().nextDouble(0, 100 + 1));
						System.out.println("===========================================");
						break;
					}

					sideA = Double.parseDouble(command[2]);
					sideB = Double.parseDouble(command[3]);
					sideC = Double.parseDouble(command[4]);

					database.insert(new Square(sideA, sideB, sideC, command[1]));
					T.creationDonePrompt("square");

					frame.setSize(frameWidth, frameHeight);

					frame.setVisible(true);

					canvas.setSquare(sideA, sideB, sideC, 3);
					frame.getContentPane().add(canvas);
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "LINE":
					if(command.length != 6) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					for(Clevis x:database) {
						if(x.getId().equals(command[1])) {
							System.out.println("A line has already been created with a similar name.\nPlease use a different name!\n");
							System.out.println("===========================================");
							tracking = true;
							break;
						}
					}
					if(tracking == true) {
						break;
					}

					try {
						Double.parseDouble(command[2]);
						Double.parseDouble(command[3]);
						Double.parseDouble(command[4]);
						Double.parseDouble(command[5]);
					}
					catch(NumberFormatException e){
						System.err.println("Please input a double value. eg: " + ThreadLocalRandom.current().nextDouble(0, 100 + 1));
						System.out.println("===========================================");
						break;
					}

					sideA = Double.parseDouble(command[2]);
					sideB = Double.parseDouble(command[3]);
					sideC = Double.parseDouble(command[4]);
					sideD = Double.parseDouble(command[5]);

					database.insert(new Line(sideA, sideB, sideC, sideD, command[1]));
					T.creationDonePrompt("Line");

					frame.setSize(frameWidth, frameHeight);

					frame.setVisible(true);

					canvas.setLine(sideA, sideB, sideC, sideD, 1);
					frame.getContentPane().add(canvas);
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "CIRCLE":

					if(command.length != 5) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					for(Clevis x:database) {
						if(x.getId().equals(command[1])) {
							System.out.println("A circle has already been created with a similar name.\nPlease use a different name!\n");
							System.out.println("===========================================");
							tracking = true;
							break;
						}
					}
					if(tracking == true) {
						break;
					}

					try {
						Double.parseDouble(command[2]);
						Double.parseDouble(command[3]);
						Double.parseDouble(command[4]);
					}
					catch(NumberFormatException e){
						System.err.println("Please input a double value. eg: " + ThreadLocalRandom.current().nextDouble(0, 100 + 1));
						System.out.println("===========================================");
						break;
					}

					sideA = Double.parseDouble(command[2]);
					sideB = Double.parseDouble(command[3]);
					sideC = Double.parseDouble(command[4]);

					database.insert(new Circle(sideA, sideB, sideC, command[1]));
					T.creationDonePrompt("Circle");

					frame.setSize(frameWidth, frameHeight);

					frame.setVisible(true);

					canvas.setCircle(sideA, sideB, sideC, 2);
					frame.getContentPane().add(canvas);
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "LISTALL":
					if(command.length != 1) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					ArrayList<String> reC = new ArrayList<String>();
					int Track=0;
					for (int i=database.size()-1; i>=0; i--) {
						Y = database.get(i);

						for(int q=0; q<reC.size(); q++) {
							if(Y.getId().equals(reC.get(q))) {
								Track=1;
								break;
							}
						}
						if(Track == 1) {
							Track = 0;
							continue;
						}

						check = false;

						for(String I: map.keySet()) {
							if(Y.getId().equals(I)) {
								check = true;
								reC.add(I);
								break;
							}
						}


						if(check == false) {
							System.out.println("Shape not in a group:\n");
							System.out.println(Y.toString());
							System.out.println("===========================================");
						}else {
							System.out.println("Shapes in group name \"" + Y.getId() + "\": \nThe shapes will have the same name as their group name.\n");
							for(Clevis x:database) {
								if(x.getId().equals(Y.getId())) {
									System.out.println(x.toString());
								}
							}
							System.out.println("===========================================");
						}

					}
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "LIST":
					if(command.length != 2) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					try {
						Y = database.get(command[1]);
					}catch(Exception e) {
						System.err.println("No records with name - " + command[1] + " found in database.");
						System.out.println("===========================================");
						break;
					}

					check = false;

					for(String i: map.keySet()) {
						if(command[1].equals(i)) {
							check = true;
							break;
						}
					}


					if(check == false) {
						System.out.println("Shapes not in a group:\n");
						for(Clevis x:database) {
							if(x.getId().equals(Y.getId())) {
								System.out.println(x.toString());
								System.out.println("===========================================");
							}
						}
						System.out.println("===========================================");
					}else {
						System.out.println("Shapes in group name \"" + command[1] + "\": \nThe shapes will have the same name as their group name.\n");
						for(Clevis x:database) {
							if(x.getId().equals(Y.getId())) {
								System.out.println(x.toString());
							}
						}
						System.out.println("===========================================");
					}
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;

				case "GROUP":
					int length=0;
					for(Clevis x:database) {
						if(x.getId().equals(command[1])) {
							System.out.println("Shapes have already been grouped with a similar name.\nPlease use a different grouping name!\n");
							System.out.println("===========================================");
							tracking = true;
							break;
						}
					}
					if(tracking == true) {
						break;
					}

					try {
						for(int i=2; i<command.length; i++) {
							try {
								database1.add(database.get(command[i]).getId());
								length++;
							}catch(Exception e) {
								System.err.println("No records with name - " + command[i] + " found in database.\n");
								System.out.println("===========================================");
								throw new NoSuchElementException();
							}
						}
					}catch(Exception e) {
						break;
					}
					map.put(command[1], starting_index);
					int j = starting_index;
					for (Clevis x: database) {
						for(int i=j; i<database1.size(); i++) {
							if(x.getId().equals(database1.get(i))) {
								T.swap(database1, database1.get(j), database1.get(i));
								j++;
								break;
							}
						}
					}

					for (int i=starting_index; i<database1.size(); i++) {
						for(Clevis y: database) {
							if(database1.get(i).equals(y.getId())) {
								System.out.println("The shape \"" + database1.get(i) + "\" has been grouped in group \"" + command[1] + "\".");
								database.changeID(y.getId(), command[1]);
								break;
							}
						}
					}
					System.out.println("===========================================");
					starting_index += length;
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "UNGROUP":
					if(command.length != 2) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}
					int l=0;
					for(Clevis x: database) {
						try {
							if(x.getId().equals(command[1])) {
								l++;
							}
						}catch(Exception e) {
							System.err.println("No records with name - " + command[1] + " found in database.\n");
							System.out.println("===========================================");
							tracking = true;
							break;
						}
					}
					if(tracking == true) {
						break;
					}

					try {
						start = map.get(command[1]);
					}catch(Exception e) {
						System.out.println("No shapes can be found grouped with name - " + command[1] + " in the database.");
						System.out.println("===========================================");
						break;
					}

					for (int i=start; i<start+l; i++) {
						for(Clevis x: database) {
							if(x.getId().equals(command[1])) {
								System.out.println("The shape \"" + database1.get(i) + "\" has been removed from group \"" + command[1] + "\".");
								database.changeID(x.getId(), database1.get(i));
								break;
							}
						}
					}
					System.out.println("===========================================");
					getStartnum = l;
					getStartI = start;
					map.remove(command[1]);

					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "BOUNDINGBOX":
					if(command.length != 2) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					Clevis y;
					final double wide = 0.01;
					
					try {
						y = database.get(command[1]);
					}catch(Exception e) {
						System.err.println("No records with name - " + command[1] + " found in database.");
						System.out.println("===========================================");
						break;
					}

					check = false;
					for(String i: map.keySet()) {
						if(command[1].equals(i)) {
							check = true;
							break;
						}
					}


					if(check == false) {
						if(y.getClass() == Square.class) {
							Square z = (Square)(y);
							T.printBoundBox(z.getX(), z.getY(), z.getSide(), z.getSide());
						}
						else if(y.getClass() == Rectangle.class) {
							Rectangle z = (Rectangle)(y);
							T.printBoundBox(z.getX(), z.getY(), z.getSideAlpha(), z.getSideBravo());
						}
						else if(y.getClass() == Line.class) {
							Line z = (Line)(y);
							T.printBoundBox(z.getminX1(), z.getcorrY(), z.getLen(), wide);
						}
						else if(y.getClass() == Circle.class) {
							Circle z = (Circle)(y);
							T.printBoundBox(z.getX()-z.getRad(), z.getY()-z.getRad(), z.getRad()*2, z.getRad()*2);
						}
					}else {
						List<Double> minx = new ArrayList<>();
						List<Double> miny = new ArrayList<>();
						List<Double> maxx = new ArrayList<>();
						List<Double> maxy = new ArrayList<>();
						for(Clevis x: database) {
							if(x.getId().equals(command[1])) {
								if(x.getClass() == Square.class) {
									Square z = (Square)(x);
									minx.add(z.getX());
									miny.add(z.getY());
									maxx.add(z.getX1());
									maxy.add(z.getY1());
								}
								else if(x.getClass() == Rectangle.class) {
									Rectangle z = (Rectangle)(x);
									minx.add(z.getX());
									miny.add(z.getY());
									maxx.add(z.getX1());
									maxy.add(z.getY1());
								}
								else if(x.getClass() == Line.class) {
									Line z = (Line)(x);
									minx.add(z.getminX1());
									miny.add(z.getminY1());
									maxx.add(z.getminX2());
									maxy.add(z.getminY2());
								}
								else if(x.getClass() == Circle.class) {
									Circle z = (Circle)(x);
									minx.add(z.getX() - z.getRad());
									miny.add(z.getY() - z.getRad());
									maxx.add(z.getX1());
									maxy.add(z.getY1());
								}
							}
						}

						double minX, minY, maxX, maxY;
						Collections.sort(minx);
						Collections.sort(miny);
						Collections.sort(maxx);
						Collections.sort(maxy);

						minX = minx.get(0);
						minY = miny.get(0);
						maxX = maxx.get(maxx.size()-1);
						maxY = maxy.get(maxy.size()-1);

						System.out.println("For this group shape: ");
						T.printBoundBox(minX, minY, maxX - minX, maxY - minY);
						System.out.println("===========================================");
					}

					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "MOVE":
					if(command.length != 4) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					try {
						y = database.get(command[1]);
					}catch(Exception e) {
						System.err.println("No records with name - " + command[1] + " found in database.");
						System.out.println("===========================================");
						break;
					}

					try {
						Double.parseDouble(command[2]);
						Double.parseDouble(command[3]);
					}
					catch(NumberFormatException e){
						System.err.println("Please input a double value. eg: " + ThreadLocalRandom.current().nextDouble(0, 100 + 1));
						System.out.println("===========================================");
						break;
					}

					boolean rec = false;
					for(String x:map.keySet()) {
						if(x.equals(command[1])) {
							rec = true;
							break;
						}
					}

					if(rec == false) {
						for (int i=0; i<database.size(); i++) {
							if((database.get(i).getId()).equals(command[1])) {
								y = database.get(command[1]);
								if(y.getClass() == Square.class) {
									Square z = (Square)(y);
									z.moveX(Double.parseDouble(command[2]));
									z.moveY(Double.parseDouble(command[3]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
								}
								else if(y.getClass() == Rectangle.class) {
									Rectangle z = (Rectangle)(y);
									z.moveX(Double.parseDouble(command[2]));
									z.moveY(Double.parseDouble(command[3]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
								}
								else if(y.getClass() == Line.class) {
									Line z = (Line)(y);
									z.moveX1(Double.parseDouble(command[2]));
									z.moveY1(Double.parseDouble(command[3]));
									z.moveX2(Double.parseDouble(command[2]));
									z.moveY2(Double.parseDouble(command[3]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
								}
								else if(y.getClass() == Circle.class) {
									Circle z = (Circle)(y);
									z.moveX1(Double.parseDouble(command[2]));
									z.moveY1(Double.parseDouble(command[3]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
								}
							}
						}
					}else {

						for (int i=0; i<database.size(); i++) {
							if((database.get(i).getId()).equals(command[1])) {
								y = database.get(i);
								if(y.getClass() == Square.class) {
									Square z = (Square)(y);
									z.moveX(Double.parseDouble(command[2]));
									z.moveY(Double.parseDouble(command[3]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
								}
								else if(y.getClass() == Rectangle.class) {
									Rectangle z = (Rectangle)(y);
									z.moveX(Double.parseDouble(command[2]));
									z.moveY(Double.parseDouble(command[3]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
								}
								else if(y.getClass() == Line.class) {
									Line z = (Line)(y);
									z.moveX1(Double.parseDouble(command[2]));
									z.moveY1(Double.parseDouble(command[3]));
									z.moveX2(Double.parseDouble(command[2]));
									z.moveY2(Double.parseDouble(command[3]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
								}
								else if(y.getClass() == Circle.class) {
									Circle z = (Circle)(y);
									z.moveX1(Double.parseDouble(command[2]));
									z.moveY1(Double.parseDouble(command[3]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
								}
							}
						}
					}
					System.out.println("===========================================");
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "PICK-AND-MOVE":
					if(command.length != 5) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}

					try {
						Double.parseDouble(command[1]);
						Double.parseDouble(command[2]);
						Double.parseDouble(command[3]);
						Double.parseDouble(command[4]);
					}
					catch(NumberFormatException e){
						System.err.println("Please input a double value. eg: " + ThreadLocalRandom.current().nextDouble(0, 100 + 1));
						System.out.println("===========================================");
						break;
					}

					boolean REC = false, Check=false;
					ArrayList<String> record = new ArrayList<String>();
					int track=0;
					for (int i=database.size()-1; i>=0; i--) {
						y = database.get(i);
						for(int q=0; q<record.size(); q++) {
							if(y.getId().equals(record.get(q))) {
								track=1;
								break;
							}
						}
						if(track == 1) {
							track = 0;
							continue;
						}
						if(y.getClass() == Square.class) {
							Square z = (Square)(y);
							Rectangle2D.Double square = new Rectangle2D.Double(z.getX(),z.getY(),z.getSide(),z.getSide());
							if (square.contains(Double.parseDouble(command[1]), Double.parseDouble(command[2]))) {

								for(String x: map.keySet()) {
									if((y.getId()).equals(x)) {
										record.add(y.getId());
										Check = T.checkGrpShape(database, command, x, i);
										break;
									}
								}
								if(Check == true) {
									System.out.println("The outline of group shape named \"" + y.getId() + "\" contained the point and all its shapes have been moved successfully!\n");
									Shape_moved = y;
									REC = true;
									break;
								}
								else {
									z.moveX(Double.parseDouble(command[3]));
									z.moveY(Double.parseDouble(command[4]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
									Shape_moved = y;
									REC = true;
									break;
								}
							}else {
								continue;
							}
						}
						else if(y.getClass() == Rectangle.class) {
							Rectangle z = (Rectangle)(y);
							Rectangle2D.Double rectangle = new Rectangle2D.Double(z.getX(),z.getY(),z.getSideAlpha(),z.getSideBravo());
							if (rectangle.contains(Double.parseDouble(command[1]), Double.parseDouble(command[2]))) {


								for(String x: map.keySet()) {
									if((y.getId()).equals(x)) {
										record.add(y.getId());
										Check = T.checkGrpShape(database, command, x, i);
										break;
									}
								}

								if(Check == true) {
									System.out.println("The group shape named \"" + y.getId() + "\" contained the point and has been moved successfully!\n");
									Shape_moved = y;
									REC = true;
									break;
								}
								else {
									z.moveX(Double.parseDouble(command[3]));
									z.moveY(Double.parseDouble(command[4]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
									Shape_moved = y;
									REC = true;
									break;
								}
							}else {
								continue;
							}
						}
						else if(y.getClass() == Line.class) {
							Line z = (Line)(y);
							Line2D.Double line = new Line2D.Double(z.getX1(),z.getY1(),z.getX2(),z.getY2());
							if (line.contains(Double.parseDouble(command[1]), Double.parseDouble(command[2]))) {

								for(String x: map.keySet()) {
									if((y.getId()).equals(x)) {
										record.add(y.getId());
										Check = T.checkGrpShape(database, command, x, i);
										break;
									}
								}

								if(Check == true) {
									System.out.println("The group shape named \"" + y.getId() + "\" contained the point and has been moved successfully!\n");
									Shape_moved = y;
									REC = true;
									break;
								}
								else {
									z.moveX1(Double.parseDouble(command[3]));
									z.moveY1(Double.parseDouble(command[4]));
									z.moveX2(Double.parseDouble(command[3]));
									z.moveY2(Double.parseDouble(command[4]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
									Shape_moved = y;
									REC = true;
									break;
								}
							}else {
								continue;
							}
						}
						else if(y.getClass() == Circle.class) {
							Circle z = (Circle)(y);
							Ellipse2D.Double circle = new Ellipse2D.Double(z.getX()-z.getRad(), z.getY()-z.getRad(), z.getRad()*2, z.getRad()*2);
							if (circle.contains(Double.parseDouble(command[1]), Double.parseDouble(command[2]))) {

								for(String x: map.keySet()) {
									if((y.getId()).equals(x)) {
										record.add(y.getId());
										Check = T.checkGrpShape(database, command, x, i);
										break;
									}
								}
								if(Check == true) {
									System.out.println("The group shape named \"" + y.getId() + "\" contained the point and has been moved successfully!\n");
									Shape_moved = y;
									REC = true;
									break;
								}
								else {
									z.moveX1(Double.parseDouble(command[3]));
									z.moveY1(Double.parseDouble(command[4]));
									System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
									Shape_moved = y;
									REC = true;
									break;
								}
							}else {
								continue;
							}
						}
					}

					if(REC == false) {
						System.out.println("None of the shapes contain this point.");
					}
					System.out.println("===========================================");
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "INTERSECT":
					if(command.length != 3) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}
					Clevis O;
					try {
						O = database.get(command[1]);
						y = database.get(command[2]);
					}catch(Exception e) {
						System.err.println("No records with one or both of the names can be found in database.");
						System.out.println("===========================================");
						break;
					}

					Area area1 = null;
					Area area2 = null;

					for(int i=0; i<2; i++) {
						O = database.get(command[i+1]);
						if(O.getClass() == Square.class) {
							Square z = (Square)(O);
							Rectangle2D.Double square = new Rectangle2D.Double(z.getX(),z.getY(),z.getSide(),z.getSide());
							if(i == 1) {
								area2 = new Area(square);
								break;
							}
							area1 = new Area(square);
						}
						else if(O.getClass() == Rectangle.class) {
							Rectangle z = (Rectangle)(O);
							Rectangle2D.Double rectangle = new Rectangle2D.Double(z.getX(),z.getY(),z.getSideAlpha(),z.getSideBravo());
							if(i == 1) {
								area2 = new Area(rectangle);
								break;
							}
							area1 = new Area(rectangle);

						}
						else if(O.getClass() == Line.class) {
							Line z = (Line)(O);
							Line2D.Double line = new Line2D.Double(z.getX1(),z.getY1(),z.getX2(),z.getY2());
							if(i == 1) {
								area2 = new Area(line);
								break;
							}
							area1 = new Area(line);

						}
						else if(O.getClass() == Circle.class) {
							Circle z = (Circle)(O);
							Ellipse2D.Double circle = new Ellipse2D.Double(z.getX()-z.getRad(), z.getY()-z.getRad(), z.getRad()*2, z.getRad()*2);
							if(i == 1) {
								area2 = new Area(circle);
								break;
							}
							area1 = new Area(circle);

						}
					}

					area1.intersect(area2);

					if(!area1.isEmpty()){
						System.out.println("The two shapes intersect.\n");
					}
					else{
						System.out.println("The two shapes do not intersect.\n");
					}

					System.out.println("===========================================");
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "REDO":
					if(command.length != 1 || copy == null) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}
					String sss = copy[0].toUpperCase();
					if(sss.equals("BOUNDINGBOX") || sss.equals("INTERSECT") || sss.equals("LIST") || sss.equals("LISTAll") || sss.equals("QUIT") || sss.equals("UNDO")) {
						System.out.println("Can not use redo with this command \"" + copy[0] + "\".\n");
					}else {
						redo = true;
						System.out.println("This command of \"" + copy[0] + "\" will be executed again.");
					}
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "UNDO":
					if(command.length != 1 || copy == null) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}
					String[] X = null;
					String sos = copy[0].toUpperCase();
					if(sos.equals("BOUNDINGBOX") || sos.equals("INTERSECT") || sos.equals("LIST") || sos.equals("LISTAll") || sos.equals("QUIT") || sos.equals("REDO")) {
						System.out.println("Can not use undo with this command \"" + copy[0] + "\".\n");
					}else {
						System.out.println("These are the results of the undo operation: \n");
						redo = true;
						if(sos.equals("RECTANGLE") || sos.equals("LINE") || sos.equals("SQUARE") || sos.equals("CIRCLE")) {
							String[] m = {"delete", copy[1]};
							copy = m.clone();
						}
						else if(sos.equals("GROUP")) {
							String[] m = {"ungroup", copy[1]};
							copy = m.clone();
						}
						else if(sos.equals("UNGROUP")) {
							int undo_start, index=2;
							undo_start = getStartI;

							String[] m = new String[getStartnum+2];
							m[0] = "group";
							m[1] = copy[1];
							for(int i=undo_start; i<undo_start+getStartnum; i++) {
								m[index] = database1.get(i);
								index++;
							}

							copy = m.clone();
						}
						else if(sos.equals("DELETE")) {

							for(Clevis x: undo_delete) {
								database.add(x);
								System.out.println("The shape \"" + x.getId() + "\" has been added.");
							}
							if(undo_string.equals(copy[1])) {
								map.put(undo_string, undo_index);
							}
							redo = false;
						}
						else if(sos.equals("MOVE")) {
							double num1 = Double.parseDouble(copy[2]) * -1;
							double num2 = Double.parseDouble(copy[3]) * -1;
							String[] m = {"move", copy[1], String.valueOf(num1), String.valueOf(num2)};
							copy = m.clone();
						}
						else if(sos.equals("PICK-AND-MOVE")) {
							double num3 = Double.parseDouble(copy[3]) * -1;
							double num4 = Double.parseDouble(copy[4]) * -1;
							String[] m = {"move", Shape_moved.getId(), String.valueOf(num3), String.valueOf(num4)};
							copy = m.clone();
						}
					}
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;



				case "DELETE":
					Clevis Z;
					try {
						Z = database.get(command[1]);
					}catch(Exception e) {
						System.err.println("No records with name - " + command[1] + " found in database.");
						System.out.println("===========================================");
						break;
					}
					undo_delete.clear();
					undo_index=0;
					undo_string="";

					for(String x: map.keySet()) {
						if(x.equals(Z.getId())) {
							undo_index = map.get(x);
							undo_string = command[1];
							map.remove(x);
							break;
						}
					}
					Database db = (Database)database.clone();
					for(Clevis x:db) {
						if(x.getId().equals(Z.getId())) {
							undo_delete.add(x);
							database.remove(x);
							System.out.println("Shape deleted successfully!");
							System.out.println("===========================================");
						}
					}
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;


				case "QUIT":
					if(command.length != 1) {
						System.err.println("Command not recognized, please try again");
						System.out.println("===========================================");
						break;
					}
					inMenu = false;
					System.out.println("Thank you for using our program! Have a nice day!");
					print.writefileTXT(command);
					print.writefileHTML(command, Index++);
					break;

				default:
					System.err.println("Command not recognized, please try again");
					System.out.println("===========================================");
			}
		}
        try {
            br.close();
        } catch (IOException e) {
            System.err.println("IO Exception happened when trying to close the BufferedReader, please close the program");
        }
        in.close();
		return;
	}
}




/**
 * Class for checking grouped shapes, swapping elements in an ArrayList and printing the bounding box details
 */
class print_swap_CheckGrpShape {

	/**
	 * @param db It is the database that we use in our program
	 * @param str The string array of the user command
	 * @param X The name of the grouping shape, if the shape is in a group
	 * @param k The for-loop iteration index
	 * @return  will return true if all the shapes contain that (x, y) point an dreturn false otherwise
	 */
	//Check if a shape is in a group, for the pick-and-move command
	public boolean checkGrpShape(Database db, String[] str, String X, int k){

		boolean track = true;
		for (int i=k; i>=0; i--) {
			Clevis y = db.get(i);
			if(y.getId().equals(X)) {
				if(y.getClass().equals(Square.class)) {
					Square z = (Square)(y);
					Rectangle2D.Double square = new Rectangle2D.Double(z.getX(),z.getY(),z.getSide(),z.getSide());
					if (square.contains(Double.parseDouble(str[1]), Double.parseDouble(str[2]))) {
						track = checkGrpShape(db, str, X, i-1);
						if(track == true) {
							z.moveX(Double.parseDouble(str[3]));
							z.moveY(Double.parseDouble(str[4]));
							//System.out.println("Your shape named \"" + z.getId() + "\" has been moved successfully!\n");
							return true;
						}else {
							return false;
						}
					}else {
						return false;
					}
				}

				else if(y.getClass() == Rectangle.class) {
					Rectangle z = (Rectangle)(y);
					Rectangle2D.Double rectangle = new Rectangle2D.Double(z.getX(),z.getY(),z.getSideAlpha(),z.getSideBravo());
					if (rectangle.contains(Double.parseDouble(str[1]), Double.parseDouble(str[2]))) {
						track = checkGrpShape(db, str, X, i-1);
						if(track == true) {
							z.moveX(Double.parseDouble(str[3]));
							z.moveY(Double.parseDouble(str[4]));
							return true;
						}else {
							return false;
						}
					}else {
						return false;
					}
				}

				else if(y.getClass() == Line.class) {
					Line z = (Line)(y);
					Line2D.Double line = new Line2D.Double(z.getX1(),z.getY1(),z.getX2(),z.getY2());
					if (line.contains(Double.parseDouble(str[1]), Double.parseDouble(str[2]))) {
						track = checkGrpShape(db, str, X, i-1);
						if(track == true) {
							z.moveX1(Double.parseDouble(str[3]));
							z.moveY1(Double.parseDouble(str[4]));
							z.moveX2(Double.parseDouble(str[3]));
							z.moveY2(Double.parseDouble(str[4]));
							return true;
						}else {
							return false;
						}

					}else {
						return false;
					}
				}
				else if(y.getClass() == Circle.class) {
					Circle z = (Circle)(y);
					Ellipse2D.Double circle = new Ellipse2D.Double(z.getX()-z.getRad(), z.getY()-z.getRad(), z.getRad()*2, z.getRad()*2);
					if (circle.contains(Double.parseDouble(str[1]), Double.parseDouble(str[2]))) {
						track = checkGrpShape(db, str, X, i-1);
						if(track == true) {
							z.moveX1(Double.parseDouble(str[3]));
							z.moveY1(Double.parseDouble(str[4]));
						}else {
							return false;
						}

					}else {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * @param a top-left x-coordinate
	 * @param b top-left y-coordinate
	 * @param c width of the box
	 * @param d height of the box
	 */
	public void printBoundBox(double a, double b, double c, double d) {
		System.out.println("Top left X-coordinate of bounding box: " + a);
		System.out.println("Top left Y-coordinate of bounding box: " + b);
		System.out.println("Width of bounding box: " + c);
		System.out.println("Height of bounding box: " + d);
		System.out.println("===========================================");
	}

	/**
	 * @param db It is the ArrayList that stores the shapes temporarily during swapping
	 * @param x1 Name of first shape
	 * @param x2 Name of second shape with which the first shape is to be swapped
	 */
	public void swap(ArrayList<String> db, String x1, String x2){

		// Getting the positions of the elements
		int index1 = db.indexOf(x1);
		int index2 = db.indexOf(x2);

		// Returning if the element is not present in the Linked List
		if (index1 == -1 || index2 == -1) {
			System.out.println("Shape not found!");
			System.out.println("===========================================");
			return;
		}

		// Swapping the elements
		db.set(index1, x2);
		db.set(index2, x1);
	}

	/**
	 * @param shape Takes the newly create name of the shape as the input
	 */
	//Alerts the user about the shape that they create
	public void creationDonePrompt(String shape){
		System.out.println("\nA new " + shape.toUpperCase() + " is created!");
		System.out.println("===========================================");
	}
}