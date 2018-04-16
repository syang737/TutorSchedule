import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class TutorSchedule {
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		Scanner sc = new Scanner(System.in);
		SinglyLinkedList<Student> students = new SinglyLinkedList<>();
		System.out.println("What's the name of the file you want to read?");
		String fileName = sc.next();

		FileReader in = new FileReader(fileName);
		BufferedReader br = new BufferedReader(in);
		
		String line = br.readLine();
		students = getStudents(parseRecords(line));
		
		br.close();
		sc.close();
		
		Schedule schedule = new Schedule();
		while(!students.isEmpty()) {
			schedule.insert(students.removeFirst());
		}

		Day d = schedule.bestDay();
		
		System.out.println("The best time slot to cover the most students is " + d.getDayVerbose() + " at " + (d.getTime().last() )/100 + ":00");
		
		System.out.println(schedule);
	}

	/**
	 * Checks if an expression has correctly matched delimiters
	 * 
	 * @param expression
	 * @return
	 */
	public static boolean isMatched(String expression) {
		final String opening = "(<["; // opening delimiters
		final String closing = ")>]"; // respective closing delimiters
		Stack<Character> buffer = new LinkedStack<>();
		for (char c : expression.toCharArray()) {
			if (opening.indexOf(c) != -1) // this is a left delimiter
				buffer.push(c);
			else if (closing.indexOf(c) != -1) { // this is a right delimiter
				if (buffer.isEmpty()) // nothing to match with
					return false;
				if (closing.indexOf(c) != opening.indexOf(buffer.pop()))
					return false; // mismatched delimiter
			}
		}
		return buffer.isEmpty(); // were all opening delimiters matched?
	}

	/**
	 * Takes in a set of records and separates it into a Linked List of records
	 * 
	 * @param expression
	 * @return
	 */
	public static SinglyLinkedList<String> parseRecords(String expression) {
		SinglyLinkedList<String> records = new SinglyLinkedList<>();

		int j = expression.indexOf('('); // find first ’(’ character (if any)

		while (j != -1) {
			int k = expression.indexOf(')', j + 1); // find next ’)’ character
			if (k == -1)
				return records; // invalid tag
			String tag = expression.substring(j + 1, k); // strip away ( )
			records.addLast(tag);
			j = expression.indexOf('(', k + 1); // find next ’(’ character (if any)
		}

		return records;
	}

	/**
	 * Takes a linked list of records and returns a linked list of Students while
	 * checking if formatting is correct
	 * 
	 * @param records
	 * @return
	 */
	public static SinglyLinkedList<Student> getStudents(SinglyLinkedList<String> records) {
		SinglyLinkedList<Student> students = new SinglyLinkedList<>();
		boolean errorChecked = false;
		while (!records.isEmpty()) {
			String r = records.removeFirst();
			if (!isMatched(r)) {
				if (!errorChecked) {
					System.out.println(
							"Expression is ill formatted but all correctly formmatted entries will be recorded");
					errorChecked = true;
				}
				continue;
			}
			int j = r.indexOf('<');
			String name = r.substring(0, j);
			Student s = new Student();
			s.setName(name);
			s.setDays(getDays(r));
			students.addLast(s);
		}

		return students;
	}

	public static SinglyLinkedList<Day> getDays(String record) {
		SinglyLinkedList<Day> days = new SinglyLinkedList<>();

		int j = record.indexOf('<');

		while (j != -1) {
			int k = record.indexOf('>', j + 1);
			if (k == -1)
				return days; // invalid tag
			String tag = record.substring(j + 1, k);
			Day d = new Day(tag.charAt(0));
			d.setTime(getTimes(tag));
			days.addLast(d);
			j = record.indexOf('<', k + 1);
		}

		return days;
	}

	public static SinglyLinkedList<Integer> getTimes(String dayString) {
		SinglyLinkedList<Integer> times = new SinglyLinkedList<>();

		int j = dayString.indexOf('['); // find first ’(’ character (if any)

		while (j != -1) {
			int k = dayString.indexOf(']', j + 1); // find next ’)’ character
			if (k == -1)
				return times; // invalid tag
			String tag = dayString.substring(j + 1, k); // strip away ( )
			times.addLast(Integer.parseInt(tag));
			j = dayString.indexOf('[', k + 1); // find next ’(’ character (if any)
		}

		return times;
	}
}
