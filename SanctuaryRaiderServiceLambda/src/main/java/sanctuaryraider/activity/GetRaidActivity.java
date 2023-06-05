package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetRaidRequest;
import sanctuaryraider.activity.results.GetRaidResult;
import sanctuaryraider.converters.RaidModelConverter;
import sanctuaryraider.dynamodb.dao.RaidDao;
import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.models.RaidModel;

import javax.inject.Inject;

public class GetRaidActivity {
    private final Logger log = LogManager.getLogger();

    private final RaidDao raidDao;

    @Inject
    public GetRaidActivity(RaidDao raidDao){
        this.raidDao = raidDao;
    }

    public GetRaidResult handleRequest(final GetRaidRequest getRaidRequest){
        log.info("Received GetRaidRequest {}", getRaidRequest);
        String requestedRaidName = getRaidRequest.getRaidName();
        Raid raid = raidDao.getRaid(requestedRaidName);
        RaidModel raidModel = new RaidModelConverter().toRaidModel(raid);

        return GetRaidResult.builder().withRaid(raidModel).build();
    }

}
