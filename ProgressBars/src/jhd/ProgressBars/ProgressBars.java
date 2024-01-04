package jhd.ProgressBars;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JProgressBar;


/**A class for creating and displaying multiple process bars in a Window
 * @author LazzyIzzi*/
public class ProgressBars
{

	private String windowTitle = "Default Window Title";
	private Color windowColor = new Color(240,230,190);//slightly darker than buff
	private JFrame frame;
	
	private ArrayList<JProgressBar> prgBars = new ArrayList<JProgressBar>();
	private ArrayList<String> prgBarNames = new ArrayList<String>();
	
	/**Adds a progress bar to the list, call show() to display
	 * @param progBarTitle The progressBar title
	 * @param progressbarWidth The progressBar width
	 * @param progressbarHeight The progressBar height
	 * @param minBarValue The value at the progressBar's left edge
	 * @param maxBarValue The value at the progressBar's right edge
	 */
	public void add(String progBarTitle,int progressbarWidth,int progressbarHeight,int minBarValue,int maxBarValue)
	{
		JProgressBar p = new JProgressBar(minBarValue,maxBarValue);		
		p.setPreferredSize(new Dimension(progressbarWidth,progressbarHeight));
		p.setValue(minBarValue);
		p.setStringPainted(true);
		p.setName(progBarTitle);		
		prgBars.add(p);
		prgBarNames.add(progBarTitle);
	}
	
	
	/**
	 * @return The ArrayList of progressBars created by the add() method
	 */
	public ArrayList<JProgressBar> getProgBars()
	{
		return prgBars;
	}
	
	/**
	 * @return The progressBars created by the add() method as an array
	 */
	public JProgressBar[] getProgBarsAsArray()
	{
		return prgBars.toArray(new JProgressBar[prgBars.size()]);
	}
	
	/**Creates a ProgressBars Object to contain multiple progress bars
	 * @param windowTitle The ProgressBars window title
	 */
	public ProgressBars(String windowTitle)
	{
		this.windowTitle = windowTitle;
	}
	
	/**Sets the progress bar window color
	 * @param color the progress bar new window color
	 */
	public void setBackground(Color color)
	{
		this.windowColor = color;
	}
	
	/**Shows the progress bars in a Window*/
	public void show()
	{		
		//https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html			
		frame = new JFrame(windowTitle);
		Container pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		pane.setBackground(windowColor);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill =  GridBagConstraints.HORIZONTAL;
		int j=0;
		for(JProgressBar prgBar : prgBars)
		{
			gbc.gridx=0;
			gbc.gridy=j;
			gbc.gridwidth =1;
			pane.add(new Label(prgBar.getName()),gbc);
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridy=j+1;
			pane.add(prgBar,gbc);
			j+=2;			
		}
		//display at screen center
		frame.setLocationRelativeTo(null);
		//disables the window close box
		//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.pack();
		frame.setSize(frame.getWidth()+40,frame.getHeight()+20);
		frame.setVisible(true);	
	}
	
	/**
	 * @return The size of the Progress Bar
	 */
	public int size()
	{
		return prgBars.size();
	}
	
	/**Closes the process monitor window*/
	public void close()
	{
		frame.dispose();
	}
	/**Sets and updates the progress indicator position<br>
	 * Does nothing if the progress bars window is closed.
	 * @param name the name(title) of the progress bar to set to value
	 * @param value the progress bar value between start and end 
	 */
	public void setValue(String name,int value)
	{
		if(prgBars.isEmpty()==false)
		{
			int index = prgBarNames.indexOf(name);
			prgBars.get(index).setValue(value);
		}
	}
	/**Sets the progress indicator minimum(left) limit<br>
	 * Use when the number of iterations is determined at run time.
	 * @param name the name(title) of the progress bar to set to value
	 * @param value the progress bar maximum(right) limit
	 */
	public void setMinBarValue(String name,int value)
	{
		if(prgBars.isEmpty()==false)
		{
			int index = prgBarNames.indexOf(name);
			prgBars.get(index).setMinimum(value);
		}
	}
	/**Sets the progress indicator maximum(right) limit<br>
	 * Use when the number of iterations is determined at run time.
	 * @param name the name(title) of the progress bar to set to value
	 * @param value the progress bar maximum(right) limit
	 */
	public void setMaxBarValue(String name,int value)
	{
		if(prgBars.isEmpty()==false)
		{
			int index = prgBarNames.indexOf(name);
			prgBars.get(index).setMaximum(value);
		}
	}
	/**Gets the progress indicator position<br>
	 * @param name the name(title) of the progress bar to set to value
	 * @return the progress indicator position, or -1 if the named progress bar is not found.
	 */
	public int getValue(String name)
	{
		int value=-1;
		if(prgBars.isEmpty()==false)
		{
			int index = prgBarNames.indexOf(name);
			value = prgBars.get(index).getValue();
		}
		return value;
	}
	/**Gets the progress left(minimum) position<br>
	 * @param name the name(title) of the progress bar to set to value
	 * @return the progress left(minimum) position or -1 if the named progress bar is not found.
	 */
	public int getMinimum(String name)
	{
		int value=-1;
		if(prgBars.isEmpty()==false)
		{
			int index = prgBarNames.indexOf(name);
			value = prgBars.get(index).getMinimum();
		}
		return value;
	}
	/**Gets the progress right(maximum) position<br>
	 * returns -1 if the named progress bar is not found.
	 * @param name the name(title) of the progress bar to set to value
	 * @return the progress right(maximum) position or -1 if the named progress bar is not found.
	 */
	public int getMaximum(String name)
	{
		int value=-1;
		if(prgBars.isEmpty()==false)
		{
			int index = prgBarNames.indexOf(name);
			value = prgBars.get(index).getMaximum();
		}
		return value;
	}
	
	
//	public void setBarName(String oldName, String newName)
//	{
//		if(prgBars.isEmpty()==false)
//		{
//			int index = prgBarNames.indexOf(oldName);
//			prgBarNames.set(index,newName);
//			prgBars.get(index).setName(newName);
//		}
//	}
}
