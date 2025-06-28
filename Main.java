import java.util.*;



//Node class for Binary Search Tree for students

class StudentNode {

	Student student;

	StudentNode left, right;



	public StudentNode(Student student) {

		this.student = student;

		this.left = this.right = null;

	}

}



//Binary Search Tree class for organizing students by percentile

class StudentBST {

	StudentNode root;



	public void insert(Student student) {

		root = insertRec(root, student);

	}



	private StudentNode insertRec(StudentNode root, Student student) {

		if (root == null) {

			root = new StudentNode(student);

			return root;

		}

		if (student.percentile >= root.student.percentile)

			root.left = insertRec(root.left, student);

		else if (student.percentile <= root.student.percentile)

			root.right = insertRec(root.right, student);

		return root;

	}



	// Descending order traversal for Merit List

	public void inOrderTraversal() {

		inOrderRec(root);

	}



	private void inOrderRec(StudentNode root) {

		if (root != null) {

			inOrderRec(root.left); // Displaying in descending order

			System.out.println("Application Number: " + root.student.applicationNumber + ", Name: " + root.student.name

					+ ", Percentile: " + root.student.percentile);

			inOrderRec(root.right);

		}

	}

}



class SeatType {

	String seatType;

	double cutoff;

	int availableSeats;



	SeatType(String seatType, double cutoff, int seats) {

		this.seatType = seatType;

		this.cutoff = cutoff;

		this.availableSeats = seats;

	}



	public int getAvailableSeats() {

		return availableSeats;

	}



	public void allocateSeat() {

		if (availableSeats > 0) {

			availableSeats--;

		}

	}

}



class Course {

	String courseName;

	LinkedList<SeatType> seatTypes;



	Course(String name) {

		this.courseName = name;

		this.seatTypes = new LinkedList<>();

	}



	public void addSeatType(String seatType, double cutoff, int seats) {

		seatTypes.add(new SeatType(seatType, cutoff, seats));

	}



	public LinkedList<SeatType> getSeatTypesDescending() {

		return seatTypes;

	}

}



class College {

	int collegeCode;

	String collegeName;

	HashMap<String, Course> courses;



	College(int code, String name) {

		this.collegeCode = code;

		this.collegeName = name;

		this.courses = new HashMap<>();

	}



	public Course findCourse(String courseName) {

		return courses.get(courseName);

	}



	public void addCourse(Course course) {

		courses.put(course.courseName, course);

	}

}



class Preference {

	int collegeCode;

	String courseName;



	Preference(int collegeCode, String courseName) {

		this.collegeCode = collegeCode;

		this.courseName = courseName;

	}

}



class Student {

	int applicationNumber;

	double percentile;

	String name;

	String gender;

	String category;

	LinkedList<Preference> preferences;

	boolean isAllocated = false; // Track allocation status

	String allocatedCollege = null;

	String allocatedCourse = null;

	String allocatedSeatType = null;

	Stack<String> allocationHistory; // Track allocation history



	Student(int appNumber, double percentile, String name, String gender, String category) {

		this.applicationNumber = appNumber;

		this.percentile = percentile;

		this.name = name;

		this.gender = gender;

		this.category = category;

		this.preferences = new LinkedList<>();

		this.allocationHistory = new Stack<>();

	}



	public void addPreference(Preference preference) {

		preferences.add(preference);

	}



	public void setAllocation(String college, String course, String seatType) {

		this.allocationHistory.push("Allocated to " + college + " for " + course + " with seat type " + seatType);

		this.allocatedCollege = college;

		this.allocatedCourse = course;

		this.allocatedSeatType = seatType;

		this.isAllocated = true;

	}

}



public class Main {

	static HashMap<Integer, College> colleges = new HashMap<>();

	static StudentBST studentBST = new StudentBST();

	static Queue<Student> studentQueue = new LinkedList<>();

	static Scanner scanner = new Scanner(System.in);



	public static void insertCollegeDetails() {

		System.out.print("Enter college code: ");

		int code = scanner.nextInt();

		scanner.nextLine();

		System.out.print("Enter college name: ");

		String name = scanner.nextLine();

		College college = new College(code, name);



		System.out.print("Enter number of courses: ");

		int numCourses = scanner.nextInt();

		scanner.nextLine();



		for (int i = 0; i < numCourses; i++) {

			System.out.print("Enter course name: ");

			String courseName = scanner.nextLine();

			Course course = new Course(courseName);



			System.out.print("Enter number of seat types for this course: ");

			int numSeatTypes = scanner.nextInt();

			scanner.nextLine();



			for (int j = 0; j < numSeatTypes; j++) {

				System.out.print("Enter seat type (e.g., gopen, lopen): ");

				String seatType = scanner.nextLine();

				System.out.print("Enter cutoff for this seat type: ");

				double cutoff = scanner.nextDouble();

				System.out.print("Enter number of seats for this seat type: ");

				int seats = scanner.nextInt();

				scanner.nextLine();

				course.addSeatType(seatType, cutoff, seats);

			}

			college.addCourse(course);

		}

		colleges.put(code, college);

		System.out.println("College details added successfully.");

	}



	public static void displayCollegeList() {

		for (College college : colleges.values()) {

			System.out.println("College Code: " + college.collegeCode + ", Name: " + college.collegeName);

			for (Course course : college.courses.values()) {

				System.out.println("  Course: " + course.courseName);

				for (SeatType seatType : course.seatTypes) {

					System.out.println("    Seat Type: " + seatType.seatType + ", Cutoff: " + seatType.cutoff

							+ ", Available Seats: " + seatType.getAvailableSeats());

				}

			}

		}

	}



	public static void insertStudentDetails() {

		System.out.print("Enter application number: ");

		int appNumber = scanner.nextInt();

		System.out.print("Enter percentile: ");

		double percentile = scanner.nextDouble();

		scanner.nextLine();

		System.out.print("Enter name: ");

		String name = scanner.nextLine();

		System.out.print("Enter gender: ");

		String gender = scanner.nextLine();

		System.out.print("Enter category: ");

		String category = scanner.nextLine();

		Student student = new Student(appNumber, percentile, name, gender, category);



		System.out.print("Enter number of preferences: ");

		int numPreferences = scanner.nextInt();

		scanner.nextLine();



		for (int i = 0; i < numPreferences; i++) {

			System.out.print("Enter college code for preference " + (i + 1) + ": ");

			int collegeCode = scanner.nextInt();

			scanner.nextLine();

			System.out.print("Enter course name for preference " + (i + 1) + ": ");

			String courseName = scanner.nextLine();

			student.addPreference(new Preference(collegeCode, courseName));

		}

		studentQueue.add(student);

		studentBST.insert(student); // Insert into BST for sorting

		System.out.println("Student details added successfully.");

	}



	public static void displayMeritList() {

		System.out.println("Merit List (Highest percentile at top):");

		studentBST.inOrderTraversal();

	}



	public static void allocateSeats() {

		while (!studentQueue.isEmpty()) {

			Student student = studentQueue.poll();

			if (student.isAllocated) {

				System.out.println(student.name + " is already allocated to " + student.allocatedCollege

						+ " for course " + student.allocatedCourse + " with seat type " + student.allocatedSeatType);

				continue;

			}



			boolean isAllocated = false;



			for (Preference pref : student.preferences) {

				if (isAllocated)

					break;



				College college = colleges.get(pref.collegeCode);

				if (college != null) {

					Course course = college.findCourse(pref.courseName);

					if (course != null) {

						for (SeatType seatType : course.getSeatTypesDescending()) {

							if ((student.gender.equalsIgnoreCase("male") && seatType.seatType.equals("gopen"))

									|| (student.gender.equalsIgnoreCase("female") && (seatType.seatType.equals("lopen")

											|| seatType.seatType.equals("gopen")))) {



								if (student.percentile >= seatType.cutoff && seatType.getAvailableSeats() > 0) {

									seatType.allocateSeat();

									student.setAllocation(college.collegeName, course.courseName, seatType.seatType);

									System.out.println(

											"Allocated: " + student.name + " to " + college.collegeName + " for course "

													+ course.courseName + " with seat type " + seatType.seatType);

									isAllocated = true;

									break;

								}

							}

						}

					}

				}

			}



			if (!isAllocated) {

				System.out.println("No suitable allocation found for " + student.name);

			}

		}

	}



	public static void main(String[] args) {

		int choice;

		do {

			System.out.println("1. Insert College Details");

			System.out.println("2. Display College List");

			System.out.println("3. Insert Student Details");

			System.out.println("4. Display Merit List");

			System.out.println("5. Allocate Seats");

			System.out.println("0. Exit");

			System.out.print("Enter your choice: ");

			choice = scanner.nextInt();

			scanner.nextLine();



			switch (choice) {

			case 1 -> insertCollegeDetails();

			case 2 -> displayCollegeList();

			case 3 -> insertStudentDetails();

			case 4 -> displayMeritList();

			case 5 -> allocateSeats();

			case 0 -> System.out.println("Exiting...");

			default -> System.out.println("Invalid choice! Please try again.");

			}

		} while (choice != 0);

	}

}

/*

1. Insert College Details
2. Display College List
3. Insert Student Details
4. Display Merit List
5. Allocate Seats
0. Exit
Enter your choice: 1
Enter college code: 101
Enter college name: cummins
Enter number of courses: 2
Enter course name: cs
Enter number of seat types for this course: 1
Enter seat type (e.g., gopen, lopen): lopen
Enter cutoff for this seat type: 98
Enter number of seats for this seat type: 10
Enter course name: it
Enter number of seat types for this course: 1
Enter seat type (e.g., gopen, lopen): lopen
Enter cutoff for this seat type: 97
Enter number of seats for this seat type: 10
College details added successfully.

1. Insert College Details
2. Display College List
3. Insert Student Details
4. Display Merit List
5. Allocate Seats
0. Exit
Enter your choice: 2
College Code: 101, Name: cummins
Course: cs
  Seat Type: lopen, Cutoff: 98.0, Available Seats: 10
Course: it
  Seat Type: lopen, Cutoff: 97.0, Available Seats: 10
  
1. Insert College Details
2. Display College List
3. Insert Student Details
4. Display Merit List
5. Allocate Seats
0. Exit
Enter your choice: 3
Enter application number: 23001
Enter percentile: 99
Enter name: Neha
Enter gender: female
Enter category: open
Enter number of preferences: 1
Enter college code for preference 1: 101
Enter course name for preference 1: cs
Student details added successfully.

1. Insert College Details
2. Display College List
3. Insert Student Details
4. Display Merit List
5. Allocate Seats
0. Exit
Enter your choice: 3
Enter application number: 23002
Enter percentile: 96
Enter name: Raj
Enter gender: male
Enter category: open
Enter number of preferences: 1
Enter college code for preference 1: 101
Enter course name for preference 1: it
Student details added successfully.

1. Insert College Details
2. Display College List
3. Insert Student Details
4. Display Merit List
5. Allocate Seats
0. Exit
Enter your choice: 4
Merit List (Highest percentile at top):
Application Number: 23001, Name: Neha, Percentile: 99.0
Application Number: 23002, Name: Raj, Percentile: 96.0

1. Insert College Details
2. Display College List
3. Insert Student Details
4. Display Merit List
5. Allocate Seats
0. Exit
Enter your choice: 5
Allocated: Neha to cummins for course cs with seat type lopen
No suitable allocation found for Raj

1. Insert College Details
2. Display College List
3. Insert Student Details
4. Display Merit List
5. Allocate Seats
0. Exit
Enter your choice: 0
Exiting...

*/
