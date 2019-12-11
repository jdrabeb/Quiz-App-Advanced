import { Component, OnInit } from '@angular/core';
import  { Router } from '@angular/router';

@Component({
  selector: 'app-create-quiz',
  templateUrl: './create-quiz.component.html',
  styleUrls: ['./create-quiz.component.scss']
})
export class CreateQuizComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  createQuestions() {
  this.router.navigate(['questions']), {replaceUrl:true};
  }

}