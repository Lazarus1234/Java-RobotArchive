public class HelloJava
{
    public static void main(String[] args){
        System.out.println("Hello World");
        HelloJava myObject = new HelloJava();
        myObject.sayApple();
        myObject.sayBanana();
        myObject.sayOrange();

        House house1 = new House("Red",
                18,
                "Evergreen Terrace",
                "SringField",
                2020);


        House house2 = new House("Green",
                20,
                "Evergreen Terrace",
                "SringField",
                999999999
        );

        house1.sayDetails();
        house2.sayDetails();

    }

    public void sayApple(){
        System.out.println("Apple");
    }

    public void sayBanana(){
        System.out.println("Banana");
    }

    public void sayOrange(){
        System.out.println("Orange");
    }



}
