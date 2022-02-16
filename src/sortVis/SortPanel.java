/**
 * Date: April 21, 2021
 * Description: The sorting visualizer superclass
 * @author Matthew
 * 
 */

package sortVis;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class SortPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 78316509656782639L;
	
	protected int[] select = new int[] {-1};
	protected int[] data;
	
	// TODO Implement time counting system
	public long realTime;
	public long estTime;
	
	// Method constructor
	public SortPanel(int[] d)
	{
		data = d;
	}
	
	// Method constructor
	public SortPanel(int n)
	{
		data = genData(n);
	}
	
	// Generate pseudo-random data set
	public static int[] genData(int n)
	{
		int[] data = new int[n];
		
		for (int i = 0; i < n; i++)
		{
			data[i] = (int) ((n - 1) * Math.random());
		}
		
		return data;
	}
	
	// Sorting data method stub
	public abstract void sort();
	
	// Selecting data
	protected void select(int... n)
	{
		select = n;
		
		paintComponent(getGraphics());
	}
	
	// Painting data
	@Override
	public synchronized void paintComponent(Graphics g)
	{
		// Painting data columns
		for (int i = 0; i < data.length; i++)
		{
			// Erasing data column
			g.setColor(Color.WHITE);
			g.drawLine(i, 0, i, data.length - 1);
			
			// Painting new data column
			g.setColor(Color.BLACK);
			drawCol(g, i);
		}
		
		g.setColor(Color.RED);
		
		// Painting selected data columns, if any
		for (int s : select)
			if (s >= 0 && s < data.length)
				drawCol(g, s);
	}
	
	// Painting data column
	private synchronized void drawCol(Graphics g, int s)
	{
		g.drawLine(s, data.length - 1 - data[s], s, data.length - 1);
	}
}
