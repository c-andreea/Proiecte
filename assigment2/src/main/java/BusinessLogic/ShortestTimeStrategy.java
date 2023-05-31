package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ShortestTimeStrategy implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {
        Server chosenServer = chooseServer(servers, task);
        chosenServer.addTask(task);
    }

    @Override
    public Server chooseServer(List<Server> servers, Task task) {
        return servers.stream()
                .min((s1, s2) -> Integer.compare(s1.getWaitingPeriod().get(), s2.getWaitingPeriod().get()))
                .orElseThrow(() -> new RuntimeException("No available servers."));
    }
}
