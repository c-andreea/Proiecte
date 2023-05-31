package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        Server chosenServer = chooseServer(servers, task);
        chosenServer.addTask(task);
    }
    @Override
    public Server chooseServer(List<Server> servers, Task task) {
        return servers.stream()
                .min((s1, s2) -> Integer.compare(s1.getTasks().size(), s2.getTasks().size()))
                .orElseThrow(() -> new RuntimeException("No available servers."));
    }
}

