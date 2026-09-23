package com.taskmanagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//jobdesk aya
@Entity
@DiscriminatorValue("PERSONAL")
public class PersonalTask extends Task {
    public PersonalTask() {
    }

}