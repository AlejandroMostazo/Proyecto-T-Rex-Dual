package org.example.fx.controller.event.exeption;

public class UserNotValidExeption extends Throwable {
    public UserNotValidExeption (String mns) {
        super(mns);
    }
}
