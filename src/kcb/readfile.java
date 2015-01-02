package kcb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readfile{
// read in the data
    int size=0;
    String Path = "D:/file.txt";
    ArrayList<ArrayList<Integer>> path = new ArrayList<ArrayList<Integer>>();
    public void read() throws FileNotFoundException{
        Scanner input = new Scanner(new File(Path));
        while(input.hasNext()){
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList col = new ArrayList();
            while(colReader.hasNextInt()){
                col.add(colReader.nextInt());
            }
            path.add(col);
        }
       for(int i = 0; i < path.size(); i++) {   
        System.out.print(path.get(i)+"\n");
    }
    }
    public ArrayList<ArrayList<Integer>> getPath(){
        return path;
    }
    public int sizeOfArralist(){
        return size = path.size();
    }
    public static void main(String args[]) throws FileNotFoundException {
        new readfile().read();
       
    }
            
}