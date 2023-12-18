package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import student.Student;
import subject.Subject;

public class Utils {
	private static Scanner sc = new Scanner(System.in);
	private static Random rd = new Random();
	private static final String CUR_PATH = System.getProperty("user.dir") +"\\src\\"+ Utils.class.getPackageName() + "\\";

	public static int getValue(String msg, int start, int end) {
		System.out.println("["+start+ " ~ " + end +"]" +msg);
		while(true) {
			try {
			int num = sc.nextInt();
			sc.nextLine();
			if(num < start || num > end) {
				System.out.printf("%d - %d 사이 값 입력해주세요 %n", start, end);
				continue;
			}
			return num;
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("숫자값을 입력하세요");
				break;
			}
		}
		return -1;
	}
	public static String getString(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	public static String loadDataFromFile(String filename) {
		String file = CUR_PATH + filename;
		String data = "";
		try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr) ;){
			int idx = 0;
			while(true){
				String line = br.readLine();

				if(line == null){
					break;
				}
				data += idx == 0 ? line : "," + line;
				idx++; 	
				}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
		
	}
	
	public static boolean matchStuNo(ArrayList<Student> stuList, int stuNo) {
		for(int i = 0; i < stuList.size(); i++) {
			if(stuList.get(i).getStuNo() == stuNo) {
				return true;
			}
		}
		return false;
	}
	public static int getRdScore() {
		int num = rd.nextInt(51)+50;
		return num;
	}
	public static boolean matchsubName(Subject s,ArrayList<Subject> subList) {
		for(int i = 0; i < subList.size(); i++) {
			if(subList.get(i).getSubName().equals(s.getSubName()) && subList.get(i).getStuNo() == s.getStuNo()) {
				System.out.println("해당 과목이 이미 존재합니다.");
				return true;
			}
		}
		return false;
	}
	public static void saveDataToFile(ArrayList<Student> stuList,ArrayList<Subject> subList) {
		String data = "";
		for(int i = 0; i < stuList.size(); i++) {
			data += stuList.get(i).getStuNo() + "/" + stuList.get(i).getName() + "/" + stuList.get(i).getStuId() + "\n";
		}
		data = data.substring(0, data.length() -1);
		String fileName = "student.txt";
		try(FileWriter fw = new FileWriter(CUR_PATH + fileName);){
			fw.write(data);
		} catch (IOException e) {
			System.out.println("파일저장 실패.");
			e.printStackTrace();
		}
		
		data = "";
		for(int i = 0; i < subList.size(); i++) {
			data += subList.get(i).getStuNo() + "/" + subList.get(i).getSubName() + "/" + subList.get(i).getScore() + "\n";
		}
		data = data.substring(0, data.length() -1);
		fileName = "subject.txt";
		try(FileWriter fw = new FileWriter(CUR_PATH + fileName);){
			fw.write(data);
		} catch (IOException e) {
			System.out.println("파일저장 실패.");
			e.printStackTrace();
		}
		
		System.out.println("파일 저장 완료.");
	}
	
}
