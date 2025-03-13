package models.rick_and_morty;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class Character {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    private ArrayList<String> episode;
    private String url;
    private Date created;
}