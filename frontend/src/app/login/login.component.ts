import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Usuario } from '../dto/Usuario';
import { Role } from '../dto/enums/Role.enum';
import { AuthService } from '../services/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  roles: Role[] = [Role.ADMIN, Role.USER];
  usuario: Usuario = new Usuario();

  loginError!: boolean;
  cadastrando!: boolean;

  constructor(private auth: AuthService, private router: Router, private _snackBar: MatSnackBar) { }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 2000 });
  }

  async onSubmit(): Promise<void> {
    if (this.cadastrando === true) {
      await this.auth.registrarUsuario(this.usuario)
        .then(() => this.router.navigate(['/home']))
        .catch((error) => {
          this.openSnackBar("Erro ao registrar", "X");
        });
        console.log(this.usuario);
    } else {
      await this.auth.loginUsuario(this.usuario)
        .then((res: any) => {
          const token = res.token;
          const expiresIn = res.expires_in;


          localStorage.setItem("expires_in", expiresIn);
          localStorage.setItem("token", token);
        })
        .catch((error) => {
          this.openSnackBar("Erro ao fazer login", "X");
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
