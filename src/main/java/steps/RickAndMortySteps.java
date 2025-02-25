package steps;

import api.rick_and_morty.RickAndMortyApi;
import org.apache.http.HttpStatus;
import rick_and_morty.Episode;
import rick_and_morty.Character;

import java.util.List;

public class RickAndMortySteps {
    private static final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();

    public List<Character> getCharacters() {
        return rickAndMortyApi.getCharacters()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getList("results", Character.class);
    }

    public Character getCharacterByName(String name) {
        return getCharacters().stream()
                .filter(character -> character.getName().equals(name))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public Episode getEpisodeById(String id) {
        return rickAndMortyApi.getEpisodeById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Episode.class);
    }

    public Character getCharacterById(String id) {
        return rickAndMortyApi.getCharacterById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }

}