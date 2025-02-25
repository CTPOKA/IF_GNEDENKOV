import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rick_and_morty.Episode;
import rick_and_morty.Character;
import steps.RickAndMortySteps;

public class RickAndMortyTest {

    private final RickAndMortySteps rickAndMortySteps = new RickAndMortySteps();

    @Test
    @DisplayName("Проверка совпадения расы или местонахождения персонажей")
    public void characterComparisonTest() {
        String characterName = ConfigReader.getProperty("rickandmorty.character.name");
        Character character = rickAndMortySteps.getCharacterByName(characterName);

        Character lastCharacterOfEpisode = getLastCharacterFromLastEpisode(character);

        boolean isDifferentSpeciesOrLocation = isDifferentSpeciesOrLocation(character, lastCharacterOfEpisode);

        Assertions.assertTrue(isDifferentSpeciesOrLocation, "Персонажи имеют одинаковые расы или местонахождения");
    }

    private Character getLastCharacterFromLastEpisode(Character character) {
        String lastEpisodeId = extractIdFromUrl(getLastElement(character.getEpisode()));
        Episode lastEpisode = rickAndMortySteps.getEpisodeById(lastEpisodeId);

        String lastCharacterId = extractIdFromUrl(getLastElement(lastEpisode.getCharacters()));
        return rickAndMortySteps.getCharacterById(lastCharacterId);
    }

    private boolean isDifferentSpeciesOrLocation(Character character1, Character character2) {
        return !character1.getSpecies().equals(character2.getSpecies()) || !character1.getLocation().equals(character2.getLocation());
    }

    private String extractIdFromUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }

    private <T> T getLastElement(java.util.List<T> list) {
        return list.get(list.size() - 1);
    }
}
