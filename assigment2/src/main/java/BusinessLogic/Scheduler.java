package BusinessLogic;

import BusinessLogic.Strategy;
import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Scheduler {
        private final List<Server> servers;
        private final Strategy strategy;

        public Scheduler(List<Server> servers, Strategy strategy) {
                this.servers = servers;
                this.strategy = strategy;
        }

        public void dispatchTask(Task t) {
                strategy.addTask(servers, t);
        }

        public List<Server> getServers() {
                return servers;
        }

        public List<Task> getTasks() {
                List<Task> tasks = new ArrayList<>();
                for (Server server : servers) {
                        tasks.addAll(server.getTasks());
                }
                return tasks;
        }

        public String queueStatus(int currentTime) {
                StringBuilder sb = new StringBuilder();
                sb.append("Time ").append(currentTime).append("\n");

                List<Task> waitingTasks = new ArrayList<>();
                for (Server server : servers) {
                        waitingTasks.addAll(server.getTasks());
                }
                waitingTasks = waitingTasks.stream()
                        .filter(task -> !task.isAssigned())
                        .sorted(Comparator.comparing(Task::getArrivalTime))
                        .collect(Collectors.toList());

                List<String> waitingTasksStr = waitingTasks.stream()
                        .map(task -> "(" + task.getTaskId() + "," + task.getProcessingTime() + "," + task.getArrivalTime() + ")")
                        .collect(Collectors.toList());

                sb.append("Waiting clients: ").append(waitingTasksStr.toString()).append("\n");

                for (int i = 0; i < servers.size(); i++) {
                        Server server = servers.get(i);
                        sb.append("Queue ").append(i + 1).append(": ");

                        List<Task> tasksInQueue = server.getTasks().stream()
                                .filter(task -> !task.isAssigned())
                                .sorted(Comparator.comparing(Task::getArrivalTime))
                                .collect(Collectors.toList());

                        if (!tasksInQueue.isEmpty()) {
                                List<String> tasksInQueueStr = tasksInQueue.stream()
                                        .map(task -> "(" + task.getTaskId() + "," + task.getProcessingTime() + "," + task.getArrivalTime() + ")")
                                        .collect(Collectors.toList());

                                sb.append(tasksInQueueStr.toString());
                        } else if (server.isOccupied()) {
                                sb.append("[").append(server.getCurrentTask().getTaskId()).append(",")
                                        .append(server.getTimeLeft()).append(",")
                                        .append(server.getCurrentTask().getArrivalTime()).append("]");
                        } else {
                                sb.append("closed");
                        }
                        sb.append("\n");
                }

                return sb.toString();
        }


        public double getAverageWaitingTime() {
                List<Task> tasks = getTasks();
                int currentTime = (int) (System.currentTimeMillis() / 1000);
                int totalWaitingTime = tasks.stream().mapToInt(task -> task.getWaitingTime(currentTime)).sum();
                return tasks.isEmpty() ? 0 : (double) totalWaitingTime / tasks.size();
        }

        public double getAverageServiceTime() {
                List<Task> tasks = getTasks();
                int currentTime = (int) (System.currentTimeMillis() / 1000);
                int totalServiceTime = tasks.stream().mapToInt(task -> task.getServiceTime(currentTime)).sum();
                return tasks.isEmpty() ? 0 : (double) totalServiceTime / tasks.size();
        }

        public int getPeakHour() {
                List<Task> tasks = getTasks();
                // Assuming the simulation interval is in seconds
                int interval = 3600; // 1 hour
                int currentTime = (int) (System.currentTimeMillis() / 1000);

                int maxTasks = 0;
                int peakHour = -1;
                for (int i = 0; i < currentTime; i += interval) {
                        final int startInterval = i;
                        int tasksInInterval = (int) tasks.stream().filter(task -> task.getArrivalTime() >= startInterval && task.getArrivalTime() < startInterval + interval).count();
                        if (tasksInInterval > maxTasks) {
                                maxTasks = tasksInInterval;
                                peakHour = i / interval;
                        }
                }

                return peakHour;
        }


        public String statistics() {
                StringBuilder sb = new StringBuilder();
                sb.append("Average waiting time: ").append(getAverageWaitingTime()).append(" seconds\n");
                sb.append("Average service time: ").append(getAverageServiceTime()).append(" seconds\n");
                sb.append("Peak hour: ").append(getPeakHour()).append("\n");

                return sb.toString();
        }

}
