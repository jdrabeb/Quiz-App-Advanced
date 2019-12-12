import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Evaluation } from 'src/app/datamodel/evaluation';
import { Quiz } from 'src/app/datamodel/quiz';
import { Choice } from 'src/app/datamodel/choice';
import { User } from 'src/app/datamodel/user';
import { EvaluationService } from 'src/app/services/evaluation.service';
import { QuizService } from 'src/app/services/quiz.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.component.html',
  styleUrls: ['./evaluation.component.scss']
})

export class EvaluationComponent implements OnInit {

    quiz : Quiz[];
    questions : Question[];
    answers : Choice[];
    selectedQuiz : Quiz;
    isChoseQuiz : boolean;
    studentList : User[];
    grade : number;
    evaluation : Evaluation = new Evaluation(null, null, [], 0);
    isSubmit : boolean;

    constructor(private evaluationService: EvaluationService,
        private quizService: QuizService, private usersService : UsersService,
        private router: Router) { }

    ngOnInit() {
        this.answers = [];
        let studentName = localStorage.getItem('student');
        this.quizService.getQuizList("").subscribe(
            (data) => this.quizList = data);
        this.isChoseQuiz = false;
        this.grade = 0;
        this.submit = true;
        //   this.usersService.getUserList(studentName).subscribe(
        //   (data) => this.studentList = data);
  }

    pickQuiz(quiz : Quiz)
    {
        this.selectedQuiz = quiz;
        this.isChoseQuiz = true;
        this.questions = quiz.questions;
    }

    setGrade(answers : Choice[]){
        this.answers.forEach(
            choice => {
                console.log(choice);
                if (choice.isCorrect)
                    this.grade++ ;
            })
    }

    getAnswer(choice : Choice)
    {
        this.answers.push(choice);
    }

    sendEval() {
        this.setGrade(this.answers);
        console.log(this.grade);
        this.evaluation.grade = this.grade;
        this.evaluation.quiz = this.selectedQuiz;
        this.evaluation.answers = this.answers;
        this.evaluationService.saveEvaluation(this.evaluation);
        this.isSubmit = true;
        console.log(this.evaluation);
    }

    logout() {
        this.router.navigate(['login']), {replaceUrl:true};
        localStorage.setItem('token', '');
    }
}

