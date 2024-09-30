package ExMain;

import ExDAO.Color;
import ExDAO.ExSQL;

import ExDTO.EGoals;
import ExDTO.EMember;
import ExDTO.ERecords;

import Util.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        int menu1 = 0;
        boolean run = true;
        boolean run2;
        ExSQL sql = new ExSQL();
        EMember member = new EMember();
        ERecords records = new ERecords();
        EGoals goals = new EGoals();
        BmiUtil bUtil = new BmiUtil();
        GoalsUtil gUtil = new GoalsUtil();
        RecordUtil rUtil = new RecordUtil();
        MenuUtil mUtil = new MenuUtil();
        MemberUtil memUtil = new MemberUtil();
        String EId;
        String EPw;

        System.out.println(Color.BRIGHT_RED + "                    @                     \n" +
                "                  @@@@@@                          \n" +
                "               @@@@   @@@                         \n" +
                "           @@@@@       @@@                        \n" +
                Color.BRIGHT_YELLOW +
                "      @@@@@@            @@       @@@@             \n" +
                "    @@@   @@@            @@@   @@@ @@@            \n" +
                "    @@@     @@@@@           @@@@@   @@@           \n" +
                "    @@@        @@@                 @@@@           \n" +
                Color.BRIGHT_GREEN +
                "      @@@        @@                 @@@           \n" +
                "        @@@@@    @@@              @@@@@@          \n" +
                "            @@@@@  @@@               @@@@         \n" +
                "               @@@@  @@@           @@ @@          \n" +
                Color.BRIGHT_CYAN +
                "                  @@@ @@@@          @@@@@@        \n" +
                "                   @@   @@@@@@         @@@@       \n" +
                "                    @@@      @@@@     @@@@@       \n" +
                "                     @@@@     @@@         @@@     \n" +
                Color.BRIGHT_MAGENTA +
                "                       @@@@    @@@@         @@@   \n" +
                "                          @@@@    @@@@@      @@   \n" +
                "                             @@@@@@   @@@@@@@@    \n" +
                "                                   @@@@@@@        \n" + Color.RESET);

        while(run){
            System.out.println("=====================헬쓱해요====================");
            System.out.println("[1]회원가입\t\t\t[2]로그인\t\t\t\t[3]종료");
            System.out.println("===============================================");
            System.out.println("선택 >>");
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    memUtil.exjoin(member);
                    sql.join(member);
                    break;
                case 2:
                    System.out.println("아이디 입력 : ");
                    EId = sc.next();
                    System.out.println("비밀번호 입력 : ");
                    EPw = sc.next();
                    run = true;
                    run2 = true;
                    boolean login = sql.login(EId, EPw);
                    if(login) {
                        member = sql.emList(EId);
                        System.out.println(member.getEName() + "회원님 오신걸 환영합니다!!");
                        while (run2) {
                            System.out.println("==========================================");
                            System.out.println("[1]BMI계산\t\t[2]목표설정\t\t[3]기록설정\t\t");
                            System.out.println("[4]식단관리\t\t[5]마이페이지\t\t[6]돌아가기\t\t");
                            System.out.println("==========================================");
                            System.out.println("선택 >>");

                            menu1 = sc.nextInt();
                            switch (menu1) {
                                case 1:
                                    bUtil.bmicul(EId);
                                    break;
                                case 2:
                                    gUtil.goal(goals, EId);
                                    break;
                                case 3:
                                    rUtil.record(records, EId);
                                    break;
                                case 4:
                                    mUtil.menu(EId);
                                    break;
                                case 5:
                                    memUtil.Info(EId);
                                    break;
                                case 6:
                                    run2 = false;
                                    System.out.println("프로그램을 이용해주셔서 감사합니다 ");
                                    break;
                                default:
                                    System.out.println("잘못 입력하셨습니다 다시 눌러주십쇼");
                                    break;
                            }
                        }
                    }
                    break;
                case 3:
                    run = false;
                    sql.conClose();
                    System.out.println("프로그램을 종료합니다");
                    break;
                default:
                    System.out.println("잘못 눌렀습니다");
                    break;
            }
        }
    }
}
