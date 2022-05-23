package dao;

import org.example.fx.modelBDD.dao.Join;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JoinTest {

    Join join;

    @Mock
    private ResultSet resultSet;

    @BeforeAll
    public static void initAll(){
        System.out.println("========BeforeAll========");
    }

    @BeforeEach
    public void init(){
        System.out.println("========BeforeEach========");
        join = new Join();
        join.setNombre("Felipe");
        join.setFecha(new Date(2022, 05, 20));
        join.setPuesto(1);
        join.setPuntuacion(1000);
    }

    @AfterEach
    public void destroy(){
        System.out.println("========DestroyEach========");
        join = null;
    }

    @AfterAll
    public static void destroyAll(){
        System.out.println("========DestroyAll========");
    }

    @Test
    public void setFecha_ok(){
        assumeTrue(join != null);
        Date nuevafecha = new Date(122, 4, 20);
        join.setFecha(nuevafecha);
        assertEquals(new Date(122, 4, 20), join.getFecha());
    }

    @Test
    public void setPuesto_ok(){
        assumeTrue(join != null);
        String nombre = "Mesa";
        join.setNombre(nombre);
        assertAll(
                () -> assertEquals(nombre, join.getNombre()),
                () -> assertEquals(1, join.getPuesto()),
                () -> assertEquals(1000, join.getPuntuacion()));
    }

    @Test
    public void setNombre_ok(){
        assumeTrue(join != null);
        String nombre = "Mesa";
        join.setNombre(nombre);
        assertAll(
                () -> assertEquals(nombre, join.getNombre()),
                () -> assertEquals(1, join.getPuesto()),
                () -> assertEquals(1000, join.getPuntuacion()));
    }

    @Test
    public void joinConstructor_ok() throws SQLException {

        Join expectedJoin = Join.builder()
                .puesto(1)
                .nombre("Google")
                .puntuacion(992)
                .fecha(new Date(2022- 5 -14))
                .build();

        when(resultSet.getDate(any())).thenReturn(expectedJoin.getFecha());
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

        Join actualJoin = new Join(resultSet);

        MatcherAssert.assertThat(actualJoin, Matchers.is(expectedJoin));
    }

//    @Test
//    public void allArgConstructor_ok(){
//        assumeTrue(join != null);
//        Join join2 = new Join(join.getPuesto(), join.getNombre(),  join.getPuntuacion(),
//                join.getFecha());
//       assertThat(join, is(join2));
//    }

}