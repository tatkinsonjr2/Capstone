import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";

class CreateRaid extends BindingClass{
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

        const raidName = document.getElementById('raid-name');
        const publicNote = document.getElementById('public-note');
        const officerNote = document.getElementById('officer-note');
        const instanceName = document.getElementById('instanceName');


        const raid = await this.client.createRaid(raidName,instanceName, publicNote, officerNote, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('raid', raid);
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
    const createRaid = new CreateRaid();
    createRaid.mount();
};

window.addEventListener('DOMContentLoaded', main);