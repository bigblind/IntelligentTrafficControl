package com.project.visuals.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import com.project.base.Controller;
import com.project.board.TrafficMenu;
import com.project.board.TrafficPanel;
import com.project.cellular.CellTypes;
import com.project.cellular.Grid;
import com.project.cellular.Map;
import com.project.cellular.RandomCarAdder;
/**
 * GUI of simulation
 * 2 parts:
 * -left: simulation 
 * -right: panel 
 *
 */
public class SimulationFrame extends JSplitPane implements Activatable {

	private static final long serialVersionUID = 4036879803456305768L;
	
	private TrafficPanel trafficPanel;
	private TrafficMenu trafficMenu;
	private Controller controller;
	// reference to the controller and calles initMap which creates new 
	// grid for the simulation and than use it to create new map 
	
	public SimulationFrame(Controller controller){
		this.controller = controller;
		this.initMap();
		this.trafficPanel = new TrafficPanel(controller);
		this.trafficMenu = new TrafficMenu(controller);

		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.setBorder(null);
		this.setResizeWeight(1);
		this.setDividerSize(0);
		this.add(trafficPanel, JSplitPane.LEFT);
		this.add(trafficMenu, JSplitPane.RIGHT);
	}
	//GUI for cellular automata
	public void initMap(){
		if(this.controller.getCurrentMap() == null){
			Grid grid = Grid.generate();
			System.out.println(grid.toDirString());
			
			Map map = new Map(grid);
			map.initCars(new RandomCarAdder(4));
			
			controller.setCurrentMap(map);
		}
	}
	
	public JPanel getTrafficPanel(){
		return this.trafficPanel;
	}
	
	
	
	public JPanel getP2(){
		return this.trafficMenu;
	}
	
	
	// two panels 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Point2D center = new Point2D.Float(this.getWidth() / 2, this.getHeight() / 2);
		float radius = this.getWidth() / 2;
		float[] dist = { 
				0.6f, 0.8f 
		};
		Color[] colors = { 
				new Color(180, 180, 180), 
				new Color(160, 160, 160) 
		};
		RadialGradientPaint gp = new RadialGradientPaint(center, radius, dist, colors);
		g2.setPaint(gp);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	@Override
	public void activate() {
		trafficPanel.activate();
	}
	@Override
	public void deactivate() {
		trafficPanel.deactivate();
	}
	
}
