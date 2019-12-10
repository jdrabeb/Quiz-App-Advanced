import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/datamodel/user';
import { UsersService } from 'src/app/services/users.service';import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    user : User = new User("", "", "USER");

  constructor(private userService : UsersService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.userService.login(this.user);
    this.router.navigate(['xx']), {replaceUrl:true};
  }

}
