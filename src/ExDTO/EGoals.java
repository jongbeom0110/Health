package ExDTO;


public class EGoals {

    private int EGNum;      // 목표 번호
    private String EGId;    // 유저 아이디
    private int ECNUM;
    private String ETEx;    // Ex-Target-Exercise, 목표 운동
    private int ETExTime;   // Ex-Target-Exercise, 목표 운동 시간
    private String ESDate;  // Ex-Start-Date, 목표 시작일
    private String EEDate;  // Ex-End-Date, 목표 종료일

    public int getEGNum() {
        return EGNum;
    }

    public void setEGNum(int EGNum) {
        this.EGNum = EGNum;
    }

    public String getEGId() {
        return EGId;
    }

    public void setEGId(String EGId) {
        this.EGId = EGId;
    }

    public int getECNUM() {
        return ECNUM;
    }

    public void setECNUM(int ECNUM) {
        this.ECNUM = ECNUM;
    }

    public String getETEx() {
        return ETEx;
    }

    public void setETEx(String ETEx) {
        this.ETEx = ETEx;
    }

    public int getETExTime() {
        return ETExTime;
    }

    public void setETExTime(int ETExTime) {
        this.ETExTime = ETExTime;
    }

    public String getESDate() {
        return ESDate;
    }

    public void setESDate(String ESDate) {
        this.ESDate = ESDate;
    }

    public String getEEDate() {
        return EEDate;
    }

    public void setEEDate(String EEDate) {
        this.EEDate = EEDate;
    }

    @Override
    public String toString() {
        return "┏━━━━━━━━━━━━━━━내정보━━━━━━━━━━━━━━┓\n" +
                "목표 번호 : ["+EGNum+"]\n" +
                "아이디 : ["+EGId+"]\n" +
                "운동 번호  : ["+ECNUM+"]\n" +
                "운동 이름 : ["+ETEx+"]\n" +
                "목표 운동시간 : ["+ETExTime+"]\n" +
                "목표 시작일 : ["+ESDate+"]\n" +
                "목표 종료일 : ["+EEDate+"]\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
    }
}
