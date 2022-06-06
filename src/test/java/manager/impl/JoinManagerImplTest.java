package manager.impl;

import org.example.fx.modelBDD.manager.impl.JoinManagerImpl;
import org.example.fx.modelBDD.dao.Join;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class JoinManagerImplTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private JoinManagerImpl joinManager;

    @Test
    void findAllJoin_ok() throws SQLException {

        Join expectedJoin = Join.builder()
                .puesto(1)
                .nombre("Google")
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
        when(resultSet.getTimestamp(any())).thenReturn(Timestamp.valueOf(expectedJoin.getFecha()));
        when(resultSet.getString(any())).thenReturn(expectedJoin.getNombre());
        when(resultSet.getInt(any())).thenAnswer(new Answer<Integer>() {

            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(invocationOnMock.getArgument(0).equals("puesto")){
                    return expectedJoin.getPuesto();
                } else if(invocationOnMock.getArgument(0).equals("top")) {
                    return expectedJoin.getPuntuacion();
                }else{
                    return null;
                }
            }
        });

        List<Join> joinSet = joinManager.findAll(connection);

        MatcherAssert.assertThat(joinSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(joinSet.iterator().next(), Matchers.is(expectedJoin));

    }

    @Test
    void findJoinByCode_ok() throws SQLException {

        Join expectedJoin = Join.builder()
                .puesto(1)
                .nombre("Google")
                .puntuacion(992)
                .fecha(LocalDateTime.of(2022, 5,14, 12, 18, 29))
                .build();

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
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

        when(resultSet.getTimestamp(any())).thenReturn(Timestamp.valueOf(expectedJoin.getFecha()));
        when(resultSet.getString(any())).thenReturn(expectedJoin.getNombre());
        when(resultSet.getInt(any())).thenAnswer(new Answer<Integer>() {

        @Override
        public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
            if(invocationOnMock.getArgument(0).equals("puesto")){
                return expectedJoin.getPuesto();
            } else if(invocationOnMock.getArgument(0).equals("top")) {
                return expectedJoin.getPuntuacion();
            }else{
                return null;
            }
        }
    });

    List<Join> joinSet = joinManager.findById(connection, 12);

        MatcherAssert.assertThat(joinSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(joinSet.iterator().next(), Matchers.is(expectedJoin));
    }


    @Test
    void findAllJoin_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        List<Join> citySet = joinManager.findAll(connection);

        MatcherAssert.assertThat(citySet, Matchers.nullValue());

    }

    @Test
    void findByid_ko() throws SQLException {

        when(connection.prepareStatement(any())).thenThrow(new SQLException("Esto es un error probocado"));

        List<Join> citySet = (List<Join>) joinManager.findById(connection,12);

        MatcherAssert.assertThat(citySet, Matchers.nullValue());

    }
}