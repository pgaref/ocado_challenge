package Utils;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public ArrayList<Integer> collisions;
	public ArrayList<Integer> completions;
	public ArrayList<Integer> overhead;
	public ArrayList<Integer> avgSteps;
	private long startTime;

	public Statistics() {
		startTime = System.currentTimeMillis();
		collisions = new ArrayList<Integer>();
		completions = new ArrayList<Integer>();
		overhead = new ArrayList<Integer>();
		avgSteps = new ArrayList<Integer>();
	}

	/**
	 * @return the collisions
	 */
	public ArrayList<Integer> getCollisions() {
		return collisions;
	}

	/**
	 * @param collisions
	 *            the collisions to set
	 */
	public void setCollisions(ArrayList<Integer> collisions) {
		this.collisions = collisions;
	}

	/**
	 * @return the completions
	 */
	public ArrayList<Integer> getCompletions() {
		return completions;
	}

	/**
	 * @param completions
	 *            the completions to set
	 */
	public void setCompletions(ArrayList<Integer> completions) {
		this.completions = completions;
	}

	/**
	 * @return the throughput
	 */
	public Integer getAverageThroughput() {
		return this.average(this.completions).intValue();
	}
	
	/**
	 * @return the avg. overhead
	 */
	public double getAverageOverhead() {
		return this.average(this.overhead);
	}
	
	public double getAverageSteps() {
		return this.average(this.avgSteps);
	}
	
	public Integer getCompletionSum(){
		return this.sum(this.completions).intValue();
	}
	
	public Integer getCollistionSum(){
		return this.sum(this.collisions).intValue();
	}

	private Double sum(List<Integer> values){
		double sum = 0.0;
		for (int i = 0; i < values.size(); i++) {
			sum += values.get(i);
		}
		return sum;
	}
	
	private Double average(List<Integer> values) {
		double average = 0.0;
		if (values.size() == 0)
			return 0.0;
		
		for (int i = 0; i < values.size(); i++) {
			average += values.get(i);
		}
		return average / values.size();
	}

}
