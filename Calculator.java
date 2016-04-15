import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.List;
import java.util.ArrayList;

public class Calculator {

    private Frame mainFrame;
    private Panel inputPanel;
    private Panel buttonPanel;
    private static TextField inputText;
    private Label infoLn1, infoLn2, avgLabel;
    private JButton calculateButton = new JButton("Calculate");
    private JButton clearButton = new JButton("Clear");
    private static double avg;
    private List<Double> inputs = new ArrayList<Double>();

    public Calculator() {
        ShowGUI();
    }

    public static void main(String[] args){
        Calculator avgCalc = new Calculator();
        avgCalc.ShowButtons();
    }

    public void ShowGUI(){
        mainFrame = new JFrame("Average Calculator");
        mainFrame.setSize(650, 200);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
           }
        });

        //Instantiate Labels, Panels, and TextFeilds
        infoLn1 = new Label();
        infoLn2 = new Label();
        avgLabel = new Label();
        infoLn1.setText("Enter a number and press Calulate to calculate the average with the previous numbers inputted");
        infoLn2.setText("Pro tip: Press enter for quick number entry.");

        inputPanel = new Panel();
        buttonPanel = new Panel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new FlowLayout());

        mainFrame.add(inputPanel);
        mainFrame.add(buttonPanel);

        inputText = new TextField("", 6);

        //Add elements to Panel
        inputPanel.add(infoLn1);
        inputPanel.add(infoLn2);
        inputPanel.add(inputText);
        inputPanel.add(avgLabel);
    }

    private void ShowButtons() {
        calculateButton.setActionCommand("calculate");
        calculateButton.addActionListener(new ButtonClickListener());
        clearButton.setActionCommand("clear");
        clearButton.addActionListener(new ButtonClickListener());

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        JRootPane rootPane = SwingUtilities.getRootPane(calculateButton);
        rootPane.setDefaultButton(calculateButton);
        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            if (command.equals("calculate")){
            	if (!inputText.getText().equals("") && inputText.getText().matches("^-?[0-9]{1,12}(?:\\.[0-9]{1,4})?$") ){
            		inputs.add(Double.parseDouble(inputText.getText()));
            	}
            	avg = 0;
            	if (inputs.size() != 0) {
	            	for (int i = 0; i < inputs.size(); i++) {
	            		avg += inputs.get(i);
	            	}
            		avg = avg/inputs.size();
            	}
	            avgLabel.setText(Double.toString(avg));
	            inputText.setText("");
            }
            else if (command.equals("clear")){
            	inputs.clear();
            	avgLabel.setText("");
            }
            else {
                System.out.println("Action was performed that wasn't planned for!");
                System.exit(1);
            }
        }
    }
}
