# College-Cutoff-Management-System
The College Seat Allocation System is a Java-based tool that automates college admissions based on student merit, preferences, and seat availability. It uses data structures like HashMap, BST, LinkedList, Queue, and Stack for efficient processing. The system is modular, ensuring smooth interaction for both admins and students.

 Purpose of the Project:-
The project aims to automate the college seat allocation process for students based on their percentile, preferences, category, and availability. It ensures transparency, efficiency, and fairness in how students are allotted seats across various institutions.

 What the Project Does:-
This system simplifies the college admission process by providing key features for both administrators and students. It includes:

 Application Features:-
1. Adding Colleges
2. Admins can input college names, available courses, seat types (e.g., General, OBC, SC, ST), and total available seats per category.
3. Adding Students
4. Students register by entering their personal details, entrance percentile, caste category, gender, and a list of preferred colleges and courses.
5 .Merit List Generation
6. The system sorts and displays students based on their percentile using a Binary Search Tree (BST), ensuring a transparent merit-based ranking.
7. Seat Allocation
8. Seats are allocated in rounds based on merit, category, seat availability, and the student's preference list.
9. Allocation continues to next preferences if seats are not available for the first choice.
10. Modular Interaction
Each module (college entry, student registration, allocation) works independently for ease of use and testing.
Ensures separation of concerns and better maintainability.

 
 Data Structures Used:-
1.HashMap
Used to store and quickly access college and course information.
Enables O(1) access for seat type lookup and availability tracking.

2.Binary Search Tree (BST)
Maintains a sorted list of students based on their percentiles.
Used to generate the merit list in descending order of performance.

3.LinkedList
Stores each student’s preference list in a flexible and modifiable format.

4.Queue
Processes students in a FIFO manner for fair and orderly seat allocation during each round.

5.Stack
Keeps track of allocation changes to support rollback or audit of decisions in reverse chronological order.

 Tech Stack:-
Programming Language: Java
IDE: Eclipse / IntelliJ IDEA
Data Structures: Custom implementations using Java Collections Framework

Possible Extensions:-
GUI for better user experience
Admin dashboard with allocation statistics
Email/SMS notification system
Support for multiple admission rounds
