import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

    url :string = "http://localhost:8080/quiz-rest-api/rest/questions";

  constructor() { }
}
