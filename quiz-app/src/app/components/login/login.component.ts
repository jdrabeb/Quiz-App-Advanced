import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/datamodel/user';
import { UsersService } from 'src/app/services/users.service';import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    user : User = new User("", "", "GUEST");
    role : string;

    constructor(private userService : UsersService, private router: Router) { }

    ngOnInit() {
    }

    login() {
        this.userService.login(this.user);
        this.getRole();
        if (this.role == 'USER')
            this.router.navigate(['create']), {replaceUrl:true};
        if (this.role == 'ADMIN')
            this.router.navigate(['evaluation']), {replaceUrl:true};
  }

    getRole() {
        this.userService.getUserList(this.user.username)
        .subscribe((data) =>
        {
            let role = data[0].role;
            this.role = role;
        });
    }

}
