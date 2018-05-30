package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private HashMap<byte[], String> mazesHistory = new HashMap<>();


    private Solution solveWithAlgorithm(String s, SearchableMaze sMaze) {
        if (s.equals("BestFirstSearch")) {
            ASearchingAlgorithm BestFS = new BestFirstSearch();
            return BestFS.solve(sMaze);
        } else if (s.equals("BreadthFirstSearch")) {
            ASearchingAlgorithm BreadthFS = new BreadthFirstSearch();
            return BreadthFS.solve(sMaze);
        } else {
            ASearchingAlgorithm DepthFS = new DepthFirstSearch();
            return DepthFS.solve(sMaze);
        }
    }


    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        Properties properties = new Properties();
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            Maze maze = (Maze) fromClient.readObject();
            byte[] data = maze.toByteArray();
            String path = System.getProperty("java.io.tmpdir");
            String solvedMazesPath = path + "\\solvedMazes";
            File file = new File(solvedMazesPath);
            if (file.exists()) {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(solvedMazesPath));
                if (mazesHistory.isEmpty()) {
                    mazesHistory = (HashMap) input.readObject();
                }
                input.close();
            }
            Solution sol = null;
            if (mazesHistory.containsKey(data)) {
                SearchableMaze sMaze = new SearchableMaze(maze);
                InputStream input = new FileInputStream("Resources/config.properties");
                properties.load(input);
                String search = properties.getProperty("Search Algorithm");
                sol = solveWithAlgorithm(search, sMaze);
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String mazeSolution = path + "\\mazeSolution";
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(mazeSolution + mazesHistory.size()));
                output.writeObject(sol);
                ObjectOutputStream solvedMazesFile = new ObjectOutputStream(new FileOutputStream(new File(solvedMazesPath)));
                mazesHistory.put(data, "Solution" + mazesHistory.size());
                solvedMazesFile.writeObject(mazesHistory);
                solvedMazesFile.flush();
                solvedMazesFile.close();
            } else {
                String s = mazesHistory.get(data);
                ObjectInputStream inputData = new ObjectInputStream(new FileInputStream(path + "\\" + s));
                sol = (Solution) inputData.readObject();
                inputData.close();
            }
            toClient.writeObject(sol);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
