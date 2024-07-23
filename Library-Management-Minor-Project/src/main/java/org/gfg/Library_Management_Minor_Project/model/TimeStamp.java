package org.gfg.Library_Management_Minor_Project.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass// this is to indicate that this class provides mapping information for its subclasses.
public class TimeStamp {
    @CreationTimestamp
    //@Column(name = "created_on", updatable = false)
    public Date createdOn;

    @UpdateTimestamp
    public Date updatedOn;

}
