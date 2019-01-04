package zoo;

public class Child implements PersonCategory {

    public int price() {
        return new Adult().price() / 2;
    }

}
