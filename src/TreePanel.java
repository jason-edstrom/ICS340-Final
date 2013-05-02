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
import java.util.*;

import javax.swing.*;

/**
 * This Class creates a JPanel with a supplied tree drawn as nodes and edges.<br>
 * @author Robert Dinga
 */
public class TreePanel extends JPanel{
	
	private final int DIAMETER = 40;
	private BinaryNode<Character> root;
	private ArrayList<TreePaintNode> paintNodes;
	private ArrayList<TreePaintLine> paintLines;

	/**
	 * Constructor
	 * @param a_root root of the tree to draw
	 */
	public TreePanel(BinaryNode<Character> a_root){
		root = a_root;
		paintNodes = new ArrayList<TreePaintNode>();
		paintLines = new ArrayList<TreePaintLine>();
		if(root != null){
			makePaintNodes(root);
		}
		setPreferredSize(new Dimension(DIAMETER*BinaryNode.size(root)+10, (DIAMETER*(BinaryNode.height(root)+1))+10));
	}
	
	/**
	 * Build the lists of paint nodes and lines from the supplied tree.
	 * @param a_root root of the tree to draw
	 */
	public void makePaintNodes(BinaryNode<Character> a_root){
		if(a_root.getLeft() != null){
			paintLines.add(new TreePaintLine((a_root.getX()*DIAMETER)+(DIAMETER/2), (a_root.getY()*DIAMETER)+(DIAMETER/2), 
											 (a_root.getLeft().getX()*DIAMETER)+(DIAMETER/2), (a_root.getLeft().getY()*DIAMETER)+(DIAMETER/2)));
			makePaintNodes(a_root.getLeft());
		}

        //Added logic for found and duplicates in the paintNode
		paintNodes.add(new TreePaintNode((a_root.getX()*DIAMETER), (a_root.getY()*DIAMETER), DIAMETER, a_root.getElement(), a_root.hasDuplicate, a_root.duplicateCount, a_root.wasFound));
		if(a_root.getRight() != null){
			paintLines.add(new TreePaintLine((a_root.getX()*DIAMETER)+(DIAMETER/2), (a_root.getY()*DIAMETER)+(DIAMETER/2), 
											 (a_root.getRight().getX()*DIAMETER)+(DIAMETER/2), (a_root.getRight().getY()*DIAMETER)+(DIAMETER/2)));
			makePaintNodes(a_root.getRight());
		}
	}
	
	/**
	 * Draw the tree!
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(TreePaintLine tpl:paintLines){
			tpl.display(g);
		}
		for(TreePaintNode tpn:paintNodes){
			tpn.display(g);
		}
	}
	
}
