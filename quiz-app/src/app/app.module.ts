import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpHeaders} from '@angular/common/http';
import { SigninComponent } from './components/signin/signin.component';
import { LoginComponent } from './components/login/login.component';
import { CreateQuizComponent } from './components/create-quiz/create-quiz.component';
import { CreateQuestionsComponent } from './components/create-questions/create-questions.component';
import { AuthInterceptor } from './auth-interceptor.module';
import { CreateQuizContentComponent } from './components/create-quiz-content/create-quiz-content.component';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    LoginComponent,
    CreateQuizComponent,
    CreateQuestionsComponent,
    CreateQuizContentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
    providers: [
    {
      provide : HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi   : true,
    },
],
  bootstrap: [AppComponent]
})
export class AppModule { }
