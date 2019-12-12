import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { Evaluation } from '../datamodel/evaluation';

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

    url :string = "http://localhost:8080/quiz-rest-api/rest/eval";

    constructor( private httpClient : HttpClient) { }

    saveEvaluation( evaluation : Evaluation) {
        return this.httpClient.post(this.url, evaluation)
            .subscribe((data) =>
                console.log(data));
    }
}

