package dao;

import org.example.fx.modelBDD.dao.Join;
import org.example.fx.modelBDD.dao.Player;
import org.example.fx.modelBDD.dao.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScoreTest {

    Score score;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void init(){
        score = new Score();
        score.setId(24);
        score.setFecha(LocalDateTime.now());
        score.setPlayer(30);
        score.setPuntuacion(1000);
    }



    @Test
    public void setFecha_ok(){
        assumeTrue(score != null);
        LocalDateTime nuevafecha = LocalDateTime.now();
        score.setFecha(nuevafecha);
        LocalDateTime fecha = LocalDateTime.now();
        assertThat(fecha, is(score.getFecha()));
    }

    @Test
    public void setPuntuacion_ok(){
        assumeTrue(score != null);
        int newpuntuacion = 1;
        score.setPuntuacion(newpuntuacion);
        assertAll(
                () -> assertThat(newpuntuacion, is(score.getPuntuacion())),
                () -> assertThat(1000, is(score.getPuntuacion())));
    }

    @Test
    public void setID_ok(){
        assumeTrue(score != null);
        int newId = 12;
        score.setId(newId);
        assertAll(
                () -> assertThat(newId, is(score.getId())),
                () -> assertThat(1000, is(score.getPuntuacion())));
    }

    @Test
    public void setPlayer_ok() {
        assumeTrue(score != null);
        int newPlayer = 40;
        score.setPlayer(newPlayer);
        assertThat(newPlayer, is(score.getPlayer()));
    }


    @Test
    public void scoreConstructor_ok() throws SQLException {

        Score expectedScore = Score.builder()
                .id(1)
                .player(25)
                .puntuacion(992)
                .fecha(LocalDateTime.of(2022, 5, 14, 0, 0, 0))
                .build();

        when(resultSet.getTimestamp(any())).thenReturn(Timestamp.valueOf(expectedScore.getFecha()));
        when(resultSet.getInt(any())).thenAnswer(new Answer<Integer>() {

            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(invocationOnMock.getArgument(0).equals("id")){
                    return expectedScore.getId();
                } else if(invocationOnMock.getArgument(0).equals("puntuacion")) {
                    return expectedScore.getPuntuacion();
                } else if(invocationOnMock.getArgument(0).equals("idplayer")) {
                return expectedScore.getPlayer();
                 }else{
                    return null;
                }
            }
        });

        Score actualScore = new Score(resultSet);

        assertThat(actualScore, is(expectedScore));
    }

    @Test
    public void allArgConstructor_ok(){
        assumeTrue(score != null);
        Score score1 = new Score(score.getId(), score.getPuntuacion(),  score.getFecha(),
                score.getPlayer());
       assertThat(score1, is(score1));
    }

}