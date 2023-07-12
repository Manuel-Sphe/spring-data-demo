package sphe.dev.restdemo.service.dto.mapper;

import org.mapstruct.Mapper;
import sphe.dev.restdemo.entity.Student;
import sphe.dev.restdemo.service.dto.StudentDTO;

@Mapper(componentModel ="spring")
public interface StudentMapper extends EntityMapper<StudentDTO , Student>{}
