package Presentation;
import Model.*;
import BusinessLogic.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JTextField clientsField;
    private JTextField queueField;
    private JTextField simulationField;
    private JTextField arrivalField;
    private JTextField serviceField;
    private JTextField storeField;
    private JButton enterButton;

    public View() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setBounds(100, 100, 715, 519);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel clientsLabel = new JLabel("Enter number of clients :");
        clientsLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        clientsLabel.setBounds(10, 21, 213, 27);
        this.getContentPane().add(clientsLabel);

        clientsField = new JTextField();
        clientsField.setBounds(10, 50, 134, 27);
        this.getContentPane().add(clientsField);
        clientsField.setColumns(10);

        JLabel queueLabel = new JLabel("Enter number of queues :");
        queueLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        queueLabel.setBounds(10, 88, 213, 27);
        this.getContentPane().add(queueLabel);

        queueField = new JTextField();
        queueField.setColumns(10);
        queueField.setBounds(10, 125, 134, 27);
        this.getContentPane().add(queueField);

        JLabel simulationLabel = new JLabel("Enter the simulation time :");
        simulationLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        simulationLabel.setBounds(10, 165, 213, 27);
        this.getContentPane().add(simulationLabel);

        simulationField = new JTextField();
        simulationField.setColumns(10);
        simulationField.setBounds(10, 202, 134, 27);
        this.getContentPane().add(simulationField);

        JLabel arrivalLabel = new JLabel("Enter the min and max arrival time :");
        arrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        arrivalLabel.setBounds(10, 243, 291, 27);
        this.getContentPane().add(arrivalLabel);

        arrivalField = new JTextField();
        arrivalField.setColumns(10);
        arrivalField.setBounds(10, 280, 134, 27);
        this.getContentPane().add(arrivalField);

        JLabel serviceLabel = new JLabel("Enter the min and max service time:");
        serviceLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        serviceLabel.setBounds(10, 320, 291, 27);
        this.getContentPane().add(serviceLabel);

        serviceField = new JTextField();
        serviceField.setColumns(10);
        serviceField.setBounds(10, 357, 134, 27);
        this.getContentPane().add(serviceField);

        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        enterButton.setBounds(10, 404, 143, 43);
        this.getContentPane().add(enterButton);

        storeField = new JTextField();
        storeField.setBounds(350, 43, 281, 346);
        this.getContentPane().add(storeField);
        storeField.setColumns(10);

        this.setVisible(true);
    }
    public int getClientsField() { return Integer.parseInt(this.clientsField.getText());}
    public int getQueuesField(){return Integer.parseInt(this.queueField.getText());}
    public int getSimulationField(){ return Integer.parseInt(this.simulationField.getText());}
    public String getArrivalField(){ return this.arrivalField.getText();}
    public String getServiceField(){ return this.serviceField.getText();}
    public void setStoreField(StringBuilder s){storeField.setText(String.valueOf(s));}
    public void addEnterListener(ActionListener action) {
        this.enterButton.addActionListener(action);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
