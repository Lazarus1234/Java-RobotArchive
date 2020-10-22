package CDArchiveProject;

import java.util.Arrays;
import java.util.List;

public class CDRecord {


    String title;
    String author;
    String section;
    int xLocation;
    int yLocation;
    int barcode;
    String description;
    boolean onLoan;



    public CDRecord (String title, String author, String section, int x, int y, int barcode, String description, boolean onLoan){
        this.title=title;
        this.author=author;
        this.section=section;
        this.xLocation=x;
        this.yLocation = y;
        this.barcode=barcode;
        this.description=description;
        this.onLoan=onLoan;
    }



    public CDRecord(int barcode){
        this.barcode=barcode;

    }








    public static List<CDRecord> getTestData() {
        CDRecord[] records = new CDRecord[]{
                new CDRecord("foo", "basdar","Bazr",2,0,10101,"Ablmu", false),
                new CDRecord("ro", "badsar","Bzar",1,0,10201,"Ablmu", false),
                new CDRecord("daa", "bdasar","Barz",1,1,10401,"Ablmu", false),
                new CDRecord("fsao", "bsadar","Bdar",1,1,10501,"Ablmu", false),
                new CDRecord("foas", "bdar","Baar",7,1,16001,"Ablmu", false),
                new CDRecord("foaso", "baar","Bwar",3,2,10701,"Ablmu", false),
                new CDRecord("foasdo", "bsar","Bqar",3,1,10801,"Ablmu", false),
                new CDRecord("fosado", "bar","Bdar",4,2,10501,"Ablmu", false)
        };

        return Arrays.asList(records);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSection() {
        return section;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public int getBarcode() {
        return barcode;
    }


    @Override
    public String toString() {
        return "CDRecord{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", section='" + section + '\'' +
                ", xLocation=" + xLocation +
                ", yLocation=" + yLocation +
                ", barcode=" + barcode +
                ", description='" + description + '\'' +
                ", onLoan=" + onLoan +
                '}';
    }
}
