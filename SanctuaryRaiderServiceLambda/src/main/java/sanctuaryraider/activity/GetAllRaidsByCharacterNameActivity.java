package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetAllRaidsByCharacterNameRequest;
import sanctuaryraider.activity.results.GetAllRaidsByCharacterNameResult;
import sanctuaryraider.converters.RaidModelConverter;
import sanctuaryraider.dynamodb.dao.RaidDao;
import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.models.CharacterModel;
import sanctuaryraider.models.RaidModel;

import javax.inject.Inject;
import java.util.List;

public class GetAllRaidsByCharacterNameActivity {
    private final Logger log = LogManager.getLogger();
    private final RaidDao raidDao;

    /**
     * Instantiates a new GetAllRaidsByCharacterNameActivity object.
     *
     * @param raidDao CharacterDao to access the tickets table.
     */
    @Inject
    public GetAllRaidsByCharacterNameActivity(RaidDao raidDao) {
        this.raidDao = raidDao;
    }

    /**
     * This method handles the incoming request by retrieving the tickets from the database.
     * <p>
     * It then returns the tickets list.
     * <p>
     * If the ticket does not exist, this should throw a TicketNotFound.
     *
     * @param getAllRaidsByCharacterNameRequest request object containing the username
     * @return getAllCharactersResult result object containing the ticketModel list of API defined {@link CharacterModel}s
     */
    public GetAllRaidsByCharacterNameResult handleRequest(final GetAllRaidsByCharacterNameRequest getAllRaidsByCharacterNameRequest) {
        log.info("Received GetAllRaidsByCharacterNameRequest {}", getAllRaidsByCharacterNameRequest);
        RaidModelConverter raidModelConverter = new RaidModelConverter();

        List<Raid> raids = raidDao.getAllRaidsByCharacterName(getAllRaidsByCharacterNameRequest.getCharacterName());
        List<RaidModel> raidModels = raidModelConverter.toRaidModelList(raids);

        return GetAllRaidsByCharacterNameResult.builder()
                .withRaids(raidModels)
                .build();
    }

}