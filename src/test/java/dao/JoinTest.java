package dao;

import org.example.fx.modelBDD.dao.Join;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.Matchers.is;
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
        join.setFecha(LocalDateTime.now());
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
        LocalDateTime nuevafecha = LocalDateTime.now();
        join.setFecha(nuevafecha);
        assertThat(new Date(122, 4, 20), is(join.getFecha()));
    }

    @Test
    public void setPuesto_ok(){
        assumeTrue(join != null);
        int newpuesto = 1;
        join.setPuesto(newpuesto);
        assertAll(
                () -> assertThat(newpuesto, is(join.getPuesto())),
                () -> assertThat(1000, is(join.getPuntuacion())));
    }

    @Test
    public void setNombre_ok(){
        assumeTrue(join != null);
        String nombre = "Mesa";
        join.setNombre(nombre);
        assertAll(
                () -> assertThat(nombre, is(join.getNombre())),
                () -> assertThat(1, is(join.getPuesto())),
                () -> assertThat(1000, is(join.getPuntuacion())));
    }

    @Test
    public void joinConstructor_ok() throws SQLException {

        Join expectedJoin = Join.builder()
                .puesto(1)
                .nombre("Google")
                .puntuacion(992)
                .fecha(LocalDateTime.of(2022, 5, 14, 0, 0, 0))
                .build();

        when(resultSet.getDate(any())).thenReturn((Date) java.util.Date.from(expectedJoin.getFecha().atZone(ZoneId.systemDefault()).toInstant()));
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

        assertThat(actualJoin, is(expectedJoin));
    }

    @Test
    public void allArgConstructor_ok(){
        assumeTrue(join != null);
        Join join2 = new Join(join.getPuesto(), join.getNombre(),  join.getPuntuacion(),
                join.getFecha());
       assertThat(join, is(join2));
    }

}