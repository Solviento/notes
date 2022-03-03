/*Given a reference of a node in a connected undirected graph.
Return a deep copy (clone) of the graph.
Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
Test case format:
For simplicity, each node's value is the same as the node's
index (1-indexed). For example, the first node with val == 1,
the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
An adjacency list is a collection of unordered lists used
to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
The given node will always be the first node with val = 1.
You must return the copy of the given node as a reference to the cloned graph.

Example 1:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:
Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:
Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.*/
package com.practice.code.treesgraphs;

import com.practice.code.model.GraphNode;
import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CloneGraph implements CodeRunner {
    @Override
    public void run() {

    }

    private final HashMap<GraphNode, GraphNode> visited = new HashMap<>();

    public GraphNode cloneGraphDFS(GraphNode node) {
        if (node == null) {
            return node;
        }

        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        GraphNode cloneNode = new GraphNode(node.val, new ArrayList<>());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (GraphNode neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraphDFS(neighbor));
        }
        return cloneNode;
    }

    public GraphNode cloneGraphBFS(GraphNode node) {
        if (node == null) {
            return node;
        }

        // Hash map to save the visited node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        HashMap<GraphNode, GraphNode> visited = new HashMap();

        // Put the first node in the queue
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        // Clone the node and put it in the visited dictionary.
        visited.put(node, new GraphNode(node.val, new ArrayList<>()));

        // Start BFS traversal
        while (!queue.isEmpty()) {
            // Pop a node say "n" from the from the front of the queue.
            GraphNode n = queue.remove();
            // Iterate through all the neighbors of the node "n"
            for (GraphNode neighbor : n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // Clone the neighbor and put in the visited, if not present already
                    visited.put(neighbor, new GraphNode(neighbor.val, new ArrayList<>()));
                    // Add the newly encountered node to the queue.
                    queue.add(neighbor);
                }
                // Add the clone of the neighbor to the neighbors of the clone node "n".
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        // Return the clone of the node from visited.
        return visited.get(node);
    }
}
