package ExDAO;

import ExDTO.*;
import Util.ExUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExSQL {

    Connection con; // 데이터베이스 연결을 위한 Connection 객체
    PreparedStatement pstmt; // SQL 쿼리를 실행하기 위한 PreparedStatement 객체
    ResultSet rs; // SQL 쿼리 실행 결과를 담기 위한 ResultSet 객체

    // 데이터베이스 연결 종료 메소드
    public void conClose() {
        try {
            con.close(); // 연결 종료
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 처리
        }
    }

    // 회원 가입 메소드
    public void join(EMember member) {
        con = ExUtil.getConnection(); // 데이터베이스 연결
        try {
            String sql = "INSERT INTO EMEMBER VALUES (?,?,?,?,?,?,?,?,?)"; // SQL 쿼리
            pstmt = con.prepareStatement(sql); // 쿼리 준비
            // PreparedStatement에 데이터 설정
            pstmt.setString(1, member.getEId());
            pstmt.setString(2, member.getEPw());
            pstmt.setString(3, member.getEName());
            pstmt.setInt(4, member.getEAge());
            pstmt.setString(5, member.getEGender());
            pstmt.setString(6, member.getEEmail());
            pstmt.setString(7, member.getEPhone());
            pstmt.setInt(8, member.getEHeight());
            pstmt.setInt(9, member.getEWeight());

            int result = pstmt.executeUpdate(); // 쿼리 실행

            // 결과에 따라 출력
            if (result > 0) {
                System.out.println(member.getEName() + "회원님!! 가입하신 것을 축하드립니다.");
            } else {
                System.out.println("회원가입 실패");
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 처리
        }
    }

    // 로그인 메소드
    public boolean login(String EId, String EPw) {
        con = ExUtil.getConnection(); // 데이터베이스 연결
        try {
            String sql = "SELECT * FROM EMEMBER WHERE EID = ? AND EPW =? "; // SQL 쿼리
            pstmt = con.prepareStatement(sql); // 쿼리 준비

            // PreparedStatement에 데이터 설정
            pstmt.setString(1, EId);
            pstmt.setString(2, EPw);

            rs = pstmt.executeQuery(); // 쿼리 실행

            EMember member; // 회원 정보를 담을 객체

            if (rs.next()) { // 결과가 존재하는 경우
                member = new EMember();
                member.setEId(rs.getString("EId")); // 아이디 설정
                member.setEPw(rs.getString("EPw")); // 비밀번호 설정
                return true; // 로그인 성공
            } else {
                System.out.println("아이디와 비밀번호가 일치하지 않습니다!"); // 로그인 실패 메시지
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 처리
        }
        return false; // 로그인 실패
    }

    // 운동 기록 추가 메소드
    public void ExRecord(ERecords eRecords) {
        con = ExUtil.getConnection(); // 데이터베이스 연결
        try {
            String sql = "INSERT INTO ERECORDS VALUES (?, ?, ?, ?, ?, ?, ?)"; // SQL 쿼리
            pstmt = con.prepareStatement(sql); // 쿼리 준비

            // PreparedStatement에 데이터 설정
            pstmt.setInt(1, eRecords.getERNum());
            pstmt.setString(2, eRecords.getERId());
            pstmt.setInt(3, eRecords.getERHNum());
            pstmt.setString(4, eRecords.getEExType());
            pstmt.setString(5, eRecords.getEDate());
            pstmt.setInt(6, eRecords.getETime());
            pstmt.setInt(7, eRecords.getECalolies());

            int result = pstmt.executeUpdate(); // 쿼리 실행

            // 결과에 따라 출력
            if (result > 0) {
                System.out.println("기록되었습니다");
            } else {
                System.out.println("기록실패");
            }
        } catch (SQLException e) {
            System.out.println("SQL 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 오류 출력
        }
    }

    // 최대 기록 번호 조회 메소드
    public int recordNum() {
        int ErNum = 0; // 기록 번호 초기화
        con = ExUtil.getConnection(); // 데이터베이스 연결
        try {
            String sql = "SELECT MAX(ERNUM) FROM ERECORDS"; // 최대 기록 번호 조회 SQL
            pstmt = con.prepareStatement(sql); // 쿼리 준비

            rs = pstmt.executeQuery(); // 쿼리 실행

            if (rs.next()) { // 결과가 존재하는 경우
                ErNum = rs.getInt(1); // 최대 기록 번호 설정
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 처리
        }
        return ErNum + 1; // 다음 기록 번호 반환
    }

    // 회원 정보 조회 메소드
    public EMember emList(String EId) {
        con = ExUtil.getConnection(); // 데이터베이스 연결
        EMember member = null; // 회원 객체 초기화

        try {
            String sql = "SELECT ENAME, EHEIGHT, EWEIGHT FROM EMEMBER WHERE EId = ?"; // SQL 쿼리
            pstmt = con.prepareStatement(sql); // 쿼리 준비
            pstmt.setString(1, EId); // 아이디 설정
            rs = pstmt.executeQuery(); // 쿼리 실행

            if (rs.next()) { // 결과가 존재하는 경우
                member = new EMember();
                member.setEName(rs.getString("EName")); // 이름 설정
                member.setEHeight((int) rs.getFloat("EHeight")); // 키 설정
                member.setEWeight((int) rs.getFloat("EWeight")); // 몸무게 설정
            }

        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 처리
        }
        return member;  // EMember 객체 반환
    }

    // 선택된 컬럼으로 데이터를 가져오는 함수
    private List<ExTypes> getExTypes(String column) {
        con = ExUtil.getConnection(); // 데이터베이스 연결
        List<ExTypes> exList = new ArrayList<>(); // 운동 종류 리스트 초기화
        try {
            String sql = "SELECT EXNUM, " + column + " FROM EXTYPES"; // SQL 쿼리
            pstmt = con.prepareStatement(sql); // 쿼리 준비

            rs = pstmt.executeQuery(); // 쿼리 실행

            while (rs.next()) { // 결과가 존재하는 동안 반복
                ExTypes extypes = new ExTypes(); // 운동 종류 객체 생성
                extypes.setEXNUM(rs.getInt("EXNUM")); // 운동 번호 설정
                if ("EXNAME".equals(column)) { // 이름인 경우
                    extypes.setExName(rs.getString("EXNAME")); // 이름 설정
                }
                exList.add(extypes); // 리스트에 추가
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 처리
        }
        return exList; // 운동 종류 리스트 반환
    }


    // 운동 이름만 가져오는 메소드
    public List<ExTypes> getExName() {
        return getExTypes("EXNAME"); // 이름 가져오기
    }

    // 회원 상세 정보 조회 메소드
    public EMember memberDetail(String eId) {
        con = ExUtil.getConnection(); // 데이터베이스 연결
        EMember member = new EMember(); // 회원 객체 초기화

        try {
            // (1) sql문 작성
            String sql = "SELECT * FROM EMEMBER WHERE EID = ?"; // SQL 쿼리

            // (2) DB준비
            pstmt = con.prepareStatement(sql); // 쿼리 준비

            // (3) '?' 데이터 입력 ('?' 없으면 패스)
            pstmt.setString(1, eId); // 아이디 설정

            // (4) 실행 : select => rs
            rs = pstmt.executeQuery(); // 쿼리 실행

            // (5) 결과
            if (rs.next()) { // 결과가 존재하는 경우
                member.setEId(rs.getString(1)); // 아이디 설정
                member.setEPw(rs.getString(2)); // 비밀번호 설정
                member.setEName(rs.getString(3)); // 이름 설정
                member.setEAge(rs.getInt(4)); // 나이 설정
                member.setEGender(rs.getString(5)); // 성별 설정
                member.setEEmail(rs.getString(6)); // 이메일 설정
                member.setEPhone(rs.getString(7)); // 전화번호 설정
                member.setEHeight(rs.getInt(8)); // 키 설정
                member.setEWeight(rs.getInt(9)); // 몸무게 설정
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 오류 출력
        }
        return member; // 회원 객체 반환
    }

    public void ememberUpdate(EMember member) {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기
        try {
            // (1) SQL문 작성
            String sql = "UPDATE EMEMBER SET EPW = ?, ENAME = ?, EAGE = ?, EGENDER = ?, EEMAIL = ?, EPHONE = ?, EHEIGHT=?, EWEIGHT=? WHERE EID = ?";

            // (2) PreparedStatement 준비
            pstmt = con.prepareStatement(sql);

            // (3) '?'에 데이터를 입력
            pstmt.setString(1, member.getEPw());
            pstmt.setString(2, member.getEName());
            pstmt.setInt(3, member.getEAge());
            pstmt.setString(4, member.getEGender());
            pstmt.setString(5, member.getEEmail());
            pstmt.setString(6, member.getEPhone());
            pstmt.setInt(7, member.getEHeight());
            pstmt.setInt(8, member.getEWeight());
            pstmt.setString(9, member.getEId());

            // (4) 실행
            int result = pstmt.executeUpdate();

            // (5) 결과 확인
            if (result > 0) {
                System.out.println("회원님의 정보가 성공적으로 업데이트되었습니다!");
            } else {
                System.out.println("수정 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 던지기
        }
    }

    // 탈퇴 메소드
    public void ememberDelete (String EId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ExUtil.getConnection(); // 데이터베이스 연결 얻기

            String deleteGoalsRecords = "DELETE FROM EGOALS WHERE EGID = ?"; // 종속 레코드 삭제 SQL
            pstmt = con.prepareStatement(deleteGoalsRecords);
            pstmt.setString(1, EId); // EId 바인딩
            pstmt.executeUpdate(); // 자식 레코드 삭제 실행

            String deleteMENURecords = "DELETE FROM MENU WHERE EMID = ?"; // 종속 레코드 삭제 SQL
            pstmt = con.prepareStatement(deleteMENURecords);
            pstmt.setString(1, EId); // EId 바인딩
            pstmt.executeUpdate(); // 자식 레코드 삭제 실행

            // 강제로 관련된 모든 자식 레코드 삭제
            String deleteRecords = "DELETE FROM ERECORDS WHERE ERID = ?"; // 종속 레코드 삭제 SQL
            pstmt = con.prepareStatement(deleteRecords);
            pstmt.setString(1, EId); // EId 바인딩
            pstmt.executeUpdate(); // 자식 레코드 삭제 실행

            // 이제 회원 삭제
            String sql = "DELETE FROM EMEMBER WHERE EID = ?"; // 회원 삭제 SQL
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, EId); // EId 바인딩

            int result = pstmt.executeUpdate(); // 실행 결과 확인
            if (result > 0) {
                System.out.println("회원 탈퇴가 완료되었습니다!");
            } else {
                System.out.println("삭제 실패");
            }
        } catch (SQLException e) {
            // 예외 발생 시 메시지 출력
            System.out.println("삭제 중 오류가 발생했습니다: " + e.getMessage());
        } finally {
            // 자원 해제
            try {
                if (pstmt != null) pstmt.close(); // PreparedStatement 닫기
                if (con != null) con.close(); // 연결 닫기
            } catch (SQLException e) {
                // 자원 해제 중 오류 발생 시 메시지 출력
                System.out.println("자원 해제 중 오류 발생: " + e.getMessage());
            }
        }
    }
    // 회원 정보 조회 메소드
    public void memberList(String EId) {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기
        ArrayList<EMember> memberList = new ArrayList<>(); // 회원 정보를 저장할 리스트
        try {
            String sql = "SELECT * FROM EMEMBER WHERE EID = ?"; // 회원 정보 조회 SQL 문
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, EId); // EId 바인딩
            rs = pstmt.executeQuery(); // 쿼리 실행

            while (rs.next()) { // 결과 집합 순회
                EMember member = new EMember();
                member.setEId(rs.getString(1));
                member.setEPw(rs.getString(2));
                member.setEName(rs.getString(3));
                member.setEAge(rs.getInt(4));
                member.setEGender(rs.getString(5));
                member.setEEmail(rs.getString(6));
                member.setEPhone(rs.getString(7));
                member.setEHeight(rs.getInt(8));
                member.setEWeight(rs.getInt(9));
                memberList.add(member); // 리스트에 추가
            }

            // 조회된 정보 출력
            if (memberList.isEmpty()) {
                System.out.println("조회된 정보가 없습니다.");
            } else {
                for (EMember mlist : memberList) {
                    System.out.println(mlist); // 각 회원 정보 출력
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL 오류: " + e.getMessage()); // 예외 발생 시 오류 메시지 출력
        } finally {
            conClose(); // 연결 종료 메소드 호출

        }
    }

    // 운동 기록 조회 메소드
    public List<ERecords> recordList(String EId) {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기
        List<ERecords> rList = new ArrayList<>();// 운동 기록 저장할 리스트

        try {
            String sql = "SELECT * FROM ERECORDS WHERE ERID = ? ORDER BY ERNUM"; // 운동 기록 조회 SQL 문
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, EId); // EId 바인딩
            rs = pstmt.executeQuery(); // 쿼리 실행

            while (rs.next()) { // 결과 집합 순회
                ERecords records = new ERecords();
                records.setERNum(rs.getInt(1));
                records.setERId(rs.getString(2));
                records.setERHNum(rs.getInt(3));
                records.setEExType(rs.getString(4));
                records.setEDate(rs.getString(5));
                records.setETime(rs.getInt(6));
                records.setECalolies(rs.getInt(7));
                rList.add(records); // 리스트에 추가
            }

            // 운동 기록 출력
            for (ERecords records : rList) {
                System.out.println(records);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 던지기
        }
        return rList;
    }

    // 음식 리스트 검색 메소드
    public List<EFood> getfoodlist() {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기
        List<EFood> foList = new ArrayList<>(); // 음식 정보를 저장할 리스트
        try {
            String sql = "SELECT * FROM FOOD"; // 음식 검색 SQL 문
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery(); // 쿼리 실행

            while (rs.next()) { // 결과 집합 순회
                EFood foods = new EFood();
                foods.setFoNum(rs.getInt(1));
                foods.setFoName(rs.getString(2));
                foods.setFoGrams(rs.getString(3));
                foods.setFoCals(rs.getInt(4));
                foList.add(foods); // 리스트에 추가
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 던지기
        }

        return foList; // 음식 리스트 반환
    }

    // 운동 목표 저장 메소드
    public void saveGoal(EGoals goals) {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기
        try {
            String sql = "INSERT INTO EGOALS VALUES (?, ?, ?, ?, ?, ?, ?)"; // 목표 저장 SQL 문

            // PreparedStatement를 사용하여 SQL 쿼리 생성
            pstmt = con.prepareStatement(sql);

            // 파라미터 설정
            pstmt.setInt(1, goals.getEGNum());       // 목표 번호
            pstmt.setString(2, goals.getEGId());     // 회원 아이디
            pstmt.setInt(3, goals.getECNUM());       // 운동 번호
            pstmt.setString(4, goals.getETEx());     // 목표 운동 종류
            pstmt.setInt(5, goals.getETExTime());    // 목표 운동 시간
            pstmt.setString(6, goals.getESDate());    // 목표 시작일
            pstmt.setString(7, goals.getEEDate());    // 목표 종료일

            // SQL 실행
            int result = pstmt.executeUpdate();  // 쿼리 실행, 영향을 받은 행의 개수를 반환
            if (result > 0) {
                System.out.println("운동 목표가 성공적으로 저장되었습니다!");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
    }

    // 목표 번호 조회 메소드
    public int goalsNum() {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기
        int EgNum = 0; // 목표 번호를 저장할 변수
        try {
            String sql = "SELECT MAX(EGNUM) FROM EGOALS"; // 최대 목표 번호 조회 SQL 문
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery(); // 쿼리 실행

            if (rs.next()) {
                EgNum = rs.getInt(1); // 최대 목표 번호 가져오기
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 던지기
        }
        return EgNum + 1; // 다음 목표 번호 반환
    }

    // 목표 리스트 조회 메소드
    public void gList(String EId) {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기
        ArrayList<EGoals> gList = new ArrayList<>(); // 목표 정보를 저장할 리스트

        try {
            String sql = "SELECT * FROM EGOALS WHERE EGID = ?"; // 목표 조회 SQL 문
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, EId); // EId 바인딩

            rs = pstmt.executeQuery(); // 쿼리 실행

            while (rs.next()) { // 결과 집합 순회
                EGoals goal = new EGoals();
                goal.setEGNum(rs.getInt(1));
                goal.setEGId(rs.getString(2));
                goal.setECNUM(rs.getInt(3));
                goal.setETEx(rs.getString(4));
                goal.setETExTime(rs.getInt(5));
                goal.setESDate(rs.getString(6));
                goal.setEEDate(rs.getString(7));
                gList.add(goal); // 리스트에 추가
            }

            // 목표 리스트 출력
            for (EGoals egoal : gList) {
                System.out.println(egoal); // 각 목표 정보 출력
            }

        } catch (SQLException e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        } finally {
            conClose(); // 연결 종료 메소드 호출
        }
    }

    // 운동 종류를 조회하는 메소드
    public ExTypes tList(int Exnum) {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기
        ExTypes types = null; // 결과를 저장할 ExTypes 객체

        try {
            // (1) SQL문 작성
            String sql = "SELECT EXNAME, EXCALORIES FROM EXTYPES WHERE EXNUM = ?";
            pstmt = con.prepareStatement(sql); // PreparedStatement 준비
            pstmt.setInt(1, Exnum); // 운동 번호 바인딩
            rs = pstmt.executeQuery(); // 쿼리 실행

            // (2) 결과 처리
            if (rs.next()) {
                types = new ExTypes(); // 새로운 ExTypes 객체 생성
                types.setExName(rs.getString("EXNAME")); // 운동 이름 설정
                types.setExCalories((int) rs.getFloat("EXCALORIES")); // 칼로리 설정
            }

        } catch (SQLException e) {
            throw new RuntimeException(e); // SQL 예외 발생 시 런타임 예외로 던지기
        }
        return types; // ExTypes 객체 반환
    }

    // 운동 기록의 존재 여부를 확인하는 메소드
    public boolean checkExistingRecords(String eId, String edate) {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기

        try {
            // (1) SQL문 작성
            String sql = "SELECT COUNT(*) FROM ERECORDS WHERE ERID = ? AND TO_CHAR(EDATE, 'DD') = ?";
            pstmt = con.prepareStatement(sql); // PreparedStatement 준비
            pstmt.setString(1, eId); // 회원 아이디 바인딩
            pstmt.setString(2, edate); // 날짜 바인딩

            ResultSet rs = pstmt.executeQuery(); // 쿼리 실행
            if (rs.next()) {
                // (2) 결과 확인
                int count = rs.getInt(1); // 첫 번째 컬럼의 값 가져오기
                if (count > 0) {
                    System.out.println("해당 날짜의 기록이 존재합니다"); // 기록이 존재하는 경우
                    return true; // 기록 존재
                }
            } else {
                System.out.println("해당 날짜의 기록이 존재하지 않습니다."); // 기록이 존재하지 않는 경우
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
        return false; // 기록이 존재하지 않는 경우
    }
    // 운동 기록의 존재 여부를 확인하는 메소드

    public boolean isGoalExists(String EId, String EsDate, String EeDate) {
        con = ExUtil.getConnection(); // 데이터베이스 연결 얻기

        try {
            // (1) SQL문 작성
            String sql = "SELECT COUNT(*) FROM EGOALS WHERE EGID = ? AND TO_CHAR(ESDATE,'DD') >= ? AND TO_CHAR(EEDATE,'DD')<= ?";
            pstmt = con.prepareStatement(sql); // PreparedStatement 준비
            pstmt.setString(1, EId); // 회원 아이디 바인딩
            pstmt.setString(2, EsDate); // 시작 날짜 바인딩
            pstmt.setString(3, EeDate); // 종료 날짜 바인딩
            rs = pstmt.executeQuery(); // 쿼리 실행

            // (2) 결과 확인
            if (rs.next()) {
                int count = rs.getInt(1); // 첫 번째 컬럼의 값 가져오기
                if (count > 0) {
                    System.out.println("해당 날짜의 기록이 존재합니다"); // 기록이 존재하는 경우
                    return true; // 기록 존재
                }
            } else {
                System.out.println("해당 날짜의 기록이 존재하지 않습니다."); // 기록이 존재하지 않는 경우
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
        return false; // 기록이 존재하지 않는 경우
    }

    public int getFoodCals(int FoNum) {
        int foNum =1;
        con = ExUtil.getConnection();
        try {
            String sql = "SELECT * FROM FOOD WHERE FONUM = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,FoNum);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rs.getInt("FONUM");
                rs.getString("FONAME");
                rs.getString("FOGRAMS");
                rs.getInt("FOCALS");
                rs.getInt("FOPROTEIN");
                rs.getInt("FOCARBOHYDRATES");
                rs.getInt("FOFATS");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foNum ;
    }

    public void insertMenu(E_Menu menu) {
        String sql = "INSERT INTO MENU VALUES (?,?,?,?,?,?,?,?)"; // SQL 쿼리
        try {
            pstmt = con.prepareStatement(sql); // 쿼리 준비
        // PreparedStatement에 데이터 설정
        pstmt.setInt(1, menu.getEMNUM());
        pstmt.setString(2, menu.getEMID());
        pstmt.setString(3, menu.getEMDate());
        pstmt.setInt(4, menu.getECalories());
        pstmt.setInt(5, menu.getEProtein());
        pstmt.setInt(6, menu.getECarbohydrates());
        pstmt.setInt(7, menu.getEFats());
        pstmt.setInt(8,menu.getEfNum());
        int result = pstmt.executeUpdate(); // 쿼리 실행

        // 결과에 따라 출력
        if (result > 0) {
            System.out.println("식단에 추가되었습니다");
        }
    } catch (Exception e) {
        throw new RuntimeException(e); // 예외 발생 시 런타임 예외로 처리
    }
}

    public int menuNum() {
        con = ExUtil.getConnection();
        int EmNum = 0;
        try{
            String sql = "SELECT MAX(EMNUM) FROM MENU";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery(); // 쿼리 실행
            if (rs.next()) {
                EmNum = rs.getInt(1); // 최대 목표 번호 가져오기
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return EmNum+1;
    }

}
