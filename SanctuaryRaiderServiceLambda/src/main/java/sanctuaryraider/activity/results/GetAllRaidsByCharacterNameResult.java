package sanctuaryraider.activity.results;

import sanctuaryraider.models.RaidModel;

import java.util.ArrayList;
import java.util.List;

public class GetAllRaidsByCharacterNameResult {
    private final List<RaidModel> raids;

    private GetAllRaidsByCharacterNameResult(List<RaidModel> raids){
        this.raids = raids;
    }

    public List<RaidModel> getRaids(){
        return new ArrayList<>(raids);
    }

    @Override
    public String toString() {
        return "GetAllRaidsByCharacterNameResult{" +
                "raidModels=" + raids +
                '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<RaidModel> raids;

        public Builder withRaids(List<RaidModel> raids) {
            this.raids = raids;
            return this;
        }

        public GetAllRaidsByCharacterNameResult build() {
            return new GetAllRaidsByCharacterNameResult(raids);
        }
    }
}
