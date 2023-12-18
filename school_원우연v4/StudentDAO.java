package school_원우연v4;

import java.util.ArrayList;

public class StudentDAO {
	ArrayList<Student> stuList;

	StudentDAO() {
		stuList = new ArrayList<Student>();
		loadData();

	}

	public void loadData() {
		String data = Utils.loadDataFromFile("student.txt");
		String[] temp = data.split(",");
		for(int i = 0; i < temp.length; i++) {
			stuList.add(new Student(Integer.parseInt(temp[i].split("/")[0]),temp[i].split("/")[1],temp[i].split("/")[2]));

		}

	}

	public void addStudent() {
		//아이디 이름 입력받아서 학생 객체 생성
		// 1 아이디 입력 받기
		// 2. 아이디 중복확인
		// 중복이면 다시 입력받기
		// 중복 아니면 이름 입력받기
		// 3. 학생 객체를 만든다 -> 리스트에 추가하면 서 객체를 만들 수 있다(왜냐하면 어레이리스트를 쓰니까)


		String stuId = Utils.getString("아이디 입력>>");
		//아이디 중복확인
		for(int i = 0; i < stuList.size(); i++) {
			if(stuList.get(i).getStuId().equals(stuId)) {
				System.out.println("이미 존재하는 아이디입니다.");
				return;
			}
		}	
		String name = Utils.getString("이름 입력>>");
		int stuNo = stuList.size()+1000;

		//학생 객체를 리스트에 추가
		stuList.add(new Student(stuNo,name,stuId));
		System.out.println("학생이 추가되었습니다.");
		System.out.println(stuList.get(stuList.size()-1));
	}

	public void deleteStudent(SubjectDAO subDAO) {
		// 학생 아이디 입력받아서 해당 학생 삭제
		String stuId = Utils.getString("삭제할 학생 아이디 입력>>");
		int idx = -1;
		for(int i = 0; i < stuList.size(); i++) {
			if(stuList.get(i).getStuId().equals(stuId)) {
				subDAO.deleteAllSubFromOneStudent(stuList.get(i).getStuNo()); // 학생의 과목 모두 삭제
				stuList.remove(i);
				idx = i;
				System.out.println("학생이 삭제되었습니다.");
				break;
			}
		}
		if(idx == -1) {
			System.out.println("해당 학생이 존재하지 않습니다.");
			return;
		}
		
	}

	public void printAll(ArrayList<Subject> subList) {
		// 점수 오름차순 출력
		ArrayList<Student> tempstu = new ArrayList<Student>();
		for(Student student :stuList) {
			tempstu.add(student);
		}
		//평균계산
		for(int i = 0; i < stuList.size(); i++) {
			int cnt =0;
			for(int j = 0; j < subList.size(); j++) {
				if(tempstu.get(i).getStuNo() == subList.get(j).getStuNo()) {
					tempstu.get(i).setAvg(tempstu.get(i).getAvg() + subList.get(j).getScore() *1.0);
					cnt ++;
				}
			}
			if(cnt != 0) tempstu.get(i).setAvg(tempstu.get(i).getAvg()/cnt);
		}

		//평균 오름차순으로 정렬
		for(int i = 0; i < tempstu.size();i++){
			for(int j = i; j < tempstu.size();j++){
				if(tempstu.get(i).getAvg() < tempstu.get(j).getAvg()){
					Student temp = tempstu.get(i);
					tempstu.set(i, tempstu.get(j));
					tempstu.set(j, temp);
				}
			}
		}

		//학생리스트 출력
		for(int i = 0; i < tempstu.size(); i++) {
			System.out.printf("%s %n",tempstu.get(i));
			
			for(int j = 0; j < subList.size(); j++) {
				if(tempstu.get(i).getStuNo() == subList.get(j).getStuNo()) {
					System.out.printf("%s %d점 ",subList.get(j).getSubName(),subList.get(j).getScore());
				}
			}
			
			if(tempstu.get(i).getAvg() != 0.0) {
			System.out.printf("%n평균 %.2f점 %n", tempstu.get(i).getAvg() );
			}else {
				System.out.println("[ no data ]");
			}
			System.out.println("====================");
			
		}

		
		
	}





}
