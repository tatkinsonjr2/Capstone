package sanctuaryraider.activity.results;

import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.models.CharacterModel;

public class CreateCharacterResult {
    private final CharacterModel character;

    private CreateCharacterResult(CharacterModel character){
        this.character = character;
    }

    public CharacterModel getCharacter(){
        return character;
    }

    @Override
    public String toString() {
        return "CreateCharacterResult{" +
                "character=" + character +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private CharacterModel character;

        public Builder withCharacter(CharacterModel character) {
            this.character = character;
            return this;
        }

        public CreateCharacterResult build() {
            return new CreateCharacterResult(character);
        }
    }
}
