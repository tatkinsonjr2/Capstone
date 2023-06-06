package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.UpdateRaidRequest;
import sanctuaryraider.activity.results.UpdateRaidResult;
import sanctuaryraider.converters.RaidModelConverter;
import sanctuaryraider.dynamodb.dao.RaidDao;
import sanctuaryraider.dynamodb.models.Raid;

import javax.inject.Inject;

public class UpdateRaidActivity {

    private final Logger log = LogManager.getLogger();

    private final RaidDao raidDao;

    @Inject
    public UpdateRaidActivity(RaidDao raidDao){
        this.raidDao = raidDao;
    }

    public UpdateRaidResult handleRequest(final UpdateRaidRequest updateRaidRequest){
        log.info("Received UpdateRaidRequest {}", updateRaidRequest);

        Raid raid = raidDao.getRaid(updateRaidRequest.getRaidName());

        raid.setInstanceName(updateRaidRequest.getInstanceName());
        raid.setDate(updateRaidRequest.getDate());
        raid.setPublicNote(updateRaidRequest.getPublicNote());
        raid.setOfficerNote(updateRaidRequest.getOfficerNote());
        raid.setAttendees(updateRaidRequest.getAttendees());
        raid.setStatus(updateRaidRequest.getStatus());

        raid = raidDao.saveRaid(raid);

        return UpdateRaidResult.builder().withRaid(new RaidModelConverter().toRaidModel(raid)).build();

    }
}
