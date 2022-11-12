package com.dashboard.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Currencie")
public class Currencie extends Widget {

    private String currencie1;
    private String currencie2;
}
