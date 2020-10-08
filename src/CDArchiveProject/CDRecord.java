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

    public CDRecord (String title, String author, String section, String description){
        this.title=title;
        this.author=author;
        this.section=section;
        this.description=description;
    }

    public String getAuthor() { return author; }

    public String getTitle() { return title; }

    public String getSection() {return section;}

    public String getDescription() { return description; }

    public boolean isOnLoan() { return onLoan; }

    public CDRecord(int barcode){
        this.barcode=barcode;

    }

    public CDRecord (int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public int getxLocation() { return xLocation; }

    public int getyLocation() { return yLocation; }

    public int getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return Integer.toString(this.barcode);
    }
}
