package test200226;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest{

    private static FizzBuzz checker;

    @BeforeAll
    static void createFizzBuzz(){
        checker = new FizzBuzz();
    }

    @Test
    void 入力１のとき(){
        assertEquals("1",checker.check(1));
    }

    @Test
    void 入力11のとき(){
        assertEquals("11", checker.check(11));
    }

    @Test
    void 入力３のときFIZZを表示(){
        assertEquals("fizz", checker.check(3));
    }

    @Test
    void 入力６のときFIZZを表示(){
        assertEquals("fizz", checker.check(6));
    }

    @Test
    void 入力１０のときBUZZを表示(){
        assertEquals("buzz", checker.check(10));
    }

    @Test
    void 入力１５の時FIZZBUZZを表示(){
        assertEquals("check", checker.check(15));
    }



}