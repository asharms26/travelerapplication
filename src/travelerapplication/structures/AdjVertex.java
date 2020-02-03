/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelerapplication.structures;

/**
 *
 * @author wjlax
 */
public class AdjVertex {
    private Vertex vertex;
    private int flow;
    
    public AdjVertex(Vertex v, int flow){
        this.vertex = v;
        this.flow = flow;
    }
    
    public Vertex getVertex(){
        return this.vertex;
    }
    
    public int getFlow(){
        return this.flow;
    }
}
