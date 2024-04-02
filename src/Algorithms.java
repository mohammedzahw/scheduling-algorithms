import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithms {
    public int limit = 40;

    public List<Integer> scan(List<Integer> cylinders, int head, boolean dir) {
        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : cylinders) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }

        Collections.sort(left.subList(0, left.size()), Collections.reverseOrder());
        Collections.sort(right.subList(0, right.size()));
        if (!right.isEmpty() && right.getLast() != limit)
            right.addLast(limit);
        if (!left.isEmpty() && left.getLast() != 0)
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

    public List<Integer> look(List<Integer> cylinders, int head, boolean dir) {
        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : cylinders) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }
        Collections.sort(left.subList(0, left.size()), Collections.reverseOrder());
        Collections.sort(right.subList(0, right.size()));
        if (dir) {
            temp.addAll(right);
            temp.addAll(left);

        } else {
            temp.addAll(left);
            temp.addAll(right);
        }

        return temp;
    }

    public List<Integer> fcfs(List<Integer> cylinders, int head) {
        cylinders.add(0, head);
        return cylinders;
    }

    public List<Integer> sstf(List<Integer> cylinders, int head) {

        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : cylinders) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }

        Collections.sort(left.subList(0, left.size()), Collections.reverseOrder());
        Collections.sort(right.subList(0, right.size()));

        while (!left.isEmpty() && !right.isEmpty()) {

            if (head - left.get(0) < right.get(0) - head) {
                temp.add(left.get(0));
                head = left.get(0);
                left.remove(0);
            } else {
                temp.add(right.get(0));
                head = right.get(0);
                right.remove(0);
            }

        }

        temp.addAll(left);
        temp.addAll(right);

        return temp;
    }

    public List<Integer> c_scan(List<Integer> cylinders, int head, boolean dir) {

        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : cylinders) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }
        Collections.sort(left.subList(0, left.size()));
        Collections.sort(right.subList(0, right.size()));
        if (right.getLast() != limit)
            right.addLast(limit);
        if (left.getFirst() != 0)
            left.addFirst(0);
        if (dir) {
            temp.addAll(right);
            temp.addAll(left);

        } else {
            temp.addAll(left.reversed());
            temp.addAll(right.reversed());
        }

        return temp;

    }

    public List<Integer> c_look(List<Integer> cylinders, int head, boolean dir) {

        List<Integer> temp = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        temp.add(head);
        for (Integer integer : cylinders) {
            if (integer < head)
                left.add(integer);
            else if (integer > head)
                right.add(integer);
            else
                temp.add(integer);
        }
        Collections.sort(left.subList(0, left.size()));
        Collections.sort(right.subList(0, right.size()));
        if (dir) {
            temp.addAll(right);
            temp.addAll(left);

        } else {
            temp.addAll(left.reversed());
            temp.addAll(right.reversed());
        }

        return temp;
    }

    public List<Integer> n_scan(List<Integer> cylinders, int head, int n, boolean dir) {

        List<Integer> temp = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();
        // Split the cylinders list into sublists of size n
        List<List<Integer>> sublists = new ArrayList<>();
        for (int i = 0; i < cylinders.size(); i += n) {
            sublists.add(new ArrayList<>(
                    cylinders.subList(i, Math.min(i + n, cylinders.size()))));

        }
        // Service each sublist in the current direction, then reverse the direction
        for (List<Integer> sublist : sublists) {

            temp2 = scan(sublist, head, dir);
            head = temp2.getLast();
            temp.addAll(temp2.subList(0, temp2.size() - 1));

        }

        return temp;
    }

}
