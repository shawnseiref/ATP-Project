package algorithms.search;


import java.util.Objects;

public abstract class AState{

    private String state;
    private double cost;
    private AState prev;

    public AState(String state, double cost, AState prev) {
        this.state = state;
        this.cost = cost;
        this.prev = prev;
    }

    public AState(AState other) {
        state = other.state;
        cost = other.cost;
        prev = other.prev;
    }


    public String getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    public AState getPrev() {
        return prev;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setPrev(AState prev) {
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AState)) return false;
        AState aState = (AState) o;
        return aState.hashCode()==hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}

