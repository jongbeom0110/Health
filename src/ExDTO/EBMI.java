package ExDTO;

public class EBMI {
    int EBmiNum;
    int EBmiCode;
    String EBmiId;
    String EBmiName;
    String EBmiScale;


    public int getEBmiNum() {
        return EBmiNum;
    }

    public void setEBmiNum(int EBmiNum) {
        this.EBmiNum = EBmiNum;
    }

    public String getEBmiId() {
        return EBmiId;
    }

    public void setEBmiId(String EBmiId) {
        this.EBmiId = EBmiId;
    }

    public String getEBmiName() {
        return EBmiName;
    }

    public void setEBmiName(String EBmiName) {
        this.EBmiName = EBmiName;
    }

    public String getEBmiScale() {
        return EBmiScale;
    }

    public void setEBmiScale(String EBmiScale) {
        this.EBmiScale = EBmiScale;
    }

    public int getEBmiCode() {
        return EBmiCode;
    }

    public void setEBmiCode(int EBmiCode) {
        this.EBmiCode = EBmiCode;
    }



    @Override
    public String toString() {
        return "BMI 『" +
                "EBmiNum=" + EBmiNum +
                ", EBmiId='" + EBmiId + '\'' +
                ", EBmiName='" + EBmiName + '\'' +
                ", EBmiScale='" + EBmiScale + '\'' +
                ", EBmiCode=" + EBmiCode +
                "』";
    }


}
