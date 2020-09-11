public class House {
    String colour;
    String street;
    int streetNumber;
    String state;
    int postcode;

    public House(String colour,  int streetNumber, String street, String state, int postcode){
        this.colour = colour;
        this.streetNumber = streetNumber;
        this.street = street;
        this.state = state;
        // when postcode is over 2050 it is invalid
        // invalid postcodes should be set to -1
        // If the post code is under 2050 we should use it as is
        //this.postcode = postcode;

        if (postcode >=0 && postcode <=3000) {
            this.postcode = postcode;
        }
        else {
            this.postcode = -1;
        }

    }

    public void sayDetails(){
        System.out.println("The address of this house is " +
                this.streetNumber  + " "  +  this.street  + ","  + " " +
                this.state  + ","  +" " + this.postcode + " " +
                 "and it is painted " + this.colour);
    }
}
