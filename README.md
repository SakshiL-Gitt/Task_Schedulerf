# Task_Scheduler-Java
This Java project implements a Task Scheduler that schedules a set of tasks with given durations, deadlines, and resource constraints while respecting task dependencies. The scheduling algorithm prioritizes tasks based on earliest deadline first and ensures that dependencies are satisfied.<br>
<br>**Features**<br>
•	Uses topological sorting (Kahn's Algorithm) to process tasks in the correct order.<br>
•	Uses a priority queue (earliest deadline first) for scheduling.<br>
•	Allocates resources efficiently to avoid overuse.<br>
•	Ensures that no task exceeds its deadline.<br>
•	Prints the scheduled start time, end time, and resource allocation for each task.<br>
<br>**Prerequisites**<br>
Before running this project, ensure you have:<br>
•	Java JDK (8 or higher) installed → Check using java -version<br>
•	Git installed (if cloning from GitHub)<br>
<br>**Project Structure**
/Java-DSA-Project<br>
  ├── Main.java            # Main entry point of the project<br>
  ├── Task.java            # Task class definition<br>
  ├── TaskScheduler.java   # Handles task scheduling logic<br>
  ├── README.md            # Project documentation<br>

<br>**Installation & Setup**<br>
🔹 Method 1: Clone the Repository<br>
•	Open a terminal (Linux/macOS) or Command Prompt (Windows)<br>.
•	Run the following command to clone the repository:<br>
             git clone https://github.com/SakshiL-Gitt/Task_Schedulerf.git
•	Navigate into the project directory:<br>
            cd Java-DSA-Project<br>
🔹 Method 2: Download Files from folder<br>
•	Open Command Prompt/Terminal and navigate to the folder where you saved the folder:<br>
•	cd path/to/Java-DSA-Project<br>
<br>**How to Run the Project**<br>
🔸 Windows<br>
•	Open Command Prompt (cmd) and navigate to the project folder:<br>
                             cd path\to\Java-DSA-Project<br>
•	Compile the Java files:<br>
                            javac *.java<br>
•	Run the project:<br>
                            java Main<br>
🔸 Linux/macOS<br>
•	Open a terminal and navigate to the project folder:<br>
                 cd path/to/Java-DSA-Project<br>
•	Compile:<br>
                javac *.java<br>
•	Run:<br>
                java Main<br>
<br>**Input Format**<br>
The program takes:<br>
1.	A list of tasks with the following attributes:<br>
	id: Task identifier (e.g., "A", "B").<br>
	duration: Time required to complete the task.<br>
	deadline: Maximum allowable time to complete the task.<br>
	resources: Number of resources needed for the task.<br>
2.	A list of dependencies, specifying which task depends on another.<br>
3.	The total number of available resources.<br>
<br>**📌 Example**<br>
Example Input/Output<br>
**INPUT**<br>
Enter the number of tasks: 4<br>
Enter task details (ID, Duration, Deadline, Resources):<br>
A 4 10 2<br>
B 3 15 1<br>
C 2 8  1<br>
D 5 20 3<br>
Enter the number of dependencies: 2<br>
Enter dependencies in the format A->B:<br>
A->B<br>
C->D<br>
Enter the available resources: 4<br>
**OUTPUT**<br>
Task       StartTime  EndTime    Resources <br>
C          0          2          1 <br>
A          0          4          2 <br> 
B          4          7          1 <br>  
D          2          7          3 <br>
**License**<br>
This project is licensed under the MIT License – free to use and modify.

