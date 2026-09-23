package Learn.io.HelloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Todo/Get")
public class TodoController {
    @GetMapping("/users")
    String toDo() {
        return  "TODO";
    }

    @GetMapping("/allusers")
    String toDoUsers() {
        return  "TODO all users";
    }

}


