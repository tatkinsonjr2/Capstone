package sanctuaryraider.activity.requests;

public class GetAllCharactersRequest {
    private final String username;
    private GetAllCharactersRequest(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString() {
        return "GetAllCharactersRequest{" +
                "username='" + username + '\'' +
                '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public GetAllCharactersRequest build() {
            return new GetAllCharactersRequest(username);
        }
    }
}
