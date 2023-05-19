package sanctuaryraider.converters;

import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.models.RaidModel;

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

}
