package travelerapplication;

/**
 * @author Ashish Sharma, President, Blupine LLC.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import travelerapplication.structures.*;

public class TravelerApplication {
    
    
    /**
     * Input parameters are in the following format:
     * Start Node: 'C'
     * End Node: 'D'
     * Flow from C-D: 9
     * 
     * Then the input for this Flowpath is CD9.
     */
    public static void main(String[] args) {
        String input = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
        AdjMapFactory mapCreator = new AdjMapFactory(input);
        Map<Character, Vertex> graphMap = mapCreator.getGraphMap();
        TravelerApplicationController controller = new TravelerApplicationController();
        
        //Calculate flow along a specific path.
        controller.displayDistanceOfRoute(graphMap, "ABC");
        controller.displayDistanceOfRoute(graphMap, "AD");
        controller.displayDistanceOfRoute(graphMap, "ADC");
        controller.displayDistanceOfRoute(graphMap, "AEBCD");
        controller.displayDistanceOfRoute(graphMap, "AED");
        
        //Find number of stops below and up to a max
        List<String> maxRoutes = new ArrayList<>();
        controller.getTripsWithStartEndAndMaximumStops(maxRoutes, "", graphMap, 'C', 'C', 3, false);
        System.out.println("Number of routes: " + maxRoutes.size());
        
        //Find number of stops exactly at one specific max
        List<String> inclusiveRoutes = new ArrayList<>();
        controller.getTripsWithStartEndAndMaximumStops(inclusiveRoutes, "", graphMap, 'A', 'C', 4, true);
        System.out.println("Number of routes: " + inclusiveRoutes.size());
        
        //Shortest path between two Nodes #1 (Djikstra)
        char start = 'A';
        char end = 'C';
        PriorityQueue<Vertex> pq = mapCreator.getSingleSourcePriorityQueue(start);
        int minFlow = controller.getMinimumFlow(pq, end);
        System.out.println(start + "-" + end + " shortest flow is " + minFlow);
        
        //Shortest path between two Nodes #2 (Djikstra)
        start = 'B';
        end = 'B';
        pq = mapCreator.getSingleSourcePriorityQueue(start);
        minFlow = controller.getMinimumFlow(pq, end);
        System.out.println(start + "-" + end + " shortest flow is " + minFlow);
        
        //Number of paths with up to a specific flow.
        List<String> maxFlowRoutes = new ArrayList<>();
        controller.getTripsWithStartEndAndMaximumFlow(maxFlowRoutes, "", graphMap, 'C', 'C', 30, 0);
        System.out.println("Number of routes with flow less than 30: " + maxFlowRoutes.size());
    }
    
}
