package MyExeptions;
public class DuplicateModelNameException  extends Exception {
    public DuplicateModelNameException(String name){
        super("Model" + name + "Duplicate");
    }
}
