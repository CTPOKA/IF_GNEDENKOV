package steps;

import api.rick_and_morty.RickAndMortyApi;
import io.cucumber.java.ru.*;
import models.rick_and_morty.Character;
import models.rick_and_morty.Episode;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class RickAndMortySteps {
    private static final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    private Character targetCharacter;
    private Episode lastEpisode;
    private Character lastEpisodeCharacter;

    @Когда("получим персонажа по имени {string}")
    public void getCharacterByName(String name) {
        List<Character> characters = rickAndMortyApi.getCharacters()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getList("results", Character.class);

        targetCharacter = characters.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Персонаж с именем " + name + " не найден"));
    }

    @Когда("получим последний эпизод с участием этого персонажа")
    public void getLastEpisode() {
        List<String> episodes = targetCharacter.getEpisode();
        if (episodes.isEmpty()) {
            throw new RuntimeException("У персонажа нет эпизодов");
        }
        String lastEpisodeId = episodes.get(episodes.size() - 1).replaceAll("\\D+", "");
        lastEpisode = rickAndMortyApi.getEpisodeById(lastEpisodeId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Episode.class);
    }

    @Когда("получим последнего персонажа, появившегося в данном эпизоде")
    public void getLastEpisodeCharacter() {
        List<String> characters = lastEpisode.getCharacters();
        if (characters.isEmpty()) {
            throw new RuntimeException("В эпизоде нет персонажей");
        }
        String lastCharacterId = characters.get(characters.size() - 1).replaceAll("\\D+", "");
        lastEpisodeCharacter = rickAndMortyApi.getCharacterById(lastCharacterId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }

    @Тогда("раса или местонахождение персонажей не совпадают")
    public void verifyRaceOrLocationDifferent() {
        boolean differentSpecies = !targetCharacter.getSpecies().equals(lastEpisodeCharacter.getSpecies());
        boolean differentLocation = !targetCharacter.getLocation().getName().equals(lastEpisodeCharacter.getLocation().getName());

        Assertions.assertTrue(differentSpecies || differentLocation,
                "Раса и местонахождение персонажей совпадают, хотя должны отличаться");
    }
}
