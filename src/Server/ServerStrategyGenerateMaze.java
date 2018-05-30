package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import IO.MyCompressorOutputStream;
import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            int[] size = (int[])(fromClient.readObject());
            if (size.length != 2){
                System.out.println(size.length); //CHECK CHECK CHECK!
            } else {
                AMazeGenerator mazeGenerator = new MyMazeGenerator();
                Maze maze = mazeGenerator.generate(size[0], size[1]);
                String mazeFileName = "compressedMaze.maze";
                MyCompressorOutputStream output = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
                output.write(maze.toByteArray());
                int len = output.getLen();
                output.flush();
                output.close();
                InputStream input = new FileInputStream(mazeFileName);
                byte[] savedMazeBytes = new byte[len];
                input.read(savedMazeBytes);
                input.close();
                toClient.writeObject(savedMazeBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
