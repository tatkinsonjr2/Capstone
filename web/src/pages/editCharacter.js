import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";

class EditCharacter extends BindingClass{
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
        const publicNote = document.getElementById('publicNote').value;
        const officerNote = document.getElementById('officerNote').value;
        const role = document.getElementById('role').value;
        const spec = document.getElementById('spec').value;
        const professionOne = document.getElementById('profession1').value;
        const professionTwo = document.getElementById('profession2').value;
        const race = document.getElementById('race').value;
        const characterClass = document.getElementById('class').value;


        const character = await this.client.updateCharacter(username, characterName, characterClass, spec, role, race, publicNote, officerNote, professionOne, professionTwo,(error) => {
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
        const profile = this.dataStore.get('profile')
        console.log("profile", profile);
        if (profile != null){
            window.location.href = `/viewProfile.html?username=${profile}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded
 */
const main = async() => {
    const editCharacter = new EditCharacter();
    editCharacter.mount();
};

window.addEventListener('DOMContentLoaded', main);