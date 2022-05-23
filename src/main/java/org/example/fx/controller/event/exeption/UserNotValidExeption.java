package org.example.fx.controller.event.exeption;

public class UserNotValidExeption extends Throwable {
    public UserNotValidExeption (String mns) {
        super(mns);
    }
    public static void PrintMenssage(String nombre, String contraseña) throws UserNotValidExeption {
        for (int i = 0; i < nombre.length(); i++) {
            if (nombre.charAt(i) == '#') {
                throw new UserNotValidExeption("No puedes introducir #");
            }
        }
    }
}
