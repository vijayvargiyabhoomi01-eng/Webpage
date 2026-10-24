import java.util.Scanner;

class Number {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Print numbers 60 to 100");
        System.out.println("2. Print numbers divisible by 5");
        System.out.println("3. Print numbers in reverse order");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                for (int i = 60; i <= 100; i++)
                    System.out.println(i);
                break;

            case 2:
                for (int i = 60; i <= 100; i++) {
                    if (i % 5 == 0)
                        System.out.println(i);
                }
                break;

            case 3:
                for (int i = 100; i >= 60; i--)
                    System.out.println(i);
                break;

            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}