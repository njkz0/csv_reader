package io;

import model.Entity;
import redactor.StringRedactor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortCSV {

    public void sortAndWriteToFie(String filePath) {      //in RAM
        CSVReader csvReader = new CSVReader();
        List<Entity> entityList = csvReader.getEntityFromCSVFile(filePath);
        Collections.sort(entityList);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeArrayListOfEntityToFile(filePath, entityList);
        boolean stopSort = false;
    }

    public void mergeSort(String filePath) {
        boolean stopSort = false;
        CSVSplitter csvSplitter = new CSVSplitter();
        CSVWriter csvWriter = new CSVWriter();
        List<String> partFilesPaths = csvSplitter.splitCSVFileOnMBFiles(filePath);     //split on files 8KBytes
        for (String s : partFilesPaths) {
            sortAndWriteToFie(s);                   //sort all files
        }
        BufferedReader bufferedReader;
        List<BufferedReader> bufferedReadersList = new ArrayList<>();
        List<Integer> idOfFiles = new ArrayList<>();
        List<String> objectsInString = new ArrayList<>();
        String tempText = "";

        csvWriter.writeTextToFile("FID;SERIAL_NUM;MEMBER_CODE;ACCT_TYPE;OPENED_DT;ACCT_RTE_CDE;REPORTING_DT;CREDIT_LIMIT", filePath, false);
        try {
            for (int i = 0; i < partFilesPaths.size(); i++) {
                bufferedReader = new BufferedReader(new FileReader(partFilesPaths.get(i)));
                bufferedReadersList.add(bufferedReader);
                bufferedReader.readLine();
                if ((tempText = bufferedReader.readLine()) != null) {
                    objectsInString.add(tempText);
                    String[] objectInString = tempText.split(";");   //add in array list on first obj from each  sorted file
                    idOfFiles.add(Integer.valueOf(objectInString[0]));
                } else bufferedReadersList.remove(i);
            }
            while (!stopSort){
                int minId=idOfFiles.get(0);
                int minIdIndex = 0;
                for (int i = 1; i < idOfFiles.size(); i++) {
                    if(idOfFiles.get(i)< minId){
                        minId=idOfFiles.get(i);
                        minIdIndex = i;
                    }
                }
                csvWriter.writeTextToFile("\n" + objectsInString.get(minIdIndex), filePath, true);
                bufferedReader = bufferedReadersList.get(minIdIndex);
                if ((tempText = bufferedReader.readLine()) != null) {
                    objectsInString.set(minIdIndex, tempText);
                    String[] objectInString = tempText.split(";");
                    idOfFiles.set(minIdIndex, Integer.valueOf(objectInString[0]));      // add in final file min obj from list
                } else {
                    bufferedReadersList.remove(minIdIndex);
                    bufferedReader.close();
                    objectsInString.remove(minIdIndex);
                    idOfFiles.remove(minIdIndex);
                    if (idOfFiles.size() == 0) stopSort = true;     //if splt file end, delete from list
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        File file;
        for(String filename: partFilesPaths){
            file = new File(filename);
            file.delete();
        }
    }


}
