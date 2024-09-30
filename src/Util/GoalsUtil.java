package Util;

import ExDAO.Calender;
import ExDAO.ExSQL;
import ExDTO.EGoals;
import ExDTO.ExTypes;

import java.util.List;
import java.util.Scanner;

public class GoalsUtil {
    ExSQL sql = new ExSQL(); // 데이터베이스 작업을 위한 ExSQL 객체 생성
    Scanner sc = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성

    // 목표 설정 메서드
    public void goal(EGoals goals, String EId) {

        // 목표 번호 설정 (데이터베이스에서 목표 번호를 가져와 설정)
        goals.setEGNum(sql.goalsNum());
        goals.setEGId(EId); // 회원 아이디 설정

        // 운동 이름 선택
        System.out.println("운동 목록을 선택하세요");
        // 운동 이름 목록을 데이터베이스에서 불러오기
        List<ExTypes> exNameList = sql.getExName();
        // 사용자로부터 운동 이름을 선택받음
        ExTypes selectedExercise = selectExerciseName(exNameList);

        // 운동 이름이 선택되었는지 확인
        if (selectedExercise != null) {
            // 선택한 운동 번호 및 이름을 목표에 설정
            goals.setECNUM(selectedExercise.getEXNUM());
            goals.setETEx(selectedExercise.getExName());
        } else if (exNameList == null || exNameList.isEmpty()) {
            // 운동 목록이 비어 있을 경우 처리
            System.out.println("운동 목록을 불러올 수 없습니다. 목록이 비어 있습니다.");
            return; // 종료
        }

        // 목표 운동 시간 입력
        System.out.println("목표 운동 시간을 입력하세요 (분) >>");
        int goalTime = sc.nextInt(); // 사용자로부터 목표 시간을 입력받음
        goals.setETExTime(goalTime); // 목표 운동 시간을 설정

        // 달력 출력 (목표 시작일 설정을 위한 달력 출력)
        Calender.calendar(0);
        System.out.print("목표 시작일을 입력하세요  >> ");
        String startDate = sc.next();  // 목표 시작일 입력 (일자만 입력받음)

        // 목표 종료일 입력
        System.out.print("목표 종료일을 입력하세요 >> ");
        String endDate = sc.next(); // 목표 종료일 입력

        // 날짜 형식 조정 (9월로 고정)
        String day = "2024-09-"; // 목표 날짜의 월을 9월로 고정
        // 입력받은 일자를 형식에 맞게 변환하여 설정
        String sdayStart = day + String.format("%02d", Integer.parseInt(startDate)); // 목표 시작일
        String sdayEnd = day + String.format("%02d", Integer.parseInt(endDate)); // 목표 종료일
        goals.setEEDate(sdayEnd);  // 목표 종료일 설정
        goals.setESDate(sdayStart); // 목표 시작일 설정

        // 목표 존재 여부 확인
        if (sql.isGoalExists(EId, startDate, endDate)) {
            // 회원 아이디와 입력된 날짜로 이미 설정된 목표가 있는지 확인
            System.out.println("해당 날짜에 이미 목표가 설정되어 있습니다.");
            return; // 이미 목표가 존재할 경우 메서드 종료
        }

        // 목표 저장
        sql.saveGoal(goals); // 설정한 목표를 데이터베이스에 저장
    }

    // 운동 이름 선택 메서드
    static ExTypes selectExerciseName(List<ExTypes> exList) {
        Scanner sc = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성
        System.out.println("운동 이름 목록");

        // 운동 이름 목록을 출력
        for (int i = 0; i < exList.size(); i++) {
            System.out.println("[" + (i + 1) + "] 운동이름: " + exList.get(i).getExName());
        }
        System.out.println("운동번호를 선택하세요 >>");

        // 사용자로부터 선택한 운동 번호를 입력받음
        int selectedNum = sc.nextInt() - 1; // 사용자가 입력한 번호에서 1을 빼서 인덱스로 사용

        // 선택한 번호가 유효한지 확인
        if (selectedNum >= 0 && selectedNum < exList.size()) {
            return exList.get(selectedNum); // 유효하면 선택한 운동 반환
        } else {
            // 잘못된 번호를 선택했을 경우 처리
            System.out.println("잘못된 선택입니다.");
            return null; // 잘못된 선택일 경우 null 반환
        }
    }
}