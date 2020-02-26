public class FizzBuzz {
    public String fizzbuzz(int i) {
        String str = String.valueOf(i);
        if(i % 3 == 0) {
            str += "fizz";
        }
        if(i % 5 == 0){
            str += "buzz";
        }else {
            return String.valueOf(i);
        }
        return str;
    }
}
