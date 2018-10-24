public class Engineer extends Employee implements ProjectShare, WorkTime {

    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final double PROJECT_PERCENT = 0.01;
    private double projectBonus;
    private double overtimeSalary;
    private int overtimeHours;
    private Project project;
    public static int count;


    public Engineer(Integer id, String name, String position, Double regularHourlyRate, Integer actualHours,
                    Double overtimeMultiplier,
                    Double projectPercent) {
        super(id, name, position, regularHourlyRate, actualHours);
    }

    public Engineer(Integer id, String name, String position, Double regularHourlyRate, Integer actualHours) {
        super(id, name, position, regularHourlyRate, actualHours);
        count++;
    }

    @Override
    public int getOvertimeHours() {
        return overtimeHours;
    }

    @Override
    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double getProjectBonus() {
        projectBonus = ((project == null) ? 0 : project.getBudget()) * PROJECT_PERCENT;
        return projectBonus;

    }

    @Override
    public double getBasicSalary() {
        return super.getActualHours() * super.getHourlyRate();
    }

    @Override
    public double getOvertimeSalary() {
        overtimeSalary = overtimeHours * OVERTIME_MULTIPLIER * super.getHourlyRate();
        return overtimeSalary;
    }

    @Override
    public double getSalary() {
        return getBasicSalary() + getOvertimeSalary() + getProjectBonus() + getHeadBonus();
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