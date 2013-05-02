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
 * This Class creates a paint node for use in drawing trees
 * @author Robert Dinga
 */
public class TreePaintNode {

	private int xCoord;
	private int yCoord;
	private int diameter;
	private char element;
    private boolean hasDuplicate;
    private int duplicateCount;
	private boolean wasFound;
	/**
	 * 
	 * @param initX x-coord of the left side of the circle
	 * @param initY y-coord of the top of the circle
	 * @param dia diameter of the circle
	 * @param ele character element of the node
	 */
	public TreePaintNode(int initX, int initY, int dia, char ele, boolean dup, int dupCount, boolean found){
		xCoord = initX;
		yCoord = initY;
		diameter = dia;
		element = ele;
        hasDuplicate = dup;
        duplicateCount = dupCount;
        wasFound = found;
	}
	
	public int getxCoord() {
		return xCoord;
	}
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getyCoord() {
		return yCoord;
	}
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public int getDiameter() {
		return diameter;
	}
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
	/**
	 * Draw the node!
	 * @param g Graphics from paintComponent
	 */
	public void display(Graphics g){
        if(wasFound){
        g.setColor(Color.yellow);   //Paints yellow if node is found
        } else{
		g.setColor(Color.white);
        }
		g.fillOval(xCoord, yCoord, diameter-1, diameter-1);
		g.setColor(Color.black);
		g.drawOval(xCoord,yCoord,diameter,diameter);
		char[] ele = {element};
		int offset = g.getFont().getSize()/4;
        if (hasDuplicate){        // Handles noting duplicates in the drawing
            String dupString = element + " : " + duplicateCount;
            g.drawString(dupString, xCoord+diameter/2-offset-7, yCoord+diameter/2+offset);
        }else{
		g.drawChars(ele, 0, 1, xCoord+diameter/2-offset, yCoord+diameter/2+offset);
        }
	}
}
