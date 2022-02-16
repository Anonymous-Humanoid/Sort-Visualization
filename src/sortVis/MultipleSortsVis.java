/**
 * Date: April 23, 2021
 * 
 * Description: A combination of the following sorting visualizers:
 *  	* Bubble
 *  	* Cocktail shaker
 *  	* Insertion
 *  	* Merge
 *  	* Max heap
 *  	* Quicksort
 *  
 * @author Matthew
 * 
 */

package sortVis;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

// Driver class
public class MultipleSortsVis
{
	final static int SIZE = 200;
	
	static int[] data = SortPanel.genData(SIZE);
	
	// Driver code
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
			JFrame f = new JFrame("Bubble, Cocktail Shaker, "
					+ "Insertion, Merge, Max Heap and "
					+ "Quicksort Sorting Visualizers "
					+ "(" + SIZE + " elements)");
			JMenuBar mb = new JMenuBar();
			JButton start = new JButton("Start");
			Thread[] t = new Thread[SIZE];
			SortPanel[] p = new SortPanel[]
			{
				new BubbleSortVis		 (Arrays.copyOf(data, SIZE)),
				new CocktailShakerSortVis(Arrays.copyOf(data, SIZE)),
				new InsertionSortVis	 (Arrays.copyOf(data, SIZE)),
				new MergeSortVis		 (Arrays.copyOf(data, SIZE)),
				new HeapSortVis 		 (Arrays.copyOf(data, SIZE)),
				new QuickSortVis 	 	 (Arrays.copyOf(data, SIZE))
			};
			
			// Temporarily initializing sorting threads
			for (int i = 0; i < t.length; i++)
				t[i] = new Thread();
			
			// Initializing start/stop button click listener
			start.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent me)
				{
					CountDownLatch latch = new CountDownLatch(1);
					
					// Initializing sorting components
					for (int index = 0; index < p.length; index++)
					{
						final int i = index; // Needed for thread lambda
						
						// Painting visualizer in frame
						p[i].paintComponent(p[i].getGraphics());
						
//						// Checking if thread is running to avoid interruption
//						// TODO Fix bug with static latch and while-true loop? If a thread is running, other threaded actions like mouseClicked will be delayed until they can be prioritized, skipping this check
						if (!t[i].isAlive())
						{
							// Initializing sorting thread
							t[i] = new Thread(() ->
							{
								try
								{
									latch.await();
								}
								catch (InterruptedException e)
								{
									
								}
								finally
								{
									p[i].sort();
								}
							});
							
							// Attempting to run sort: waiting for latch to open
							t[i].start();
						}
					}
					
					// Running threaded sorts
					latch.countDown();
				}
			});
			
			// Setting up frame
			mb.add(start);
			f.setLayout(new GridLayout(2, 3));
			f.setJMenuBar(mb);
			f.pack();
			f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Adding sorting visualizer panels to frame
			for (SortPanel s : p) f.add(s);
			
			// Displaying frame
			f.validate();
			f.setVisible(true);
		});
	}
}
