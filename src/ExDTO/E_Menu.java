package ExDTO;

public class E_Menu {

    private int EMNUM;
    private String EMID;
    private String EMDate;           // 날짜
    private int ECalories;          // 칼로리
    private int EProtein;           // 단백질
    private int ECarbohydrates;     // 탄수화물
    private int EFats;              // 지방
    private int EfNum;

    public int getEMNUM() {
        return EMNUM;
    }

    public void setEMNUM(int EMNUM) {
        this.EMNUM = EMNUM;
    }

    public String getEMID() {
        return EMID;
    }

    public void setEMID(String EMID) {
        this.EMID = EMID;
    }

    public String getEMDate() {
        return EMDate;
    }

    public void setEMDate(String EDate) {
        this.EMDate = EDate;
    }

    public int getECalories() {
        return ECalories;
    }

    public void setECalories(int ECalories) {
        this.ECalories = ECalories;
    }

    public int getEProtein() {
        return EProtein;
    }

    public void setEProtein(int EProtein) {
        this.EProtein = EProtein;
    }

    public int getECarbohydrates() {
        return ECarbohydrates;
    }

    public void setECarbohydrates(int ECarbohydrates) {
        this.ECarbohydrates = ECarbohydrates;
    }

    public int getEFats() {
        return EFats;
    }

    public void setEFats(int EFats) {
        this.EFats = EFats;
    }

    public int getEfNum() {
        return EfNum;
    }

    public void setEfNum(int efNum) {
        EfNum = efNum;
    }


    @Override
    public String toString() {
        return "E_Menu{" +
                "EMNUM=" + EMNUM +
                ", EMID='" + EMID + '\'' +
                ", EMDate='" + EMDate + '\'' +
                ", ECalories=" + ECalories +
                ", EProtein=" + EProtein +
                ", ECarbohydrates=" + ECarbohydrates +
                ", EFats=" + EFats +
                ", EfNum=" + EfNum +
                '}';
    }
}
