//
//
//import org.hamcrest.MatcherAssert;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.stubbing.Answer;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//import java.util.Set;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class TestCityManagerImpl {
//
//    @Mock
//    private Connection connection;
//
//    @Mock
//    private Statement statement;
//
//    @Mock
//    private PreparedStatement preparedStatement;
//
//    @Mock
//    private ResultSet resultSet;
//
//    @InjectMocks
//    private CityManagerImpl cityManager;
//
//    @Test
//    void findAllCities_ok() throws SQLException {
//
//        City expectedCity = City.builder()
//                .name("Alicante")
//                .population(new BigDecimal("300000"))
//                .district("Alicante")
//                .id(10000000)
//                .build();
//
//        when(connection.createStatement()).thenReturn(statement);
//        when(statement.executeQuery(any())).thenReturn(resultSet);
//        when(resultSet.next()).thenAnswer(new Answer<Boolean>() {
//
//            private int counter = 0;
//
//            @Override
//            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
//                if(counter < 1){
//                    counter++;
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//        doReturn(expectedCity.getId()).when(resultSet).getInt(any());
//        when(resultSet.getBigDecimal(any())).thenReturn(new BigDecimal(String.valueOf(expectedCity.getPopulation())));
//        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {
//
//            @Override
//            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
//
//                if(invocationOnMock.getArgument(0).equals("name")){
//                    return expectedCity.getName();
//                } else if(invocationOnMock.getArgument(0).equals("District")) {
//                    return expectedCity.getDistrict();
//                }else{
//                    return null;
//                }
//            }
//        });
//
//        List<City> citySet = cityManager.findAll(connection);
//
//        MatcherAssert.assertThat(citySet, Matchers.hasSize(1));
//        MatcherAssert.assertThat(citySet.iterator().next(), Matchers.is(expectedCity));
//
//    }
//
//    @Test
//    void findCitiesByCountryCode_ok() throws SQLException {
//
//        City expectedCity = City.builder()
//                .name("Alicante")
//                .population(new BigDecimal("300000"))
//                .district("Alicante")
//                .id(10000000)
//                .build();
//
//        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenAnswer(new Answer<Boolean>() {
//
//            private int counter = 0;
//
//            @Override
//            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
//                if(counter < 1){
//                    counter++;
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//        doReturn(expectedCity.getId()).when(resultSet).getInt(any());
//        when(resultSet.getBigDecimal(any())).thenReturn(new BigDecimal(String.valueOf(expectedCity.getPopulation())));
//        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {
//
//            @Override
//            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
//
//                if(invocationOnMock.getArgument(0).equals("name")){
//                    return expectedCity.getName();
//                } else if(invocationOnMock.getArgument(0).equals("District")) {
//                    return expectedCity.getDistrict();
//                }else{
//                    return null;
//                }
//            }
//        });
//
//        List<City> citySet = cityManager.findById(connection,1);
//
//        MatcherAssert.assertThat(citySet, Matchers.hasSize(1));
//        MatcherAssert.assertThat(citySet.iterator().next(), Matchers.is(expectedCity));
//
//    }
//
//    @Test
//    void findCityByCountryByName_ok() throws SQLException {
//
//        City expectedCity = City.builder()
//                .name("Alicante")
//                .population(new BigDecimal("300000"))
//                .district("Alicante")
//                .country(new Country(null, null, null, null, null, new BigDecimal(300000), 10000000, new BigInteger("300000"), 0.0, 0.0, 0.0, null, null, null, 10000000))
//                .id(10000000)
//                .build();
//
//        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenAnswer(new Answer<Boolean>() {
//
//            private int counter = 0;
//
//            @Override
//            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
//                if(counter < 1){
//                    counter++;
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//        doReturn(expectedCity.getId()).when(resultSet).getInt(any());
//        when(resultSet.getBigDecimal(any())).thenReturn(new BigDecimal(String.valueOf(expectedCity.getPopulation())));
//        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {
//
//            @Override
//            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
//
//                if(invocationOnMock.getArgument(0).equals("name")){
//                    return expectedCity.getName();
//                } else if(invocationOnMock.getArgument(0).equals("District")) {
//                    return expectedCity.getDistrict();
//                }else{
//                    return null;
//                }
//            }
//        });
//
//        List<City> citySet =  cityManager.findByName(connection,"Marbella");
//
//        MatcherAssert.assertThat(citySet, Matchers.hasSize(1));
//        MatcherAssert.assertThat(citySet.iterator().next(), Matchers.is(expectedCity));
//
//    }
//
//    @Test
//    void findAllCities_ko() throws SQLException {
//
//        when(connection.createStatement()).thenThrow(new SQLException(""));
//
//        List<City> citySet = cityManager.findAll(connection);
//
//        MatcherAssert.assertThat(citySet, Matchers.nullValue());
//
//    }
//
//    @Test
//    void findCitiesByCountryCode_ko() throws SQLException {
//
//        when(connection.prepareStatement(any())).thenThrow(new SQLException("Esto es un error probocado"));
//
//        List<City> citySet = (List<City>) cityManager.findById(connection,1);
//
//        MatcherAssert.assertThat(citySet, Matchers.nullValue());
//
//    }
//}