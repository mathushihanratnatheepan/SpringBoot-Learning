package Learn.io.HelloWorld;
//Service cannot directly talk to DB , It communicates with repo only
//so we create an instance of repo in the service
public class ToDoService {
    private ToDoRepository toDoRepository;

    public ToDoService() {
        toDoRepository = new ToDoRepository();
    }


    public void printToDo() {
        System.out.println(toDoRepository.getAllTodos());
    }






}
