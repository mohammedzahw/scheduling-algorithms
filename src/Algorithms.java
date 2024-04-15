
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithms {
    public int totalTracks = 40;

    public List<Integer> scan(List<Integer> requests, int head, boolean dir) {

        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : requests) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }

        left.sort(Collections.reverseOrder());
        Collections.sort(right);
        if (right.isEmpty() || right.getLast() != totalTracks)
            right.addLast(totalTracks);

        if (left.isEmpty() || left.getLast() != 0)
            left.addLast(0);
        if (dir) {
            temp.addAll(right);
            temp.addAll(left);

        } else {
            temp.addAll(left);
            temp.addAll(right);
        }

        return temp;

    }

    public List<Integer> look(List<Integer> requests, int head, boolean dir) {
        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : requests) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }
        left.sort(Collections.reverseOrder());
        Collections.sort(right);
        if (dir) {
            temp.addAll(right);
            temp.addAll(left);

        } else {
            temp.addAll(left);
            temp.addAll(right);
        }

        return temp;
    }

    public List<Integer> fcfs(List<Integer> requests, int head) {
        requests.addFirst(head);
        return requests;
    }

    public List<Integer> sstf(List<Integer> requests, int head) {

        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : requests) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }

        left.sort(Collections.reverseOrder());
        Collections.sort(right);

        while (!left.isEmpty() && !right.isEmpty()) {

            if (head - left.getFirst() < right.getFirst() - head) {
                temp.add(left.getFirst());
                head = left.getFirst();
                left.removeFirst();
            } else {
                temp.add(right.getFirst());
                head = right.getFirst();
                right.removeFirst();
            }

        }

        temp.addAll(left);
        temp.addAll(right);

        return temp;
    }

    public List<Integer> c_scan(List<Integer> requests, Integer head, boolean dir) {

        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : requests) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }
        Collections.sort(left);
        Collections.sort(right);
        if (right.isEmpty() || right.getLast() != totalTracks)
            right.addLast(totalTracks);

        if (left.isEmpty() || left.getFirst() != 0)
            left.addFirst(0);

        if (dir) {
            temp.addAll(right);
            temp.addAll(left);

        } else {
            temp.addAll(left.reversed());
            temp.addAll(right.reversed());
        }

        temp.removeLast();
        return temp;

    }

    public List<Integer> c_look(List<Integer> requests, int head, boolean dir) {

        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : requests) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }
        Collections.sort(left);
        Collections.sort(right);

        if (dir) {
            temp.addAll(right);
            temp.addAll(left);

        } else {
            temp.addAll(left.reversed());
            temp.addAll(right.reversed());
        }

        return temp;
    }

   

}
