import SanctuaryRaiderClient from "../api/sanctuaryRaiderClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from "../util/DataStore";

class CreateProfile extends BindingClass{
    constructor() {
        super();
        this.bindClassMethods([mount, submit, ])
    }
}