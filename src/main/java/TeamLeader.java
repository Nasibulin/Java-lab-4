public class TeamLeader extends Programmer implements Heading {

    private static int HEAD_RATE = 1000;
    private double headBonus;


    public TeamLeader(Integer id, String name, String position, Double regularHourlyRate, Integer actualHours) {
        super(id, name, position, regularHourlyRate, actualHours);
    }

    public double getHeadBonus() {
        headBonus = HEAD_RATE * (Engineer.count - 1);
        return headBonus;
    }

}