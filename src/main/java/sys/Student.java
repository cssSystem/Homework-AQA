package sys;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private String name, group;
    private int course;
    private float subjectGrades;

    public Student(String name, String group, int course, float subjectGrades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.subjectGrades = subjectGrades;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public float getSubjectGrades() {
        return subjectGrades;
    }

    public static void deleteStudent(Set<Student> students, float averageScore) {
        if (students == null) {
            return;
        }
        Set<Student> studentsToDelete = new HashSet<>();
        for (Student student : students) {
            if (student.subjectGrades < averageScore) {
                studentsToDelete.add(student);
            }
        }
        for (Student student : studentsToDelete) {
            students.remove(student);
        }
    }

    public static void nextCurse(Set<Student> students, float averageScore) {
        if (students == null) {
            return;
        }
        for (Student student : students) {
            if (student.subjectGrades >= averageScore) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        if (students == null) {
            return;
        }
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }
}
