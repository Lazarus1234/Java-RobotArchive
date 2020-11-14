package Trees;
import java.util.HashMap;
import java.util.Scanner;


public class HelloHashMap {

    public static void main(String[] args) {
        int[] numbers = new int[]{10, 30, 50, 70, 90};

        System.out.println(numbers[2]);

        HashMap<String, String> fruitToColour = new HashMap<>();

        fruitToColour.put("banana", "yellow");
        fruitToColour.put("pumpkin", "orange");
        fruitToColour.put("apple", "red");
        fruitToColour.put("apple", "green");
        fruitToColour.put("tomato", "red");

        // index in an array
        // int x = numbers(index);

        //lookup in hashmap
        // String colour = fruitToColour.get("banana")
        System.out.println("Enter the name of the fruit (or q to exit):");

        Scanner input = new Scanner(System.in);
        // prompt th user to enter a fruit

        // while there is input
        while (input.hasNextLine()) {
            // take a line of input
            String fruit = input.nextLine();

            // if the input is "q" exit the loop
            if (fruit.equalsIgnoreCase("q")) break;

            // look up the colour of the input fruit
            String colour =fruitToColour.get(fruit);

            // print the colour

            System.out.println(colour);

        }
        //keys - fruits
        String[] fruit = new String[] {"banana","pumpkin","apple","apple","tomato"};
        //values - colours
        String[] colours = new String[]{"yellow","orange","red","green","red"};

        //Lookup

        String key = "pumpkin";

        for(int i=0; i<fruit.length; i++){
            if (fruit[i] == key) {
                System.out.println(colours[i]);
                break;
            }
        }


    }
}

