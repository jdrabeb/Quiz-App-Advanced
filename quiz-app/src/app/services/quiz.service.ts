import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Quiz } from '../datamodel/quiz';
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class QuizService {

    url :string = "http://localhost:8080/quiz-rest-api/rest/quiz";

    constructor( private httpClient : HttpClient) { }

    getQuizList(criterion : string): Observable<Quiz[]>{
        var quizList : Quiz[];
        return this.httpClient.get(this.url+ "/search?title=" + criterion) as Observable<Quiz[]>;
    }

    saveQuiz(quiz : Quiz){
        return this.httpClient.post(this.url, quiz)
            .subscribe((data) =>
                console.log(data));
    }
}

