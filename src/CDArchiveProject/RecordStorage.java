package CDArchiveProject;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class RecordStorage {

    public static void main(String[] args) {
        List<CDRecord> records = loadRecordList("records.data");
        System.out.println(records.toArray());
    }

    public static List<CDRecord> loadRecordList(String filepath){
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) !=null){
                System.out.println("Line:  " + line);

            }

        } catch (Exception e) {
            System.err.println("Failed to load records:" + e.toString());
        }


        return null;
    }

    public static void saveRecordList(String filepath, List<CDRecord> records){

    }
}
