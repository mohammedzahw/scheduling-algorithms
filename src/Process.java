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
    List<Integer> cylinders = new ArrayList<>();

    void run() {

        Algorithms algorithms = new Algorithms();
        algorithms.limit = limit;

        if (right)
            dir = true;

        switch (algorithm) {
            case "FCFS":
                cylinders = algorithms.fcfs(cylinders, head);
                break;
            case "SSTF":
                cylinders = algorithms.sstf(cylinders, head);
                break;
            case "SCAN":
                cylinders = algorithms.scan(cylinders, head, dir);
                break;

            case "C-SCAN":
                cylinders = algorithms.c_scan(cylinders, head, dir);
                break;
            case "LOOK":
                cylinders = algorithms.look(cylinders, head, dir);
                break;

            case "C-LOOK":
                cylinders = algorithms.c_look(cylinders, head, dir);
                break;
            case "N-Step SCAN":
                cylinders = algorithms.n_scan(cylinders, head, N, dir);
                break;
        }

    }
}
