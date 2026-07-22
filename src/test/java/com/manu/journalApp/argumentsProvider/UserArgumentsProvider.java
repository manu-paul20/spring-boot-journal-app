//package com.manu.journalApp.argumentsProvider;
//
//
//import com.manu.journalApp.entity.User;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//
//import java.util.stream.Stream;
//
//public class UserArgumentsProvider implements ArgumentsProvider {
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
//        return Stream.of(
//                Arguments.of(User.builder().userName("Rahul").password("ab").build()),
//                Arguments.of(User.builder().userName("Shayam").password("ab").build()),
//                Arguments.of(User.builder().userName("Rekha").password("ab").build())
//        );
//
//    }
//}
