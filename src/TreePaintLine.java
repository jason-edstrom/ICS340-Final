/* ================================================
Course:      ICS 340 Data Structures
Semester:    Spring 2013
Instructor:  Siva Jasthi
Student:     Robert Dinga
Assignment:  Programming Assignment 5
Compiler:    JDK 1.7 with Eclipse SDK
Due Date:    4/15/2013
================================================ */
import java.awt.*;

/**
 * This Class creates a line between two points for use in drawing a tree.
 * @author Robert Dinga
 */
public class TreePaintLine {
	
	private int startX,
				startY,
				endX,
				endY;
	
	/**
	 * 
	 * @param x1 x-coord of first point
	 * @param y1 y-coord of first point
	 * @param x2 x-coord of second point
	 * @param y2 y-coord of second point
	 */
	public TreePaintLine(int x1, int y1, int x2, int y2){
		startX = x1;
		startY = y1;
		endX = x2;
		endY = y2;
	}
	
	/**
	 * Draw the line!
	 * @param g Graphics from paintComponent
	 */
	public void display(Graphics g){
		g.setColor(Color.black);
		g.drawLine(startX, startY, endX, endY);
	}
	

}
