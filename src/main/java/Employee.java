public class Employee implements Comparable<Employee>, WorkTime, ProjectShare, Heading {

    private static final int WORKDAY_DURATION = 8;
    private static int businessDays;
    private static int businessHours;
    private int id;
    private String name;
    private String position;
    private double salary;
    private double hourlyRate;
    private int actualHours;
    private int actualDays;
    public static int count;

    public Employee(Integer id, String name, String position, Double hourlyRate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.hourlyRate = hourlyRate;
    }

    public Employee(Integer id, String name, String position, Double hourlyRate, Integer actualHours) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.hourlyRate = hourlyRate;
        this.actualHours = actualHours;
        count++;
    }

    public static int getBusinessDays() {
        return businessDays;
    }

    public static void setBusinessDays(int bd) {
        businessDays = bd;
        businessHours = bd * WORKDAY_DURATION;
    }

    public static int getBusinessHours() {
        return businessHours;
    }

    public static void setBusinessHours(int bh) {
        businessHours = bh;
        businessDays = businessHours / WORKDAY_DURATION;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getSalary() {
        return hourlyRate * actualHours;
    }


    public int getActualHours() {
        return actualHours;
    }

    public void setActualHours(int actualHours) {
        this.actualHours = actualHours;
        this.actualDays = actualHours / WORKDAY_DURATION;
    }

    public int getActualDays() {
        return actualDays;
    }

    public void setActualDays(int actualDays) {
        this.actualDays = actualDays;
        this.actualDays = actualHours / WORKDAY_DURATION;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Employee o) {
        if (this.getId() < o.getId()) {
            return -1;
        } else {
            return 1;
        }
    }
}