package Learn.io.HelloWorld;

import Learn.io.HelloWorld.models.Todo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Todo/Get")
public class TodoController {
    @Autowired
    private ToDoService toDoService;
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
    ResponseEntity <Todo> getById (@PathVariable Long id) {
        try{
            Todo createdTodo = toDoService.getById(id);
            return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<Todo>  createUser(@RequestBody Todo todo) {
         toDoService.createTodo(todo);
         return new ResponseEntity<>(toDoService.createTodo(todo), HttpStatus.CREATED);


    }





}


