public class SeniorManager extends ProjectManager {

    private static final double PROJECT_PERCENT = 0.1;
    private static final int HEAD_RATE = 4000;
    private double headBonus;


    public SeniorManager(Integer id, String name, String position, Double hourlyRate, Integer actualHours) {
        super(id, name, position, hourlyRate, actualHours);
    }

    @Override
    public double getHeadBonus() {
        headBonus = HEAD_RATE * (Employee.count - 1);
        return headBonus;
    }

}