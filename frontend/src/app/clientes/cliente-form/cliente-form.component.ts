import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ClientesService } from 'src/app/services/clientes.service';
import { Cliente } from 'src/app/dto/Clientes';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.scss'],
})
export class ClienteFormComponent implements OnInit {
  cliente: Cliente = new Cliente();
  errors!: string[];
  id!: number;

  constructor(
    private service: ClientesService,
    private _snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 7000 });
  }

  redirecionar(): void {
    this.router.navigate(['/clientes/lista']);
  }

  onSubmit(): void {
    if (this.id) {
      this.service.atualizarCliente(this.cliente).subscribe(
        (rsponse) => {
          console.log(this.cliente);
          window.location.reload();
          this.openSnackBar('Cliente atualizado com sucesso.', 'X');
        },
        (error) => {
          this.openSnackBar(
            'O cliente não pode ser atualizado. Entre em contato com o suporte.',
            'X'
          );
        }
      );
    } else {
      this.service.cadastrarCliente(this.cliente).subscribe(
        (response) => {
          this.router.navigate(['/cliente-lista']);
          this.openSnackBar('Cliente salvo com sucesso.', 'X');
        },
        (error) => {
          this.openSnackBar(
            `${error.error}`,
            'X'
          );
        }
      );
    }
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.route.params;
    params.subscribe((urlParams) => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service.buscarPorId(this.id).subscribe(
          (data) => (this.cliente = data),
          (error) => (this.cliente = new Cliente())
        );
      }
    });
  }
}
