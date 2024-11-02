package microteam.junit;

public class MyMath {
    public static int sum(int[] numbers) {
        int sum = 0;
        for (int i: numbers) {
            sum +=i;
        }
        return sum;
    }
}
