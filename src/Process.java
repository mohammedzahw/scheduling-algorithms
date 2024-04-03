import java.util.ArrayList;
import java.util.List;

public class Process {
    String algorithm = "";
    boolean left = false;
    boolean right = false;
    Integer head = 0;
    boolean dir = false;
    int limit = 40;
    int N = 10;
    List<Integer> cylinder = new ArrayList<>();

    void run() {

        Algorithms algorithms = new Algorithms();
        algorithms.limit = limit;

        if (right)
            dir = true;
        else
            dir = false;

        switch (algorithm) {
            case "FCFS":
                cylinder = algorithms.fcfs(cylinder, head);
                break;
            case "SSTF":
                cylinder = algorithms.sstf(cylinder, head);
                break;
            case "SCAN":
                cylinder = algorithms.scan(cylinder, head, dir);
                break;

            case "C-SCAN":
                cylinder = algorithms.c_scan(cylinder, head, dir);
                break;
            case "LOOK":
                cylinder = algorithms.look(cylinder, head, dir);
                break;

            case "C-LOOK":
                cylinder = algorithms.c_look(cylinder, head, dir);
                break;
            case "N-Step SCAN":
                cylinder = algorithms.n_scan(cylinder, head, N, dir);
                break;
        }

    }
}
