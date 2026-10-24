class armstrong {
    public static void main(String[] args) {

        for (int n = 1; n <= 500; n++) {
            int temp = n;
            int sum = 0;

            while (temp > 0) {
                int digit = temp % 10;
                sum = sum + (digit * digit * digit);
                temp = temp / 10;
            }

            if (sum == n)
                System.out.println(n);
        }
    }
}