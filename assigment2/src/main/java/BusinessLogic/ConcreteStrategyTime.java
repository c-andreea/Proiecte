package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        Server shortestTimeServer = null;
        int minProcessingTime = Integer.MAX_VALUE;

        for (Server server : servers) {
            AtomicInteger currentProcessingTime = server.getWaitingPeriod();

            if (currentProcessingTime.get() < minProcessingTime) {
                minProcessingTime = currentProcessingTime.get();
                shortestTimeServer = server;
            }
        }

        if (shortestTimeServer != null) {
            shortestTimeServer.addTask(t);
        }
    }

    @Override
    public Server chooseServer(List<Server> servers, Task task) {
        Server shortestTimeServer = null;
        int minProcessingTime = Integer.MAX_VALUE;

        for (Server server : servers) {
            AtomicInteger currentProcessingTime = server.getWaitingPeriod();

            if (currentProcessingTime.get() < minProcessingTime) {
                minProcessingTime = currentProcessingTime.get();
                shortestTimeServer = server;
            }
        }

        if (shortestTimeServer != null) {
            return shortestTimeServer;
        } else {
            throw new RuntimeException("No available servers.");
        }
    }
}
