import io.CSVReader;
import io.CSVSplitter;
import io.CSVWriter;
import io.SortCSV;
import model.Entity;
import redactor.StringRedactor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

   SortCSV sortCSV = new SortCSV();
   sortCSV.sortAndWriteToFie("C:\\Users\\PC\\Desktop\\abcd.csv");
   sortCSV.mergeSort("C:\\Users\\PC\\Desktop\\abcd.csv");
    }
}
