package sanctuaryraider.activity.results;

import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.models.RaidModel;

public class GetRaidResult {
    private final RaidModel raid;

    private GetRaidResult(RaidModel raid){
        this.raid = raid;
    }

    public RaidModel getRaid(){
        return raid;
    }

    @Override
    public String toString(){
        return "GetRaidResult{" + "raid=" + raid + '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RaidModel raid;

        public Builder withRaid(RaidModel raid) {
            this.raid = raid;
            return this;
        }

        public GetRaidResult build() {
            return new GetRaidResult(raid);
        }
    }
}
