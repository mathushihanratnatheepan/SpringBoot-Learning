package Learn.io.HelloWorld;
//Service cannot directly talk to DB , It communicates with repo only
//so we create an instance of repo in the service
//public class ToDoService {
//    private ToDoRepository toDoRepository;
//
//    public ToDoService() {
//        toDoRepository = new ToDoRepository();
//    }
//
//
//    public void printToDo() {
//        System.out.println(toDoRepository.getAllTodos());
//    }

import Learn.io.HelloWorld.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

//    public void printToDo() {
//        System.out.println(toDoRepository.getAllTodos());
//    }

    public Todo createTodo (Todo todo) {
        return toDoRepository.save(todo);

    }

    public Todo getById (Long id) {
        return toDoRepository.getReferenceById(id);
    }


}
