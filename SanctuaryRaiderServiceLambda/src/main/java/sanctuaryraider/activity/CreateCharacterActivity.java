package sanctuaryraider.activity;

import com.amazonaws.services.dynamodbv2.xspec.S;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.CreateCharacterRequest;
import sanctuaryraider.activity.results.CreateCharacterResult;
import sanctuaryraider.converters.CharacterModelConverter;
import sanctuaryraider.dynamodb.dao.CharacterDao;
import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.exceptions.InvalidAttributeValueException;
import sanctuaryraider.models.CharacterModel;
import sanctuaryraider.utils.SanctuaryRaiderServiceUtils;

import javax.inject.Inject;

public class CreateCharacterActivity {

    private final Logger log = LogManager.getLogger();
    private final CharacterDao characterDao;

    @Inject
    public CreateCharacterActivity(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

    public CreateCharacterResult handleRequest(final CreateCharacterRequest createCharacterRequest){
        log.info("Received CreateCharacterRequest {}", createCharacterRequest);

        if(!SanctuaryRaiderServiceUtils.isValidString(createCharacterRequest.getUsername())){
            throw new InvalidAttributeValueException("Username [" + createCharacterRequest.getUsername() + "] contains illegal characters");
        }
        if(!SanctuaryRaiderServiceUtils.isValidString(createCharacterRequest.getCharacterName())) {
            throw new InvalidAttributeValueException("Character Name [" + createCharacterRequest.getCharacterName() + "] contains illegal characters");
        }
            Character character = new Character();
            character.setUsername(createCharacterRequest.getUsername());
            character.setCharacterName(createCharacterRequest.getCharacterName());
            character.setCharacterClass(createCharacterRequest.getCharacterClass());
            character.setSpec(createCharacterRequest.getSpec());
            character.setRace(createCharacterRequest.getRace());
            character.setRole(createCharacterRequest.getRole());
            character.setPublicNote(createCharacterRequest.getPublicNote());
            character.setOfficerNote(createCharacterRequest.getOfficerNote());
            character.setProfessionOne(createCharacterRequest.getProfessionOne());
            character.setProfessionTwo(createCharacterRequest.getProfessionTwo());
            character.setAlternateCharacter(createCharacterRequest.getAlternateCharacter());
            character.setWishlist(createCharacterRequest.getWishList());

            characterDao.saveCharacter(character);

            CharacterModel characterModel = new CharacterModelConverter().toCharacterModel(character);

            return CreateCharacterResult.builder().withCharacter(characterModel).build();
        }
    }

