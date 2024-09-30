package ExDTO;


public class ERecords {
    private int ERNum;          // 기록 번호
    private String ERId;// 유저 아이디
    private int ERHNum;
    private String EExType;     // 운동 종류
    private String EDate;       // 운동 시작일
    private int ETime;          // 하루 운동 시간
    private int ECalolies;      // 하루 소모 칼로리

    public int getERNum() {
        return ERNum;
    }

    public void setERNum(int ERNum) {
        this.ERNum = ERNum;
    }

    public String getERId() {
        return ERId;
    }

    public void setERId(String ERId) {
        this.ERId = ERId;
    }

    public int getERHNum() {
        return ERHNum;
    }

    public void setERHNum(int ERHNum) {
        this.ERHNum = ERHNum;
    }

    public String getEExType() {
        return EExType;
    }

    public void setEExType(String EExType) {
        this.EExType = EExType;
    }

    public String getEDate() {
        return EDate;
    }

    public void setEDate(String EDate) {
        this.EDate = EDate;
    }

    public int getETime() {
        return ETime;
    }

    public void setETime(int ETime) {
        this.ETime = ETime;
    }

    public int getECalolies() {
        return ECalolies;
    }

    public void setECalolies(int ECalolies) {
        this.ECalolies = ECalolies;
    }

    @Override
    public String toString() {
        return "                  \uD83C\uDFC3\u200D♂\uFE0F"+ "\n"+
                "╭──────── · · 운동기록조회 · · ────────╮" +"\n"+
                "     기록번호 : " + ERNum +"\n" +
                "     아이디 : " + ERId + "\n" +
                "     운동번호 : " + ERHNum + "\n"+
                "     운동종류 : " + EExType + "\n" +
                "     운동시작일 : " + EDate + "\n" +
                "     하루운동시간 : " + ETime +"\n" +
                "     하루소모칼로리 : " + ECalolies +"\n"+
                "╰───────────────────────────────────╯";
    }
}
