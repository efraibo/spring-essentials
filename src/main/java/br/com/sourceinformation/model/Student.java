package br.com.sourceinformation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
public class Student extends AbstractEntity{
    @NotEmpty(message = "O campo nome do estudante é obrigátorio")
    private String name;

    @NotEmpty
    @Email
    private String email;
}
