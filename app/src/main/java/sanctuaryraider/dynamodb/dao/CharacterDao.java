package sanctuaryraider.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.exceptions.CharacterNotFoundException;

import javax.inject.Inject;
import java.util.List;

public class CharacterDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public CharacterDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Character getProfile(String username, String characterName){
        Character character = dynamoDBMapper.load(Character.class, username, characterName);
        if(character == null){
            throw new CharacterNotFoundException();
        }
        return character;
    }

    public Character saveCharacter(Character character){
        this.dynamoDBMapper.save(character);
        return character;
    }

    public void deleteCharacter(Character character){
        this.dynamoDBMapper.delete(character);
    }

    public List<Character> getAllCharacters(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Character.class, scanExpression);
    }
}
