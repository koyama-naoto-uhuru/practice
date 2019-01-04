package zoo;

public class Child implements IPersonCategory {

    public int price() {
        return new Adult().price() / 2;
    }

}
