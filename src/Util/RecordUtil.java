package Util;

import ExDAO.Calender;
import ExDAO.ExSQL;
import ExDTO.EMember;
import ExDTO.ERecords;
import ExDTO.ExTypes;

import java.util.List;
import java.util.Scanner;

public class RecordUtil {
    ExSQL sql = new ExSQL(); // 데이터베이스 연동을 위한 ExSQL 객체 생성
    Scanner sc = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체 생성

    public void record(ERecords records, String EId) {
        // 기록 번호와 회원 ID 설정
        records.setERNum(sql.recordNum()); // 새 기록 번호 생성
        records.setERId(EId); // 회원 ID 설정

        int ExNum = 1; // 기본 운동 번호 설정

        // 회원 정보 가져오기
        EMember member = sql.emList(EId); // 회원 정보 가져오기

        // 운동 종류 목록 가져오기
        ExTypes extype = sql.tList(ExNum); // 운동 종류 정보 가져오기

        System.out.println("운동 목록을 선택하세요");
        List<ExTypes> exNameList = sql.getExName(); // 운동 이름 목록 가져오기

        // 운동 목록이 비어있을 경우 메시지 출력
        if (exNameList == null || exNameList.isEmpty()) {
            System.out.println("운동 목록을 불러올 수 없습니다. 목록이 비어 있습니다.");
            return; // 목록이 비어 있으면 종료
        }

        // 운동 이름 목록 출력
        System.out.println("운동 이름 목록");
        for (int i = 0; i < exNameList.size(); i++) {
            System.out.println("[" + (i + 1) + "] 운동이름: " + exNameList.get(i).getExName()); // 운동 이름 출력
        }

        System.out.print("운동번호를 선택하세요 >> ");
        int selectedIndex = sc.nextInt() - 1; // 사용자 입력 받기

        // 사용자가 선택한 운동 번호에 대한 유효성 검사
        if (selectedIndex >= 0 && selectedIndex < exNameList.size()) {
            ExTypes selectedExercise = exNameList.get(selectedIndex); // 선택한 운동 정보 가져오기
            records.setERHNum(selectedExercise.getEXNUM()); // 선택한 운동의 번호 설정
            records.setEExType(selectedExercise.getExName()); // 선택한 운동의 이름 설정
        } else {
            System.out.println("올바른 운동 번호를 선택하지 않았습니다."); // 잘못된 선택 시 메시지 출력
            return; // 선택 오류 시 종료
        }

        // 날짜 입력 받기
        Calender.calendar(0);  // 기본 달력 출력
        System.out.print("날짜 선택 (일) >> ");
        String EDate = sc.next();  // 사용자가 입력한 일자
        String day = "2024-09-";  // 기본 년월 설정
        String sday = day + String.format("%02d", Integer.parseInt(EDate));  // '2024-09-01' 형식으로 변환
        records.setEDate(sday); // 설정한 날짜 저장

        // 운동 시간 입력 받기
        System.out.print("시간 (분) >> ");
        int time = sc.nextInt(); // 사용자가 입력한 운동 시간
        records.setETime(time); // 운동 시간 설정

        // 체중 및 칼로리 계산
        float weight = member.getEWeight(); // 회원의 체중 가져오기
        float exCalories = extype.getExCalories(); // 운동 종류별 칼로리 소모율 가져오기

        System.out.println("체중: " + weight); // 체중 출력
        System.out.println("운동 종류별 칼로리 소모율: " + exCalories); // 칼로리 소모율 출력

        // 소모 칼로리 계산 및 저장
        double caloriesBurned = exCalories * weight * (time / 60.0); // 총 소모 칼로리 계산
        records.setECalolies((int) caloriesBurned); // 소모 칼로리 설정
        System.out.printf("총 소모 칼로리: %.2f kcal\n", caloriesBurned); // 소모 칼로리 출력

        // 운동 기록 확인 및 강조 표시
        if (sql.checkExistingRecords(EId, EDate)) { // 기존 기록이 있으면 강조
            Calender.calendar(Integer.parseInt(EDate)); // 선택한 날짜 강조
        }

        // 운동 기록을 데이터베이스에 저장
        sql.ExRecord(records); // 운동 기록 저장
    }
}