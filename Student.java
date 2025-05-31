public class Student {
    String name;
    int age;
    int rollNo;
    String course;
    String department;
    public Student(String name, int age, int rollNo, String course, String department) {
        this.name = name;
        this.age = age;
        this.rollNo = rollNo;
        this.course = course;
        this.department = department;
    }
    public void displayInfo() {
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);
        System.out.println("Roll No    : " + rollNo);
        System.out.println("Course     : " + course);
        System.out.println("Department : " + department);
        System.out.println("---------------------------");
    }
    public static void main(String[] args) {
        Student[] students = new Student[25];
        students[0] = new Student("Alice", 20, 101, "B.Tech", "Computer Science");
        students[1] = new Student("Bob", 21, 102, "B.Tech", "Mechanical");
        students[2] = new Student("Charlie", 20, 103, "B.Sc", "Mathematics");
        students[3] = new Student("David", 22, 104, "B.Com", "Commerce");
        students[4] = new Student("Eva", 19, 105, "B.Tech", "Electronics");
        students[5] = new Student("Frank", 21, 106, "B.Sc", "Physics");
        students[6] = new Student("Grace", 20, 107, "B.A", "English");
        students[7] = new Student("Helen", 22, 108, "B.Tech", "Civil");
        students[8] = new Student("Ivy", 19, 109, "BBA", "Management");
        students[9] = new Student("Jack", 23, 110, "BCA", "Computer Applications");
        students[10] = new Student("Karan", 20, 111, "B.Tech", "IT");
        students[11] = new Student("Lara", 21, 112, "B.Sc", "Biology");
        students[12] = new Student("Mike", 22, 113, "B.Com", "Accounting");
        students[13] = new Student("Nina", 20, 114, "B.Sc", "Chemistry");
        students[14] = new Student("Oscar", 21, 115, "B.A", "History");
        students[15] = new Student("Paul", 22, 116, "B.Tech", "Mechanical");
        students[16] = new Student("Queen", 20, 117, "B.Tech", "Computer Science");
        students[17] = new Student("Ravi", 23, 118, "B.Sc", "Mathematics");
        students[18] = new Student("Sara", 19, 119, "BCA", "Computer Applications");
        students[19] = new Student("Tom", 21, 120, "BBA", "Management");
        students[20] = new Student("Uma", 22, 121, "B.Com", "Finance");
        students[21] = new Student("Vikram", 20, 122, "B.Tech", "IT");
        students[22] = new Student("Wendy", 21, 123, "B.A", "Psychology");
        students[23] = new Student("Xander", 22, 124, "B.Tech", "Civil");
        students[24] = new Student("Yara", 19, 125, "B.Sc", "Zoology");
        for (int i = 0; i < students.length; i++) {
            students[i].displayInfo();
        }
    }
}