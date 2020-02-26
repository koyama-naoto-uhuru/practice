package group_b;

public class FizzBuzz {
    public FizzBuzz() {

    }

    public String check(int i) {
        if (i % 15 == 0)
            return "check";
        if (i % 3 == 0)
            return "fizz";
        if (i % 5 == 0)
            return "buzz";
        return String.valueOf(i);
    }
}
