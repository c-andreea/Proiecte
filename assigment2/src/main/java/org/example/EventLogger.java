package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EventLogger {
    private final String fileName;

    public EventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(int time, List<String> waitingClients, List<String> queue1, List<String> queue2) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Time " + time + "\n");
            bw.write("Waiting clients: " + waitingClients.toString() + "\n");
            bw.write("Queue 1: " + queue1.toString() + "\n");
            bw.write("Queue 2: " + queue2.toString() + "\n");
            bw.newLine();

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
