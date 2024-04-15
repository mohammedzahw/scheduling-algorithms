import java.util.ArrayList;
import java.util.List;

public class Process {
    String algorithm = "";
    boolean left = false;
    boolean right = false;
    Integer head = 0;
    boolean dir = false;
    int totalTracks = 40;
    int N = 10;
    List<Integer> requests = new ArrayList<>();

    void run() {

        Algorithms algorithms = new Algorithms();
        algorithms.totalTracks = totalTracks;
        // System.out.println(algorithm);

        if (right)
            dir = true;
        else
            dir = false;

        switch (algorithm) {
            case "FCFS":
                requests = algorithms.fcfs(requests, head);
                break;
            case "SSTF":
                requests = algorithms.sstf(requests, head);
                break;
            case "SCAN":
                requests = algorithms.scan(requests, head, dir);
                break;

            case "C-SCAN":
                requests = algorithms.c_scan(requests, head, dir);
                break;
            case "LOOK":
                requests = algorithms.look(requests, head, dir);
                break;

            case "C-LOOK":
                requests = algorithms.c_look(requests, head, dir);
                break;
        }

    }
}
