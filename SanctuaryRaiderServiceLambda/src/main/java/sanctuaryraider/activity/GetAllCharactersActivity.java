package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetAllCharactersRequest;
import sanctuaryraider.activity.results.GetAllCharactersResult;
import sanctuaryraider.converters.CharacterModelConverter;
import sanctuaryraider.dynamodb.dao.CharacterDao;
import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.models.CharacterModel;

import javax.inject.Inject;
import java.util.List;

public class GetAllCharactersActivity {
    private final Logger log = LogManager.getLogger();
    private final CharacterDao characterDao;

    /**
     * Instantiates a new GetAllCharactersActivity object.
     *
     * @param characterDao CharacterDao to access the tickets table.
     */
    @Inject
    public GetAllCharactersActivity(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    /**
     * This method handles the incoming request by retrieving the tickets from the database.
     * <p>
     * It then returns the tickets list.
     * <p>
     * If the ticket does not exist, this should throw a TicketNotFound.
     *
     * @param getAllCharactersRequest request object containing the project ID
     * @return getAllCharactersResult result object containing the ticketModel list of API defined {@link CharacterModel}s
     */
    public GetAllCharactersResult handleRequest(final GetAllCharactersRequest getAllCharactersRequest) {
        log.info("Received GetAllCharactersRequest {}", getAllCharactersRequest);
        CharacterModelConverter characterModelConverter = new CharacterModelConverter();

        List<Character> characters = characterDao.getAllCharactersForUsername(getAllCharactersRequest.getUsername());
        List<CharacterModel> characterModels = characterModelConverter.toCharacterModelList(characters);

        return GetAllCharactersResult.builder()
                .withCharacters(characterModels)
                .build();
    }

}