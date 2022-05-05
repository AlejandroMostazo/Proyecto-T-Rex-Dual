package org.example.fx.event;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class MyKeyEvent implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
        System.out.println(keyEvent.getText());
    }
}