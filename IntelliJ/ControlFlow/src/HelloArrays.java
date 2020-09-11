

public class HelloArrays {

    public static void main(String[] args)
    {
        int[] myNumbers =new int[5];

        myNumbers[0]=25;
        myNumbers[1]=19;
        myNumbers[2]=1;
        myNumbers[3]=12;
        myNumbers[4]=23;

        for (int i = 0; i < myNumbers.length; i++) {
            System.out.println(myNumbers[i]);
        }
    }

}
