import java.util.HashMap;
import java.util.ArrayList;

class Project {
    private String projectCode;
    private String projectName;
    private int projectStrength;

    public Project(String projectCode, String projectName, int projectStrength) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectStrength = projectStrength;
    }

    // Getters and setters
}

class Employee {
    private String employeeId;
    private String employeeName;
    private String employeePhone;
    private String employeeAddress;
    private int employeeSalary;

    public Employee(String employeeId, String employeeName, String employeePhone, String employeeAddress, int employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeAddress = employeeAddress;
        this.employeeSalary = employeeSalary;
    }

    // Getters and setters
}

class Thread1 implements Runnable {
    private HashMap<Project, ArrayList<Employee>> hMap;

    public Thread1(HashMap<Project, ArrayList<Employee>> hMap) {
        this.hMap = hMap;
    }

    @Override
    public void run() {
        Producer producer = new Producer(hMap);
        producer.serialize();
        Consumer consumer = new Consumer(hMap);
        consumer.deserialize();
    }
}

class Producer {
    private HashMap<Project, ArrayList<Employee>> hMap;

    public Producer(HashMap<Project, ArrayList<Employee>> hMap) {
        this.hMap = hMap;
    }

    public void serialize() {
        synchronized (hMap) {
            System.out.println("Serialize called by Producer");
            System.out.println("Serialized Data: ");
            System.out.println(hMap);
        }
    }
}

class Consumer {
    private HashMap<Project, ArrayList<Employee>> hMap;

    public Consumer(HashMap<Project, ArrayList<Employee>> hMap) {
        this.hMap = hMap;
    }

    public void deserialize() {
        synchronized (hMap) {
            System.out.println("Deserialize Called by Consumer");
            System.out.println("DeSerialized Data: ");
            System.out.println(hMap);
        }
    }
}

public class InterThreadCom {
    public static void main(String[] args) {
        // Sample data
        HashMap<Project, ArrayList<Employee>> hMap1 = new HashMap<>();
        HashMap<Project, ArrayList<Employee>> hMap2 = new HashMap<>();
        HashMap<Project, ArrayList<Employee>> hMap3 = new HashMap<>();

        // Populate sample data
        // ...

        // Create threads and start serialization and deserialization
        Thread1 obj1 = new Thread1(hMap1);
        Thread1 obj2 = new Thread1(hMap2);
        Thread1 obj3 = new Thread1(hMap3);

        // Start serialization and deserialization threads
        Thread thread1 = new Thread(obj1);
        Thread thread2 = new Thread(obj2);
        Thread thread3 = new Thread(obj3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
