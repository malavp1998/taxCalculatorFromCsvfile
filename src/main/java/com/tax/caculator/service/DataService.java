package com.tax.caculator.service;

import com.opencsv.CSVWriter;
import com.tax.caculator.model.Data;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataService {

    ArrayList<Data> inputData = new ArrayList<>();
    ArrayList<String> headres = null;
    Map<Integer, Integer> itemTypeMapsTaxRate = new HashMap<>();

    public DataService() {
        itemTypeMapsTaxRate.put(0, 5);
        itemTypeMapsTaxRate.put(1, 8);
        itemTypeMapsTaxRate.put(2, 18);
    }

    public DataService(ArrayList<Data> inputData, ArrayList<String> headres, Map<Integer, Integer> itemTypeMapsTaxRate) {
        this.inputData = inputData;
        this.headres = headres;
        this.itemTypeMapsTaxRate = itemTypeMapsTaxRate;
    }

    public void readCustomerData() {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/rmalav/Downloads/invoice.csv"));
            while ((line = br.readLine()) != null) {
                String[] customerData = line.split(splitBy);
                if (headres == null) {
                    headres = new ArrayList<>();
                    for (int i = 0; i < customerData.length; i++) {
                        headres.add(customerData[0]);
                    }
                } else {
                    System.out.println(customerData[0] + ", " + customerData[1] + ", " + customerData[2]);
                    inputData.add(new Data(Integer.parseInt(customerData[0]), Integer.parseInt(customerData[1]), Integer.parseInt(customerData[2]), 0));
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Data> printCustomerData() {
        return inputData;
    }

    public void calculateTax() {
        System.out.print("in service calculate tax");
        for (Data d : inputData) {
            if (itemTypeMapsTaxRate.containsKey(d.getItem_type()) == true) {

                double tax = getTaxFromData(d.getAmount(), itemTypeMapsTaxRate.get(d.getItem_type()) );
             //   double tax = (d.getAmount() * itemTypeMapsTaxRate.get(d.getItem_type())) / 100;
                d.setTax(tax);
            } else {
                d.setTax(-1);
            }
        }
    }

    public double getTaxFromData(int amount, int taxRate)
    {
        return (amount * taxRate)/100;
    }


    public void writeDataOnfile() {
        headres.add("tax");
        File file = new File("/Users/rmalav/Downloads/result.csv");
        /*try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext((String[]) headres.toArray());

            for(Data d : inputData)
            {
                writer.writeNext(new String[]{d.toString()});
            }

            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            // adding header to csv
            String[] header = {"Name", "Class", "Marks"};
            writer.writeNext(new String[]{headres.get(0), headres.get(1), headres.get(2), headres.get(3)});
            for (Data d : inputData) {
                writer.writeNext(new String[]{String.valueOf(d.getS_no()), String.valueOf(d.getAmount()), String.valueOf(d.getItem_type()), String.valueOf(d.getTax())});
            }

            // add data to csv
            //  String[] data1 = { "Aman", "10", "620" };
            //  writer.writeNext(data1);
            //  String[] data2 = { "Suraj", "10", "630" };
            //  writer.writeNext(data2);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public double add(double a, double b) {
        return a + b;
    }

}
