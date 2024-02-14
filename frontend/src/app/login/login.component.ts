import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth-service.service';
import { Usuario } from '../dto/Usuario';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  usuario: Usuario = new Usuario();

  loginError!: boolean;
  cadastrando!: boolean;

  constructor(private auth: AuthService, private router: Router) { }

  onSubmit(): void {
    if (this.cadastrando === true) {
      this.auth.registrarUsuario(this.usuario).subscribe();
    } else {
      this.auth.loginUsuario(this.usuario).subscribe((res: any) => {
        const token = res.token;
        const expiresIn = res.expires_in;


        localStorage.setItem("expires_in", expiresIn);
        localStorage.setItem("token", token);
      });
    }
    this.router.navigate(['/home']);
  }

  prepararCadastro(event: any): void {
    event.preventDefault();
    this.cadastrando = true;
  }

  cancelarCadastro(): void {
    this.cadastrando = false;
  }
}
