package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.CreateProfileRequest;
import sanctuaryraider.activity.requests.CreateRaidRequest;
import sanctuaryraider.activity.results.CreateProfileResult;
import sanctuaryraider.activity.results.CreateRaidResult;
import sanctuaryraider.converters.ProfileModelConverter;
import sanctuaryraider.converters.RaidModelConverter;
import sanctuaryraider.dynamodb.dao.RaidDao;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.exceptions.InvalidAttributeValueException;
import sanctuaryraider.models.ProfileModel;
import sanctuaryraider.models.RaidModel;
import sanctuaryraider.utils.SanctuaryRaiderServiceUtils;

import javax.inject.Inject;

public class CreateRaidActivity {
    private final Logger log = LogManager.getLogger();

    private final RaidDao raidDao;

    @Inject
    public CreateRaidActivity(RaidDao raidDao){
        this.raidDao = raidDao;
    }

    public CreateRaidResult handleRequest(final CreateRaidRequest createRaidRequest){
        log.info("Received CreateRaidRequest {}", createRaidRequest);

        if(!SanctuaryRaiderServiceUtils.isValidString(createRaidRequest.getRaidName())){
            throw new InvalidAttributeValueException("Username [" + createRaidRequest.getRaidName() + "] contains illegal characters");
        }

        Raid newRaid = new Raid();
        newRaid.setRaidName(createRaidRequest.getRaidName());
        newRaid.setDate(createRaidRequest.getDate());
        newRaid.setPublicNote(createRaidRequest.getPublicNote());
        newRaid.setOfficerNote(createRaidRequest.getOfficerNote());
        newRaid.setStatus(createRaidRequest.getStatus());
        newRaid.setInstanceName(createRaidRequest.getInstanceName());
        newRaid.setAttendees(createRaidRequest.getAttendees());


        raidDao.saveRaid(newRaid);

        RaidModel raidModel = new RaidModelConverter().toRaidModel(newRaid);
        return CreateRaidResult.builder()
                .withRaid(raidModel)
                .build();
    }
}
