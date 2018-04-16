
public class Student {
	private String name;
	private SinglyLinkedList<Day> days = new SinglyLinkedList<>(); 
	public Student() {
		
	}
	public Student(String n, SinglyLinkedList<Day> d) {
		this.name = n;
		this.days = d;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SinglyLinkedList<Day> days() {
		return days;
	}
	public void addDay(Day d) {
		this.days.addLast(d);
	}
	public void setDays(SinglyLinkedList<Day> d) {
		this.days = d;
	}
	public String toString() {
		return this.name;
	}
}
