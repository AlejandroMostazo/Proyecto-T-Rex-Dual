//package dao;
//
//import org.example.fx.modelBDD.dao.Join;
//import org.example.fx.modelBDD.dao.Player;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.stubbing.Answer;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Base64;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assumptions.assumeTrue;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class PlayerTest {
//
//    Player player;
//
//    @Mock
//    private ResultSet resultSet;
//
//    @BeforeAll
//    public static void initAll(){
//        System.out.println("========BeforeAll========");
//    }
//
//    @BeforeEach
//    public void init(){
//        System.out.println("========BeforeEach========");
//        player = new Player();
//        player.setId(12);
//        String password = "hola";
//        String encodedString = Base64.getEncoder().encodeToString(password.getBytes());
//        player.setContraseña(encodedString);
//        player.setName("Monsty");
//    }
//
//    @AfterEach
//    public void destroy(){
//        System.out.println("========DestroyEach========");
//        player = null;
//    }
//
//    @AfterAll
//    public static void destroyAll(){
//        System.out.println("========DestroyAll========");
//    }
//
//    @Test
//    public void setNombrePlayer_ok(){
//        assumeTrue(player != null);
//        String newnombre = "hernesto";
//        player.setName(newnombre);
//        assertThat(newnombre, is(player.getName()));
//    }
//
//    @Test
//    public void setID_ok(){
//        assumeTrue(player != null);
//        int id = 1;
//        player.setId(id);
//        assertAll(
//                () -> assertThat(id, is(player.getId())));
//    }
//
//    @Test
//    public void setContraseña(){
//        assumeTrue(player != null);
//        String contraseña = "321";
//        String encodedString = Base64.getEncoder().encodeToString(contraseña.getBytes());
//        player.setContraseña(encodedString);
//        assertAll(
//                () -> assertThat(encodedString, is(player.getContraseña())));
//    }
//
//    @Test
//    public void playerConstructor_ok() throws SQLException {
//
//        Player expectedPlayer = Player.builder()
//                .id(2)
//                .name("Juanito")
//                .contraseña("4321")
//                .build();
//
//        when(resultSet.getInt(any())).thenReturn(expectedPlayer.getId());
//        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {
//
//            @Override
//            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
//                if(invocationOnMock.getArgument(0).equals("nombre")){
//                    return expectedPlayer.getName();
//                } else if(invocationOnMock.getArgument(0).equals("contraseña")) {
//                    return expectedPlayer.getContraseña();
//                }else{
//                    return null;
//                }
//            }
//        });
//
//        Player actualPlayer = new Player(resultSet);
//
//        assertThat(actualPlayer, is(expectedPlayer));
//    }
//
//    @Test
//    public void allArgConstructor_ok(){
//        assumeTrue(player != null);
//        Player player1 = new Player(player.getId(), player.getName(),  player.getContraseña());
//       assertThat(player, is(player1));
//    }
//
//}