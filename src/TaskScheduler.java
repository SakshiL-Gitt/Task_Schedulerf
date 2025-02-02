import java.util.*;

public class TaskScheduler {
    public void scheduleTasks(List<Task> tasks, List<int[]> dependencies, int availableResources) {
        // Check for null or empty input lists
        if (tasks == null || dependencies == null) {
            throw new IllegalArgumentException("Tasks or dependencies cannot be null.");
        }
        if (availableResources < 0) {
            throw new IllegalArgumentException("Available resources must be non-negative.");
        }

        int numTasks = tasks.size();
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[numTasks];

        // Initialize adjacency list for all tasks
        for (int i = 0; i < numTasks; i++) {
            adjList.add(new ArrayList<>());
        }

        try {
            // Process dependencies
            for (int[] dep : dependencies) {
                if (dep.length != 2) {
                    throw new IllegalArgumentException("Invalid dependency format.");
                }
                if (dep[0] < 0 || dep[0] >= numTasks || dep[1] < 0 || dep[1] >= numTasks) {
                    throw new IndexOutOfBoundsException("Dependency references invalid task index.");
                }
                adjList.get(dep[0]).add(dep[1]); // Add dependency edge A -> B
                inDegree[dep[1]]++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid dependency indices. " + e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // Min heap to process tasks based on end time (earliest first)
        PriorityQueue<Task> readyQueue = new PriorityQueue<>((a, b) ->
                a.endTime != b.endTime ? a.endTime - b.endTime : a.duration - b.duration
        );

        int currentTime = 0;
        List<Task> availableTasks = new ArrayList<>();

        // Add all tasks with no dependencies (in-degree 0) to the ready queue
        for (int i = 0; i < numTasks; i++) {
            if (inDegree[i] == 0) {
                tasks.get(i).startTime = currentTime;
                readyQueue.add(tasks.get(i));
            }
        }

        System.out.printf("%-10s %-10s %-10s %-10s%n", "Task", "StartTime", "EndTime", "Resources");

        try {
            while (!readyQueue.isEmpty() || !availableTasks.isEmpty()) {
                availableTasks.clear();
                int resourcesUsed = 0;

                while (!readyQueue.isEmpty() && resourcesUsed < availableResources) {
                    Task task = readyQueue.poll();
                    if (resourcesUsed + task.resources <= availableResources) {
                        availableTasks.add(task);
                        resourcesUsed += task.resources;
                    } else {
                        readyQueue.add(task);
                    }
                }

                for (Task task : availableTasks) {
                    int taskIndex = findTaskIndex(tasks, task.id);
                    task.endTime = task.startTime + task.duration;

                    System.out.printf("%-10s %-10d %-10d %-10d%n", task.id, task.startTime, task.endTime, task.resources);

                    currentTime = Math.max(currentTime, task.endTime);

                    for (int nextTaskIndex : adjList.get(taskIndex)) {
                        inDegree[nextTaskIndex]--;
                        if (inDegree[nextTaskIndex] == 0) {
                            tasks.get(nextTaskIndex).startTime = currentTime;
                            readyQueue.add(tasks.get(nextTaskIndex));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected error occurred during scheduling: " + e.getMessage());
        }
    }

    public int findTaskIndex(List<Task> tasks, String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Task ID cannot be null or empty.");
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id.equals(id)) {
                return i;
            }
        }
        return -1; // Return -1 if task ID is not found
    }
}
