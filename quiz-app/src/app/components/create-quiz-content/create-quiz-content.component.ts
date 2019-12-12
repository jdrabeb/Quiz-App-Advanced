import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/datamodel/question';
import { Quiz } from 'src/app/datamodel/quiz';
import { QuizService } from 'src/app/services/quiz.service';
import { QuestionsService } from 'src/app/services/questions.service';

@Component({
  selector: 'app-create-quiz-content',
  templateUrl: './create-quiz-content.component.html',
  styleUrls: ['./create-quiz-content.component.scss']
})
export class CreateQuizContentComponent implements OnInit {

    questions : Question[];
    quizQuestions : Question[];
    quiz : Quiz = new Quiz("", this.questions);
    isEditEnable : number = -1;
    isChanged : boolean;
    quizCreated : boolean;
    newQuestionContent : string;

    constructor(private questionsService : QuestionsService,
        private quizService : QuizService, private router: Router)
    { }

    ngOnInit() {
        this.quizQuestions = [];
        this.questionsService.getQuestionList("").subscribe (
            (data) => this.questions = data);
        this.isChanged = false;
        this.quizCreated = false;
    }

    getQuizQuestions(i) {
        this.quizQuestions.push(this.questions[i]);
    }

    save() {
        this.quiz.questions = this.quizQuestions;
        this.quizService.saveQuiz(this.quiz);
        this.quizCreated = true;
    }

    back() {
        this.router.navigate(['create']), {replaceUrl:true};
    }

    delete(i : number, id : number) {
        this.questionsService.deleteQuestion(id);
        this.isChanged = true;
    }

    update(i : number, id : number) {
        if (this.isEditEnable !== i)
            this.isEditEnable = i;
        else
        {
            this.questionsService.updateQuestion(this.newQuestionContent, id)
            this.isEditEnable = -1;
        }
    }

    logout() {
        this.router.navigate(['login']), {replaceUrl:true};
        localStorage.setItem('token', '');
    }

}
