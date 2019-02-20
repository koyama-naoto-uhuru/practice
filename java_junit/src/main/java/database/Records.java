package database;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Records {
    public List items;

    public Records(List items) {
        this.items = items;
    }

    public Map first() {
        return (Map) items.get(0);
    }

    public int size() {
        return items.size();
    }

    public <T> List<T> mapTo(Class<T> clazz) {
        return (List<T>) items.stream().map(item -> new ObjectMapper().convertValue(item, clazz)).collect(Collectors.toList());
    }

    public <T> T firstMapTo(Class<T> clazz) {
        return new ObjectMapper().convertValue(first(), clazz);
    }
}
