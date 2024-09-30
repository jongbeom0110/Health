package Util;

import ExDAO.ExSQL;
import ExDTO.EMember;

import java.util.Scanner;

public class MemberUtil {

    public void Info(String EId) {
        Scanner sc = new Scanner(System.in);
        ExSQL sql = new ExSQL();

        EMember member;
        boolean run = true;

        while (run) {
            System.out.println("=============================================");
            System.out.println("[1]내정보수정\t\t[2]내정보조회\t\t[3]운동기록조회\t\t");
            System.out.println("[4]운동목표조회    [5]탈퇴하기\t\t[6]돌아가기");
            System.out.println("=============================================");
            System.out.println("선택 >>");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    member = sql.memberDetail(EId);
                    System.out.println("===========================================");
                    System.out.println("[1]비밀번호\t\t[2]이   름\t\t[3]나   이");
                    System.out.println("[4]성   별\t\t[5]이메일\t\t[6]전화번호");
                    System.out.println("[7]키   \t\t[8]몸무게\t\t");
                    System.out.println("===========================================");
                    System.out.print("수정할 정보 선택 >> ");
                    int menu2 = sc.nextInt();
                    switch (menu2) {
                        case 1:
                            System.out.print("비밀번호 >> ");
                            member.setEPw(sc.next());
                            break;
                        case 2:
                            System.out.print("이름 >> ");
                            member.setEName(sc.next());
                            break;
                        case 3:
                            System.out.print("나이 >> ");
                            member.setEAge(sc.nextInt());
                            break;
                        case 4:
                            System.out.print("성별 >> ");
                            member.setEGender(sc.next());
                            break;
                        case 5:
                            System.out.print("이메일 >> ");
                            member.setEEmail(sc.next());
                            break;
                        case 6:
                            System.out.print("전화번호 >> ");
                            member.setEPhone(sc.next());
                            break;
                        case 7:
                            System.out.print("키 >> ");
                            member.setEHeight(sc.nextInt());
                            break;
                        case 8:
                            System.out.print("몸무게 >> ");
                            member.setEWeight(sc.nextInt());
                            break;
                        default:
                            System.out.println("다시 입력해주세요");
                            break;
                    }
                    sql.ememberUpdate(member);
                    break;
                case 2:
                    sql.memberList(EId);
                    break;
                case 3:
                    GraphUtility.displayCaloriesGraph(EId);
                    break;
                case 4:
                    sql.gList(EId);
                    break;
                case 5:
                    System.out.print("정말 탈퇴하시겠습니까? (Y/N)>> ");
                    String checkDelete = sc.next();
                    if (checkDelete.equals("y")) {
                        sql.ememberDelete(EId);
                        EId = "";
                    } else {
                        System.out.println("삭제를 취소합니다.");
                    }
                    break;
                case 6:
                    run = false; // 돌아가기
                    break;

                default:
                    System.out.println("잘못 입력하셨습니다.");
                    break;
            }
        }
    }
    public void exjoin(EMember member) {
        Scanner sc = new Scanner(System.in);
        System.out.println("아이디 >>");
        member.setEId(sc.next());
        System.out.println("비밀번호 >>");
        member.setEPw(sc.next());
        System.out.println("이름 >>");
        member.setEName(sc.next());
        System.out.println("나이 >>");
        member.setEAge(sc.nextInt());
        System.out.println("성별 >>");
        member.setEGender(sc.next());
        System.out.println("이메일 >>");
        member.setEEmail(sc.next());
        System.out.println("전화번호 >>");
        member.setEPhone(sc.next());
        System.out.println("키 >>");
        member.setEHeight(sc.nextInt());
        System.out.println("몸무게 >>");
        member.setEWeight(sc.nextInt());
    }
}
