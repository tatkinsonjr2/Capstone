package sanctuaryraider.activity.requests;

public class GetRaidRequest {
    private final String raidName;

    private GetRaidRequest(String raidName){
        this.raidName = raidName;
    }

    public String getRaidName(){
        return raidName;
    }

    @Override
    public String toString(){
        return "GetRaidRequest{" + "raidName='" + raidName + '\'' + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String raidName;

        public Builder withRaidName(String raidName) {
            this.raidName = raidName;
            return this;
        }

        public GetRaidRequest build() {
            return new GetRaidRequest(raidName);
        }
    }
}
