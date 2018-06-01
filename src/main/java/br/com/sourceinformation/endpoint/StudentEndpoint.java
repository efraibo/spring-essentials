package br.com.sourceinformation.endpoint;

import br.com.sourceinformation.endpoint.error.CustomErrorType;
import br.com.sourceinformation.model.Student;
import br.com.sourceinformation.repository.StundentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author William Suane for DevDojo on 6/5/17.
 */
@RestController
@RequestMapping("students")
public class StudentEndpoint {

    private final StundentRepository repository;

    //colocando injeção de dependencia no construtor para sair o warning
    @Autowired
    public StudentEndpoint(StundentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        Student student = repository.findById(id).get();
        if (student == null) {
            return new ResponseEntity<>(new CustomErrorType("Student Not Found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        return new ResponseEntity<>(repository.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        return new ResponseEntity<>(repository.save(student), HttpStatus.OK);
    }

}
