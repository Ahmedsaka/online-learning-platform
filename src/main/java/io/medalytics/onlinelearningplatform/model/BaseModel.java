package io.medalytics.onlinelearningplatform.model;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private java.sql.Date createdDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCreatedDate(java.sql.Date createdDate) {
        this.createdDate = new java.sql.Date(createdDate.getTime());
    }

    public java.sql.Date getCreatedDate(){
        return this.createdDate;
    }
}
