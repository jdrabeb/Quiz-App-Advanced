export class User {

    username : string;
    password : string;
    isAdmin : boolean;

    constructor(username : string, password : string, role : string){
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
