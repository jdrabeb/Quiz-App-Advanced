import { Injectable } from '@angular/core';
import { Choice } from '../datamodel/choice';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChoicesService {

    url :string = "http://localhost:8080/quiz-rest-api/rest/choices";

    constructor(private httpClient: HttpClient) { }

    getChoiceList(criterion : string): Observable<Choice[]>{
        var choiceList : Choice[];
        return this.httpClient.get(this.url+ "search?content=" + criterion) as Observable<Choice[]>;
    }

    saveChoice(choice : Choice){
        return this.httpClient.post(this.url, choice)
            .subscribe((data) =>
                console.log(data));
    }
}
