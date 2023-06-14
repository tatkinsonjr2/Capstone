package sanctuaryraider.activity.results;

import sanctuaryraider.models.CharacterModel;

import java.util.ArrayList;
import java.util.List;

public class GetAllCharactersResult {
    private final List<CharacterModel> characters;

    private GetAllCharactersResult(List<CharacterModel> characters){
        this.characters = characters;
    }

    public List<CharacterModel> getCharacters(){
        return new ArrayList<>(characters);
    }

    @Override
    public String toString() {
        return "GetAllCharactersResult{" +
                "characterModels=" + characters +
                '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<CharacterModel> characters;

        public Builder withCharacters(List<CharacterModel> characters) {
            this.characters = new ArrayList<>(characters);
            return this;
        }

        public GetAllCharactersResult build() {
            return new GetAllCharactersResult(characters);
        }
    }
}
