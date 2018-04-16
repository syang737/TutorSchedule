import java.util.ArrayList;
import java.util.Collections;

public class Schedule {
	/*
	 * Grid of students where the rows are different timeslots from 0:00 to 21:00
	 * (0:00 - 8:00 will be blank) Columns represent days of the week (M-F)
	 */

	private SinglyLinkedList<Student>[][] grid = new SinglyLinkedList[22][5];
//	private ArrayList<SinglyLinkedList<Student>> timeSlots = new ArrayList<>();

	public Schedule() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new SinglyLinkedList<>();
			}
		}
	}

	/**
	 * Takes a Student s and inserts it into the grid for every day and time they
	 * are available
	 * 
	 * @param s
	 * @throws CloneNotSupportedException 
	 */
	public void insert(Student s) throws CloneNotSupportedException {
		SinglyLinkedList<Day> d = s.days().clone();
		while (!d.isEmpty()) {
			Day oneDay = d.removeFirst();
			while (!oneDay.getTime().isEmpty()) {
				int oneTime = oneDay.getTime().removeFirst();
				grid[oneTime / 100][oneDay.getDayAsInt()].addLast(s);
			}
		}
	}

	/**
	 * Finds the best day out of the schedule
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Day bestDay() throws CloneNotSupportedException {
		Day d1 = new Day(); 
		int best = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].size() == best) {
					if (d1.getDayAsInt() == j) {
						if (d1.getTime().first() / 100 > i) {
							d1.clearTime();
							d1.addTime(i * 100);
						}
					}
					if (d1.getDayAsInt() > j) {
						d1.setDay(j);
						d1.clearTime();
						d1.addTime(i * 100);
					}
				}
				if (grid[i][j].size() > best) {
					best = grid[i][j].size();
					d1.setDay(j);
					d1.clearTime();
					d1.addTime(i * 100);

				}
			}
		}

		return d1;
	}
//	public void printTime() {
//		this.loadTimeSlots();
//		System.out.println(timeSlots);
//	}
//	public ArrayList<Day> topDays(int n) {
//		this.loadTimeSlots();
//		int l = timeSlots.size();
//		SinglyLinkedList<Student> temp;
//		for (int i = 0; i < l; i++) {
//			for (int j = 1; j < (l - i); j++) {
//				if (timeSlots.get(j-1).size() < timeSlots.get(j).size()) {
//					Collections.swap(timeSlots, j-1, j);
//				}
//			}
//		}
//
//		int counter = 0;
//		ArrayList<Day> td = new ArrayList<>();
//		while(counter < n) {
//			td.add(timeSlots.get(counter).first().days().first());
//			counter ++;
//		}
//		return td;
//
//	}
//
//	private void loadTimeSlots() {
//		timeSlots.clear();
//		for (int i = 0; i < grid.length; i++) {
//			for (int j = 0; j < grid[i].length; j++) {
//				if (grid[i][j].size() > 0)
//					timeSlots.add(grid[i][j]);
//			}
//		}
//	}
//
	public String toString() {
		System.out.println("\tM   T   W   H   F");
		for (int i = 0; i < grid.length; i++) {
			System.out.print((i) * 100 + ": ");
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " | ");
			}
			System.out.println();
		}
		return "";

	}
}
