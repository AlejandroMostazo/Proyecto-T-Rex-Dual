import org.example.fx.controller.event.InicioController;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestInicioController {

    @Mock
    private TextField text;


    @Test
    void TestConseguirID_ok () {
        int exceptedId = 12;
        int actualId = new InicioController().conseguirID();

        when(text.getText()).thenReturn("Monsty");

        MatcherAssert.assertThat(actualId, Matchers.is(exceptedId));
    }

}


