public class Personal extends Employee implements WorkTime {
    private double hourlyRate;
    private double actualHours;

    public Personal(Integer id, String name, String position, Double hourlyRate, Integer actualHours) {
        super(id, name, position, hourlyRate, actualHours);
        this.hourlyRate = hourlyRate;
        this.actualHours = actualHours;
    }


    @Override
    public double getBasicSalary() {
        return actualHours * hourlyRate;
    }

    @Override
    public double getSalary() {
        return getBasicSalary();
    }

}