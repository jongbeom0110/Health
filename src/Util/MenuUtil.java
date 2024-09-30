package Util;

import ExDAO.ExSQL;
import ExDTO.EFood;
import ExDTO.ERecords;
import ExDTO.E_Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUtil {

    ExSQL sql = new ExSQL(); // 데이터베이스 연동을 위한 ExSQL 객체 생성
    Scanner sc = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체 생성

    public void menu(String EId) {
        boolean run = true; // 프로그램 실행 상태 변수
        List<EFood> savedFood = new ArrayList<>(); // 사용자가 선택한 음식을 저장하는 리스트
        List<EFood> foodList = sql.getfoodlist(); // 데이터베이스에서 음식 리스트를 가져옴

        while (run) {
            System.out.println("검색 리스트 : 닭, 현미, 샐러드");
            System.out.println("검색어를 입력하세요: ");
            String food = sc.next(); // 사용자가 입력한 음식 이름

            foodList = sql.getfoodlist(); // 음식 리스트를 매번 갱신
            boolean found = false; // 음식 검색 성공 여부

            // 음식 리스트에서 검색어와 일치하는 항목 찾기
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getFoName().contains(food)) {
                    System.out.println("[" + (i + 1) + "] " + foodList.get(i).getFoName() + " (" + foodList.get(i).getFoGrams() + ", " + foodList.get(i).getFoCals() + " kcal)");
                    found = true; // 음식이 발견되면 true로 변경
                }
            }

            // 검색 결과가 없을 경우 메시지 출력
            if (!found) {
                System.out.println("검색 결과가 없습니다.");
            } else {
                System.out.println("항목 선택: ");
                int selectedIndex = sc.nextInt(); // 사용자가 선택한 음식 번호 입력

                // 음식 종류에 따라 선택 가능한 번호 범위를 지정
                if (food.equals("현미")) {
                    if (selectedIndex >= 1 && selectedIndex <= 3) {
                        EFood selectedFood = foodList.get(selectedIndex - 1); // 선택한 음식을 리스트에서 가져옴
                        savedFood.add(selectedFood); // 선택한 음식을 저장 리스트에 추가
                        System.out.println(selectedFood.getFoName() + " 추가 완료");
                    } else {
                        System.out.println("현미에서 선택할 수 있는 번호는 1, 2, 3입니다.");
                    }
                } else if (food.equals("닭")) {
                    if (selectedIndex >= 4 && selectedIndex <= 6) {
                        EFood selectedFood = foodList.get(selectedIndex - 1); // 선택한 음식을 리스트에서 가져옴
                        savedFood.add(selectedFood); // 선택한 음식을 저장 리스트에 추가
                        System.out.println(selectedFood.getFoName() + " 추가 완료");
                    } else {
                        System.out.println("닭에서 선택할 수 있는 번호는 4, 5, 6입니다.");
                    }
                } else if (food.equals("샐러드")) {
                    if (selectedIndex >= 7 && selectedIndex <= 9) {
                        EFood selectedFood = foodList.get(selectedIndex - 1); // 선택한 음식을 리스트에서 가져옴
                        savedFood.add(selectedFood); // 선택한 음식을 저장 리스트에 추가
                        System.out.println(selectedFood.getFoName() + " 추가 완료");
                    } else {
                        System.out.println("샐러드에서 선택할 수 있는 번호는 7, 8, 9입니다.");
                    }
                } else {
                    System.out.println("유효하지 않은 선택입니다.");
                }
            }

            // 추가 검색 여부를 묻는 부분
            System.out.println("검색을 더 하시겠습니까? (y/n)");
            String search = sc.next();
            run = search.equalsIgnoreCase("y"); // 'y' 입력 시 반복, 'n' 입력 시 종료
        }

        // 선택된 음식을 출력하는 부분
        if (savedFood.isEmpty()) {
            System.out.println("저장된 식단이 없습니다.");
        } else {
            System.out.println("오늘의 식단");
            int totalCals = 0; // 총 칼로리 계산

            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            for (EFood food : savedFood) {
                System.out.println(food.getFoName() + "\t" + food.getFoGrams() + "\t" + food.getFoCals() + " kcal"); // 선택한 음식 출력
                totalCals += food.getFoCals();  // 총 칼로리 합산
            }
            System.out.println("\n총 " + totalCals + " kcal"); // 총 칼로리 출력
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        }

        // 음식의 영양 정보 및 총합 계산
        E_Menu menu = new E_Menu();
        double totalProtein = 0;
        double totalCarbohydrates = 0;
        double totalFats = 0;
        int totalCals = 0;

        for (EFood foods : foodList) {
            totalProtein += foods.getFoProtein(); // 단백질 합산
            totalCarbohydrates += foods.getFoCarbohydrates(); // 탄수화물 합산
            totalFats += foods.getFoFats(); // 지방 합산
            totalCals += foods.getFoCals(); // 칼로리 합산
        }

        // 메뉴 저장을 위한 SQL 메소드 호출
        int FoNum = 1;
        int efnum = sql.getFoodCals(FoNum); // 음식을 통한 총 칼로리 가져오기
        menu.setEMNUM(sql.menuNum()); // 메뉴 번호 설정
        menu.setEMID(EId); // 사용자 ID 설정
        menu.setECalories(totalCals); // 총 칼로리 설정
        menu.setEProtein((int) totalProtein); // 총 단백질 설정
        menu.setECarbohydrates((int) totalCarbohydrates); // 총 탄수화물 설정
        menu.setEFats((int) totalFats); // 총 지방 설정
        menu.setEfNum(efnum); // 메뉴에 대한 음식 번호 설정
        sql.insertMenu(menu); // 메뉴를 데이터베이스에 저장

        // 운동 기록을 가져와 출력하고 처리하는 부분
        List<ERecords> recordList = sql.recordList(EId); // 운동 기록 가져오기
        if (recordList == null || recordList.isEmpty()) {
            System.out.println("운동 기록이 없습니다.");
            return; // 운동 기록이 없으면 종료
        }

        System.out.println("운동 기록을 선택하세요: ");
        int exChoose = sc.nextInt() - 1; // 사용자가 선택한 운동 기록 번호

        // 선택한 운동 기록을 처리
        if (exChoose >= 0 && exChoose < recordList.size()) {
            ERecords selectedExercise = recordList.get(exChoose); // 선택한 운동 기록
            double caloriesBurned = selectedExercise.getECalolies(); // 소모 칼로리 가져오기
            String exerciseDate = selectedExercise.getEDate(); // 운동 기록 날짜 가져오기
            menu.setEMDate(exerciseDate); // 메뉴의 날짜 설정

            // 저장된 음식 리스트에서 각 음식의 칼로리를 기준으로 표시
            List<EFood> foodCalsList = savedFood; // 저장된 음식 리스트
            if (foodCalsList != null) {
                foodCalsList.sort((a, b) -> b.getFoCals() - a.getFoCals()); // 음식 리스트를 칼로리 내림차순으로 정렬
                System.out.println("총 소모 칼로리: " + caloriesBurned + " kcal");

                // 소모된 칼로리만큼 음식을 몇 개 먹을 수 있는지 계산 후 출력
                for (EFood foods : foodCalsList) {
                    int foodCalories = foods.getFoCals(); // 음식의 칼로리
                    if (foodCalories > 0) {
                        int foodCount = (int) (caloriesBurned / foodCalories); // 음식의 개수 계산
                        System.out.println(foods.getFoName() + " " + foodCount + "개");
                    }
                }
            } else {
                System.out.println("음식 정보를 가져오는 데 실패했습니다.");
            }
        } else {
            System.out.println("올바른 운동 번호를 선택하지 않았습니다."); // 잘못된 선택에 대한 메시지
        }
    }
}