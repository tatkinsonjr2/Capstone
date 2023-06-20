import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";
import * as url from "url";

class ViewProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'getProfileForPage', 'getCharactersForPage', 'getRaidsForPage', 'createProfileTable'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);

        // this.dataStore.addChangeListener(this.addCharactersToPage);
        // this.dataStore.addChangeListener(this.addRaidsToPage);
    }

    async updateProfile(event) {
        event.preventDefault();
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const guild = document.getElementById('guild').value;
        const publicNote = document.getElementById('publicNote').value;
        const officerNote = document.getElementById('officerNote').value;
        const profile = await this.client.updateProfile(username, guild, publicNote, officerNote);
        this.dataStore.set('profile', profile);
        console.log(profile);
        alert(username + "has been updated");
    }

    async getProfileForPage() {
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const profile = await this.client.getProfile(username);
        this.dataStore.set('profile', profile);
        console.log('profile has been stored');
    }

    async getCharactersForPage() {
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const characters = await this.client.getAllCharactersByUsername(username);
        this.dataStore.set('characterList', characters);
        console.log("characters are stored");
    }

    async getRaidsForPage() {
        const urlParams = new URLSearchParams(window.location.search);
        const characterName = urlParams.get('characterName');
        const raids = await this.client.getAllRaidsByCharacterName(characterName);
        this.dataStore.set('raids', raids);
        console.log('raids are stored');
    }

    async mount() {
        this.client = new SanctuaryRaiderClient();
        await this.getProfileForPage();
        console.log('this is', this.dataStore.get('profile'));
        await this.createProfileTable(this.dataStore.get('profile'));
        // this.getRaidsForPage();
        // this.getCharactersForPage();
    }

    async createProfileTable(profile) {
        const profileName = document.getElementById("profile-username");
        profileName.innerText = profile.username;
        const guildName = document.getElementById("guild-name");
        guildName.innerText = profile.guild;
        console.log("html error");
    }

}
const main = async() => {
    const viewProfile = new ViewProfile();
    await viewProfile.mount();
};
 window.addEventListener('DOMContentLoaded', main);

