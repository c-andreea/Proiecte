package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public interface Strategy {
    void addTask(List<Server> servers, Task task);

    Server chooseServer(List<Server> servers, Task task);
}
