package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDemoApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void test_Junit() {
		assertEquals(2,1+1);
	}
	@Test
	public void SuccessReadDict() {
		Wordladder w = new Wordladder();
        w.filename="smalldict1.txt";
        w.words[0]="bee";
        w.words[1]="bog";
        w.start();
        assertEquals(w.dict.size(),91);
        assertFalse(!w.dict.contains("bee"));
	}
	@Test
	public void SuccessGetLadder() {
		Wordladder w = new Wordladder();
        w.filename="smalldict1.txt";
        w.words[0]="code";
        w.words[1]="data";
        String tmp = w.start();
        assertEquals("A ladder from code back to data: data date cate cade code ",tmp);
	}
	@Test
	public void Word_isvalid1() {
		Wordladder w = new Wordladder();
        w.filename="dictionary.txt";
        w.words[0]="bee";
        w.words[1]="bog";
        w.start();
		assertFalse(!w.word_valid(w.words, w.dict));
	}
	@Test
	public void Word_isvalid2() {
		Wordladder w = new Wordladder();
        w.filename="dictionary.txt";
        w.words[0]="bee";
        w.words[1]="code";
        String tmp = w.start();
        assertFalse(w.word_valid(w.words, w.dict));
        assertEquals("The two words must be the same length.",tmp);
	}
	@Test
	public void Word_isvalid3() {
		Wordladder w = new Wordladder();
        w.filename="dictionary.txt";
        w.words[0]="bee";
        w.words[1]="cddasdasd";
        String tmp = w.start();
        assertFalse(w.word_valid(w.words, w.dict));
        assertEquals("The two words must be found in the dictionary.",tmp);
	}
}
