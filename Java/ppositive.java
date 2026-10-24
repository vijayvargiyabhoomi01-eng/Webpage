import java.util.Scanner;

class ppositive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        if (n > 0)
            System.out.println("This is positive.");
        else if (n < 0)
            System.out.println("This is negative.");
        else 
            System.out.println("Number is zero");
    }
    
}
