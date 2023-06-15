package sanctuaryraider.converters;

import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.models.RaidModel;

import java.util.ArrayList;
import java.util.List;

public class RaidModelConverter {
    /**
     * Converts between Data and API models.
     */
    /**
     * Converts a provided {@link Raid} into a {@link RaidModel} representation.
     *
     * @param raid the Raid to convert
     * @return the converted project
     */

    public RaidModel toRaidModel(Raid raid){
        return RaidModel.builder().withRaidName(raid.getRaidName())
                .withPublicNote(raid.getPublicNote())
                .withDate(raid.getDate())
                .withOfficerNote(raid.getOfficerNote())
                .withStatus(raid.getStatus())
                .withInstanceName(raid.getInstanceName())
                .withAttendees(raid.getAttendees())
                .build();
    }
    public List<RaidModel> toRaidModelList(List<Raid> raids){
        List<RaidModel> raidModels = new ArrayList<>();

        for(Raid raid : raids){
            raidModels.add(toRaidModel(raid));
        }
        return raidModels;
    }
}
