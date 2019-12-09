import { Injectable } from '@angular/core';
import { User } from '../datamodel/user';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  url :string = "http://localhost:8080/quiz-rest-api/rest/users";

  constructor(private httpClient: HttpClient) { }

    getUserList(criterion : string): Observable<User[]>{
        var userList : User[];
        return this.httpClient.get(this.url+ "?username=" + criterion) as Observable<User[]>;
  }

    save(user : User){
        this.httpClient.post(this.url, user).subscribe((data) =>
        console.log(data)
    );
  }


}
