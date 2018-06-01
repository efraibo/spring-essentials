package br.com.sourceinformation.repository;

import br.com.sourceinformation.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StundentRepository extends CrudRepository<Student, Long>{
    List<Student> findByName(String name);
}
