import org.example.fx.modelBDD.dao.Join;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JoinTest {

    @Mock
    private ResultSet resultSet;

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

}