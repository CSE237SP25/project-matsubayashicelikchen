package bankapp;

import java.util.ArrayList;

public class FinancialStatement {
	private ArrayList<String> activity;
	public ArrayList<Double> amount;
 	public FinancialStatement(){
		this.activity = new ArrayList<String>();
		this.amount = new ArrayList<Double>();
		this.activity.add("initial:");
		this.amount.add(0.0);
	}
	public void add(double num) {
		this.activity.add("+");
		this.amount.add(num);
	}
	public void minus(double num) {
		this.activity.add("-");
		this.amount.add(num);
	}
	private ArrayList getActivity() {
		return this.activity;
	}
	private ArrayList getAmount() {
		return this.amount;
	}
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < activity.size(); i++) {
	        sb.append(activity.get(i))
	          .append("\t")
	          .append(amount.get(i))
	          .append("\n");
	    }
	    return sb.toString();
	}
}
