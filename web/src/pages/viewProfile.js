import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";
import * as url from "url";

class ViewProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'getProfileForPage', 'getCharactersForPage', 'getRaidsForPage', 'createProfileTable', 'addCharactersToPage'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);


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
        console.log("characters are stored", characters);
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
        this.dataStore.addChangeListener(this.addCharactersToPage);
        await this.getProfileForPage();
        await this.getCharactersForPage();
        await this.getRaidsForPage();
        console.log('this is', this.dataStore.get('profile'));
        await this.createProfileTable(this.dataStore.get('profile'));
        await this.createCharactersTable(this.dataStore.get('characters'));

        // this.getRaidsForPage();
        // this.getCharactersForPage();
    }

    async createProfileTable(profile) {
        const profileName = document.getElementById("profile-username");
        profileName.innerText = profile.username;
        const guildName = document.getElementById("guild-name");
        guildName.innerText = profile.guild;
        // const characterName = document.getElementById()
        console.log("html error");
    }

    addCharactersToPage(){
        const characters = this.dataStore.get('characterList');
        if(!characters){
            return;
        }
        console.log("what we are looking for", characters);
        const ul = document.getElementById('characterList');
        let characterHtml = "";
        characters.forEach(c => {
            characterHtml += `                        <li>
                            <div class="dropdown">
                                <a class="dropdown-toggle mt-3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-original-title="" title="">
                            <span class="font-weight-bold h2" data-original-title="" title="">
                    <span class="" data-original-title="" id="current-character" title="">${c.characterName}</span>
                </span>
                                </a>
                            </div>
                        </li>
                        <li>
                            <ul class="list-unstyled" style="list-style-type: none;">
                                <li class="list-inline-item list-unstyled mb-1 mt-1">
                                    <span class="" data-original-title="" title="">${c.characterClass}</span>
                                </li>
                            </ul>
                        </li>`;
        });
        ul.innerHTML = characterHtml;
    }

    async createCharactersTable() {
        const username = document.getElementById("profile-username");
        const characters = await this.getCharactersForPage()
    }
}
const main = async() => {
    const viewProfile = new ViewProfile();
    await viewProfile.mount();
};
 window.addEventListener('DOMContentLoaded', main);

