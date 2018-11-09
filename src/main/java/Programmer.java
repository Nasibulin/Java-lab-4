public class Programmer extends Engineer {
    public int count;

    public Programmer(Integer id, String name, String position, Double regularHourlyRate, Integer actualHours,
                      Double overtimeMultiplier,
                      Double projectPercent) {
        super(id, name, position, regularHourlyRate, actualHours, overtimeMultiplier, projectPercent);
    }

    public Programmer(Integer id, String name, String position, Double regularHourlyRate, Integer actualHours) {
        super(id, name, position, regularHourlyRate, actualHours);
        count++;
    }

}