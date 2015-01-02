/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kcb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vecker
 */
public class GreedySearch {
    private static int rows; 
    private String filePath;
    private ArrayList<Integer> currentOrder = new ArrayList<Integer>();
    private ArrayList<Integer> nextOrder = new ArrayList<Integer>();
    private double[][] distances;
    private ArrayList<ArrayList<String>> distance = new ArrayList<ArrayList<String>>();
    private static String path;

    //inner class Stack
    public GreedySearch() {
       
    }
    public void setPath(String a){
        path = a;
    }
    public void read() throws FileNotFoundException, Exception{
           Scanner input = new Scanner(new File(path));
            while(input.hasNext()){
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList<String> col = new ArrayList();
            while(colReader.hasNext()){
                col.add(colReader.next());
            }
            distance.add(col);
            rows++;
            }
            distance.get(0).set(0, "0.0");
            
        }
    private class Stack {
        
        private final int SIZE = rows;
        private int[] st;
        private int top;

        public Stack() {
            st = new int[SIZE+1];
            top = 0;
        }

        public void push(int j) {
            st[top++] = j;
        }

        public int pop() {
            return st[top--];
       }

        public int peek() {
            return st[top];
        }

        public boolean isEmpty() {
            return (top == 0);
        }
    }

    //inner class Node
    private class Node {

        //private double data;
        private Node next;
        private int index;
        private boolean wasVisited;

        public Node(int index) {

            wasVisited = false;
            this.index = index;
        }

    }

    private Node[] node;
    private double jarak;
    private double table[][];//table
    private int jumlahNode;//edit ini sebanyak node yang dibuat

    public void GreedySearchManual(int c) {
        jumlahNode = c;
        node = new Node[jumlahNode + 1];
        table = new double[jumlahNode + 1][jumlahNode + 1];
        for (int i = 0; i < jumlahNode; i++) {
            node[i] = new Node(i);//ini juga buat namain vertexnya
        }
        for (int i = 0; i < jumlahNode; i++) {
            for (int j = 0; j < jumlahNode; j++) {
                table[i][j] = Double.parseDouble(distance.get(i).get(j));
            }
        }
        jarak = 0;
    }

    public void greedy(int c, int d) {
        Stack theStack = new Stack();
        node[c].wasVisited = true;
        theStack.push(c);
        int temp = c;
        System.out.println("awal\ttujuan\tjarak");
        while (!theStack.isEmpty()) {
            int v = getAdjUnvisitedVertex(theStack.peek());
            if (v == -2) {
                theStack.pop();
            } else {
                System.out.print(temp + "\t");
                node[v].wasVisited = true;
                theStack.push(v);
                
                System.out.print(v + "\t");
                System.out.print(jarak + " + " + table[v][temp] + " = ");
                jarak += table[v][temp];
                System.out.print(jarak + "\t");
                System.out.println("");
                //System.out.println(node[v].index);
                temp = v;
                //bisa nambah jarak disini, seumpama x+=namaarray[v];
            }
        }
        System.out.println("\nTotal Jarak = " + jarak);
        for (int j = 0; j < jumlahNode; j++) {
            node[j].wasVisited = false;
        }
    }

    public int getAdjUnvisitedVertex(int v) {
        int temp = -1, g = 0;
        for (int j = 0; j <jumlahNode; j++) {
            if (node[j].wasVisited == false) {
                if (temp == -1) {
                    temp = (int) table[v][j];
                    g = j;
                } else if (table[v][j] < temp) {
                    temp = (int) table[v][j];
                    g = j;
                }
            }
        }
        if (temp != -1) {
            return g;
        } else {
            return -2;
        }
    }
    public void CreateGready(int a){
        
    }
    public int getRows(){
        return rows;
    }
    public static void main(String[] args) throws Exception {
        //System.out.println(new GreedySearch().distance);
        new GreedySearch().greedy(1, rows);
//        new GreedySearch().greedy(1, rows);
//        new GreedySearch().greedy(2, rows);
//        new GreedySearch().greedy(3, rows);
//        new GreedySearch().greedy(4, rows);
//        new GreedySearch().greedy(5, rows);
//        new GreedySearch().greedy(6, rows);
//        new GreedySearch().greedy(7, rows);
//        new GreedySearch().greedy(8, rows);
//        new GreedySearch().greedy(9, rows);
//        new GreedySearch().greedy(10, rows);
//        new GreedySearch().greedy(11, rows);
//        new GreedySearch().greedy(12, rows);
//        new GreedySearch().greedy(13, rows);
//        new GreedySearch().greedy(14, rows);
//        new GreedySearch().greedy(15, rows);
//        new GreedySearch().greedy(16, rows);
//        new GreedySearch().greedy(17, rows);
//        new GreedySearch().greedy(18, rows);
//        new GreedySearch().greedy(19, rows);
        
        
        
    }
}

