import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";

class ViewProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['getProfileForPage', 'getCharactersForProfile',''])
    }

    async getProfileForPage(){
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const profile = await this.getProfileForPage(username);
        this.dataStore.set('profile', profile);
        console.log('profile has been stored');
    }

    async getCharactersForProfile() {
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const characters = await this.client.getAllCharacters
    }
}