package com.example.conjeuguez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to allow the reading of the CSV files
 */
public class CSVFile {
    InputStream inputStream;

    /**
     * Constructor for the reader
     * @param inputStream - Input of the CSV file to be read
     */
    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    /**
     * Reads the CSV file and outputs a list of lists, with each row being contained.
     * @return - List of each row of the CSV file
     */
    public List read(){
        List resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
            }
        }
        catch (IOException ex) { throw new RuntimeException("Error in reading CSV file: "+ex); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { throw new RuntimeException("Error while closing input stream: "+e); }
        }
        return resultList;
    }
}