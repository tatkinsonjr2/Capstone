package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetCharacterRequest;
import sanctuaryraider.activity.requests.GetProfileRequest;
import sanctuaryraider.activity.results.GetCharacterResult;
import sanctuaryraider.converters.CharacterModelConverter;
import sanctuaryraider.dynamodb.dao.CharacterDao;
import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.models.CharacterModel;

import javax.inject.Inject;

public class GetCharacterActivity {

    private final Logger log = LogManager.getLogger();

    private final CharacterDao characterDao;

    @Inject
    public GetCharacterActivity(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

public GetCharacterResult handleRequest(final GetCharacterRequest getCharacterRequest){
        log.info("Received GetCharacterRequest {}", getCharacterRequest);
        Character character = characterDao.getProfile(getCharacterRequest.getUsername(), getCharacterRequest.getCharacterName());
        CharacterModel characterModel = new CharacterModelConverter().toCharacterModel(character);

        return GetCharacterResult.builder().withCharacter(characterModel).build();
}
}
