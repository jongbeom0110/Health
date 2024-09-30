package ExDTO;

public class EMember {
    private String EId;     //회원 아이디
    private String EPw;     //회원 비번
    private String EName;   //회원 이름
    private int EAge;       //회원 나이
    private String EGender; //회원 성별
    private String EEmail;  //회원 이메일
    private String EPhone;  //회원 전화번호
    private int EHeight;    //회원 키
    private int EWeight;    //회원 몸무게

    public String getEId() {
        return EId;
    }

    public void setEId(String EId) {
        this.EId = EId;
    }

    public String getEPw() {
        return EPw;
    }

    public void setEPw(String EPw) {
        this.EPw = EPw;
    }

    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    public int getEAge() {
        return EAge;
    }

    public void setEAge(int EAge) {
        this.EAge = EAge;
    }
    public String getEGender() {
        return EGender;
    }

    public void setEGender(String EGender) {
        this.EGender = EGender;
    }
    public String getEEmail() {
        return EEmail;
    }

    public void setEEmail(String EEmail) {
        this.EEmail = EEmail;
    }

    public String getEPhone() {
        return EPhone;
    }

    public void setEPhone(String EPhone) {
        this.EPhone = EPhone;
    }

    public int getEHeight() {
        return EHeight;
    }

    public void setEHeight(int EHeight) {
        this.EHeight = EHeight;
    }

    public int getEWeight() {
        return EWeight;
    }

    public void setEWeight(int EWeight) {
        this.EWeight = EWeight;
    }

    @Override
    public String toString() {
        return "┏━━━━━━━━━━━━━━━내정보━━━━━━━━━━━━━━┓\n" +
                "           아이디 : ["+EId+"]\n" +
                "           비밀번호 : ["+EPw+"]\n" +
                "           이름 : ["+EName+"]\n" +
                "           나이 : ["+EAge+"]\n" +
                "           성별 : ["+EGender+"]\n" +
                "           이메일 : ["+EEmail+"]\n" +
                "           연락처 : ["+EPhone+"]\n" +
                "           키 : ["+EHeight+"]\n" +
                "           몸무게 : ["+EWeight+"]\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
    }
}
