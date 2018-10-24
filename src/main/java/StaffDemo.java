import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StaffDemo {
    private static final String PACKAGE_PREFIX = "";
    private static final String STAFF_FILE = "Staff.json";
    private static final String STAFF_PATH = System.getProperty("user.dir") + "/db/" + STAFF_FILE;
    private static HashMap<String, String> jobToClass;
    private static List<Employee> staff = new ArrayList<Employee>();
    private static HashMap<Integer, String> pos = new HashMap<Integer, String>();
    private static HashMap<Integer, Project> projects = new HashMap<Integer, Project>();

    static {
        jobToClass = new HashMap<String, String>();
        jobToClass.put("Уборщица", "Cleaner");
        jobToClass.put("Водитель", "Driver");
        jobToClass.put("Инженер-программист", "Programmer");
        jobToClass.put("Ведущий программист", "TeamLeader");
        jobToClass.put("Инженер по тестированию", "Tester");
        jobToClass.put("Инженер", "Engineer");
        jobToClass.put("Менеджер", "Manager");
        jobToClass.put("Менеджер проекта", "ProjectManager");
        jobToClass.put("Руководитель направления", "SeniorManager");
    }

    public static void main(
            String[] args) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        StaffDemo sd = new StaffDemo();
        Employee.setBusinessHours(176);
        sd.importPositions();
        sd.importProjects();
        sd.importStaff();
        Collections.sort(staff);
        sd.printSummary();
    }

    void printSummary() {
        double summary = 0.0;
        class Format {
            private Formatter f = new Formatter(System.out);

            private void printTitle(String title) {
                f.format("\n%-22s\n", title);
                f.format(
                        "------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            }

            private void printHeader() {
                f.format("%-3s %-27s %-27s %-6s %-6s %-5s %-7s %-10s %-7s %-11s %-9s %-6s %-6s\n", "ID", "ФИО",
                         "Должность", "План", "Факт", "Сверх.", "Почас.", "Тариф", "Сверх.", "Проект", "Бонус",
                         "Руковод.", "Итого:");
                f.format(
                        "------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            }

            private void printBody(int id, String name, String pos, int bh, int ah, int oth, double hr, double bs,
                                   double ots, String proj, double pb, double hb, double s) {
                f.format("%-3d %-27s %-27s %-6d %-6d %-5d %7.2f %9.2f %9.2f %-10s %9.2f %9.2f %9.2f\n", id, name, pos,
                         bh, ah, oth, hr, bs, ots, proj, pb, hb, s);
            }

            private void printFooter(double summary) {
                f.format(
                        "------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                f.format("%-137s %5.2f\n", "Итого по ведомости:", summary);

            }
        }
        Format format = new Format();

        format.printTitle("Ведомость заработной платы:");
        format.printHeader();

        for (Employee e : staff) {
            format.printBody(e.getId(), e.getName(), e.getPosition(), e.getBusinessHours(), e.getActualHours(),
                             e.getOvertimeHours(), e.getHourlyRate(), e.getBasicSalary(), e.getOvertimeSalary(),
                             ((e.getProject() == null) ? '-' : e.getProject()).toString(), e.getProjectBonus(),
                             e.getHeadBonus(), e.getSalary());

            summary += e.getSalary();

        }
        format.printFooter(summary);

    }

    public void importPositions() {
        pos.clear();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(STAFF_PATH));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray posArray = (JSONArray) jsonObject.get("Positions");

            Iterator<JSONArray> iterator = posArray.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                iterator.next();
                JSONObject posData = (JSONObject) posArray.get(i);
                Integer id = ((Long) posData.get("id")).intValue();
                String position = (String) posData.get("position");
                pos.put(id, position);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void importProjects() {
        projects.clear();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(STAFF_PATH));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray posArray = (JSONArray) jsonObject.get("Projects");

            Iterator<JSONArray> iterator = posArray.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                iterator.next();
                JSONObject posData = (JSONObject) posArray.get(i);
                int id = ((Long) posData.get("id")).intValue();
                String title = (String) posData.get("title");
                int budget = ((Long) posData.get("budget")).intValue();
                projects.put(id, new Project(id, title, budget));
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void importStaff() {
        staff.clear();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(STAFF_PATH));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray staffArray = (JSONArray) jsonObject.get("Staff");

            Iterator<JSONArray> iterator = staffArray.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                iterator.next();
                JSONObject staffData = (JSONObject) staffArray.get(i);
                int id = ((Long) staffData.get("staff_id")).intValue();
                String fio = (String) staffData.get("fio");
                int position = ((Long) staffData.get("position")).intValue();
                double hourly_rate = ((Long) (staffData.get("hourly_rate") == null ? 0L : staffData.get(
                        "hourly_rate"))).doubleValue();
                int actual_hours = ((Long) (staffData.get("actual_hours") == null ? 0L : staffData.get(
                        "actual_hours"))).intValue();
                int ot_hours = ((Long) (staffData.get("ot_hours") == null ? 0L : staffData.get(
                        "ot_hours"))).intValue();
                int project = ((Long) (staffData.get("project") == null ? 0L : staffData.get(
                        "project"))).intValue();
                //System.out.println(pos.get(position));
                Class<?> localstaff = Class.forName(PACKAGE_PREFIX + jobToClass.get(pos.get(position)));
                Constructor<?> ctor = localstaff.getConstructor(Integer.class, String.class, String.class, Double.class,
                                                                Integer.class);

                Object object = ctor.newInstance(new Object[]{id, fio, pos.get(position), hourly_rate, actual_hours});
                Employee emp = (Employee) object;
                emp.setOvertimeHours(ot_hours);
                emp.setProject(projects.get(project));

                staff.add(emp);
                i++;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

