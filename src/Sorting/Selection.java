package Sorting;

import CDArchiveProject.CDRecord;

import java.util.ArrayList;
import java.util.List;

public class Selection {
    public static void main(String[]args)
    {
        List<CDRecord> records = new ArrayList<>();

        for (int i =0; i<20; i++) {
            int randomBarcode = (int) (Math.random() * 1000);
            records.add(new CDRecord(randomBarcode));
        }
        System.out.println("Before sort" + records.toString());

        Selection.sort(records);

        System.out.println("After sort" + records.toString());
    }

    public static void sort(List<CDRecord> records) {


        for (int index = 0; index <records.size() -1; index++) {
            int smallest_index = index;
            for (int current_Index = index+1; current_Index<records.size(); current_Index++)
                if (records.get(current_Index).getBarcode() <
                        records.get(smallest_index).getBarcode()) {
                    smallest_index=current_Index;

                }
            CDRecord smallest_Record = records.get(smallest_index);
            CDRecord index_record = records.get(index);
            records.set(index, smallest_Record);
            records.set(smallest_index, index_record);
        }


    }
}
