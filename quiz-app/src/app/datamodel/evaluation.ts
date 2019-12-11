import { Choice } from './choice';
import { User } from './user';
import { Quiz } from './quiz';

export class Evaluation {

    student: User;
    quiz: Quiz;
    answers : Choice[];
    grade: number;

    constructor(student : User, quiz: Quiz, answers : Choice[], grade : number){
        this.student = student;
        this.quiz = quiz;
        this.answers = answers;
        this.grade = grade;
    }
}

