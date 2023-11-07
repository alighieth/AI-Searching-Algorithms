public class ProblemTree {
    private static ProblemTree instance;

    public static int initialProsperity;
    public static int initialFood;
    public static int initialMaterials;
    public static int initialEnergy;
    public static int unitPriceMaterials;
    public static int unitPriceFood;
    public static int unitPriceEnergy;
    public static int amountRequestFood;
    public static int amountRequestMaterials;
    public static int delayRequestFood;    
    public static int delayRequestMaterials;   
    public static int amountRequestEnergy;
    public static int foodUseBUILD1;
    public static int priceBUILD1;
    public static int priceBUILD2;   
    public static int materialsUseBUILD1;
    public static int energyUseBUILD1;
    public static int foodUseBUILD2;
    public static int delayRequestEnergy;
    public static int prosperityBUILD1;
    public static int materialsUseBUILD2;    
    public static int energyUseBUILD2;
    public static int prosperityBUILD2;
    public static Node currentNode = null;

    private ProblemTree(String initialState) {
        this.setAttributesFromString(initialState);
    }

    public void setAttributesFromString(String inputString) {
        String[] parts = inputString.split(";");
        if (parts.length == 8) { 
            ProblemTree.initialProsperity = Integer.parseInt(parts[0]);

            String[] initalFoodMatEnrgy = parts[1].split(",");
            ProblemTree.initialFood = Integer.parseInt(initalFoodMatEnrgy[0]);
            ProblemTree.initialMaterials = Integer.parseInt(initalFoodMatEnrgy[1]);
            ProblemTree.initialEnergy = Integer.parseInt(initalFoodMatEnrgy[2]);

            String[] initialUnitPrices = parts[2].split(",");
            ProblemTree.unitPriceFood = Integer.parseInt(initialUnitPrices[0]);
            ProblemTree.unitPriceMaterials = Integer.parseInt(initialUnitPrices[1]);
            ProblemTree.unitPriceEnergy = Integer.parseInt(initialUnitPrices[2]);

            String[] initialRequestFood = parts[3].split(",");
            ProblemTree.amountRequestFood = Integer.parseInt(initialRequestFood[0]);
            ProblemTree.delayRequestFood = Integer.parseInt(initialRequestFood[1]);

            String[] initialRequestMaterial = parts[4].split(",");
            ProblemTree.amountRequestMaterials = Integer.parseInt(initialRequestMaterial[0]);
            ProblemTree.delayRequestMaterials = Integer.parseInt(initialRequestMaterial[1]);

            String[] initialRequestEnergy = parts[5].split(",");
            ProblemTree.amountRequestEnergy = Integer.parseInt(initialRequestEnergy[0]);            
            ProblemTree.delayRequestEnergy = Integer.parseInt(initialRequestEnergy[1]);

            String[] initialBuild1 = parts[6].split(",");
            ProblemTree.priceBUILD1 = Integer.parseInt(initialBuild1[0]);
            ProblemTree.foodUseBUILD1 = Integer.parseInt(initialBuild1[1]);
            ProblemTree.materialsUseBUILD1 = Integer.parseInt(initialBuild1[2]);
            ProblemTree.energyUseBUILD1 = Integer.parseInt(initialBuild1[3]);
            ProblemTree.prosperityBUILD1 = Integer.parseInt(initialBuild1[4]);

            String[] initialBuild2 = parts[7].split(",");
            ProblemTree.priceBUILD2 = Integer.parseInt(initialBuild2[0]);
            ProblemTree.foodUseBUILD2 = Integer.parseInt(initialBuild2[1]);
            ProblemTree.materialsUseBUILD2 = Integer.parseInt(initialBuild2[2]);
            ProblemTree.energyUseBUILD2 = Integer.parseInt(initialBuild2[3]);
            ProblemTree.prosperityBUILD2 = Integer.parseInt(initialBuild2[4]);

        } else {
            System.err.println("Input string has an incorrect number of parts.");
        }
    }

    public static ProblemTree getInstance(String initialState) {
        if (instance == null) {
            instance = new ProblemTree(initialState);
        }
        return instance;
    }

    
}
