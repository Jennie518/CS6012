package assignment08;

import java.io.*;
import java.util.*;

public class PathFinder {
    // Constants representing the different elements in the maze
    private static final char WALL = 'X';
    private static final char START = 'S';
    private static final char GOAL = 'G';
    private static final char EMPTY = ' ';
    private static final char PATH = '.';
    /**
     * Method to solve the maze
     * @param inputFile
     * @param outputFile
     */
    public static void solveMaze(String inputFile, String outputFile) {
        // List to store the lines read from the input file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            // Read each line from the file and add to the list
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Parse the dimensions of the maze from the first line
        String[] firstLine = lines.get(0).split(" ");
        int height = Integer.parseInt(firstLine[0]);
        int width = Integer.parseInt(firstLine[1]);

        // Initialize the maze array and find the start and goal nodes
        char[][] maze = new char[height][width];
        Node startNode = null;
        Node endNode = null;
        for (int i = 0; i < height; i++) {
            maze[i] = lines.get(i + 1).toCharArray();
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == START) {
                    startNode = new Node(i, j);
                } else if (maze[i][j] == GOAL) {
                    endNode = new Node(i, j);
                }
            }
        }
        // Initialize the queue and structures for BFS
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> prev = new HashMap<>();
        Set<Node> visited = new HashSet<>();
        queue.add(startNode);
        visited.add(startNode);

        // Perform BFS to find the shortest path to the goal
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.equals(endNode)) {
                break;
            }
            // Add all unvisited neighbors to the queue
            for (Node neighbor : getNeighbors(current, maze, height, width)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }

        // Reconstruct the path by tracing back from the goal to the start
        Node current = endNode;
        while (current != null && prev.containsKey(current)) {
            Node next = prev.get(current);
            if (next != null && maze[next.x][next.y] == EMPTY) {
                maze[next.x][next.y] = PATH;
            }
            current = next;
        }
        // Write the solved maze to the output file
        try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
            output.println(height + " " + width);
            for (char[] row : maze) {
                output.println(new String(row));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to get the valid neighbors of a given node in the maze
     * @param node
     * @param maze
     * @param height
     * @param width
     * @return
     */
    private static List<Node> getNeighbors(Node node, char[][] maze, int height, int width) {
        List<Node> neighbors = new ArrayList<>();
        // Directions to move in the maze (up, down, left, right)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Check each direction from the current node
        for (int i = 0; i < dx.length; i++) {
            int newX = node.x + dx[i];
            int newY = node.y + dy[i];
            // Add the neighbor if it's not a wall and within the maze boundaries
            if (newX >= 0 && newX < height && newY >= 0 && newY < width && maze[newX][newY] != WALL) {
                neighbors.add(new Node(newX, newY));
            }
        }
        return neighbors;
    }
    /**
     * Internal class to represent a node (position) in the maze
     */
    private static class Node {
        int x, y; // Coordinates of the node in the maze
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // Override equals to compare nodes based on their coordinates
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return x == node.x && y == node.y;
        }
        // Override hashCode to compute a hash based on the node's coordinates
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
