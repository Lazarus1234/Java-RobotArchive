import java.util.Scanner;

public class ControlFlow {



    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your Age: ");
        int i = Integer.parseInt(input.nextLine());


        if(i >=0 && i <=5)
        {
            System.out.println("Infant");
        }else if (i >=6 && i <=13){
            System.out.println("Child");
        } else if (i >=14 && i <=17){
            System.out.println("Teenager");
        }else if (i >=18 && i <=29){
            System.out.println("Adult");
        } else if (i >=30) {
            System.out.println("You are Boomer, congratulations!");
        }else{
            System.out.println("Invalid input");
        }



    }

}





