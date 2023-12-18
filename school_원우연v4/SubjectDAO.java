package school_원우연v4;

import java.util.ArrayList;
import java.util.Vector;

public class SubjectDAO {
	public ArrayList<Subject> subList;
	int cnt;
	public void init() {
		subList = new ArrayList<Subject>();
		cnt = 0;
		loadData();
	}
	// 파일에서 읽어오기
	public void loadData() {
		String data = Utils.loadDataFromFile("subject.txt");
		String[] temp = data.split(",");
		for(int i = 0; i < temp.length; i++) {
			Subject s = new Subject(Integer.parseInt(temp[i].split("/")[0]),temp[i].split("/")[1],Integer.parseInt(temp[i].split("/")[2]) );
			subList.add(s);
			cnt++;
		}
	}
	
	public void addSub(ArrayList<Student> stuList) {
		// 학번 과목명 입력받아서 과목 객체 생성. 점수는 50~100 사이 랜덤값
		Subject s = new Subject( Utils.getValue("학번 입력>>", 1001, 9999), null, 0);
		// 학번 유효성 검사
		if(s.getStuNo() == -1) return;
		if(!Utils.matchStuNo(stuList, s.getStuNo())) {
			System.out.println("해당 학생이 존재하지 않습니다.");
			return;
		}
		// 학생 이름과 과목 출력
		printsubOnestudent(stuList, s.getStuNo());
		s.setSubName(Utils.getString("과목명 입력>>")); 
		s.setScore(Utils.getRdScore());
		// 과목 객체를 리스트에 추가
		subList.add(s);
		System.out.println("과목이 추가되었습니다.");
		printsubOnestudent(stuList, s.getStuNo());
	}

	public void deleteSub(ArrayList<Student> stuList) {
		if(stuList.size() == 0) {
			System.out.println("[no student data]");
		}
		// 학번 입력 받아서 과목이 존재하는지 확인
		int stuNo = Utils.getValue("학번 입력>>", 1001, 9999);
		if(stuNo == -1) return;
		if(!Utils.matchStuNo(stuList, stuNo)) {
			System.out.println("해당 학생이 존재하지 않습니다.");
			return;
		}
		printsubOnestudent(stuList, stuNo);
		// 과목명 입력 받아서 해당 과목 삭제
		String subName = Utils.getString("과목명 입력>>");
		for(int i = 0; i < subList.size(); i++) {
			if(subList.get(i).getStuNo() == stuNo && subList.get(i).getSubName().equals(subName)) {
				subList.remove(i);
				System.out.println("삭제되었습니다.");
				printsubOnestudent(stuList, stuNo);
				return;
			}
		}
		System.out.println("[no subject data]");
		
	}
	public void deleteAllSubFromOneStudent(int stuNo) {
		//해당 학번의 모든 과목 삭제
		for(int i = 0; i < subList.size(); i++) {
			if(subList.get(i).getStuNo() == stuNo) {
				subList.remove(i);
				return;
			}
		}
		System.out.println("[no subject data]");
		
	}
	public void printsubOnestudent(ArrayList<Student> stuList ,int stuNo) {
		for(int i = 0; i < stuList.size(); i++) {
			if(stuList.get(i).getStuNo() == stuNo) {
				System.out.println(stuList.get(i));
				//해당 학생의 과목과 점수 출력
				for(int j = 0; j < subList.size(); j++) {
					if(subList.get(j).getStuNo() == stuNo) {
						System.out.print(subList.get(j) + " ");
					}
				}
			}
		}
		System.out.println();
		System.out.println("==============================");
	
	}
	public void printSub(ArrayList<Student> stuList) {
		// 과목 이름을 입력받아서 해당과목의 모든 학생 출력(이름 오름차순)
		// 과목이름, 학생이름, 점수를 기록할 Vevtor 만들기
		Vector<String> subnames = new Vector<String>();
		Vector<String> names = new Vector<String>();
		Vector<Integer> scores = new Vector<Integer>();
		String subName = Utils.getString("과목명 입력>>");
		for(int i = 0; i < subList.size(); i++) {
			if(subList.get(i).getSubName().equals(subName)) {
				for(int j = 0; j < stuList.size(); j++) {
					if(subList.get(i).getStuNo() == stuList.get(j).getStuNo()) {
						subnames.add(subList.get(i).getSubName());
						names.add(stuList.get(j).getName());
						scores.add(subList.get(i).getScore());

					}
				}
			}
		}
		if(names.size() == 0) {
			System.out.println("[ n o  d a t a ]");
			return;
		}
		// 학생 names의 오름차순 정렬
		for(int i = 0; i < names.size()-1; i++) {
			for(int j = i+1; j < names.size(); j++) {
				if(names.get(i).compareTo(names.get(j)) > 0) {
					String temp = names.get(i);
					names.set(i, names.get(j));
					names.set(j, temp);
					
					temp = subnames.get(i);
					subnames.set(i, subnames.get(j));
					subnames.set(j, temp);
					
					int temp2 = scores.get(i);
					scores.set(i, scores.get(j));
					scores.set(j, temp2);
				}
			}
		}
		// 출력
		System.out.println("["+subName+"]");
		System.out.println("=====================");
		for(int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i) + " " + scores.get(i));
		}
	}


}
