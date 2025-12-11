package sys;

import java.util.*;

public class Student {
    private String name, group;
    private int course;
    private Map<String, List<Integer>> subjectGrades;

    public Student(String name, String group, int course) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.subjectGrades = new HashMap<>();
    }

    public void addSubjectGrade(String subject, int grade) {
        if (subjectGrades.containsKey(subject)) {
            subjectGrades.get(subject).add(grade);
        } else {
            List<Integer> grades = new ArrayList<>();
            grades.add(grade);
            subjectGrades.put(subject, grades);
        }
    }

    public float getAverageScore() {
        var averageScore = 0;
        var count = 0;
        for (String subject : subjectGrades.keySet()) {
            for (Integer grade : subjectGrades.get(subject)) {
                averageScore += grade;
                count++;
            }
        }
        return averageScore / count;
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

    public static void deleteStudent(Set<Student> students, float averageScore) {
        if (students == null) {
            return;
        }
        Set<Student> studentsToDelete = new HashSet<>();
        for (Student student : students) {
            if (student.getAverageScore() < averageScore) {
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
            if (student.getAverageScore() >= averageScore) {
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
