package sanctuaryraider.activity.results;

import sanctuaryraider.models.CharacterModel;
import sanctuaryraider.models.ProfileModel;

public class GetCharacterResult {

    private final CharacterModel character;

    private GetCharacterResult(CharacterModel character){
        this.character = character;
    }

    public CharacterModel getCharacter(){
        return character;
    }

    @Override
    public String toString(){
        return "GetCharacterResult{" + "character=" + character + '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private CharacterModel character;

        public Builder withCharacter(CharacterModel character){
            this.character = character;
            return this;
        }

        public GetCharacterResult build(){
            return new GetCharacterResult(character);
        }
    }

}
