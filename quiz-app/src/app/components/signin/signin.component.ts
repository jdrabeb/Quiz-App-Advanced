import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/datamodel/user';
import { UsersService } from 'src/app/services/users.service';import {Router} from '@angular/router';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  user : User = new User("", "", "USER");

  constructor(private userService : UsersService, private router: Router) { }

  ngOnInit() {
  }

  save(){
        console.log(this.user.username)
        this.userService.save(this.user);
        this.router.navigate(['login']), {replaceUrl:true};
        }
}
