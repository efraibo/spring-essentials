package br.com.sourceinformation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Student extends AbstractEntity{
    private String name;
}
