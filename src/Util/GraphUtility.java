package Util;

import ExDAO.ExSQL;
import ExDTO.ERecords;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GraphUtility {
    private static JFrame frame; // 그래프를 표시할 JFrame 객체

    // 운동 기록 정보를 기반으로 그래프를 표시하는 메서드
    public static void displayCaloriesGraph(String EId) {
        ExSQL sql = new ExSQL(); // 데이터베이스 작업을 위한 ExSQL 객체 생성
        // SQL에서 EId에 해당하는 운동 기록을 가져옴
        List<ERecords> recordsList = sql.recordList(EId); // EId로 모든 운동 기록 가져오기

        // 이미 JFrame이 존재하는 경우 기존 프레임을 업데이트
        if (frame == null) {
            frame = new JFrame("운동 기록 그래프"); // 새 JFrame 생성
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 버튼 설정
            frame.setSize(1000, 1000); // 프레임 크기 설정
        } else {
            frame.getContentPane().removeAll(); // 기존 패널 제거
        }

        // 날짜와 칼로리 정보를 저장할 맵 (TreeMap을 사용하여 날짜순 정렬)
        Map<String, Integer> caloriesByDate = new TreeMap<>();

        // 각 운동 기록을 순회하며 날짜별 칼로리 합산
        for (ERecords record : recordsList) {
            // 날짜에서 시간 정보를 제거 (예: "2024-09-26 10:00:00" -> "2024-09-26")
            String exerciseDate = record.getEDate().split(" ")[0];
            int calories = record.getECalolies();

            // 같은 날짜의 기록이 있을 경우 칼로리를 합산하여 저장
            caloriesByDate.merge(exerciseDate, calories, Integer::sum);
        }

        // 그래프를 표시할 패널을 JFrame에 추가하고 업데이트
        frame.add(new GraphPanel(caloriesByDate)); // Map 전달
        frame.revalidate(); // 프레임 다시 유효화
        frame.repaint(); // 프레임 다시 그리기
        frame.setVisible(true); // 프레임을 보이게 설정
    }
}

// 그래프를 그리는 JPanel 클래스
class GraphPanel extends JPanel {
    private Map<String, Integer> caloriesByDate; // 날짜별 칼로리 정보를 저장하는 맵

    // 생성자에서 날짜별 칼로리 정보를 전달받음
    public GraphPanel(Map<String, Integer> caloriesByDate) {
        this.caloriesByDate = caloriesByDate;
    }

    // 그래프를 그리는 메서드 (JPanel의 paintComponent 메서드 오버라이드)
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 기본 그래픽 작업 수행

        // 패널의 너비와 높이를 가져옴
        int width = getWidth();
        int height = getHeight();
        // 막대 너비는 날짜별 기록 개수를 고려하여 동적으로 계산
        int barWidth = width / (caloriesByDate.size() + 2);
        // 최대 칼로리 값을 계산하여 그래프의 최대 높이 설정
        int maxCalories = caloriesByDate.values().stream().max(Integer::compareTo).orElse(1);

        int index = 0; // 그래프의 막대 인덱스
        // 다양한 색상을 선택하여 날짜별로 다른 색을 지정
        List<Color> colors = Arrays.asList(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK);

        // 날짜별로 그래프의 막대 그리기
        for (Map.Entry<String, Integer> entry : caloriesByDate.entrySet()) {
            String date = entry.getKey(); // 날짜
            int calories = entry.getValue(); // 해당 날짜의 칼로리

            // 날짜의 해시값을 기반으로 색상 선택
            Color color = colors.get(Math.abs(date.hashCode()) % colors.size());
            g.setColor(color); // 선택한 색상 설정

            // 바 높이를 최대 칼로리에 비례하여 계산
            int barHeight = (int) ((calories / (double) maxCalories) * (height - 100)); // 100은 여백

            // 막대 그리기
            g.fillRect(50 + (index * barWidth), height - barHeight - 50, barWidth - 30, barHeight); // 막대 위치 및 크기 설정

            // 각 막대 위에 칼로리 값 표시
            g.setColor(Color.BLACK);
            g.drawString(calories + " kcal", 50 + (index * barWidth) + (barWidth / 4), height - barHeight - 60);

            // X축에 날짜 표시 (중앙 정렬)
            g.setColor(Color.BLACK);
            int textWidth = g.getFontMetrics().stringWidth(date);
            g.drawString(date, 50 + (index * barWidth) + (barWidth / 2) - (textWidth / 2) - 10, height - 5); // 중앙 정렬하여 날짜 표시

            index++; // 다음 막대를 위한 인덱스 증가
        }

        // Y축에 칼로리 숫자 표시
        g.setColor(Color.BLACK);
        // 최대 칼로리 값을 기준으로 10 단위로 눈금을 표시
        for (int i = 0; i <= maxCalories; i += (maxCalories / 10)) {
            int y = height - 50 - (int) ((i / (double) maxCalories) * (height - 100));
            g.drawString(String.valueOf(i), 10, y); // Y축에 칼로리 값 표시
            g.drawLine(45, y, 50, y); // Y축의 기준선 표시
        }

        // X축과 Y축 및 그래프 제목 추가
        g.drawString("운동 기록 그래프", width / 2 - 50, 30); // 그래프 제목 설정
        g.drawLine(50, height - 50, width, height - 50); // X축 그리기
        g.drawLine(50, 50, 50, height - 50); // Y축 그리기
    }
}