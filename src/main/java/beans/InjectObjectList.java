package beans;

import java.util.ArrayList;
import java.util.List;

public class InjectObjectList {
    private final List<InjectObject> injectObjectList=new ArrayList<InjectObject>();

    public InjectObjectList() {
    }
    public  void addInjectObject(InjectObject obj){
        this.getInjectObjectList().add(obj);
    }
    public List<InjectObject> getInjectObjectList() {
        return injectObjectList;
    }
}
