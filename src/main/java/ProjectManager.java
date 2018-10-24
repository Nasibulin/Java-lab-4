public class ProjectManager extends Manager implements Heading {

    private static final double PROJECT_PERCENT = 0.1;
    private static final int HEAD_RATE = 4000;
    private Project project;
    private double projectBonus;
    private double headBonus;


    public ProjectManager(Integer id, String name, String position, Double hourlyRate, Integer actualHours) {
        super(id, name, position, hourlyRate, actualHours);
    }

    @Override
    public double getHeadBonus() {
        headBonus = HEAD_RATE * (Manager.count - 2);
        return headBonus;
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

}