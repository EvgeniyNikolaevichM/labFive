package MyExeptions;
public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String name) {
        super("No this model in array " + name);
    }
}
