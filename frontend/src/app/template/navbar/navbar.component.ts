import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input() profile!: string;

  constructor(private router: Router){
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
