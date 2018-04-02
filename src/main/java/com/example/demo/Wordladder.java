package com.example.demo;

import java.io.*;
import java.util.*;

public class Wordladder {
	static String res;
	static PrintStream mytxt;
	public static String filename;
	public static String words[] = new String[2];//init when use localhost
	public static Set<String> dict;
	public static String start() {
		dict = new HashSet<String>(); 
		read_dict(dict);
		Queue<Stack<String>> que = new LinkedList<Stack<String>>();
		Stack<String> tempstack = new Stack<String>();
		tempstack.push(words[0]);
		que.add(tempstack);
		if(!word_valid(words,dict)) {
			return res;
		}
		ladder(que,words,dict);
		return res;
	}
	public static void log(String mes) {
		try {
			mytxt=new PrintStream("./log.txt");
			PrintStream out = System.out;
			System.setOut(mytxt);
			System.out.println("[Time:]"+new Date()+mes);
			System.setOut(out);
			System.out.println("日志保存完毕。");
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	public static void ladder(Queue<Stack<String>> que, String[] words, Set<String> dict) {
		Stack<String> tempstack = new Stack<String>();
		Set<String> used_dic = new HashSet<String>();
		used_dic.add(words[0]);
		while(!que.isEmpty()) {
			while(!tempstack.isEmpty())
				tempstack.pop();
			tempstack = que.poll();
			for(int i = 0; i < tempstack.peek().length(); i++) {
				for(char c = 'a'; c <='z'; c++) {
					if(c == tempstack.peek().charAt(i))
						continue;
					String tempword = tempstack.peek();
					StringBuilder strb = new StringBuilder(tempword);
					strb.replace(i, i+1, String.valueOf(c));
					tempword = strb.toString();//replace [i]
					
					if(used_dic.contains(tempword))
						continue;
					if(tempword.equals(words[1])) {
						res="A ladder from "+words[0]+" back to "+words[1]+": "+words[1]+" ";
						log(res);
						while(!tempstack.isEmpty()) {
							res += (tempstack.peek()+" ");
							tempstack.pop();
						}
						return;
					}
					if(dict.contains(tempword)) {
						tempstack.push(tempword);
						Stack<String> tmps = (Stack<String>) tempstack.clone();
						que.add(tmps);
						tempstack.pop();
						used_dic.add(tempword);
					}
				}
			}
		}
	}
	public static void read_dict(Set<String> dict) {
		try {
			File file = new File("src\\main\\java\\com\\example\\demo\\"+filename);
			BufferedReader bufr = new BufferedReader(new FileReader(file));
			String nextline = null;
			while((nextline = bufr.readLine()) != null) {
				dict.add(nextline);
			}
			bufr.close();
			if(dict.isEmpty()) {
				res="dict is empty.";
				log("ERROR!"+res);
				
			}
			else {
				res="dict ok";
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static boolean word_valid(String[] words, Set<String> dict) {
		if(dict.contains(words[0]) && dict.contains(words[1])) {
			if(words[0].length() != words[1].length()) {
				res="The two words must be the same length.";
				log("ERROR!"+res);
				return false;
			}
			if(words[0].equals(words[1])) {
				res="The two words must be different.";
				log("ERROR!"+res);
				return false;
			}
		}else {
			res="The two words must be found in the dictionary.";	
			log("ERROR！"+res);
			return false;
		}
		return true;
	}
}
