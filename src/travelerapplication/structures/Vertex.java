/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelerapplication;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wjlax
 */
public class Vertex{
    
    private Character label;
    private Map<Character, AdjVertex> adjacentVertices;
    private int cost;
    
    public Vertex(char c){
        this.label = c;
        adjacentVertices = new HashMap<>();
    }
    
    /**
     * @return the label
     */
    public Character getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(Character label) {
        this.label = label;
    }
    
    public void addAdjVertex(AdjVertex v){
        
        if(this.adjacentVertices == null)
            this.adjacentVertices = new HashMap<>();
        
        this.adjacentVertices.put(v.getVertex().getLabel(), v);
    }
    
    public Map<Character, AdjVertex> getAdjacentVertices(){
        return this.adjacentVertices;
    }
    
    public AdjVertex getAdjVertex(char c){
        return this.adjacentVertices.get(c);
    }
    
    public void setCost(int cost){
        this.cost = cost;
    }
    
    public int getCost(){
        return this.cost;
    }
    
    @Override
    public boolean equals(Object vertex){
        return vertex instanceof Vertex 
                && ((Vertex)vertex).getLabel().equals(this.label);
    }

}
