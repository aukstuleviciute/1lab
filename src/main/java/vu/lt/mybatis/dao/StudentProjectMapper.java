package vu.lt.mybatis.dao;

import java.util.List;
import vu.lt.mybatis.model.StudentProject;

public interface StudentProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT_PROJECT
     *
     * @mbg.generated Sat Mar 23 23:07:25 EET 2019
     */
    int insert(StudentProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT_PROJECT
     *
     * @mbg.generated Sat Mar 23 23:07:25 EET 2019
     */
    List<StudentProject> selectAll();
}