package ExDTO;

public class EFood {
    private int FoNum;            // 음식 번호
    private String FoName;        // 음식 이름
    private String FoGrams;       // 중량
    private int FoCals;           // 칼로리
    private int FoProtein;        // 단백질
    private int FoCarbohydrates;   // 탄수화물
    private int FoFats;           // 지방

    public int getFoNum() {
        return FoNum;
    }

    public void setFoNum(int foNum) {
        FoNum = foNum;
    }

    public String getFoName() {
        return FoName;
    }

    public void setFoName(String foName) {
        FoName = foName;
    }

    public String getFoGrams() {
        return FoGrams;
    }

    public void setFoGrams(String foGrams) {
        FoGrams = foGrams;
    }

    public int getFoCals() {
        return FoCals;
    }

    public void setFoCals(int foCals) {
        FoCals = foCals;
    }

    public int getFoProtein() {
        return FoProtein;
    }

    public void setFoProtein(int foProtein) {
        FoProtein = foProtein;
    }

    public int getFoCarbohydrates() {
        return FoCarbohydrates;
    }

    public void setFoCarbohydrates(int foCarbohydrates) {
        FoCarbohydrates = foCarbohydrates;
    }

    public int getFoFats() {
        return FoFats;
    }

    public void setFoFats(int foFats) {
        FoFats = foFats;
    }

    @Override
    public String toString() {
        return "EFood{" +
                "FoNum=" + FoNum +
                ", FoName='" + FoName + '\'' +
                ", FoGrams='" + FoGrams + '\'' +
                ", FoCals=" + FoCals +
                ", FoProtein=" + FoProtein +
                ", FoCarbohydrates=" + FoCarbohydrates +
                ", FoFats=" + FoFats +
                '}';
    }
}