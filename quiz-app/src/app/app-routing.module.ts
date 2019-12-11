import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SigninComponent } from './components/signin/signin.component';
import { CreateQuizComponent } from './components/create-quiz/create-quiz.component';
import { CreateQuestionsComponent } from './components/create-questions/create-questions.component';

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'signin', component: SigninComponent },
    { path: 'create', component: CreateQuizComponent },
    { path: 'questions', component: CreateQuestionsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
