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
	private long startTime;

	public Statistics() {
		startTime = System.currentTimeMillis();
		collisions = new ArrayList<Integer>();
		completions = new ArrayList<Integer>();
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
	public Double getAverageThroughput() {
		return this.average(this.completions);
	}
	
	public Double getCompletionSum(){
		return this.sum(this.completions);
	}
	
	public Double getCollistionSum(){
		return this.sum(this.collisions);
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
		for (int i = 0; i < values.size(); i++) {
			average += values.get(i);
		}
		return average / values.size();
	}

}
