package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dao.FactoryDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;

import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.Types.STUDENT);

    public Boolean addStudent(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact_no(),
                studentDTO.getDob(),
                studentDTO.getGender());

        return studentDAO.add(student);
    }

    public Boolean deleteStudent(StudentDTO studentDTO) {
        return studentDAO.delete(studentDTO.getId());
    }

    public ArrayList<StudentDTO> getStudentData() {
        ArrayList<StudentDTO> StudentDTOs = new ArrayList<>();
        ArrayList<Student> studentData = studentDAO.getData();

        for (Student std : studentData) {
            StudentDTOs.add(new StudentDTO(std.getId(),
                    std.getName(), std.getAddress(),
                    std.getContact_no(),
                    std.getDob(),
                    std.getGender()));
        }
        return StudentDTOs;
    }

    public String getCurrentID() {
        return studentDAO.getCurrentID();
    }

    @Override
    public Boolean updateStudent(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact_no(),
                studentDTO.getDob(),
                studentDTO.getGender());

        return studentDAO.update(student);
    }
}
