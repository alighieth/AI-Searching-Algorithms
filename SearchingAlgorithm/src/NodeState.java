public class NodeState {
    public int food;    
    public int energy; 
    public int prosperity;
    public int materials;
    public int money_spent;
    public int depth;

    public NodeState(int prosperity, int food, int materials, int energy, int money_spent){
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.money_spent = money_spent;
        this.prosperity = prosperity;
    }
}
