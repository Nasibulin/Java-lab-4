import java.util.List;

public class Project {
    private int id;
    private String title;
    private int budget;
    private List<Engineer> engineers;
    private List<Manager> managers;

    public Project(int id, String title, int budget) {
        this.id = id;
        this.title = title;
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return (title == null) ? "" : title;
    }

}
