import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Evaluation } from 'src/app/datamodel/evaluation';
import { Quiz } from 'src/app/datamodel/quiz';
import { Choice } from 'src/app/datamodel/choice';
import { EvaluationService } from 'src/app/services/evaluation.service';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.component.html',
  styleUrls: ['./evaluation.component.scss']
})

export class EvaluationComponent implements OnInit {

    constructor(private evaluationService: EvaluationService,
        private quizService: QuizService, private router: Router) { }

  ngOnInit() {
  }

    back() {
        this.router.navigate(['create']), {replaceUrl:true};
    }

    logout() {
        this.router.navigate(['login']), {replaceUrl:true};
        localStorage.setItem('token', '');
    }
}

