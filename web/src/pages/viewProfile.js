import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";
import * as url from "url";

class ViewProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'getProfileForPage', 'getCharactersForPage', 'getRaidsForPage', 'createProfileTable', 'createCharactersTable', 'createRaidsTable', 'addProfileToPage', 'addCharactersToPage', 'addRaidsToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addProfileToPage);
        // this.dataStore.addChangeListener(this.addCharactrersToPage);
        // this.dataStore.addChangeListener(this.addRaidsToPage);
    }

    async updateProfile(event){
        event.preventDefault();
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const guild = document.getElementById('guild').value;
        const publicNote = document.getElementById('publicNote').value;
        const officerNote = document.getElementById('officerNote').value;
        const profile = await this.client.updateProfile(username,guild,publicNote,officerNote);
        this.dataStore.set('profile', profile);
        alert(username + "has been updated");
    }

    async getProfileForPage() {
        const urlParams = new URLSearchParams(window.location.search);
        const username = urlParams.get('username');
        const profile = await this.getProfile(username);
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

    mount() {
        this.client = new SanctuaryRaiderClient();
        this.getRaidsForPage();
        this.getProfileForPage();
        this.getCharactersForPage();
    }

    createProfileTable(profile) {
        if (profile.length === 0) {
            return '<h4>No profile found<h4>';
        }
        let html =
            `<form>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Guild</th>
                    <th scope="col">Public Note</th>
                    <th scope="col">Officer Note</th>
                </tr>
                </thead>`
        html += `
    <tr>
        <td>
            <input readonly type="text" value="${profile.username}" id="username">
        </td>
        <td>
            <input readonly type="text" value="${profile.guild}" id="guild">
        </td>
        <td>
            <input readonly type="text" value="${profile.publicNote}" id="publicNote">
        </td>
        <td>
            <input readonly type="text" value="${profile.officerNote}" id="officerNote">
        </td>
        <td>
            <a href="editProfile.html?username=${profile.username}">
                <span class="fas fa-fw fa-pencil">edit</span>
            </a>
        </td>
    </tr> 
   </table>
   <span>
     <button type="button" class="btn btn-success">Save</button> 
     <i class="fa-thin fa-floppy-disk"></i> 
     </span>
</form>`;
        return html;
    }
    //

    // createCharacterTable(characters) {
    //     let html = `
    //     <form>
    //         <table class="table">
    //             <thead class="thead-dark">
    //             <tr>
    //                 <th scope="col">Username</th>
    //                 <th scope="col">Guild</th>
    //                 <th scope="col">Public Note</th>
    //                 <th scope="col">Officer Note</th>
    //              </tr>
    //             </thead>
    //     `
    // }

    addProfileToPage() {
        const profile = this.data.get('profile');
        if (profile == null) {
            return;
        }
        document.getElementById('viewProfileTable').innerHTML = this.createProfileTable(profile);
        document.getElementById('saveProfile').addEventListener('click', this.updateProject);
    }
}

const main = async() => {
    const viewProfile = new ViewProfile();
    viewProfile.mount()
};
 window.addEventListener('DOMContentLoaded', main);

