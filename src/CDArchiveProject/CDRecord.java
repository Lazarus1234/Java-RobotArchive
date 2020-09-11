package CDArchiveProject;

public class CDRecord {


    String title;
    String author;
    String section;
    int xLocation;
    int yLocation;
    int barcode;
    String description;
    boolean onLoan;


    public CDRecord(int barcode){
        this.barcode=barcode;

    }

    public int getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return Integer.toString(this.barcode);
    }
}
