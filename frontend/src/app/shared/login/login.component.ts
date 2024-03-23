import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/shared/models/dto/Usuario';
import { Role } from 'src/app/shared/enums/Role.enum';
import { AuthService } from 'src/app/core/services/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  roleAdmin: Role = Role.ADMIN;
  roleUser: Role = Role.USER;
  roles: Role[] = [this.roleUser, this.roleAdmin];
  selectedRoles: string[] = [];
  usuario: Usuario = new Usuario();

  loginError!: boolean;
  cadastrando!: boolean;

  constructor(private auth: AuthService, private router: Router, private _snackBar: MatSnackBar) { }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 2000 });
  }

  async onSubmit(): Promise<void> {
    if (this.cadastrando === true) {
      this.usuario.roles = [this.selectedRoles];
      await this.registrarUsuario();
    } else {
      await this.loginUsuario();
    }
  }

  async registrarUsuario(): Promise<void> {
    await this.auth.registrarUsuario(this.usuario)
      .then(() => this.openSnackBar('✓ Usuário cadastrado com sucesso.', 'X'))
      .catch((error) => {
        this.openSnackBar(`${error.error}`, "X");
      });
  }

  async loginUsuario(): Promise<void> {
    await this.auth.loginUsuario(this.usuario)
      .then((res: any) => {
        const token = res.token;
        const expiresIn = res.expires_in;

        this.openSnackBar('✓ Login feito com sucesso.', 'X');

        localStorage.setItem("expires_in", expiresIn);
        localStorage.setItem("token", token);

        this.router.navigate(['/home']);
      })
      .catch((error) => {
        this.openSnackBar(`${error.error}`, "X");
      });
  }

  prepararCadastro(event: any): void {
    event.preventDefault();
    this.cadastrando = true;
  }

  cancelarCadastro(): void {
    this.cadastrando = false;
  }
}
