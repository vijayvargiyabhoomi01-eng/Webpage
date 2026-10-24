import java.util.Scanner;

class Blood {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the age: ");
        int n = sc.nextInt();

        if (n < 18)
            System.out.println("Not Eligible for blood donation");
        else if (n >= 18 && n <= 65)
            System.out.println("Eligible for blood donation");
        else
            System.out.println("Not Eligible");

        sc.close();
    }
}