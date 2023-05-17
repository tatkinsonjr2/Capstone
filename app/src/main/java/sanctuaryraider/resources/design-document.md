# v2-design-document

# ***Design Document***

## ***Instructions***

## ***Group 3 - Midstone Design***

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

*The functionality here does not need to be accounted for in your design.*

# ***5. Proposed Architecture Overview***

*Describe broadly how you are proposing to solve for the requirements you described in Section 2.*

*Broadly speaking, we will need to design api endpoints that will be able to call our databases for profile information, character information and loot distribution information. Our databases we will need a database to hold each profile, raid instance and guild (not sure about complete architecture right now). *

# ***6. API***

### ***6.1. Public Models***

*Define the data models your service will expose in its responses via your -Model package.*

### ***6.2. Get Project/Tasks Endpoint***

*Describe the behavior of the first endpoint you will build into your service API.*

*(You should have a separate section for each of the endpoints you are expecting to build...)*

- *Accepts GET requests to /projects/:projectId and/or /tasks/:taskId*
- *Accepts project ID and returns the corresponding ProjectModel.*
    - *If the given project ID is not found, will throw a ProjectNotFoundException.*
    - *If the given task ID is not found, wil throw a TaskNotFoundException.*

### ***6.3 Create Project and/or Create Task***

*(repeat, but you can use shorthand here, indicating what is different, likely primarily the data in/out and error conditions. If the sequence diagram is nearly identical, you can say in a few words how it is the same/different from the first endpoint)*

- *Accepts POST requests to /projects and/or /tasks.*
- *Accepts data to create a new project or a new task with a provided name and description. Returns the new project/task, including a unique projectId/taskId assigned by the task management service.*
- *For security concerns, we will validate the provided project/task name does not contain any invalid characters: " ' \*
    - *If the project/task name contains any of the invalid characters, will throw an InvalidAttributeValueException.*

### ***6.4 Update Project/Task Endpoint***

- *Accepts PUT requests to /projects/:projectId and /tasks/:taskId.*
- *Accepts data to update a project/task including a projectId/taskId, an updated project/task name and updated status. Returns the updated project or task.*
    - *If the project/task is not found, will throw a ProjectNotFoundException or TaskNotFoundException.*
- *For security concerns, we will validate the provided project/task name does not contain invalid characters: " ' \*
    - *If the project/task name contains invalid characters, will throw an InvalidAttributeValueException.*

### ***6.5 Delete Project/Task***

- *Accepts DELETE requests to /projects/:projectId and /tasks/:taskId.*
- *Accepts a projectId or taskId.*
    - *If the project or task is not found, will throw a ProjectNotFoundException or TaskNotFoundException.*

# ***7. Tables***

*Define the DynamoDB tables you will need for the data your service will use.*

### ***7.1 Projects***

| Field | Type |
| --- | --- |
| projectId | String (Hash Key) |
| name | String |
| description | String |
| status | String |

### ***7.2 Tasks***

| Field | Type |
| --- | --- |
| projectId | String (Hash Key) |
| taskId | String (Range Key) |
| name | String |
| description | String |
| status | String |

# ***8. Pages***

*Include mock-ups of the web pages you expect to build.*

![untitled_720.jpg](v2-design-document%204202480f787143fbbfe957f51481b4b3/untitled_720