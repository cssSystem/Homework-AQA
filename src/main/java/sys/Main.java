package sys;

public class Main {
    public static void main(String[] args) {

        // Задание 1
//        Set<Student> students = new HashSet<>();
//
//        students.add(new Student("Вася", "1IT", 1, 4.5f));
//        students.add(new Student("Лиза", "2IT", 3, 3.5f));
//        students.add(new Student("Кирил", "1IT", 4, 2.5f));
//        students.add(new Student("Афдосия", "1DiG", 3, 1.5f));
//        students.add(new Student("Вахтанг", "Per1_it", 2, 4.5f));
//
//
//        Student.printStudents(students, 3);
//        System.out.println(students.size());
//        Student.deleteStudent(students, 3);
//        Student.printStudents(students, 3);
//        System.out.println(students.size());
//        Student.nextCurse(students, 3);
//        Student.printStudents(students, 4);
//        System.out.println(students.size());

        // Задание 2
        var list = new DirectoryOfTelephoneNumbers();
        list.add("Вася", "89111111111");
        list.add("Вася", "89222222222");
        list.add("Вася", "89333333333");
        list.add("Натася", "89444444444");
        list.add("Мася", "89555555555");

        System.out.println(list.get("Вася"));

    }


}
