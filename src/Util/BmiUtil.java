package Util;

import ExDAO.ExSQL;
import ExDTO.EBMI;
import ExDTO.EMember;

public class BmiUtil {

    // 일정 시간(1초) 대기하는 메서드
    public static void del() {
        try {
            Thread.sleep(1000); // 1초 대기 (단위: 밀리초)
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt(); // 스레드 인터럽트 시 처리
        }

    }
    // BMI 계산을 위한 메서드
    public void bmicul(String EId) {
        ExSQL sql = new ExSQL();
        EMember member = sql.emList(EId);  // 회원 정보를 데이터베이스에서 가져옴

        System.out.println("*- BMI 계산 -*");

        // 회원의 이름, 체중, 키를 가져옴
        String Name = member.getEName();
        float weight = member.getEWeight();
        float height = member.getEHeight();

        // BMI 계산 (키는 cm 단위로 가정하므로 10000으로 나누어 줌)
        float bmicode = weight / ((height * height) / 10000); // BMI 계산 식

        EBMI ebmi = new EBMI(); // BMI 정보를 저장할 객체 생성

        ebmi.setEBmiName(Name); // 회원 이름을 BMI 정보에 설정
        ebmi.setEBmiCode((int) bmicode); // BMI 값을 정수로 변환하여 저장 (소수점은 버림)

        // 시각적 효과를 위한 출력과 대기
        System.out.println(" ⠀⠀⠀⠀⠀⠀⠀⠀    ⢀⠂⠁⠂⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        del();
        System.out.println("        ⠀⠀⠀⠀⠀⠀⠘⡀⠀⡠⠁⠀⠀⠀⠀⠀⠀⠀⠀      ⣿⣿⣿⣿⣿⣿⣿⣿ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        del();
        System.out.println("        ⠀⠀⠀⠀⠀⢈⠔⠀⠀⠀⠢⡀⠀⠀⠀⠀⠀⠀⠀      ⣿⣿⣿⣿⠀ " + weight + "⠀ ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        del();
        System.out.println("        ⠀⠀⠀⠔⠁⠠⠀⠀⠀   ⣄⠀⢀⠀⠀⠀⠀⠀     ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        del();
        System.out.println("            ⠘⠀⠊⠐" + Name + "⡇⠑⠤⠃⠀⠀           ⣿⣿⠀   " + height + "⠀   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        del();
        System.out.println("        ⠀⠀⠀⠀⠀⠀⡇⠀⣀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀      ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        del();
        System.out.println("        ⠀⠀⠀⠀⠀ ⡇⠀⢸⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀      ⣿⣿⣿⣿⠀⠀" + (int) bmicode + "⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        del();
        System.out.println("        ⠀⠀⠀⠀ ⠀⡇⠀⢸⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀      ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        del();
        System.out.println("        ⠀⠀⠀⠀⠀⠈⠤⠘⠤⠃");
        del();

        BMICategory(bmicode);  // BMI 카테고리 를 출력하는 메서드 호출
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

        // BMI 기준 출력
        System.out.println("+----------------+----------------+");
        System.out.println("|     Scale      |      BMI       |");
        System.out.println("+----------------+----------------+");
        System.out.println("|   저체중         |     ~18.5     |");
        System.out.println("|   정상           |     18.5~22   |");
        System.out.println("|   과체중         |     23~25     |");
        System.out.println("|   비만           |     26~30     |");
        System.out.println("|   고도비만        |     30~       |");
        System.out.println("+----------------+----------------+");
    }

    // BMI 값에 따라 카테고리를 출력하는 메서드
    public void BMICategory(float bmicode) {
        if (bmicode < 18.5) {
            System.out.println("저체중"); // BMI가 18.5 미만일 경우
        } else if (bmicode >= 18.5 && bmicode < 23) {
            System.out.println("정상"); // BMI가 18.5 이상 23 미만일 경우
        } else if (bmicode >= 23 && bmicode < 25) {
            System.out.println("과체중"); // BMI가 23 이상 25 미만일 경우
        } else if (bmicode >= 25 && bmicode < 30) {
            System.out.println("비만"); // BMI가 25 이상 30 미만일 경우
        } else {
            System.out.println("고도비만"); // BMI가 30 이상일 경우
        }
    }
}