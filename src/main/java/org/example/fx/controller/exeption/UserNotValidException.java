package org.example.fx.controller.exeption;

public class UserNotValidException extends Throwable {
    public UserNotValidException(String mns) {
        super(mns);
    }
    public static void printMenssage(String nombre, String contraseña) throws UserNotValidException {
        for (int i = 0; i < nombre.length(); i++) {
            if (nombre.charAt(i) == '#') {
                throw new UserNotValidException("No puedes introducir #");
            }
        }
    }
}
