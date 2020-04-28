package domain;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
    List<Name> list;
    public TodoService(){
        System.out.println("TodoService injiziert");
    }

    public List<Name> listNames(){
        list = new ArrayList<>();
        list.add(new Name("Max"));
        list.add(new Name("Christian"));
        list.add(new Name("Martin"));
        return list;
    }
}
