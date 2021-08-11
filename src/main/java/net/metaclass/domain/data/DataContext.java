package net.metaclass.domain.data;

import java.util.HashMap;
import java.util.Map;

public class DataContext {

    private Map<String, Object> map;

    public static DataContext builder() {
        return new DataContext();
    }

    private DataContext() {
        this.map = new HashMap<>();
    }

    public DataContext setData(String dataName, Object data) {
        map.put(dataName, data);
        return this;
    }

    public Map<String, Object> to() {
        return map;
    }

}
