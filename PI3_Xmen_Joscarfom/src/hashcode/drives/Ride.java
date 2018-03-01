package hashcode.drives;

public class Ride {

	private int startRow;
	private int startColumn;
	private int endRow;
	private int endColumn;
	private int earliestStart;
	private int latestFinish;
	private int steps;
	
	public Ride(String ride) {
		  String[] values = ride.trim().split(" ");
		  this.startRow = Integer.valueOf(values[0]);
		  this.startColumn = Integer.valueOf(values[1]);
		  this.endRow = Integer.valueOf(values[2]);
		  this.endColumn = Integer.valueOf(values[3]);
		  this.earliestStart = Integer.valueOf(values[4]);
		  this.latestFinish = Integer.valueOf(values[5]);
		  this.setSteps(Math.abs((this.getEndRow() - this.getStartRow())) + Math.abs((this.getEndColumn() - this.getStartColumn())));
		 }
	
	public int getEarliestStart() {
		return earliestStart;
	}

	public void setEarliestStart(int earliestStart) {
		this.earliestStart = earliestStart;
	}

	public int getLatestFinish() {
		return latestFinish;
	}

	public void setLatestFinish(int latestFinish) {
		this.latestFinish = latestFinish;
	}

	public Ride() {
		
	}
	
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getStartColumn() {
		return startColumn;
	}
	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getEndColumn() {
		return endColumn;
	}
	public void setEndColumn(int endColumn) {
		this.endColumn = endColumn;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	
}
