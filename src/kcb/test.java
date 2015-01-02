/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kcb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Vecker
 */
public class test {
        private String filePath;
        private ArrayList<Integer> currentOrder = new ArrayList<Integer>();
        private ArrayList<Integer> nextOrder = new ArrayList<Integer>();
        private double[][] distances;
        private ArrayList<ArrayList<String>> distance = new ArrayList<ArrayList<String>>();
        private Random random = new Random();
        private double shortestDistance = 0;
        private static int rows; 
        static double jarak = 0;
        int iteration = -1;
        double temperature = 10000.0;
        double deltaDistance = 0;
        double coolingRate = 0.9999;
        double absoluteTemperature = 0.00001;

        public void read() throws FileNotFoundException, Exception{
//            File file = new File("D:/TSP/Cities.txt");
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            String cities;
//            
//            while ((cities = reader.readLine()) != null){
//                 new SimulatedAnnealing().rows(cities.split("\n"));
//                 distances = new double[rows.length][rows.length];
//                 System.out.println(cities);
//            }
//            reader.close();
//                        distances = new double[rows.length][rows.length];
//
//            for (int i = 0; i < rows.length; i++)
//            {
//                String[] distance =  rows[i].split("\\\\s+");
//                System.out.println("ini distance: "+distance[i]);
//                for (int j = 0; j < distance.length; j++)
//                {
//                    distances[i][j] = Double.parseDouble(distance[j]);
//                    System.out.println("distances["+i+"]["+j+"] = "+ Double.parseDouble(distance[j]));
//                }
//
//                //the number of rows in this matrix represent the number of cities
//                //we are representing each city by an index from 0 to N - 1
//                //where N is the total number of cities
//                currentOrder.add(i);
//            }
//
//            if (currentOrder.size() < 1)
//                throw new Exception("No cities to order.");
//        }
            
           Scanner input = new Scanner(new File("D:/TSP/Cities.txt"));
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
            distances = new double[rows][rows];
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < rows; j++)
                {
                    distances[i][j] = Double.parseDouble(distance.get(i).get(j));
                    //System.out.println("distances["+i+"]["+j+"] = "+ Double.parseDouble(distance.get(i).get(j)));
                }

                //the number of rows in this matrix represent the number of cities
                //we are representing each city by an index from 0 to N - 1
                //where N is the total number of cities
                currentOrder.add(i);
            
            }

            if (currentOrder.size() < 1)
                throw new Exception("No cities to order.");
        }
                private double GetTotalDistance(ArrayList<Integer> order)
        {
            double distance = 0;

            for (int i = 0; i < order.size() - 1; i++)
            {
                distance += distances[order.get(i)][order.get(i+1)];
            }

//            if (order.size() > 0)
//            {
//                distance += distances[order.get(order.size()-1)][0];
//            }

            return distance;
        }
                private ArrayList<Integer> GetNextArrangement(ArrayList<Integer> order)
        {
            ArrayList<Integer> newOrder = new ArrayList<Integer>();

            for (int i = 0; i < order.size(); i++)
                newOrder.add(order.get(i));

            //we will only rearrange two cities by random
            //starting point should be always zero - so zero should not be included

            int firstRandomCityIndex = (int) Math.floor( newOrder.size()* Math.random());
            int secondRandomCityIndex = (int) Math.floor( newOrder.size()* Math.random());
            System.out.println(firstRandomCityIndex+" dan "+secondRandomCityIndex);
            int dummy = newOrder.get(firstRandomCityIndex);
            newOrder.set(firstRandomCityIndex, newOrder.get(secondRandomCityIndex));
            newOrder.set(secondRandomCityIndex, dummy);

            return newOrder;
        }
                public void Anneal() throws Exception
        {
            
            double distance = GetTotalDistance(currentOrder);
            System.out.println(distance);
            while (temperature > absoluteTemperature)
            {
                nextOrder = GetNextArrangement(currentOrder);

                deltaDistance = GetTotalDistance(nextOrder) - distance;

                //if the new order has a smaller distance
                //or if the new order has a larger distance but satisfies Boltzman condition then accept the arrangement
                if ((deltaDistance < 0) || (distance > 0 && Math.exp(-deltaDistance / temperature) > random.nextDouble()))
                {
                    for (int i = 0; i < nextOrder.size(); i++)
                        currentOrder.set(i, nextOrder.get(i));

                    distance = deltaDistance + distance;
                }

                //cool down the temperature
                temperature *= coolingRate;

                iteration++;
            }

            shortestDistance = distance;
        }
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
       {
            test x = new test();
            x.read();
            System.out.println(x.GetTotalDistance(x.currentOrder));
            System.out.println(x.GetNextArrangement(x.currentOrder));
            System.out.println(x.GetTotalDistance(x.GetNextArrangement(x.currentOrder)));
            x.Anneal();
//            double distance = x.GetTotalDistance(x.currentOrder);
//            while (x.temperature > x.absoluteTemperature)
//            {
//                x.nextOrder = x.GetNextArrangement(x.currentOrder);
//
//                x.deltaDistance = x.GetTotalDistance(x.nextOrder)- distance;
//
//                //if the new order has a smaller distance
//                //or if the new order has a larger distance but satisfies Boltzman condition then accept the arrangement
//                if ((x.deltaDistance < 0) || (distance > 0 && Math.exp(-x.deltaDistance / x.temperature) > x.random.nextDouble()))
//                {
//                    for (int i = 0; i < x.nextOrder.size(); i++)
//                        x.currentOrder.set(i, x.nextOrder.get(i));
//
//                    distance = x.deltaDistance + distance;
//                }
//                System.out.println(x.nextOrder+":"+distance);
//                //cool down the temperature
//                x.temperature *= x.coolingRate;
//
//                x.iteration++;
//            }
//           x.shortestDistance = distance;
//           System.out.println(x.shortestDistance);
    }
}
}
