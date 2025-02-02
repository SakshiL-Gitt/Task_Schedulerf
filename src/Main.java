import java.util.*;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        List<int[]> dependencies = new ArrayList<>();
        int availableResources = 0;

        try 
        {
            int numTasks = getPositiveInt(sc, "Enter the number of tasks: ");

            System.out.println("Enter task details (ID, Duration, Deadline, Resources):");
            for (int i = 0; i < numTasks; i++) 
            {
                String id = sc.next();
                int duration = sc.nextInt();
                int deadline = sc.nextInt();
                int resources = sc.nextInt();

                if (duration <= 0 || deadline < 0 || resources <= 0) 
                {
                    throw new IllegalArgumentException("Task attributes must be positive.");
                }

                tasks.add(new Task(id, duration, deadline, resources));
            }

            int numDependencies = getPositiveInt(sc, "Enter the number of dependencies: ");
            sc.nextLine(); // Consume newline

            System.out.println("Enter dependencies in the format A->B:");
            for (int i = 0; i < numDependencies; i++) {
                String[] parts = sc.nextLine().split("->");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid dependency format. Use A->B.");
                }

                int from = findTaskIndex(tasks, parts[0].trim());
                int to = findTaskIndex(tasks, parts[1].trim());

                if (from == -1 || to == -1) 
                {
                    throw new IllegalArgumentException("Error: Task IDs mismatch.");
                }

                dependencies.add(new int[]{from, to});
            }

            availableResources = getPositiveInt(sc, "Enter the available resources: ");

            // Task scheduling execution
            TaskScheduler scheduler = new TaskScheduler();
            scheduler.scheduleTasks(tasks, dependencies, availableResources);
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Invalid input! Please enter numbers where required.");
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println("Error: " + e.getMessage());
        } 
        catch (Exception e) 
        {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
        finally 
        {
            sc.close(); // Ensure Scanner is closed
        }
    }

    private static int getPositiveInt(Scanner sc, String prompt) 
    {
        int value;
        while (true) 
        {
            System.out.print(prompt);
            try 
            {
                value = sc.nextInt();
                if (value > 0) break;
                System.out.println("Error: Value must be positive.");
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid input! Please enter a positive number.");
                sc.next(); // Clear invalid input
            }
        }
        return value;
    }

    private static int findTaskIndex(List<Task> tasks, String id) 
    {
        for (int i = 0; i < tasks.size(); i++) 
        {
            if (tasks.get(i).id.equals(id)) 
            {
                return i;
            }
        }
        return -1;
    }
}
