package com.ace.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestSubjectTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    //此用例中使用spy的原因是我要测试的是TestSubject的methodB方法，所以需要
    //调用真实对象的methodB，此例中需要verify输入特定的i,是否能分别走进case 0,1,2,
    //methodA,C,D方法体内的东西都没法获取并证明methodA,C,D被调用过。那么就只能verify了，
    //verify只能针对mock对象，其实spy对象，也可以使用verify
    @Test
    public void testMethodB(){
        TestSubject t = new TestSubject();
        TestSubject spyT  = Mockito.spy(t);
        spyT.methodB(1);
        //assert && verify
        verify(spyT,times(1)).methodC();
    }

    //此用例中，使用mock的原因是我要测试的是TestSubject的methodB方法，
    //methodB方法中调用了methodA,C,D方法，我需要验证methodA,C,D方法是否被调用过
    @Test
    public void testMethodB2(){
        // You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList. class);

        // stubbing
        when(mockedList. get(0)).thenReturn("first");
        when(mockedList. get(1)).thenThrow(new RuntimeException());

        // following prints "first"
        System. out. println(mockedList. get(0));

        // following throws runtime exception
//        System. out. println(mockedList. get(1));

        // following prints "null" because get(999) was not stubbed
        System. out. println(mockedList. get(999));

        // Although it is possible to verify a stubbed invocation, usually it's just redundant
        // If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        // If your code doesn't care what get(0) returns, then it should not be stubbed.
        verify(mockedList).get(0);
    }

    @Test
    public void testMethodB3(){
        // You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList. class);
// stubbing using built-in anyInt() argument matcher
//        when(mockedList. get(anyInt())).thenReturn("element");

        // stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
        when(mockedList.contains(argThat(argument -> argument instanceof Integer))).thenReturn(true);


        assertFalse(mockedList.contains("element"));
        assertFalse(mockedList.contains(123L));
        assertTrue(mockedList.contains(123));
//        // following prints "element"
//        System. out. println(mockedList. get(999));
//
//        //you can also verify using an argument matcher
//        verify(mockedList).get(anyInt());
//
//        //argument matchers can also be written as Java 8 Lambdas
//        verify(mockedList).add(argThat(someString -> someString. length() > 5));
    }

}