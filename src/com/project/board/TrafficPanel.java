package com.project.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import com.project.base.Controller;
import com.project.graph.Edge;
import com.project.graph.Node;
import com.project.graph.Road;
import com.project.graph.SingleRoad;
import com.project.cellular.Grid;
import com.project.cellular.GridCell;
import com.project.cellular.Map;


public class TrafficPanel extends JPanel{
	
	private static final long serialVersionUID = -8086343848521884074L;
	private Controller controller;
	private ArrayList<Node> doubleEdges;
	private int frameCounter = 0;
	
	/**
	 * Max road location 	x-axis: 1200
	 * 						y-axis: 750
	 * @param controller
	 */
	
	public TrafficPanel(Controller controller){
		this.controller = controller;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawMap(controller.getCurrentMap(), g2);
		frameCounter += 1;
		if(frameCounter % 10 == 0){
			frameCounter = 0;
			controller.getCurrentMap().tick();
			this.repaint();
			System.out.println("Tick.");
		}
	}
	
	private void drawMap(Map map, Graphics2D g2){
		int cellSize = 32;
		int xPadding = 10;
		int yPadding = 10;
		Grid grid = map.getGrid();
		for(int x=0; x<grid.getWidth(); x++){
			for(int y=0; y<grid.getHeight(); y++){
				GridCell cell = grid.getCellAt(x, y);
				if(cell == null) continue;
				
				Color c = (cell.getState()) ? new Color(255, 255, 255) : new Color(50, 50, 50);
				g2.setColor(c);
				
				g2.fillRect(xPadding + x*cellSize, yPadding + y*cellSize, cellSize, cellSize);
				
			}
		}
	}
}
