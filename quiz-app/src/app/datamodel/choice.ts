export class Choice {

    choiceContent: string;
    isCorrect : boolean;

    constructor(choiceContent : string, isCorrect : boolean){
        this.choiceContent = choiceContent;
        this.isCorrect = isCorrect;
    }

    setContent(choiceContent : string) {
        this.choiceContent = choiceContent;
    }

    setIsCorrect(isCorrect : boolean) {
        this.isCorrect = isCorrect;
    }

}
