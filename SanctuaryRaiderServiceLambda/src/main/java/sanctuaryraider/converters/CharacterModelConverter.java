package sanctuaryraider.converters;

import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.models.CharacterModel;

import java.util.ArrayList;
import java.util.List;

public class CharacterModelConverter {

    public CharacterModel toCharacterModel(Character character){
        return CharacterModel.builder().withUsername(character.getUsername())
                .withCharacterName(character.getCharacterName())
                .withCharacterClass(character.getCharacterClass())
                .withSpec(character.getSpec())
                .withRace(character.getRace())
                .withRole(character.getRole())
                .withPublicNote(character.getPublicNote())
                .withOfficerNote(character.getOfficerNote())
                .withProfessionOne(character.getProfessionOne())
                .withProfessionTwo(character.getProfessionTwo())
                .withAlternateCharacter(character.getAlternateCharacter())
                .withWishList(character.getWishlist())
                .build();
    }

    public List<CharacterModel> toCharacterModelList(List<Character> characters){
        List<CharacterModel> characterModels = new ArrayList<>();

        for(Character character : characters){
            characterModels.add(toCharacterModel(character));
        }
        return characterModels;
    }

}
