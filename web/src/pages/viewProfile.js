import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";

class ViewProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'getProfileForPage', 'getCharactersForPage','getRaidsForPage', 'createProfileTable', 'createCharactersTable','createRaidsTable', 'addProfileToPage', 'addCharactersToPage', 'addRaidsToPage'], this)
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addProfileToPage);
        this.dataStore.addChangeListener(this.addCharactrersToPage);
        this.dataStore.addChangeListener(this.addRaidsToPage);
    }

    async getProfileForPage(){
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const profile = await this.getProfileForPage(username);
        this.dataStore.set('profile', profile);
        console.log('profile has been stored');
    }

    async getCharactersForPage() {
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const characters = await this.client.getAllCharactersByUsername(username);
        this.dataStore.set('characterList',characters);
        console.log("characters are stored");
    }

    async getRaidsForPage(){
        const urlParams = new URLSearchParams(window.location.search);
        const
    }
}