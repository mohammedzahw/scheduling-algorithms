import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputGUI {
    private JTextField requestsField, headField, totalTracksField;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private ArrayList<Integer> requests;
    private int head, totalTracks;
    private JRadioButton leftButton, rightButton;
    private JComboBox<String> comboBox;

    public InputGUI() {
        requests = new ArrayList<>();

        JFrame frame = new JFrame("List Input GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        requestsField = new JTextField(10);
        totalTracksField = new JTextField(10);
        headField = new JTextField(10);
        JButton button = new JButton("Display");
        textArea1 = new JTextArea();

        leftButton = new JRadioButton("Left");
        rightButton = new JRadioButton("Right");

        ButtonGroup group = new ButtonGroup();
        group.add(leftButton);
        group.add(rightButton);

        comboBox = new JComboBox<>(new String[] { "FCFS", "SSTF", "SCAN", "C-SCAN", "LOOK", "C-LOOK" });
        JFrame frame2 = new JFrame();
        Process process = new Process();
        Represent represent = new Represent();

        textArea1 = new JTextArea(3, 25);
        textArea1.setEditable(false);

        textArea2 = new JTextArea(3, 5);
        textArea2.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea1);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    requests.clear();
                    String[] inputs = requestsField.getText().split(",");
                    for (String input : inputs) {
                        int number = Integer.parseInt(input.trim());
                        requests.add(number);
                    }
                    if (totalTracksField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Please Enter The Total Number of Tracks");
                        return;
                    }
                    totalTracks = Integer.parseInt(totalTracksField.getText().trim());
                    if (headField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please Enter The Head Position");
                        return;
                    }
                    head = Integer.parseInt(headField.getText().trim());

                    if (head > totalTracks) {
                        JOptionPane.showMessageDialog(null,
                                "Please Enter The Head Position Between 0 and " + totalTracks);
                        return;
                    }
                    /********************* */
                    process.totalTracks = totalTracks;
                    process.algorithm = (String) comboBox.getSelectedItem();
                    process.left = leftButton.isSelected();
                    process.right = rightButton.isSelected();
                    process.head = head;
                    process.requests = requests;
                    process.run();

                    /****************************** */
                    int totalSeekTime = 0;
                    for (int i : process.requests) {
                        if (i < 0 || i > totalTracks) {
                            JOptionPane.showMessageDialog(null, "Please Enter Requests Between 0 and " + totalTracks);
                            return;
                        }
                    }
                    for (int i = 0; i < process.requests.size() - 1; i++) {
                        totalSeekTime += Math.abs(process.requests.get(i) - process.requests.get(i + 1)) * 1;
                    }
                    textArea2.setText(totalSeekTime + "");

                    textArea1.setText(process.requests.toString() + "\n");

                    represent.cord = process.requests;
                    represent.totalTracks = totalTracks;
                    frame2.setTitle(process.algorithm);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.add(represent);
                    frame2.setSize(1200, 1000);
                    frame2.setLocationRelativeTo(null);
                    frame2.setVisible(true);
                    frame2.revalidate();
                    frame2.repaint();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid integers separated by commas.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }

            }
        });
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel outputRequests = new JPanel();
        outputRequests.add(new JLabel("Output Requests:"));
        outputRequests.add(scrollPane);
        frame.add(outputRequests);

        JPanel totalSeekTime = new JPanel();
        totalSeekTime.add(new JLabel("Total Seek Time:"));
        totalSeekTime.add(textArea2);
        frame.add(totalSeekTime);

        JPanel totalTracksPanel = new JPanel();
        totalTracksPanel.add(new JLabel("Total Number of Tracks:"));
        totalTracksPanel.add(totalTracksField);
        frame.add(totalTracksPanel);

        JPanel requestsPanel = new JPanel();
        requestsPanel.add(new JLabel("Enter Requests:"));
        requestsPanel.add(requestsField);
        frame.add(requestsPanel);

        JPanel headPanel = new JPanel();
        headPanel.add(new JLabel("Head Position:"));
        headPanel.add(headField);
        frame.add(headPanel);

        JPanel radioPanel = new JPanel();
        radioPanel.add(leftButton);
        radioPanel.add(rightButton);
        frame.add(radioPanel);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.add(new JLabel("Select Scheduling Algorithm:"));
        comboBoxPanel.add(comboBox);
        frame.add(comboBoxPanel);

        frame.add(button);
        // frame.add(new JScrollPane(textArea));

        frame.setVisible(true);
    }

}