import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";

class CreateProfile extends BindingClass{
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

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = "Loading...";

        const username = document.getElementById('username').value;
        const guild = document.getElementById('guild').value;
        const publicNote = document.getElementById('public-note').value;
        const officerNote = document.getElementById('officer-note').value;

        const profile = await this.client.createProfile(username, guild, publicNote, officerNote, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('profile', profile);
        console.log("complete");
        alert('Profile has been saved successfully')
    }

    /**
     * When the profile has been updated/created redirect to the view profile page
     */

    redirectToViewProfile(){
        const profile = this.dataStore.get('profile');
        if (profile != null){
            window.location.href = `/viewProfile.html?username=${profile.username}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded
 */
const main = async() => {
    const createProfile = new CreateProfile();
    createProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);