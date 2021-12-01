package io;

import model.Entity;
import redactor.StringRedactor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public String readFromFile(String fileName) {
        StringBuilder result = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String tempText = "";
            while ((tempText = bufferedReader.readLine()) != null) {
                result.append(tempText).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public List<Entity> getEntityFromCSVFile(String filePath) {
        String text = readFromFile(filePath);
        String[] nameOfFields = new String[0];
        List<Entity> entities = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try (StringReader stringReader = new StringReader(text);
             BufferedReader bufferedReader = new BufferedReader(stringReader)) {
            String tempText = "";
            if ((tempText = bufferedReader.readLine()) != null) {
                nameOfFields = tempText.split(";");
            }

            while ((tempText = bufferedReader.readLine()) != null) {
                if (tempText.contains("”")) tempText = StringRedactor.removeQuotesFromString(tempText);   //if obj have ";" in "", delete " and change ; on ,
                String[] entityFields = tempText.split(";");
                Entity entity = new Entity();
                for (int i = 0; i < nameOfFields.length; i++) {
                    switch (nameOfFields[i]) {
                        case "FID":
                            entity.setFid(Integer.valueOf(entityFields[i]));
                            break;
                        case "SERIAL_NUM":
                            entity.setSerialNumber(Integer.valueOf(entityFields[i]));
                            break;
                        case "MEMBER_CODE":
                            entity.setMemberCode(entityFields[i]);
                            break;
                        case "ACCT_TYPE":
                            entity.setAcctType(Integer.valueOf(entityFields[i]));
                            break;
                        case "OPENED_DT":
                            entity.setOpenedDate(LocalDate.parse(entityFields[i], formatter));
                            break;
                        case "ACCT_RTE_CDE":
                            entity.setAcctRteCde(Integer.valueOf(entityFields[i]));
                            break;
                        case "REPORTING_DT":
                            entity.setReportingDate(LocalDate.parse(entityFields[i], formatter));
                            break;
                        case "CREDIT_LIMIT":
                            entity.setCreditLimit(Integer.valueOf(entityFields[i]));
                            break;
                    }
                }
                entities.add(entity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return entities;
    }



}
//FID;SERIAL_NUM;MEMBER_CODE;ACCT_TYPE;OPENED_DT;ACCT_RTE_CDE;REPORTING_DT;CREDIT_LIMIT
//
//2000;2202099;4B01GG000001;9;04.06.2014;0;14.10.2014;100000
