package gr.codehub.universitydb.model;

import gr.codehub.universitydb.repository.StudentRepository;

public class Student {

    private int studentId;

    private String studentName;
    private int studentDepartmentId;
    private String studentDepartment;

    public Student(int studentId, String studentName, int studentDepartmentId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentDepartmentId = studentDepartmentId;
    }

    public int getStudentId() {
        return studentId;
    }


    public int getStudentDepartment() {
        return studentDepartmentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentDepartment(int studentDepartment) {
        this.studentDepartmentId = studentDepartment;
    }

    public void setStudentDepartmentName(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public String getStudentDepartmentName() {
      return studentDepartment;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentDepartmentId=" + studentDepartmentId +
                ", studentDepartmentName=" + studentDepartment +
                '}';
    }
}
