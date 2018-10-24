public interface WorkTime {

    public default double getBasicSalary() {
        return 0;
    }

    ;

    public default double getOvertimeSalary() {
        return 0;
    }

    public default void setOvertimeHours(int overtimeHours) {
    }

    public default int getOvertimeHours() {
        return 0;
    }


}