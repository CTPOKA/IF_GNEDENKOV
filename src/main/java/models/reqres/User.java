package models.reqres;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String name;
    private String job;
    private Date createdAt;
}