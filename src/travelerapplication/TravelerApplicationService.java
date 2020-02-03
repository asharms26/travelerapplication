/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelerapplication;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author wjlax
 */
public class TravelerApplicationService {
    
    public Integer getDistanceFromRoute(Vertex vertex, char[] route){
        Integer flow = 0;
        Vertex source = vertex;
        for(int i = 0; i < route.length; i++){
            AdjVertex adjVertex = source.getAdjVertex(route[i]);
            if(adjVertex == null){
                return null;
            } else {
                flow += adjVertex.getFlow();
                source = adjVertex.getVertex();
            }
        }
        return flow;
    }
    
    
    public Set<Vertex> runDjikstra(PriorityQueue<Vertex> pq){
        Set<Vertex> vSet = new HashSet();
        Character rootVertex = null;
        while(!pq.isEmpty()){
            Vertex vertex = pq.poll();
            if(rootVertex == null){
                rootVertex = vertex.getLabel();
            }
            vSet.add(vertex);
            for(Map.Entry<Character, AdjVertex> entry 
                    : vertex.getAdjacentVertices().entrySet()){
                Vertex adj = entry.getValue().getVertex();
                if(adj.getLabel().equals(rootVertex)){
                    int increment = vertex.getCost() + entry.getValue().getFlow();
                    relaxRoot(adj, increment);
                } else {
                    relax(vertex, adj, entry.getValue().getFlow());
                }
                
                //Refresh the PQ..Could create a custom heap to make it faster
                if(!pq.isEmpty()){
                   pq.add(pq.remove());
                }
            }
        }
        return vSet;
    }
    private void relaxRoot(Vertex root, int flow){
        if(root.getCost() == 0 || root.getCost() > flow){
            root.setCost(flow);
        }
    }
    
    private void relax(Vertex source, Vertex dest, int flow){
        int w = source.getCost() + flow;
        if(dest.getCost() > w){
            dest.setCost(w);
        }
    }

    int getVertexFromSet(Set<Vertex> vertices, char dest) {
        for(Iterator<Vertex> iter = vertices.iterator(); iter.hasNext();){
            Vertex current = iter.next();
            if(current.getLabel().equals(dest)){
                return current.getCost();
            }
        }
        return -1;
    }
    
    
}
