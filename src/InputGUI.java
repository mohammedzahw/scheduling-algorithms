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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputGUI {
    private JTextField cylindersField, headField, limitField, NField;
    private JTextArea textArea;
    private ArrayList<Integer> cylinders;
    private int head, limit, N;
    private JRadioButton leftButton, rightButton;
    private JComboBox<String> comboBox;

    public InputGUI() {
        cylinders = new ArrayList<>();

        JFrame frame = new JFrame("List Input GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        cylindersField = new JTextField(10);
        limitField = new JTextField(10);
        headField = new JTextField(10);
        NField = new JTextField(10);
        JButton button = new JButton("Submit");
        textArea = new JTextArea();

        leftButton = new JRadioButton("Left");
        rightButton = new JRadioButton("Right");

        ButtonGroup group = new ButtonGroup();
        group.add(leftButton);
        group.add(rightButton);

        comboBox = new JComboBox<>(new String[] { "FCFS", "SSTF", "SCAN", "C-SCAN", "LOOK", "C-LOOK", "N-Step SCAN" });
        JFrame frame2 = new JFrame();
        Process process = new Process();
        Represent represent = new Represent();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cylinders.clear();
                    String[] inputs = cylindersField.getText().split(",");
                    for (String input : inputs) {
                        int number = Integer.parseInt(input.trim());
                        cylinders.add(number);
                        textArea.append(number + "\n");
                    }
                    head = Integer.parseInt(headField.getText().trim());
                    N = Integer.parseInt(NField.getText().trim());
                    limit = Integer.parseInt(limitField.getText().trim());

                    /********************* */

                    process.limit = limit;
                    process.N = N;
                    process.algorithm = (String) comboBox.getSelectedItem();
                    process.left = leftButton.isSelected();
                    process.right = rightButton.isSelected();
                    process.head = head;
                    process.cylinder = cylinders;
                    process.run();

                    /****************************** */
                    System.out.println("cylinder: " + process.cylinder);

                    represent.cord = process.cylinder;
                    represent.limit = limit;
                    frame2.setTitle(process.algorithm);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.add(represent);
                    frame2.setSize(1200, 1200);
                    frame2.setLocationRelativeTo(null);
                    frame2.setVisible(true);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid integers separated by commas.");
                }
            }
        });
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel limitPanel = new JPanel();
        limitPanel.add(new JLabel("limit:"));
        limitPanel.add(limitField);
        frame.add(limitPanel);

        JPanel cylindersPanel = new JPanel();
        cylindersPanel.add(new JLabel("Cylinders:"));
        cylindersPanel.add(cylindersField);
        frame.add(cylindersPanel);

        JPanel headPanel = new JPanel();
        headPanel.add(new JLabel("Head:"));
        headPanel.add(headField);
        frame.add(headPanel);

        JPanel NPanel = new JPanel();
        NPanel.add(new JLabel("N:"));
        NPanel.add(NField);
        frame.add(NPanel);

        JPanel radioPanel = new JPanel();
        radioPanel.add(leftButton);
        radioPanel.add(rightButton);
        frame.add(radioPanel);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.add(new JLabel("Algorithm:"));
        comboBoxPanel.add(comboBox);
        frame.add(comboBoxPanel);

        frame.add(button);
        // frame.add(new JScrollPane(textArea));

        frame.setVisible(true);
    }

}