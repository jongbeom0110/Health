package ExDAO;

public class Calender {
    // ANSI 색상 코드 상수 정의
    public static final String red = "\u001B[31m";     // 빨간색
    public static final String blue = "\u001B[34m";    // 파란색
    public static final String exit = "\u001B[0m";     // 색상 초기화
   // 청록색 추가

    public static void calendar(int highlightDay) {
        // 달력을 위한 StringBuilder 초기화
        StringBuilder calendar = new StringBuilder();

        // 달력 상단 레이아웃
        calendar.append("┌─────────────────────────────────────────┐\n");
        calendar.append("│               2024 - 09                 │\n");
        calendar.append("├─────┬─────┬─────┬─────┬─────┬─────┬─────┤\n");
        calendar.append("│ " + red + "SUN" + exit + " │ MON │ TUE │ WED │ THU │ FRI │ " + blue + "SAT" + exit + " │\n");
        calendar.append("├─────┼─────┼─────┼─────┼─────┼─────┼─────┤\n");

        int startDay = 1;          // 월의 첫날 (SUN이 1일이라고 가정)
        int daysInMonth = 30;      // 9월의 일수 (예: 30일)

        // 주 단위 반복
        for (int week = 0; week < 5; week++) {
            // 하루 단위 반복
            for (int day = 1; day <= 7; day++) {
                int currentDay = week * 7 + day - startDay + 1; // 현재 날짜 계산

                // 현재 날짜가 유효한지 확인
                if (currentDay > 0 && currentDay <= daysInMonth) {
                    if (highlightDay == currentDay) {
                        // 강조된 날짜는 CYAN 색상으로 출력
                        calendar.append(" │ " + Color.CYAN + String.format("%2d", currentDay) + exit + " ");
                    } else {
                        // 요일에 따라 색상을 변경합니다.
                        if (day == 1) { // 일요일
                            calendar.append("│ " + red + String.format("%2d", currentDay) + exit + " ");
                        } else if (day == 7) { // 토요일
                            calendar.append(" │ " + blue + String.format("%2d", currentDay) + exit + " ");
                        } else { // 평일
                            calendar.append(" │ " + String.format("%2d", currentDay) + " ");
                        }
                    }
                } else {
                    calendar.append(" │    "); // 빈 날짜 출력
                }
            }
            calendar.append("│\n"); // 주 끝
            if (week < 4) {
                calendar.append("├─────┼─────┼─────┼─────┼─────┼─────┼─────┤\n"); // 주 간 경계
            }
        }

        calendar.append("└─────┴─────┴─────┴─────┴─────┴─────┴─────┘\n"); // 달력 하단 레이아웃

        System.out.print(calendar); // 완성된 달력 출력
    }
}