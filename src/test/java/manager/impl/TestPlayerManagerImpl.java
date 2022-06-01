package manager.impl;


import org.example.fx.cliente.dto.Player;
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
import java.util.List;
import org.example.fx.modelBDD.manager.impl.PlayerManagerImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TestPlayerManagerImpl {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private PlayerManagerImpl playerManager;

    @Test
    void findAllJoin_ok() throws SQLException {

        Player expectedPlayer = Player.builder()
                .id(12)
                .name("Google")
                .contraseña("google")
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
        when(resultSet.getInt(any())).thenReturn(expectedPlayer.getId());
        when(resultSet.getInt(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(invocationOnMock.getArgument(0).equals("nombre")){
                    return expectedPlayer.getName();
                } else if(invocationOnMock.getArgument(0).equals("contraseña")) {
                    return expectedPlayer.getContraseña();
                }else{
                    return null;
                }
            }
        });

        List<Player> playerList = playerManager.findAll(connection);

        MatcherAssert.assertThat(playerList, Matchers.hasSize(1));
        MatcherAssert.assertThat(playerList.iterator().next(), Matchers.is(expectedPlayer));

    }

    @Test
    void findPlayerbyName_ok() throws SQLException {

        Player expectedPlayer = Player.builder()
                .id(12)
                .name("Google")
                .contraseña("google")
                .build();

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
        when(resultSet.getInt(any())).thenReturn(expectedPlayer.getId());
        when(resultSet.getInt(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(invocationOnMock.getArgument(0).equals("nombre")){
                    return expectedPlayer.getName();
                } else if(invocationOnMock.getArgument(0).equals("contraseña")) {
                    return expectedPlayer.getContraseña();
                }else{
                    return null;
                }
            }
        });

        Player playerList = playerManager.findByName(connection, "Google");

        MatcherAssert.assertThat(playerList, Matchers.is(expectedPlayer));

    }


    @Test
    void validationPLayer_ok() throws SQLException {

        Player expectedPlayer = Player.builder()
                .id(12)
                .name("Google")
                .contraseña("google")
                .build();

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
        when(resultSet.getInt(any())).thenReturn(expectedPlayer.getId());
        when(resultSet.getInt(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(invocationOnMock.getArgument(0).equals("nombre")){
                    return expectedPlayer.getName();
                } else if(invocationOnMock.getArgument(0).equals("contraseña")) {
                    return expectedPlayer.getContraseña();
                }else{
                    return null;
                }
            }
        });

        Player playerList = playerManager.validatePlayer(connection, "Google", "google");

        MatcherAssert.assertThat(playerList, Matchers.is(expectedPlayer));

    }

    @Test
    void findAllJoin_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        List<Player> citySet = playerManager.findAll(connection);

        MatcherAssert.assertThat(citySet, Matchers.nullValue());

    }

    @Test
    void findPlayerByName_ko() throws SQLException {

        when(connection.prepareStatement(any())).thenThrow(new SQLException("Esto es un error probocado"));
        Player citySet =  playerManager.findByName(connection,"Googlw");

        MatcherAssert.assertThat(citySet, Matchers.nullValue());

    }
}