//package com.manu.journalApp.service;
//
//import com.manu.journalApp.entity.User;
//import com.manu.journalApp.repository.UserRepo;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//
//import java.util.ArrayList;
//
//
//public class UserDetailServiceImplTest {
//    @InjectMocks
//    private UserDetailServiceImpl userDetailService;
//
//    @Mock
//    private UserRepo userRepo;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void loadUserByUserName(){
//       Mockito.when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("manu").password("dhhsdkj").roles(new ArrayList<>()).build());
//      UserDetails userDetails =  userDetailService.loadUserByUsername("Ram");
//
//        Assertions.assertNotNull(userDetails);
//    }
//}
