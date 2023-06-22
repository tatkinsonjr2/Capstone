package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.DeleteCharacterRequest;
import sanctuaryraider.activity.results.DeleteCharacterResult;
import sanctuaryraider.converters.CharacterModelConverter;
import sanctuaryraider.dynamodb.dao.CharacterDao;
import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.models.CharacterModel;

import javax.inject.Inject;

public class DeleteCharacterActivity {
    private final Logger log = LogManager.getLogger();
    private final CharacterDao characterDao;

    @Inject
    public DeleteCharacterActivity(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

    public DeleteCharacterResult handleRequest(final DeleteCharacterRequest deleteCharacterRequest){
        log.info("Received request to delete project with id{}", deleteCharacterRequest);

        Character character = new Character();

        character.setUsername(deleteCharacterRequest.getUsername());
        character.setCharacterName(deleteCharacterRequest.getCharacterName());
        character.setCharacterClass(deleteCharacterRequest.getCharacterClass());
        character.setSpec(deleteCharacterRequest.getSpec());
        character.setRace(deleteCharacterRequest.getRace());
        character.setRole(deleteCharacterRequest.getRole());
        character.setPublicNote(deleteCharacterRequest.getPublicNote());
        character.setOfficerNote(deleteCharacterRequest.getOfficerNote());
        character.setProfessionOne(deleteCharacterRequest.getProfessionOne());
        character.setProfessionTwo(deleteCharacterRequest.getProfessionTwo());
        character.setAlternateCharacter(deleteCharacterRequest.getAlternateCharacter());
        character.setWishlist(deleteCharacterRequest.getWishList());

        characterDao.deleteCharacter(character);
        CharacterModel characterModel = new CharacterModelConverter().toCharacterModel(character);

        return DeleteCharacterResult.builder().withCharacter(characterModel).build();
    }
}
