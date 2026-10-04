package Learn.io.HelloWorld;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Todo/Get")
public class TodoController {
    @Autowired
    private ToDoService toDoService;
    @GetMapping("/users")
    String toDo() {
        toDoService.printToDo();
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

    //@RequestBody
    /*This annotation cannot be used with GetMapping
    This is basically used to send things without making them visible in the url.
     */

    @PostMapping("/create")
    String createUser(@RequestBody String body) {
        return body;
    }



}


