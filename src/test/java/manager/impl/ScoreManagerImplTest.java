package manager.impl;

import org.example.fx.modelBDD.dao.Join;
import org.example.fx.modelBDD.dao.Score;
import org.example.fx.modelBDD.manager.impl.JoinManagerImpl;
import org.example.fx.modelBDD.manager.impl.ScoreManagerImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ScoreManagerImplTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;


    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ScoreManagerImpl scoreManager;

    @Test
    void findAllScore_ok() throws SQLException {

        Score expectedScore = Score.builder()
                .id(12)
                .player(29)
                .puntuacion(992)
                .fecha(LocalDateTime.of(2022, 5, 14, 0, 0))
                .build();

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
        when(resultSet.next()).thenAnswer(new Answer<Boolean>() {

            private int counter = 0;

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(counter < 1){
                    counter++;
                    return true;
                } else {
                    return false;
                }
            }
        });
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

        List<Score> scoreSet = scoreManager.findAll(connection);

        MatcherAssert.assertThat(scoreSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(scoreSet.iterator().next(), Matchers.is(expectedScore));

    }


    @Test
    void findAllJoin_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        List<Score> scoreset = scoreManager.findAll(connection);

        MatcherAssert.assertThat(scoreset, Matchers.nullValue());

    }

}