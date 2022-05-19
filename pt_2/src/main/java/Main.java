import Presentation.*;
import Model.*;
import BusinessLogic.*;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        View view = new View();
        SimulationManager s = new SimulationManager(view,new ArrayList<>(), new ArrayList<>());
        Controller controller = new Controller(view,s);

    }
}
