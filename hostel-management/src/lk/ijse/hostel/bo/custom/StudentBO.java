package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    Boolean addStudent(StudentDTO studentDTO);

    Boolean deleteStudent(StudentDTO studentDTO);

    ArrayList<StudentDTO> getStudentData();

    String getCurrentID();

    Boolean updateStudent(StudentDTO studentDTO);
}
