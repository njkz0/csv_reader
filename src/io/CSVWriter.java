package io;

import model.Entity;
import redactor.StringRedactor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {

    public void checkAndCreateFileAndDir(String fileDir, String fileName) {
        File file = new File(fileDir);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeTextToFile(String text, String filePath, boolean append) {
        try (FileWriter writer = new FileWriter(filePath, append);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(text);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int writeArrayListOfEntityToFile(String filePath, List<Entity> List) {
        String headOfFile = "FID;SERIAL_NUM;MEMBER_CODE;ACCT_TYPE;OPENED_DT;ACCT_RTE_CDE;REPORTING_DT;CREDIT_LIMIT";
        StringBuilder textInFile = new StringBuilder();
        int count=0;
        try (FileWriter writer = new FileWriter(filePath, false);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(headOfFile);
            for (Entity e: List) {
                bufferedWriter.write("\n" + e.toString());
                count++;
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}