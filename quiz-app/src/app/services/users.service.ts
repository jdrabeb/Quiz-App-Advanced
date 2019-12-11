import { Injectable } from '@angular/core';
import { User } from '../datamodel/user';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

    url :string = "http://localhost:8080/quiz-rest-api/rest/";
    constructor(private httpClient: HttpClient) { }

    getUserList(criterion : string): Observable<User[]>{
        var userList : User[];
        return this.httpClient.get(this.url+ "search?username=" + criterion) as Observable<User[]>;
    }

    save(user : User){
        return this.httpClient.post(this.url + "register", user)
            .subscribe((data) =>
                console.log(data));
    }

    login(user: User){
        return this.httpClient.post(this.url + "login", user, {observe: 'response'})
            .subscribe((data) => {
                let header = data.headers.get('Authorization');
                localStorage.setItem('token', header);
        });
    }
}
