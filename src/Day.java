public class Day implements Cloneable{
	public char day;
	public SinglyLinkedList<Integer> times = new SinglyLinkedList<>();
	public Day() {
		
	}
	public Day(char d) {
		this.day = d;
	}
	public void setDay(int d) {
		switch(d) {
		case 0:
			this.day = 'M';
			break;
		case 1:
			this.day = 'T';
			break;
		case 2:
			this.day = 'W';
			break;
		case 3:
			this.day = 'H';
			break;
		case 4:
			this.day = 'F';
			break;
		default:
			this.day = 'Z';
		}
	}
	public void setDay(char c) {
		this.day = c;
	}
	public void addTime(int t) {
		times.addLast(t);
	}
	public void clearTime() {
		while(!times.isEmpty()) {
			times.removeFirst();
		}
	}
	public void setTime(SinglyLinkedList<Integer> t) {
		this.times = t;
	}
	public SinglyLinkedList<Integer> getTime(){
		return this.times;
	}
	public int getDayAsInt() {
		switch(this.day) {
		case 'M':
			return 0;
		case 'T':
			return 1;
		case 'W':
			return 2;
		case 'H':
			return 3;
		case 'F':
			return 4;
		default:
			return -1;
		}
	}
	public String getDayVerbose() {
		switch(this.day) {
		case 'M':
			return "Monday";
		case 'T':
			return "Tuesday";
		case 'W':
			return "Wednesday";
		case 'H':
			return "Thursday";
		case 'F':
			return "Friday";
		default:
			return "N/A";
		}
	}
	public String toString() {
		return day + times.toString();
	}
	
	public Day clone() throws CloneNotSupportedException{
		Day cloneDay = (Day)super.clone();
		cloneDay.day = this.day;
		cloneDay.times = this.times.clone();
		return cloneDay;
	}
	
}
