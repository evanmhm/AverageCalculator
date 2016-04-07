import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;

public class Calculator {

    private Frame mainFrame;
    private Panel inputPanel;
    private Panel buttonPanel;
    private static TextField inputText;
    private Label info;
    private Label avgLabel;
    private static double avg;

    public Calculator() {
        ShowGUI();
    }

    public static void main(String[] args){
        Calculator avgCalc = new Calculator();
        avgCalc.ShowButtons();
    }

    public void ShowGUI(){
        mainFrame = new Frame("Average Calculator");
        mainFrame.setSize(500, 200);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
           }
        });
        //Instantiate Labels, Panels, and TextFeilds
        info = new Label();
        avgLabel = new Label();

        inputPanel = new Panel();
        buttonPanel = new Panel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new FlowLayout());

        mainFrame.add(inputPanel);
        mainFrame.add(buttonPanel);

        inputText = new TextField("", 6);

        //Add elements to Panel
        inputPanel.add(inputText);
        inputPanel.add(avgLabel);
    }

    private void ShowButtons() {
        Button calculateButton = new Button("Calculate");

        calculateButton.setActionCommand("calculate");
        calculateButton.addActionListener(new ButtonClickListener());

        buttonPanel.add(calculateButton);
        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            if (command.equals("calculate")){
                avgLabel.setText("Pressed!");
            }
            else if (command.equals("exit")) {
                System.exit(0);
            }
            else {
                System.out.println("Action was performed that wasn't planned for!");
                System.exit(1);
            }
        }
    }
}
