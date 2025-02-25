package rick_and_morty;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class Episode {
    private int id;
    private String name;
    private String air_date;
    private String episode;
    private ArrayList<String> characters;
    private String url;
    private Date created;
}