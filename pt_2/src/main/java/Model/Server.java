package Model;
import Presentation.*;
import BusinessLogic.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{

    private int waitingTime = 0;
    private Task currentTask;
    private List<Task> taskList = new ArrayList<>();;
    private boolean check;

    public int getWaitingTime() {
        return waitingTime;
    }


    public List<Task> getTaskList() {
        return taskList;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void addTask(Task t){
        this.taskList.add(t);
        waitingTime = t.getServiceTime();
    }

    @Override
    public void run(){
        while(check)
        {
            if(!taskList.isEmpty()){
                currentTask = taskList.get(0);
                currentTask.setServiceTime(currentTask.getServiceTime()-1);
                waitingTime--;
                if(currentTask.getServiceTime()<1){
                    taskList.remove(currentTask);
                }
            }
            try{
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    }
