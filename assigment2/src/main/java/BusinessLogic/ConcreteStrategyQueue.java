package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        Server shortestQueue = servers.get(0);
        int minTasks = shortestQueue.getTasks().size();

        for (Server server : servers) {
            int currentTasks = server.getTasks().size();
            if (currentTasks < minTasks) {
                minTasks = currentTasks;
                shortestQueue = server;
            }
        }

        shortestQueue.addTask(t);
    }

    @Override
    public Server chooseServer(List<Server> servers, Task task) {
        return servers.stream()
                .min((s1, s2) -> Integer.compare(s1.getTasks().size(), s2.getTasks().size()))
                .orElseThrow(() -> new RuntimeException("No available servers."));
    }
}