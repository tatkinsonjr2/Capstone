package sanctuaryraider.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.exceptions.CharacterNotFoundException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public CharacterDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Character getCharacter(String username, String characterName){
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

    public List<Character> getAllCharactersForUsername(String username){

        if (username == null) {
        throw new IllegalArgumentException("passed in username is null");
    }
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":username", new AttributeValue().withS(username));

        DynamoDBQueryExpression<Character> queryExpression = new DynamoDBQueryExpression<Character>()
                .withKeyConditionExpression("username = :username")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Character> characters = dynamoDBMapper.query(Character.class, queryExpression);

        if(characters == null) {
            throw new CharacterNotFoundException("characters not found for requested projectId");
        }

        return characters;
    }
}
