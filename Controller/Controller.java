package Controller;

import student.StudentDAO;
import subject.SubjectDAO;
import utils.Utils;

/*
 * 
 *  무조건 파일 업로드 먼저
 *  
 *  처음부터 데이터가 연결된 상태여야 한다.
 */
public class Controller {
	public StudentDAO stuDAO;
	public SubjectDAO subDAO;
	public Controller(){
		stuDAO = new StudentDAO();
		subDAO = new SubjectDAO();
		
	}
	private void mainMenu() {
		System.out.println("[1]학생추가"); // 학번(1001) 자동증가 : 학생ID 중복 저장 불가
		System.out.println("[2]학생삭제"); // 학생 ID 입력 후 삭제, 과목도 같이 삭제
		System.out.println("[3]학생과목추가"); // 학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
		System.out.println("[4]학생과목삭제"); // 학번 입력 후 과목 이름 받아서 해당 과목에서 학생 1명 삭제
		System.out.println("[5]전체학생목록"); // 점수로(오름차순) 출력
		System.out.println("[6]한 과목 학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력(오름차순 출력)
		System.out.println("[7]파일 저장");
		System.out.println("[8]파일 로드");
		System.out.println("[0]종료");

	}

	public void run() {

		while(true){
			mainMenu();
			int sel = Utils.getValue("메뉴 선택", 0, 8);
			if(sel == 0) {
				System.out.println("프로그램 종료");
				break;
			}else if(sel == 1) {
				stuDAO.addStudent();
			}else if(sel == 2) {
				stuDAO.deleteStudent(subDAO);
			}else if(sel == 3) {
				subDAO.addSub(stuDAO.stuList);
			}else if(sel == 4) {
				subDAO.deleteSub(stuDAO.stuList);
			}else if(sel == 5) {
				stuDAO.printAll(subDAO.subList);
			}else if(sel == 6) {
				subDAO.printSub(stuDAO.stuList);
			}else if(sel == 7) {
				Utils.saveDataToFile(stuDAO.stuList,subDAO.subList);
			}else if(sel == 8) {
				stuDAO.loadData();
				subDAO.loadData();
			}

		}

	}
	
}
