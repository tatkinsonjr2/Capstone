import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";


/**
 * Client to call the SanctuaryRaiderService.
 *
 * This could be a great place to explore Mixins. Currently, the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class SanctuaryRaiderClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createProfile', 'createRaid', 'createCharacter', 'getProfile', 'getRaid', 'getCharacter', 'updateCharacter', 'updateProfile', 'updateRaid'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * creates the character with a userName and CharacterName
     * @param characterName
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The character's metadata.
     */
    async createCharacter(userName, characterName, characterClass, spec, race, role, publicNote, officerNote, professionOne, professionTwo, alternateCharacter, wishList) {
        try {
            const token = await this.getTokenOrThrow("Must be signed in to create a character.")
            const response = await this.axiosClient.post(`characters`, {
            username: username,
            characterName: characterName,
            characterClass: characterClass,
            spec: spec,
            race: race,
            role: role,
            publicNote: publicNote,
            officerNote: officerNote,
            professionOne: professionOne,
            professionTwo: professionTwo,
            alternateCharacter: alternateCharacter,
            wishList: wishList,
            }, {
                headers: {
                    Authorization: 'Bearer ${token}'
                }
            });
            return response.data.character;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Creates a profile.
     * @param userName Unique identifier for a playlist
     * @param guild identifies guild entity
     * @param publicNote includes player notes
     * @param officerNote includes notes that only officers/admin can see
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns a new profile.
     */
    async createProfile(username, guild, publicNote, officerNote, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Must be signed in to create a profile.")
            const response = await this.axiosClient.post(`profiles`, {
                username: username,
                guild: guild,
                publicNote: publicNote,
                officerNote: officerNote,
            }, {
                headers: {
                    Authorization: 'Bearer ${token}'
                }
            });
            return response.data.profile;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Create a new raid with a given @param raidName.
     * @param raidName The name of the raid to create.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The raid that has been created.
     */
    async createRaid(raidName, date, publicNote, officerNote, status, instanceName, attendees, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create playlists.");
            const response = await this.axiosClient.post(`raids`, {
                raidName: raidName,
                publicNote: publicNote,
                officerNote: officerNote,
                status: status,
                instanceName: instanceName,
                attendees: attendees,
            }, {
                headers: {
                    Authorization: 'Bearer ${token}'
                }
            });
            return response.data.raid;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get a profile using the unique username.
     * @param username The unique name of the profile.
     * @returns The requested profile.
     */
    async getProfile(username, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Must be signed in to view this profile.");
            const response = await this.axiosClient.get(`profiles/${username}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.profile;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

        /**
         * Get a raid using the unique raidName.
         * @param raidName The unique name of the raid.
         * @returns The requested raid.
         */
        async getRaid(raidName, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Must be signed in to view this profile.");
                const response = await this.axiosClient.get(`raids/${raidName}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                return response.data.raid;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

        /**
         * Get a character using the unique characterName.
         * @param characterName The unique name of the character.
         * @returns The requested character.
         */
        async getCharacter(characterName, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Must be signed in to view this profile.");
                const response = await this.axiosClient.get(`/characters/${characterName}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                return response.data.character;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

  /**
     * Update an existing character's details on a character in the database.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The character that has been updated.
     */
    async updateCharacter(username, characterName, title, characterClass, spec, race, role, publicNote, officerNote, professionOne, professionTwo, alternateCharacter, wishList, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update a character.");
            const response = await this.axiosClient.put(`/character/${username}/${characterName}`, {
                username: username,
                characterName: characterName,
                characterClass: characterClass,
                spec: spec,
                race: race,
                role: role,
                publicNote: publicNote,
                officerNote: officerNote,
                professionOne: professionOne,
                professionTwo: professionTwo,
                alternateCharacter: alternateCharacter,
                wishList: wishList,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.character;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

        /**
         * Update an existing profile's details on a profile in the database.
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The profile that has been updated.
         */
    async updateProfile(username, guild, publicNote, officerNote, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update a profile.");
            const response = await this.axiosClient.put(`/profiles/{username}`, {
                username: username,
                guild: guild,
                publicNote: publicNote,
                officerNote: status,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.profile;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

       /**
         * Update an existing raid's details on a raid in the database.
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The raid that has been updated.
         */
        async updateRaid(raidName, date, publicNote, officerNote, status, instanceName, attendees, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can update a raid.");
                const response = await this.axiosClient.put(`/raids/{raidName}`, {
                    raidName: raidName,
                    date: date,
                    publicNote: publicNote,
                    officerNote: officerNote,
                    status: status,
                    instanceName: instanceName,
                    attendees: attendees,

                }, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                return response.data.profile;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}