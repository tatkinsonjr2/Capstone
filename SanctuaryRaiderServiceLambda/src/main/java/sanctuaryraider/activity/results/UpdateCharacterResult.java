package sanctuaryraider.activity.results;

import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.models.CharacterModel;

public class UpdateCharacterResult {
    private final CharacterModel character;

    public UpdateCharacterResult(CharacterModel character){
        this.character = character;
    }

    public CharacterModel getCharacter(){
        return character;
    }

    @Override
    public String toString() {
        return "UpdateCharacterResult{" +
                "character=" + character +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private CharacterModel character;

        public Builder withCharacter(CharacterModel character) {
            this.character = character;
            return this;
        }

        public UpdateCharacterResult build() {
            return new UpdateCharacterResult(character);
        }
    }
}
