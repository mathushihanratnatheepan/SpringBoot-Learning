package Learn.io.HelloWorld;

import org.springframework.stereotype.Component;

@Component
public class ToDoRepository {
    String getAllTodos() {
        return "todos";
    }
}
