package sanctuaryraider.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "characters")
public class Character {
    private String username;
    private String characterName;
    private String characterClass;
    private String spec;
    private String role;
    private String race;
    private String publicNote;
    private String officerNote;
    private String professionOne;
    private String professionTwo;
    private Boolean alternateCharacter;
    private List<String> wishlist;

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @DynamoDBRangeKey(attributeName = "characterName")
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @DynamoDBAttribute(attributeName = "class")
    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }
    @DynamoDBAttribute(attributeName = "specialization")
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @DynamoDBAttribute(attributeName = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @DynamoDBAttribute(attributeName = "race")
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @DynamoDBAttribute(attributeName = "publicNote")
    public String getPublicNote() {
        return publicNote;
    }

    public void setPublicNote(String publicNote) {
        this.publicNote = publicNote;
    }

    @DynamoDBAttribute(attributeName = "officerNote")
    public String getOfficerNote() {
        return officerNote;
    }

    public void setOfficerNote(String officerNote) {
        this.officerNote = officerNote;
    }

    @DynamoDBAttribute(attributeName = "professionOne")
    public String getProfessionOne() {
        return professionOne;
    }

    public void setProfessionOne(String professionOne) {
        this.professionOne = professionOne;
    }

    @DynamoDBAttribute(attributeName = "professionTwo")
    public String getProfessionTwo() {
        return professionTwo;
    }

    public void setProfessionTwo(String professionTwo) {
        this.professionTwo = professionTwo;
    }

    @DynamoDBAttribute(attributeName = "alternateCharacter")
    public Boolean getAlternateCharacter() {
        return alternateCharacter;
    }

    public void setAlternateCharacter(Boolean alternateCharacter) {
        this.alternateCharacter = alternateCharacter;
    }

    @DynamoDBAttribute(attributeName = "wishList")
    public List<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<String> wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Character character = (Character) o;
        return Objects.equals(username, character.username) && Objects.equals(characterName, character.characterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, characterName);
    }
}