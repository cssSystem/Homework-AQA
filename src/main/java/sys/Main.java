package sys;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        System.out.println("Задание 1");
        Set<Student> students = new HashSet<>();

        var student = new Student("Вася", "1IT", 3);
        student.addSubjectGrade("Математика", 5);
        student.addSubjectGrade("Математика", 4);
        student.addSubjectGrade("Математика", 3);
        student.addSubjectGrade("Математика", 2);
        student.addSubjectGrade("Математика", 1);
        students.add(student);

        student = new Student("Лиза", "1IT", 3);
        student.addSubjectGrade("Математика", 5);
        student.addSubjectGrade("Математика", 4);
        student.addSubjectGrade("Математика", 3);
        student.addSubjectGrade("Математика", 4);
        student.addSubjectGrade("Математика", 5);
        students.add(student);

        student = new Student("Кирил", "2IT", 3);
        student.addSubjectGrade("История", 2);
        student.addSubjectGrade("Математика", 2);
        student.addSubjectGrade("Геометрия", 3);
        student.addSubjectGrade("Информатика", 4);
        student.addSubjectGrade("Право", 2);
        students.add(student);

        Student.printStudents(students, 3);
        System.out.println(students.size());
        Student.deleteStudent(students, 3);
        Student.printStudents(students, 3);
        System.out.println(students.size());
        Student.nextCurse(students, 3);
        Student.printStudents(students, 4);
        System.out.println(students.size());

        System.out.println("\nЗадание 2");
        var list = new DirectoryOfTelephoneNumbers();
        list.add("Вася", "89111111111");
        list.add("Вася", "89222222222");
        list.add("Вася", "89333333333");
        list.add("Натася", "89444444444");
        list.add("Мася", "89555555555");

        System.out.println(list.get("Вася"));

    }


}
