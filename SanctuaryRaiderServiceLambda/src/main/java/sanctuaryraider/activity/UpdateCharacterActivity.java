package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.UpdateCharacterRequest;
import sanctuaryraider.activity.results.UpdateCharacterResult;
import sanctuaryraider.converters.CharacterModelConverter;
import sanctuaryraider.dynamodb.dao.CharacterDao;
import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.models.CharacterModel;

import javax.inject.Inject;

public class UpdateCharacterActivity {

    private final Logger log = LogManager.getLogger();

    private final CharacterDao characterDao;

    @Inject
    public UpdateCharacterActivity(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

    public UpdateCharacterResult handleRequest(final UpdateCharacterRequest updateCharacterRequest){
        log.info("Received UpdateCharacterRequest {}", updateCharacterRequest);

        Character character = characterDao.getCharacter(updateCharacterRequest.getUsername(), updateCharacterRequest.getCharacterName());

        character.setCharacterClass(updateCharacterRequest.getCharacterClass());
        character.setSpec(updateCharacterRequest.getSpec());
        character.setRace(updateCharacterRequest.getRace());
        character.setRole(updateCharacterRequest.getRole());
        character.setPublicNote(updateCharacterRequest.getPublicNote());
        character.setOfficerNote(updateCharacterRequest.getOfficerNote());
        character.setProfessionOne(updateCharacterRequest.getProfessionOne());
        character.setProfessionTwo(updateCharacterRequest.getProfessionTwo());
        character.setAlternateCharacter(updateCharacterRequest.getAlternateCharacter());
        character.setWishlist(updateCharacterRequest.getWishList());

        character = characterDao.saveCharacter(character);

        return UpdateCharacterResult.builder().withCharacter(new CharacterModelConverter().toCharacterModel(character)).build();

    }
}
