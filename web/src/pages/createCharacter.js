import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";

class CreateCharacter extends BindingClass{
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewProfile'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewProfile);
        this.header = new Header(this.dataStore);
    }

    /**
     * Load the SanctuaryRaiderClient
     */
    mount() {
        document.getElementById("create").addEventListener("click", this.submit);
        this.client = new SanctuaryRaiderClient();
    }

    /**
     * Method to run when the create profile submit button is pressed.
     */
    async submit(evt){
        evt.preventDefault();

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = "Loading...";

        const username = document.getElementById('username').value;
        const characterName = document.getElementById('characterName').value;
        const characterClass = document.getElementById('class').value;
        const role = document.getElementById('role').value;
        const spec = document.getElementById('spec').value;
        const race = document.getElementById('race').value;
        const profession1 = document.getElementById('profession1').value;
        const profession2 = document.getElementById('profession2').value;
        const publicNote = document.getElementById('publicNote').value;
        const officerNote=  document.getElementById('officerNote').value;
        //unsure of alternate character logic


        const character = await this.client.createCharacter(username, characterName, characterClass, role, spec, race, profession1, profession2, publicNote, officerNote, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('profile', username);
        this.dataStore.set('character', character);
        console.log("complete");
        alert('character has been saved successfully')
    }

    /**
     * When the profile has been updated/created redirect to the view profile page
     */

    redirectToViewProfile(){
        const profile = this.dataStore.get('profile');
        if (profile != null){
            window.location.href = `/viewProfile.html?username=${profile}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded
 */
const main = async() => {
    const createCharacter = new CreateCharacter();
    createCharacter.mount();
};

window.addEventListener('DOMContentLoaded', main);