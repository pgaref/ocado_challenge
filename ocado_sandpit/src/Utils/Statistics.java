package Utils;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.LinePlotPanel;

public class Statistics extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int collisions = 0;
	private int completions = 0;
	private long startTime;
	private JLabel tf = null;
	
	public Statistics(){
		tf = new JLabel("Collisions: "+ this.collisions + "\n Completions: "+ this.completions);
		this.add(tf);
		startTime = System.currentTimeMillis();
	}

	
	public void addCollision(){
		this.collisions+=1;
		LinePlotPanel.collisions.add(100);
		repaint();
	}
	
	public void addCompletion(){
		this.completions+=1;
		LinePlotPanel.completions.add(100);
		LinePlotPanel.throughput.add((double)((double)this.completions/(double)((System.currentTimeMillis()-this.startTime)/1000)));
		repaint();
	}

	@Override
	public void repaint(){
		if(tf == null)
			tf = new JLabel("Collisions: "+ this.collisions + "\n Completions: "+ this.completions);
		else
			this.tf.setText("Collisions: "+ this.collisions + "\n Completions: "+ this.completions);
	}
	
	/**
	 * @return the collisions
	 */
	public int getCollisions() {
		return collisions;
	}


	/**
	 * @param collisions the collisions to set
	 */
	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}


	/**
	 * @return the completions
	 */
	public int getCompletions() {
		return completions;
	}


	/**
	 * @param completions the completions to set
	 */
	public void setCompletions(int completions) {
		this.completions = completions;
	}
	
	
	
}
