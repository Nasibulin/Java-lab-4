public class Manager extends Employee implements ProjectShare {

    private double projectBonus;
    private static final double PROJECT_PERCENT = 0.075;
    private Project project;
    public static int count;


    public Manager(Integer id, String name, String position, Double hourlyRate) {
        super(id, name, position, hourlyRate);
    }

    public Manager(Integer id, String name, String position, Double hourlyRate, Integer actualHours) {
        super(id, name, position, hourlyRate, actualHours);
        count++;
    }

    @Override
    public double getProjectBonus() {
        projectBonus = ((project == null) ? 0 : project.getBudget()) * PROJECT_PERCENT;
        return projectBonus;

    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public double getSalary() {
        return getProjectBonus() + getHeadBonus();
    }
}