//package com.manu.journalApp.service;
//
//import com.manu.journalApp.argumentsProvider.UserArgumentsProvider;
//import com.manu.journalApp.entity.User;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTests {
//
//
//    @Autowired
//    private UserService userService;
//
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Manu",
//            "Mili",
//            "Amal",
//
//    })
//    public void findByUserNameTest(String userName) {
//        assertNotNull(userService.findByUserName(userName));
//    }
//
//
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "1,2,3",
//            "2,5,7",
//            "2,6,8"
//    })
//    public void test(int a, int b, int expected) {
//        assertEquals(expected, a + b);
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentsProvider.class)
//    public void saveNewUserTest(User user) {
//        assertTrue(userService.saveNewUser(user));
//    }
//
//
//}
