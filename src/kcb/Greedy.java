///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package kcb;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Scanner;
//
///**
// *
// * @author Vecker
// */
//public class Greedy {
//        private String filePath;
//        private ArrayList<ArrayList<Node>> Node2 = new ArrayList<ArrayList<Node>>();
//        private ArrayList<Node> Pilihan = new ArrayList<Node>();
//        private ArrayList<Node> Temp = new ArrayList<Node>();
//        private ArrayList<Node> Temp1 = new ArrayList<Node>();
//        private ArrayList<String> textWriter = new ArrayList<String>();
//        private ArrayList<ArrayList<String>> textRead = new ArrayList<ArrayList<String>>();
//        private double[][] distances;
//        private ArrayList<ArrayList<String>> jarak = new ArrayList<ArrayList<String>>();
//        private Random random = new Random();
//        private double shortestDistance = 0;
//        private static int export = 0;
//        private static int rows; 
//        int iteration = 10000;
//        double deltaDistance = 0;
//        
//        double distance;
//    class Node{
//        private String index;
//        private double jarak;
//        private boolean status;
//        private Node next;
//        
//        public Node(String i, double jarak){
//            this.index = i;
//            this.jarak = jarak;
//            this.status = true;
//        }
//        public void setStatus(boolean status){
//            this.status = status;
//        }
//        public String getIndex(){
//            return index;
//        }
//        public double getJarak(){
//            return jarak;
//        }
//        public boolean getStatus(){
//            return status;
//        }
//    }
//    public void read() throws FileNotFoundException, Exception{
//           int i =0;
//           int j =0;
//           Scanner input = new Scanner(new File("D:/file.txt"));
//            while(input.hasNext()){
//                Scanner colReader = new Scanner(input.nextLine());
//                ArrayList<String> col = new ArrayList();
//                while(colReader.hasNext()){
//                    col.add(colReader.next());   
//                }
//            jarak.add(col);
//            rows++;
//        }
//            for (int k = 0; k < rows; k++) {
//                ArrayList<Node> Node1 = new ArrayList<Node>();
//                for (int l = 0; l < rows; l++) {
//                    Node1.add(new Node(k+","+l,Double.parseDouble(jarak.get(k).get(l))));
//                }
//            Node2.add(Node1);
//        }
//        if (rows < 1)
//             throw new Exception("No cities to order.");
//        }
//    public void Greedy(int i){
//        Node2.get(i).get(i).setStatus(false);
//        Pilihan.add(Node2.get(i).get(i));
//        for (int j = 0; j < rows; j++) {
//            Node2.get(j).get(j).setStatus(false);
//        }
//        for (int j = 0; j < rows; j++) {
//            for (int k = 0; k < rows; k++) {
//                if(Node2.get(j).get(k).getJarak()<Node2.get(j).get(k+1).getJarak() && (Node2.get(j).get(k+1).getStatus() == true)){
//                   Temp.add(Node2.get(j).get(k));
//                   
//            }
//                Temp.add(Node2.get(j).get(k));                
//            }
//            for (int k = 0; k < rows-1; k++) {
//                if((Temp.get(k).jarak<Temp.get(k+1).getJarak()) && (Temp.get(k+1).getStatus() == true)){
//                    Temp1.add(Temp.get(k+1));
//                    Temp.set(k+1, Temp.get(k));
//                    Temp.get(k+1).setStatus(false);
//                } 
//                Pilihan.add(Te)
//                
//                
//            }
//            if
//        }
//    }
//    public static void main(String[] args) throws Exception {
//        Greedy x = new Greedy();
//        x.read();
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < rows; j++) {
//               System.out.println(x.Node2.get(i).get(j).getIndex()+" : "+x.Node2.get(i).get(j).jarak);
//               
//            }
//            
//        }
//        System.out.println();
//    }
//}
