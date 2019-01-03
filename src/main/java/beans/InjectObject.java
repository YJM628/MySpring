package beans;

import java.util.ArrayList;
import java.util.List;

//对象里的需要注入的对象
public class InjectObject {
    private  String name;
    private  Object value;

    public InjectObject(String name) {
        this.name = name;
    }

    public InjectObject(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
