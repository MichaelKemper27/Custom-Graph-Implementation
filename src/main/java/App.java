package edu.sdsu.cs.datastructures;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 */

public class App
{
    static File currFile;
    public static void  main(String args[])
    {
        System.out.println("CS310: Program 3");
        System.out.println("The Graph and Shortest Path");
        System.out.println("Please enter the file directory of the CSV you " +
                "want to graph: \n");

        Scanner scan = new Scanner(System.in);
        String input;
        input = scan.nextLine();
        if(readFile(input)){
            currFile = new File(input);
        }
        IGraph graph = new DirectedGraph<>();
        buildGraph(currFile,graph);

        Iterable graphVertecies = graph.vertices();
        System.out.println(graphVertecies);
        //Put toString method here

        /**
        System.out.println("Select a Start and End Location:\n");
        String start = "";
        String end = "";
        while(!validateVertices(graph,start,end)){
            System.out.println("Start: \n");
            start = scan.nextLine();
            System.out.println("End: \n");
            end = scan.nextLine();
        }
         */



    }

    private static boolean readFile(String path){
        try{
            File csv = new File(path);
             if(!csv.canRead()){
                 throw new IOException("Unable to read: "
                         + csv.getName() + "\n" + "Verify the file exists, is" +
                         " accessible, and meets syntax requirements");
             }
             if(csv.canRead()){
                 return true;
             }
        }catch (IOException e){
            System.exit(0);
        }
        return false;
    }

    private boolean readFile(File csv){
        try{
            if(!csv.canRead()){
                throw new IOException("Unable to read: "
                        + csv.getName() + "\n" + "Verify the file exists, is" +
                        " accessible, and meets syntax requirements");
            }
            if(csv.canRead()){
                return true;
            }
        }catch (IOException e){
            System.exit(0);
        }
        return false;
    }

    /**
     * build graph based on the input line. Read value with delimiter ","
     * if the value is "value" + ","
     * @param graph
     */
    private static void buildGraph(File file, IGraph graph){

        BufferedReader csvReader = null;
        try {
            String currLine = "";
             csvReader = new BufferedReader(new FileReader(file));

             while(currLine != null){
                 currLine = csvReader.readLine();
                 String[] lineLst = currLine.split(",");

                 //for debugging
                 for (String str: lineLst){
                     System.out.println(str);
                 }

                 for (String str: lineLst){
                     if(!graph.contains(str)){
                         System.out.println(str + " not found. Adding");
                         graph.add(str);
                     }
                 }

                 if(lineLst.length > 1){
                     System.out.println(" connecting " + lineLst[0] +
                             " and " + lineLst[1]);
                     graph.connect(lineLst[0],lineLst[1]);
                 }

             }
        }catch(Exception e){
        }
    }

    private static boolean validateVertices(IGraph graph, String start,
                                            String end){

        if(graph.contains(start) && graph.contains(end)){
            return true;
        }else if(!graph.contains(start) && graph.contains(end)){
            System.out.println(start + " Not Found, Try Again");
            return false;
        }else if(!graph.contains(end) && graph.contains(start)){
            System.out.println(end + " Not Found, Try Again");
            return false;
        }else if(!graph.contains(start) && graph.contains(end)){
            System.out.println(start + " and " + end + " Not Found Try Again");
            return false;
        }
        return false;
    }

    private static void graphPathStats(IGraph graph, String start, String end){
        List shortestPath = graph.shortestPath(start,end);

    }

}