package CDArchiveProject;


import GUI.ArchiveConsole;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordStorage {
    HashMap<Integer, String> hashMap = new HashMap<>();
    CDRecord record;
    ArchiveConsole console;

    public static void main(String[] args) {
        List<CDRecord> records = loadRecordList("records.data");
        System.out.println(records);
    }

    //<editor-fold desc="Load and Save Record Lists">
    public static List<CDRecord> loadRecordList(String filepath) {

        List<CDRecord> records = new ArrayList<>();

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataColumns = line.split(";");
                CDRecord record = new CDRecord(
                        //dataColumns[0],

                        dataColumns[1],
                        dataColumns[2],

                        dataColumns[3],
                        Integer.parseInt(dataColumns[4]),
                        Integer.parseInt(dataColumns[5]),
                        Integer.parseInt(dataColumns[6]),
                        dataColumns[7],
                        dataColumns[8].equalsIgnoreCase("Yes")
                );

                records.add(record);

            }

        } catch (Exception e) {
            System.err.println("Failed to load records:" + e.toString());
        }

        return records;
    }

    public static void saveRecordList(String filepath, List<CDRecord> records) {
        //Open File for writing


        try {
            FileWriter fr = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fr);


            //for (int i=0; i< records.size(); i++){

            //}
            for (CDRecord record : records) {


                if (record.isOnLoan()) {

                }


                bw.write(record.getTitle() + ";"
                        + record.getAuthor() + ";" + record.getSection() + ";" +
                        record.getxLocation() + ";" + record.getyLocation() + ";" +
                        record.getBarcode() + ";" + record.getDescription() + ";" + record.onLoan);
                bw.newLine();


            }
            bw.close();

        } catch (Exception e) {
            System.err.println("Failed to save records:" + e.toString());
        }


        // for each record
        // create string with member fields seperated by:
        // write line to file

        //save and close file


    }
    //</editor-fold>

    //<editor-fold desc="Simple Hash Map Save">
    public static void saveHashMap(String filepath, String textarea) {

        try {
            FileWriter fr = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fr);
            bw.write(textarea);

            bw.close();


        } catch (Exception e) {

        }

    }
    //</editor-fold>
}