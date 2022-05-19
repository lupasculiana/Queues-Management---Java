package BusinessLogic;
import Presentation.*;
import Model.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager extends Thread {
    private AtomicInteger time = new AtomicInteger();
    private int simulationTime;
    private List<Server> queues = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private List<Task> toRemove = new ArrayList<>();
    private List<Integer> peakTime = new ArrayList<>();
    private List<Integer> peakHourClients = new ArrayList<>();
    private int waitingTime;
    private int serviceTime;
    private int peakHour;
    private int peakHourClientsCompared;
    private FileData fData = new FileData();;
    private View view;

    public SimulationManager(View view, List<Task> tasks, List<Server> queues) throws IOException {
        this.tasks = tasks;
        this.queues = queues;
        this.view = view;
        fData.createFile();
    }

    public void newTask(int id, int minArrival, int maxArrival, int minService, int maxService){
        Random randomNr = new Random();
        int arrivalTime=randomNr.nextInt(minArrival,maxArrival);
        int serviceService=randomNr.nextInt(minService,maxService);
        Task newTask = new Task(id,arrivalTime,serviceService);
        tasks.add(newTask);
    }

    public Server findMinWaitingTime(){
        int min = 9000;
        Server server = null;

        for(Server s: queues)
        {
            if(s.getWaitingTime()<min)
            {
                min = s.getWaitingTime();
                server = s;
            }
        }
        waitingTime += min;
        return server;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public List<Server> getQueues() {
        return queues;
    }

    @Override
    public synchronized void run(){
        for(Server server:queues){
            server.start();
            server.setCheck(true); }
        while(time.get()<=simulationTime){
            StringBuilder string = new StringBuilder();
            string.append("Time ");
            string.append(this.time.get());
            string.append("\n");
            for(Task task :tasks)
            {
                if(task.getArrivalTime() == this.time.get())
                {
                    if(task.getArrivalTime()+task.getServiceTime() <= simulationTime)
                    {
                        Server queue = findMinWaitingTime();
                        waitingTime+=task.getServiceTime();
                        serviceTime = task.getServiceTime();
                        queue.addTask(task);
                        toRemove.add(task);
                    }
                }
            }
            tasks.removeAll(toRemove);
            string.append("Waiting clients: \n");
            if(tasks.size()==0){
               string.append("closed \n");
            }else
            {
                for(Task task :tasks)
                string.append(task.toString());
            }
            int i =0;
            for(Server server:queues){
                string.append("Queue");
                string.append(i);
                string.append(":");
                i++;
                if(!server.getTaskList().isEmpty()) {
                    for (Task task : server.getTaskList()) {
                        peakHour++;
                        string.append(task.toString());
                        string.append("\n");
                    }
                }
                else
                {
                    string.append(" closed");
                    string.append("\n");
                }
            }
            peakHourClients.add(peakHour);
            peakHour=0;
            view.setStoreField(string);
            System.out.println(string);
            try {
                fData.writeInFile(string);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.time.incrementAndGet();
            if(this.time.get()>simulationTime) {
                for (Server q : queues){
                    q.setCheck(false);
                     }
                }
            }
        peakHour=peakHourClients.get(0);
        for(int i= 0;i<peakHourClients.size();i++){
            peakHourClientsCompared=peakHourClients.get(i);
            if(peakHour<peakHourClientsCompared){
                peakHour=peakHourClientsCompared;}}
        for(int i=0 ;i<peakHourClients.size();i++){
            if(peakHourClients.get(i).compareTo(peakHour)==0) {
                peakTime.add(i);
            }
        }
        try {
            fData.writeInFile(new StringBuilder("The average waiting time is : " + (double) waitingTime / view.getClientsField() + "\n"));
            fData.writeInFile(new StringBuilder("The average service time is : " + (double) serviceTime / view.getClientsField() + "\n"));
            fData.writeInFile(new StringBuilder("The peak hour and number of clients is : " + peakTime + "," + peakHour));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
