import { Injectable } from '@angular/core';
import { Question } from '../datamodel/question';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

    url :string = "http://localhost:8080/quiz-rest-api/rest/questions";

    constructor(private httpClient: HttpClient) { }

    getQuestionList(criterion : string): Observable<Question[]>{
        var questionList : Question[];
        return this.httpClient.get(this.url+ "/search?questionContent=" + criterion) as Observable<Question[]>;
    }

    saveQuestion(question : Question){
        return this.httpClient.post(this.url, question)
            .subscribe((data) =>
                console.log(data));
    }

    deleteQuestion(id : number){
        return this.httpClient.delete(this.url + '/delete/' + id)
            .subscribe((data) =>
                console.log(data));
    }

    updateQuestion(questionContent : string, id : number){
        return this.httpClient.put(this.url + '/update/' + id + '?questionContent=' + questionContent, null)
            .subscribe((data) =>
                console.log(data));
    }

}
