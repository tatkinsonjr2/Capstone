# v2-design-document

# ***Design Document***

## ***Instructions***

## ***Capstone***

## ***1. Problem Statement***

*Build a guild raid organizer as a web application. The app will allow players to schedule raids, invite members, assign roles, and track loot distribution. It could also provide helpful raiding tools such as boss strategies, timers, and a calendar to keep everyone on the same page.*

## ***2. Top Questions to Resolve in Review***

*Important questions:*

1. Is the ERD well-designed, specifically regarding cardinality?
2. What is the API design and implementation in the code?
3. What are the general front end questions?
4. What is the MVC design pattern?
5. Can Spring Boot be implemented in this project?

## ***3. Use Cases***

*Customer use cases:*

- U1. As a user, I want to create a user profile.
- U2. As a user, I want to create a new Guild.
- U3. As a user, I want to add new members to the Guild.
- U4. As a user, I want to create new raids.
- U5. As a user, I want to sign-up/add myself to raids.
- U6. As a user, I want to create a wishlist of items for myself.
- U7. As a user, I want to edit items in the wishlist.
- U8. As a user, I want to clear the entire wishlist at once.
- U9. As a user, I want to see a list of all the loot that I have received.
- U10. As a user, I want to create new characters in my profile.
- U11. As a user, I want to view a list of all created raids.
- U12. As a user, I want to view raids based upon search results or character names.

## ***4. Project Scope***

*Clarify which parts of the problem you intend to solve.*

### ***4.1. In Scope***

*Which parts of the problem defined in Sections 1 and 2 will you solve with this design?*

- *CRUD functionality for a user to perform operations on raid instances, loot assignment, profile creation and character creation*

*The functionality described above should be what your design is focused on.*

### ***4.2. Out of Scope***

*Based on your problem description in Sections 1 and 2, are there any aspects you are not planning to solve?*

- Assigning loot may be out of the scope for this depending on complexity.
- Implementing calendar functionality, reminders or boss strategies will also most likely be out of scope.
- Discord functionality likely out of scope.

*The functionality here does not need to be accounted for in your design.*

# ***5. Proposed Architecture Overview***

*Describe broadly how you are proposing to solve for the requirements you described in Section 2.*

*Broadly speaking, we will need to design api endpoints that will be able to call our databases for profile information, character information and loot distribution information. We will need a database to hold each profile, raid instance and guild (not sure about complete architecture right now).*

# ***6. API***

### ***6.1. Public Models***

*Define the data models your service will expose in its responses via your -Model package.*
1. Guild Model.
2. Profile Model.
3. Item Model.
4. Character Model.
5. Raid/Raid Instance Model.
6. Raid Group Model(possibly).

### ***6.2. Get Profile Endpoint***

*Describe the behavior of the first endpoint you will build into your service API.*
- Accepts Get requests to /profile/:username
  - If the given username is not found will throw ProfileNotFoundException.


### ***6.3 Create Profile Endpoint***

*(repeat, but you can use shorthand here, indicating what is different, likely primarily the data in/out and error conditions. If the sequence diagram is nearly identical, you can say in a few words how it is the same/different from the first endpoint)*

- *Accepts POST requests to /profile.*
- *Accepts data to create a new profile. Returns the new profile, including a unique username.*
- *For security concerns, we will validate the provided username name does not contain any invalid characters: " ' \*
    - *If the profile username contains any of the invalid characters, will throw an InvalidAttributeValueException.*

### ***6.4 Update Profile Endpoint***

- *Accepts PUT requests to /profile/:username.*
- *Accepts data to update a profile. Returns the updated profile.*
    - *If the profile is not found, will throw a ProfileNotFoundException.*
- *For security concerns, we will validate the provided project/task name does not contain invalid characters: " ' \*
    - *If the profile name contains invalid characters, will throw an InvalidAttributeValueException.*

### ***6.5 Delete Profile Endpoint***

- *Accepts DELETE requests to /profile/:username.*
- *Accepts a username.*
    - *If the profile or task is not found, will throw a ProfileNotFoundException.*

### ***6.6 Get Character Endpoint***
- Accepts GET requests to /profile/:username/character/:characterName.
- Accepts a username.
  - If character is not found, throws a CharacterNotFoundException or if the character already exists/has been created throws CharacterInUseException.
- For security concerns, we will validate the provided project/task name does not contain invalid characters: " ' \*
  - *If the project/task name contains invalid characters, will throw an InvalidAttributeValueException.*

### ***6.7 Create Character Endpoint***
- Accepts POST requests to /profile/:username/character.
- Accepts data to create a new character. Returns a new character including a unique character name.
- *For security concerns, we will validate the provided username name does not contain any invalid characters: " ' \*
  - *If the profile username contains any of the invalid characters, will throw an InvalidAttributeValueException.*

### ***6.8 Update Character Endpoint***
- Accepts PUT requests to /profile/:username/character/:characterName.
- Accepts data to update a character within a profile.
  - *If the profile is not found, will throw a ProfileNotFoundException.*
- *For security concerns, we will validate the provided project/task name does not contain invalid characters: " ' \*
  - *If the profile name contains invalid characters, will throw an InvalidAttributeValueException.*

### ***6.9 Create Raid Endpoint***
- Accepts POST requests to /raidInstance.
- Accepts data to create a new raidInstance. Returns a new raid including a unique raid name.
  - *If the raid name contains invalid characters, will throw an InvalidAttributeValueException.*

### ***6.10 Update Raid Endpoint***
- Accepts PUT requests to /raidInstance/:raidName.
- Accepts data to update a new raidInstance. Returns a new raid including the unique raid name.
  - *If the raid name contains invalid characters, will throw an InvalidAttributeValueException.*

### ***6.11 GET Raid Endpoint***
- Accepts GET requests to /raidInstance/:raidName.
- Accepts a raidName.
  - If a raidName does not exist or is incorrectly typed, will throw a RaidNotFoundException.
  - *If the raid name contains invalid characters, will throw an InvalidAttributeValueException.*

# ***7. Tables***

*Define the DynamoDB tables you will need for the data your service will use.*

### ***7.1 Profiles***

| Field       | Type              |
|-------------|-------------------|
| username    | String (Hash Key) |
| guild       | List String       |
| publicNote  | String            |
| officerNote | String            |

### ***7.2 Characters***

| Field              | Type               |
|--------------------|--------------------|
| username           | String (Hash Key)  |
| characterName      | String (Range Key) |
| class              | String             |
| spec               | String             |
| role               | String             |
| race               | String             |
| publicNote         | String             |
| OfficerNote        | String             |
| professionOne      | String             |
| professionTwo      | String             |
| alternateCharacter | Boolean            |
| archive            | Boolean            |
| wishList           | List Item          |


### ***7.3 Raids***

| Field        | Type              |
|--------------|-------------------|
| raidName     | String (Hash Key) |
| date         | ZonedDateTime     |
| publicNote   | String            |
| officerNote  | String            |
| status       | String            |
| instanceName | String            |
| attendees    | List String       |


# ***8. Pages***

*Include mock-ups of the web pages you expect to build.*

https://www.canva.com/design/DAFjR-j3eeI/yTRDLBh9DCGKoUy-ntC9sQ/edit?utm_content=DAFjR-j3eeI&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
