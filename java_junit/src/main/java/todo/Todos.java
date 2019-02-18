package todo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;

public class Todos {
    List<Todo> items = new ArrayList();

    public Todos(List<Todo> items) {
        this.items = items;
    }

    public String toJson() {
        return items.stream().map(Todo::toJson).collect(Collectors.toList()).toString();
    }


}
