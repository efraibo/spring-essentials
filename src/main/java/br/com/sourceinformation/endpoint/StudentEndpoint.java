package br.com.sourceinformation.endpoint;

import br.com.sourceinformation.error.ResourceNotFoundException;
import br.com.sourceinformation.model.Student;
import br.com.sourceinformation.repository.StundentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        verifyIfStudentExist(id);
        Student student = repository.findById(id).get();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findStundentByName(@PathVariable String name) {
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(repository.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        verifyIfStudentExist(id);
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentExist(student.getId());
        return new ResponseEntity<>(repository.save(student), HttpStatus.OK);
    }

    private void verifyIfStudentExist(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Student not found for ID "+ id);
        }
    }

}
