package com.example.tredgate_learningapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void temp() {
        String number = "123456";
        String text = "12345678";
        int number2 = 1234;
        System.out.println(number.matches("\\d{6,6}")); ////regex: "\\d+{6,6}";
    }
}