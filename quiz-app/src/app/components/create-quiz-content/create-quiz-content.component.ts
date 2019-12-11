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

    constructor(private questionsService : QuestionsService,
        private quizService : QuizService, private router: Router)
    { }

    ngOnInit() {
        this.quizQuestions = [];
        this.questionsService.getQuestionList("").subscribe (
            (data) => this.questions = data);
    }

    getQuizQuestions(i) {

        this.quizQuestions.push(this.questions[i]);
    }

    save() {
        this.quiz.questions = this.quizQuestions;
        this.quizService.saveQuiz(this.quiz);
        console.log(this.quiz);
    }

    back() {
        this.router.navigate(['create']), {replaceUrl:true};
    }


    logout() {
        this.router.navigate(['login']), {replaceUrl:true};
        localStorage.setItem('token', '');
    }

}
