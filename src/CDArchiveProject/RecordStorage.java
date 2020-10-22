package CDArchiveProject;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RecordStorage {

    public static void main(String[] args) {
        List<CDRecord> records = loadRecordList("records.data");
        System.out.println(records);
    }

    public static List<CDRecord> loadRecordList(String filepath){

        List<CDRecord> records = new ArrayList<>();

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) !=null){
                String[] dataColumns = line.split(";");
                CDRecord record = new CDRecord(
                        //dataColumns[0],  for ID
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

    public static void saveRecordList(String filepath, List<CDRecord> records){
        //Open File for writing

        // for each record
            // create string with member fields seperated by:
            // write line to file

        //save and close file

    }
}
