package sanctuaryraider.activity.results;

import sanctuaryraider.models.RaidModel;

public class UpdateRaidResult {

    private final RaidModel raid;

    public UpdateRaidResult(RaidModel raid){
        this.raid = raid;
    }

    public RaidModel getRaid(){
        return raid;
    }

    @Override
    public String toString() {
        return "UpdateRaidResult{" +
                "character=" + raid +
                '}';
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

        public UpdateRaidResult build() {
            return new UpdateRaidResult(raid);
        }
    }
}
