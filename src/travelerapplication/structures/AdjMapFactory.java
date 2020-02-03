/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelerapplication;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author wjlax
 */
public class AdjMapFactory {
    
    Map<Character, Vertex> map = new HashMap();
    
    public AdjMapFactory(String input){
        this.convert(input);
    }
    
    public AdjMapFactory convert(String input){
        String[] elements = input.split(",");
        for(int i = 0; i < elements.length; i++){
            this.populateMap(elements[i]);
        }
        return this;
    }
    
    private void populateMap(String element){
        if(element.length() >= 3){
            char source = element.charAt(0);
            char dest = element.charAt(1);
            String num = "";
            for(int i = 2; i < element.length(); i++){
                num += element.charAt(i);
            }
            int flow = Integer.parseInt(num);
            this.populateMapItem(source, dest, flow);
        }
    }
    
    private void populateMapItem(char source, char dest, int flow){
        Vertex sourceVertex = new Vertex(source);
        Vertex destVertex = new Vertex(dest);
        
        if(this.map.containsKey(source)){
            sourceVertex = this.map.get(source);
        } else {
            this.map.put(source, sourceVertex);
        }
        
        if(this.map.containsKey(dest)){
            destVertex = this.map.get(dest);
        } else {
            this.map.put(dest, destVertex);
        }
        
        AdjVertex adj = new AdjVertex(destVertex, flow);
        sourceVertex.addAdjVertex(adj);
    }
    
    public Map<Character, Vertex> getGraphMap(){
        return this.map;
    }
    
    public PriorityQueue<Vertex> getSingleSourcePriorityQueue(char source){
        PriorityQueue<Vertex> pq = new PriorityQueue(new VertexCostComparator());
        for(Map.Entry<Character, Vertex> entry : this.map.entrySet()){
            if(entry.getKey().equals(source)){
                entry.getValue().setCost(0);
            } else {
                entry.getValue().setCost(1000);
            }
            pq.add(entry.getValue());
        }
        return pq;
    }
    
    public class VertexCostComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex v1, Vertex v2) {
            return v1.getCost() - v2.getCost();
        }
    }
}
