package todo;

import database.DataBase;
import database.Records;

import java.util.Map;

public class TodoController {

    private DataBase dataBase = new DataBase();

    public String create(Map<String, String> params) {
        dataBase.execute("insert into todos (title) values('" + params.get("title") + "')");
        return "created";
    }

    public String show(int id) {
        Records records = dataBase.find("select * from todos where id = " + id + ";");
        return records.firstMapTo(Todo.class).toJson();
    }

    public String index() {
        Records records = dataBase.find("select * from todos;");
        return new Todos(records.mapTo(Todo.class)).toJson();
    }

}
