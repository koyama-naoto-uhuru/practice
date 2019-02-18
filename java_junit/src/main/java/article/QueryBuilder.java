package article;

import java.util.ArrayList;
import java.util.List;

class QueryBuilder {
    private List<String> list = new ArrayList();

    QueryBuilder like(String key, String value) {
        if (value.equals("")) return this;
        list.add(key + " like '%" + value + "%'");
        return this;
    }

    QueryBuilder where(String key, String value) {
        list.add(key + " = " + value);
        return this;
    }

    public String build() {
        String where = "";
        if (list.size() != 0) {
            where = " where " + String.join(" and ", list) + ";";
        }
        return where;
    }


}
