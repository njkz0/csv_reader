package io;

import java.io.*;
import java.util.ArrayList;

public class CSVSplitter {

    public ArrayList<String> splitCSVFileOnMBFiles(String filePathName) {
        int partCount = 1;
        StringBuilder textInSplitFile = new StringBuilder();
        String fileHead;
        String tempText;
        int countOfChar = 0;
        CSVWriter csvWriter = new CSVWriter();
        File splitFile = new File (filePathName);
        String separator = System.getProperty("file.separator");
        String dirName = System.getProperty("user.dir") + separator + "files";;
        String filePartName = dirName + separator + partCount + splitFile.getName();

        ArrayList<String> result = new ArrayList<>();
        try (FileReader fileReader = new FileReader(splitFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            if ((fileHead = bufferedReader.readLine()) != null) {
                textInSplitFile.append(fileHead);
                textInSplitFile.append("\n");
                countOfChar += fileHead.length();
            }
            while ((tempText = bufferedReader.readLine()) != null) {
                countOfChar += tempText.length();
                if (countOfChar < 1024*8) {                 //split file one on 8 KByte
                    textInSplitFile.append(tempText);
                    textInSplitFile.append("\n");
                } else {
                    csvWriter.checkAndCreateFileAndDir(dirName, filePartName);
                    csvWriter.writeTextToFile(textInSplitFile.toString(), filePartName, false);
                    result.add(filePartName);
                    partCount++;
                    filePartName = dirName + separator + partCount + splitFile.getName();
                    countOfChar = 0;
                    textInSplitFile.setLength(0);
                    textInSplitFile.append(fileHead).append("\n").append(tempText).append("\n");
                }

            }
            csvWriter.writeTextToFile(textInSplitFile.toString(), filePartName, false);
            result.add(filePartName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;      //path on split files
    }
}