/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kcb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Vecker
 */
public class SimulatedAnnealing{
        private String filePath;
        private ArrayList<Integer> currentOrder = new ArrayList<Integer>();
        private ArrayList<Integer> nextOrder = new ArrayList<Integer>();
        private double[][] distances;
        private ArrayList<ArrayList<String>> distance = new ArrayList<ArrayList<String>>();
        private ArrayList<String> textWriter = new ArrayList<String>();
        private ArrayList<ArrayList<String>> textRead = new ArrayList<ArrayList<String>>();
        private Random random = new Random();
        private double shortestDistance = 0;
        private static int rows; 
        static double jarak = 0;
        private static int export = 0;
        int iteration = 400;
        double temperature = 10000.0;
        double deltaDistance = 0;
        double coolingRate = 0.999;
        double absoluteTemperature = 0.00001;
        
        public SimulatedAnnealing(){
        }
        public void setPath(String path){
            this.filePath = path;
        }
        public void read() throws FileNotFoundException, Exception{
           
           Scanner input = new Scanner(new File(filePath));
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

            if (order.size() > 0)
            {
                distance += distances[order.get(order.size()-1)][0];
            }

            return distance;
        }
        public void exportToExcel() throws FileNotFoundException, IOException{
        String []data = new String[iteration];
        String []best = new String[iteration];
        String nomor;
//        int i,j = 0;
            Scanner input = new Scanner(new File("simulated_annealing.txt"));
            while(input.hasNext()){
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList<String> col = new ArrayList();
            
            while(colReader.hasNext()){
                col.add(colReader.next());
//                col.get(i);
//                hasil[j][i] = data[i].split("\t");
//                i++;
            }
            textRead.add(col);
            export++;
            }
//        File file = new File("random_search.txt"); 
//             BufferedReader reader = new BufferedReader(new FileReader(file));
//             String cities;
//             String [] row;
//             String []data = new String[iteration];
//             String [][] hasil = new String[iteration][4];
//             String[] distance;
//             int i = 0;
//            while ((cities = reader.readLine()) != null){
//                 row = cities.split("\n");
//                 for (int j = 0; j < iteration; j++) {
//                    data= row[1].split("\t");
//                    
//                         hasil[j][0] = data[0];
//                         
//                     
//    
//                }
//                
////                 data[i][1] = row[1];
////                 data[i][2] = row[2];
////                 data[i][3] = row[3];
//                // System.out.println("array :"+ data[i][0]);
//                 i++;
//                 //distances = new double[rows.length][rows.length];
//                 //System.out.println(cities);
//        }
//            reader.close();
                    HSSFWorkbook hwb = new HSSFWorkbook();
                    HSSFSheet hsf = hwb.createSheet("Simulated Annealing");
                    Map<String, Object[]> excel = new TreeMap<String, Object[]>();
                    excel.put("0", new Object[] {"Nomor","Rute Perjalanan","Jarak","Peluang","Random","Best Rute","Best Jarak","Keputusan"});
                    for (int j = 0; j < export; j++) {
                        data[j]="";
                        best[j]="";
                        for (int i = 1; i <= rows; i++) {
                            data[j] += textRead.get(j).get(i);
                            best[j] += textRead.get(j).get(rows+3+i);
                        }
                        
                        if(j<10){
                            nomor = "00000"+j+1;
                        } else if(j>=10 && j<100){
                            nomor = "0000"+j+1;
                        } else if(j>=100 && j<1000){
                            nomor = "000"+j+1;
                        } else if(j>=1000 && j<10000){
                            nomor = "00"+j+1;
                        } else if(j>=10000 && j<100000){
                            nomor = "0"+j+1;
                        }
                        else{
                            nomor = ""+j+1;
                        }
                    excel.put(nomor, new Object[] {Integer.parseInt(textRead.get(j).get(0)),data[j],Double.parseDouble(textRead.get(j).get(rows+1)),textRead.get(j).get(rows+2),textRead.get(j).get(rows+3),best[j],Double.parseDouble(textRead.get(j).get(2*rows+4)),textRead.get(j).get(2*rows+5)});
                        //System.out.print(textRead.get(i).get(0));
                        //System.out.println("");                    
                }
                    Set<String> setkey = excel.keySet();
				int brsnum = 0;
                                Sheet x;
                               
				for(String key : setkey)
				{
					Row r = hsf.createRow(brsnum++);
					Object[] objarr = excel.get(key);
					int klmnum = 0;
					for(Object obj : objarr)
					{
						Cell c = r.createCell(klmnum++);
						if(obj instanceof String)
							c.setCellValue((String)obj);
                                                else if(obj instanceof Integer)
								c.setCellValue((Integer)obj);
                                                else if(obj instanceof Double)
								c.setCellValue((Double)obj);
					}
					
				}
				try
				{
					FileOutputStream fos = new FileOutputStream(new File("Simulated_Annealing.xls"));
					hwb.write(fos);
					fos.close();
					JOptionPane.showMessageDialog(null, "Data berhasil diexsport ke Excel","Pesan",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/exportExcel/berhasil.png"));
				}
				catch(Exception ex)
				{
					System.out.println(ex);
                                        JOptionPane.showMessageDialog(null, "Maaf terjadi kesalahan dalam export file to excel","Pesan",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/exportExcel/berhasil.png"));
				}

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
            
            read();
            main x = new main();
            int k = 1;
            int nomor= k+1;
            String Path = "simulated_annealing.txt";
            Writer output = new BufferedWriter(new FileWriter(Path));
            double distance = GetTotalDistance(currentOrder);
            textWriter.add("1\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\t-\t-\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\tditerima");
            
            x.setSimulated("1\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\t-\t-\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\tditerima\n");
            output.write(textWriter.get(0)+"\r\n");
            //System.out.println(distance);
            while (temperature > absoluteTemperature)
            {
                nextOrder = GetNextArrangement(currentOrder);

                deltaDistance = GetTotalDistance(nextOrder) - distance;
                double peluang = Math.exp(-deltaDistance / temperature);
                double bilanganRandom = random.nextDouble();
                //if the new order has a smaller distance
                //or if the new order has a larger distance but satisfies Boltzman condition then accept the arrangement
                if ((deltaDistance < 0))
                {
                    for (int i = 0; i < nextOrder.size(); i++)
                        currentOrder.set(i, nextOrder.get(i)); //swap the best
                    distance = deltaDistance + distance;
                    
                    textWriter.add(""+nomor+"\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\t-\t-\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\tditerima");
                    x.setSimulated(""+nomor+"\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\t-\t-\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\tditerima\n");
                    nomor++;
                }else if((distance > 0 && peluang > bilanganRandom)){
                    for (int i = 0; i < nextOrder.size(); i++)
                        currentOrder.set(i, nextOrder.get(i)); //swap the best
                    distance = deltaDistance + distance;
                    
                    textWriter.add(""+nomor+"\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\t"+peluang+"\t"+bilanganRandom+"\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\tditerima");
                    x.setSimulated(""+nomor+"\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\t"+peluang+"\t"+bilanganRandom+"\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\tditerima\n");
                    nomor++;
                }
                else{
                    textWriter.add(""+nomor+"\t"+nextOrder+"\t"+GetTotalDistance(nextOrder)+"\t"+peluang+"\t"+bilanganRandom+"\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\tditolak");
                    x.setSimulated(""+nomor+"\t"+nextOrder+"\t"+GetTotalDistance(nextOrder)+"\t"+peluang+"\t"+bilanganRandom+"\t"+currentOrder+"\t"+GetTotalDistance(currentOrder)+"\tditolak");
                    nomor++;
                }
                System.out.println(currentOrder+" : "+distance);
                //cool down the temperature
                temperature *= coolingRate;
                output.write(textWriter.get(k)+"\r\n");
                iteration++;
                k++;
            }
            output.close();
            shortestDistance = distance;
        }
               
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
       {
            SimulatedAnnealing x = new SimulatedAnnealing();
//            x.read();
//            System.out.println(x.GetTotalDistance(x.currentOrder));
//            System.out.println(x.GetNextArrangement(x.currentOrder));
//            System.out.println(x.GetTotalDistance(x.GetNextArrangement(x.currentOrder)));
            x.setPath("D:/TSP/cities.txt");
            x.Anneal();
//            x.writeTxt();
            x.exportToExcel();
            
    }
}
}
