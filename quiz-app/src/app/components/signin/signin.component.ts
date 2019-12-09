import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/datamodel/user';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  user : User = new User("", "", false);

  constructor(private userService : UsersService) { }

  ngOnInit() {
  }

  save(){
    this.userService.save(this.user);
    }

}
