package sanctuaryraider.activity.results;

import sanctuaryraider.models.CharacterModel;

public class DeleteCharacterResult {
    private final CharacterModel character;

    private DeleteCharacterResult(CharacterModel character){
        this.character = character;
    }

    public CharacterModel getCharacter(){
        return character;
    }

    @Override
    public String toString(){
        return "DeleteCharacterResult{" + "character=" + character + '}';
    }
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private CharacterModel character;

        public Builder withCharacter(CharacterModel character){
            this.character = character;
            return this;
        }

        public DeleteCharacterResult build(){
            return new DeleteCharacterResult(character);
        }
    }
}
