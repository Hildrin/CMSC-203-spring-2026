

import java.util.InputMismatchException;

public class CustomInputMismatchException extends InputMismatchException {
 public CustomInputMismatchException(String message) {
     super(message);
 }
}