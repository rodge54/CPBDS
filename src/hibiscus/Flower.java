package hibiscus;

public class Flower {

    private int flowerId;
    private int momId;
    private int dadId;
    private int hybridizerId;
    private int growerId;
    private String name;
    private String mainColor;
    private String freeText;
    private String momName;
    private String dadName;
    private String hybridizerName;
    private String growerName;

    public Flower(int flowerId, int momId, int dadId, int hybridizerId, int growerId, String name, String mainColor,
                  String freeText, String momName, String dadName, String hybridizerName, String growerName) {
        this.flowerId = flowerId;
        this.momId = momId;
        this.dadId = dadId;
        this.hybridizerId = hybridizerId;
        this.growerId = growerId;
        this.name = name;
        this.mainColor = mainColor;
        this.freeText = freeText;
        this.momName = momName;
        this.dadName = dadName;
        this.hybridizerName = hybridizerName;
        this.growerName = growerName;
    }

    public int getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public int getMomId() {
        return momId;
    }

    public void setMomId(int momId) {
        this.momId = momId;
    }

    public int getDadId() {
        return dadId;
    }

    public void setDadId(int dadId) {
        this.dadId = dadId;
    }

    public int getHybridizerId() {
        return hybridizerId;
    }

    public void setHybridizerId(int hybridizerId) {
        this.hybridizerId = hybridizerId;
    }

    public int getGrowerId() {
        return growerId;
    }

    public void setGrowerId(int growerId) {
        this.growerId = growerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    // TODO: This method needs to call the database and retrieve the mom flowers name or
    //  assign this name when flower is created
    public String getMomName(){

        if (momName == null){
            return "UNK";
        }
        else {
            return momName;
        }
    }

    public String getDadName(){
        if (dadName == null){
            return "UNK";
        }
        else {
            return dadName;
        }
    }

    public String getHybridizerName(){
        if (hybridizerName == null){
            return "UNK";
        }
        else {
            return hybridizerName;
        }
    }

    public String getGrowerName(){
        if (growerName == null){
            return "UNK";
        }
        else {
            return growerName;
        }
    }

    public void printFlower() {
        System.out.println("Flower:" +
                "Flower Id: " + flowerId + "\n" +
                "Mom Id: " + momId + "\n" +
                "Dad Id: " + dadId + "\n" +
                "Hybridizer Id: " + hybridizerId + "\n" +
                "Grower Id: " + growerId + "\n" +
                "Name: " + name + "\n" +
                "Main Color: " + mainColor + "\n" +
                "Free Text: " + freeText + "\n" +
                "Mom Name: " + getMomName() + "\n" +
                "Dad Name: " + getDadName() + "\n" +
                "Hybridizer Name: " + getHybridizerName() + "\n" +
                "Grower Name: " + getGrowerName() + "\n");
    }

    @Override
    public String toString() {
        return getMomName();
    }
}

