package ExDTO;



public class ExTypes {
    private int EXNUM;
    private String ExName;       // 운동이름
    private String ExCategory;   // 운동카테고리
    private int ExCalories;   // 칼로리소모량

    public int getEXNUM() {
        return EXNUM;
    }

    public void setEXNUM(int EXNUM) {
        this.EXNUM = EXNUM;
    }

    public String getExName() {
        return ExName;
    }

    public void setExName(String exName) {
        ExName = exName;
    }


    public String getExCategory() {
        return ExCategory;
    }

    public void setExCategory(String exCategory) {
        ExCategory = exCategory;
    }

    public int getExCalories() {
        return ExCalories;
    }

    public void setExCalories(int exCalories) {
        ExCalories = exCalories;
    }

    @Override
    public String toString() {
        return
                "운동종류 : " + ExCategory +
                "운동종류 : " + ExName;
    }
}

