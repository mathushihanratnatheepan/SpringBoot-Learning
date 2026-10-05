package Learn.io.HelloWorld;

import Learn.io.HelloWorld.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

//@Component
//public class ToDoRepository {
//    String getAllTodos() {
//        return "todos";
//    }
//}


public interface ToDoRepository extends JpaRepository<Todo,Long> {

}

