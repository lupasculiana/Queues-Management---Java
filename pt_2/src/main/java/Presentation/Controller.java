package Presentation;

import BusinessLogic.SimulationManager;
import Model.*;
import BusinessLogic.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private final View view;
    private final SimulationManager simulationManager;

    public Controller(View view, SimulationManager simulationManager){
        this.view = view;
        this.simulationManager = simulationManager;
        view.addEnterListener(new EnterListener());
    }

    class EnterListener implements ActionListener{
        int clientNumber;
        int queueNumber;
        int simulationTime;
        String arrivalTime;
        String[] arrival;
        int maxArrival;
        int minArrival;
        String serviceTime;
        String[] service;
        int minService;
        int maxService;

        public void actionPerformed(ActionEvent e) {
            clientNumber=view.getClientsField();
            queueNumber=view.getQueuesField();
            simulationTime=view.getSimulationField();
            arrivalTime=view.getArrivalField();
            arrival=arrivalTime.split(",");
            minArrival=Integer.parseInt(arrival[0]);
            maxArrival=Integer.parseInt(arrival[1]);
            serviceTime=view.getServiceField();
            service=serviceTime.split(",");
            minService=Integer.parseInt(service[0]);
            maxService=Integer.parseInt(service[1]);
            try {
                for (int i = 0; i < queueNumber; i++) {
                    Server queue = new Server();
                    simulationManager.getQueues().add(queue);
                }
                simulationManager.setSimulationTime(simulationTime);
                for (int i = 0; i < clientNumber; i++) {
                    simulationManager.newTask(i, minArrival, maxArrival, minService, maxService);
                }
                simulationManager.start();
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
    }
}