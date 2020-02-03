/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelerapplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author wjlax
 */
public class TravelerApplicationController {
    
    TravelerApplicationService service = new TravelerApplicationService();
    private final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
    
    //Simple iteration
    public void displayDistanceOfRoute(Map<Character, Vertex> adjMap, String fullRoute){
        char[] arr = fullRoute.toCharArray();
        char[] route = Arrays.copyOfRange(arr, 1, arr.length);
        Vertex source = adjMap.get(arr[0]);
        Integer dist = this.service.getDistanceFromRoute(source, route);
        String result = NO_SUCH_ROUTE + " for Route: " + fullRoute;
        
        if(dist != null) 
            result = "Distance of route " + fullRoute + " is " + dist;
        
        System.out.println(result);
    }
        
    
    //Recursion
    public void getTripsWithStartEndAndMaximumStops(List<String> output, String pre, Map<Character, Vertex> adjMap, char start, char stop, int maxStops, boolean inclusive){
        pre += start;
        boolean isInclusiveCheck = inclusive ? pre.length() == maxStops + 1 : true;
        if(pre.length() <= maxStops + 1){
            if(start == stop && pre.length() > 1 && isInclusiveCheck){
                output.add(pre);
            }
            Vertex vertex = adjMap.get(start);
            Map<Character, AdjVertex> adjVertices = vertex.getAdjacentVertices();
            for (Map.Entry<Character,AdjVertex> entry : adjVertices.entrySet()) {
                getTripsWithStartEndAndMaximumStops(output, pre, adjMap, entry.getValue().getVertex().getLabel(), stop, maxStops, inclusive);
            }
        }      
    }
    
    //Recursion
    public void getTripsWithStartEndAndMaximumFlow(List<String> output, String pre, Map<Character, Vertex> adjMap, char start, char stop, int maxFlow, int flow){
        pre += start;
        if(flow < maxFlow){
            if(start == stop && pre.length() > 1){
                output.add(pre);
            }
            Vertex vertex = adjMap.get(start);
            Map<Character, AdjVertex> adjVertices = vertex.getAdjacentVertices();
            for (Map.Entry<Character,AdjVertex> entry : adjVertices.entrySet()) {
                getTripsWithStartEndAndMaximumFlow(output, pre, adjMap, entry.getValue().getVertex().getLabel(), stop, maxFlow, entry.getValue().getFlow() + flow);
            }
        }      
    }
    
    //Djikstra's Algorithm
    public int getMinimumFlow(PriorityQueue pq, char dest){
        int minFlow = 0;
        Set<Vertex> vertices = this.service.runDjikstra(pq); 
        return this.service.getVertexFromSet(vertices, dest);
    }
    
    
}
