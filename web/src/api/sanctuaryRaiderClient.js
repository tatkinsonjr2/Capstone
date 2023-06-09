import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";


/**
 * Client to call the SanctuaryRaiderService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class SanctuaryRaiderClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createCharacter', 'createProfile', 'createRaid', 'createCharacter', 'getProfile', 'getRaid', 'getCharacter', 'updateCharacter', 'updateProfile', 'updateRaid'];
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
            userName: userName,
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
    async createProfile(userName, guild, publicNote, officerNote, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Must be signed in to create a profile.")
            const response = await this.axiosClient.post(`profiles`, {
                userName: userName,
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
                    Authorization: `Bearer {$token}`
                }
            });
            return response.data.playlist;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Add a song to a playlist.
     * @param id The id of the playlist to add a song to.
     * @param asin The asin that uniquely identifies the album.
     * @param trackNumber The track number of the song on the album.
     * @returns The list of songs on a playlist.
     */
    async addSongToPlaylist(id, asin, trackNumber, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can add a song to a playlist.");
            const response = await this.axiosClient.post(`playlists/${id}/songs`, {
                id: id,
                asin: asin,
                trackNumber: trackNumber
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.songList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Search for a soong.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The playlists that match the search criteria.
     */
    async search(criteria, errorCallback) {
        try {
            const queryParams = new URLSearchParams({ q: criteria })
            const queryString = queryParams.toString();

            const response = await this.axiosClient.get(`playlists/search?${queryString}`);

            return response.data.playlists;
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