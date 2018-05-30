package test;

import Client.*;
import IO.MyDecompressorInputStream;
import Server.*;
import algorithms.mazeGenerators.Maze;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RunCommunicateWithServers {
    public static void
    main(String[] args) {
//Initializing servers
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
//        Server solveSearchProblemServer = new Server(5401,1000,new ServerStrategySolveSearchProblem());
//Server stringReverserServer = new Server(5402, 1000, new ServerStrategyStringReverser());
// Starting  servers
//        solveSearchProblemServer.start();
        mazeGeneratingServer.start();
//stringReverserServer.start();
        //Communicating with servers
        CommunicateWithServer_MazeGenerating();
//        CommunicateWithServer_SolveSearchProblem();
//CommunicateWithServer_StringReverser();
        //Stopping all servers
        mazeGeneratingServer.stop();
//        solveSearchProblemServer.stop();
//stringReverserServer.stop();
    }

    private static void
    CommunicateWithServer_MazeGenerating() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy(){
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int[] mazeDimensions = new int[]{50, 50};
                        toServer.writeObject(mazeDimensions);
//send maze dimensions to server
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) fromServer.readObject();
//read generated maze (compressed withMyCompressor)from server
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[100000]; /*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/
//allocating byte[] for the decompressed maze -
                        is.read(decompressedMaze);
//Fill decompressedMaze with bytes
                        Maze maze = new Maze(decompressedMaze);
                        maze.print();
                    } catch
                            (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch
                (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}