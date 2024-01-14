import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username!: string;
  password!: string;

  loginError!: boolean;

  constructor() { }

  onSubmit() {
    console.log(`User: ${this.username}, Pass: ${this.password}`);
  }
}
