import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/datamodel/question';
import { Choice } from 'src/app/datamodel/choice';
import { QuestionsService } from 'src/app/services/questions.service';
import { ChoicesService } from 'src/app/services/choices.service';

@Component({
  selector: 'app-create-questions',
  templateUrl: './create-questions.component.html',
  styleUrls: ['./create-questions.component.scss']
})
export class CreateQuestionsComponent implements OnInit {

    array = Array;
    count = 3;
    choices = [];
    question : Question = new Question("", 0, this.choices);

  constructor(private choicesService :ChoicesService, private questionsService : QuestionsService, private router: Router) { }

    ngOnInit() {
        this.choices = [];
    }

    dataChanged(event, i, type) {
        if (type === 'choiceContent')
            this.choices[i] = Object.assign(this.choices[i] ||
                {isCorrect:false}, {choiceContent:event})
        if (type === 'isCorrect')
            this.choices[i] = Object.assign(this.choices[i] ||
                {choiceContent:""}, {isCorrect:event})
   }

    save() {
        this.choices.forEach(
            choice => {
                this.choicesService.saveChoice(choice);
                console.log(choice); })
        this.question.choices = this.choices;
        this.questionsService.saveQuestion(this.question);
        console.log(this.question);
   }

}
