package gis2.scenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.color.ColorSpace;
import java.awt.geom.Ellipse2D;

import javax.swing.JOptionPane;

import rescuecore2.log.Logger;
import rescuecore2.misc.gui.ScreenTransform;

import maps.gml.view.Overlay;

/**
 * divide map view into several regions
 * show probability of regions
 * @author Bing Shi
 *
 */
public class RegionOverlay implements Overlay {

	private ScenarioEditor editor;

	private int xLength;
	private int yLength;
	private double dis[][];

	public RegionOverlay(ScenarioEditor e, int x, int y, double dis[][]) {
		editor = e;
		xLength = x;
		yLength = y;
		this.dis = dis;
	}

	@Override
	public void render(Graphics2D g, ScreenTransform transform) {
		double maxX = editor.getMap().getMaxX();
		double maxY = editor.getMap().getMaxY();
		double minX = editor.getMap().getMinX();
		double minY = editor.getMap().getMinY();
		g.setColor(Color.RED);
		double xSize = (maxX - minX) / xLength;
		double ySize = (maxY - minY) / yLength;

		double size;
		if (xSize < ySize) {
			size = (transform.xToScreen(maxX) - transform.xToScreen(minX))
					/ xLength;
		} else {
			size = (transform.yToScreen(maxY) - transform.yToScreen(minY))
					/ yLength;
		}

		for (int i = 0; i < xLength + 1; i++) {
			g.drawLine(transform.xToScreen(minX + i * xSize),
					transform.yToScreen(minY),
					transform.xToScreen(minX + i * xSize),
					transform.yToScreen(maxY));
		}
		for (int i = 0; i < yLength + 1; i++) {
			g.drawLine(transform.xToScreen(minX),
					transform.yToScreen(minY + i * ySize),
					transform.xToScreen(maxX),
					transform.yToScreen(minY + i * ySize));
		}
		for (int i = 0; i < xLength; i++) {
			for (int j = 0; j < yLength; j++) {
				int x = transform.xToScreen(minX + (i + 0.5) * xSize);
				int y = transform.yToScreen(minY + (j + 0.5) * ySize);
				Shape show = new Ellipse2D.Double(x - size / 2 * dis[i][j], y
						- size / 2 * dis[i][j], size * dis[i][j], size
						* dis[i][j]);
				Color transparentGreen = new Color(0, 255, 0, 120);
				g.setColor(transparentGreen);
				g.fill(show);
			}
		}
	}

	public double[][] getDis() {
		return dis;
	}

	public void setDis(double[][] dis) {
		this.dis = dis;
	}
}