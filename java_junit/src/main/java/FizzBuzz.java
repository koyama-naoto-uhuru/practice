public class FizzBuzz {
    private int inputValue;

    public FizzBuzz(int inputValue) {
        this.inputValue = inputValue;
    }

    public String execute() {
        if (inputValue == 3) {
            return "Fizz";
        }
        return String.valueOf(inputValue);
    }
}
