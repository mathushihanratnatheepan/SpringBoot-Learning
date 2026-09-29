package Learn.io.HelloWorld;

import org.springframework.web.bind.annotation.*;

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

    //pathvariable
    @GetMapping("/{id}")
    String toDoId(@PathVariable int id) {
        return  "TODO with id" + " " + id;
    }

    //Request Param
    @GetMapping
    String toDoWithId(@RequestParam("todo") int id) {
        return  "TODO with id" + " " + id;
    }



}


