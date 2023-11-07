public class NodeState {
    public int food; 
    public int prosperity;
    public int materials;
    public int money_spent;

    public NodeState(int prosperity, int food, int materials, int energy, int money_spent){
        this.food = food;
        this.materials = materials;
        this.money_spent = money_spent;
        this.prosperity = prosperity;
    }
}
