import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/datamodel/question';
import { Choice } from 'src/app/datamodel/choice';
import { QuestionsService } from 'src/app/services/questions.service';
import { ChoicesService } from 'src/app/services/choices.service';

@Component({
  selector: 'app-create-quiz-content',
  templateUrl: './create-quiz-content.component.html',
  styleUrls: ['./create-quiz-content.component.scss']
})
export class CreateQuizContentComponent implements OnInit {

    questions : Question[];
    questionIds = [];
    quiz : Quiz ("", this.questions);

    constructor(private choicesService :ChoicesService,
        private questionsService : QuestionsService, private router: Router)
    { }

    ngOnInit() {
        this.questionsService.getQuestionList("").subscribe (
            (data) => this.questions = data);
    }

    getQuestionIds() {

    }

    logout() {
        this.router.navigate(['login']), {replaceUrl:true};
        localStorage.setItem('token', '');
    }

}
