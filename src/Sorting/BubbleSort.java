package Sorting;

import CDArchiveProject.CDRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class BubbleSort {

    public static void main(String[] args){
        //Object[] items = null;
        List<CDRecord> records = new ArrayList<>();

        for (int i =0; i<30; i++) {
            int randomBarcode = (int) (Math.random() * 1000);
            records.add(new CDRecord(randomBarcode));
        }

        System.out.println("Before sort" + records.toString());

        BubbleSort.sort(records);

        System.out.println("After sort" + records.toString());



    }

    public static void sort(List<CDRecord> records){

        boolean swapped = true;

        // The list is sorted once i can be traversed without swapping
        while (swapped) {
            swapped =false;
            for (int i = 1; i < records.size(); i++){
                CDRecord lefty = records.get(i-1);
                CDRecord righty = records.get(i);
                //if (lefty.getBarcode() > righty.getBarcode()) {
                if (lefty.getTitle().compareTo(righty.getTitle())>0) {
                    CDRecord left = records.get(i-1);
                    CDRecord right = records.get(i);
                    records.set(i, left);
                    records.set(i-1, right);
                    swapped = true;

                }
            }
        }


    }


}
