import { Choice } from './choice';

export class Question {

    questionContent: string;
    difficulty : number;
    choices : Choice[];

    constructor(questionContent : string, difficulty : number, choices : Choice[]){
        this.questionContent = questionContent;
        this.difficulty = difficulty;
        this.choices = choices;
    }
}
