import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from 'src/app/dto/Clientes';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.scss'],
})
export class ClienteFormComponent implements OnInit {
  cliente: Cliente = new Cliente();
  errors!: string[];

  constructor(
    private service: ClientesService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) {}

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 2000 });
  }

  redirecionar(): void {
    this.router.navigate(['/cliente-lista']);
  }

  onSubmit(): void {
    this.service.cadastrarCliente(this.cliente).subscribe(
      (response) => {
        this.router.navigate(['/cliente-lista']);
        this.openSnackBar('Cliente salvo com sucesso.', 'X');
      },
      (error) => {
        this.openSnackBar('O cliente não pode ser criado. Coloque um CPF e nome válido.', 'X');
      }
    );
  }

  ngOnInit(): void {}
}
