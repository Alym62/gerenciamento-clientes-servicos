import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ClientesService } from 'src/app/core/services/clientes.service';
import { Cliente } from 'src/app/shared/models/dto/Clientes';
import { ServicoPrestado } from 'src/app/shared/models/dto/ServicoPrestado';
import { ServicoPrestadoService } from 'src/app/core/services/servico-prestado.service';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html',
  styleUrls: ['./servico-prestado-form.component.scss']
})
export class ServicoPrestadoFormComponent implements OnInit {
  clientes: Cliente[] = [];

  servico: ServicoPrestado = new ServicoPrestado();

  constructor(private clienteService: ClientesService,
    private router: Router, private servicoService: ServicoPrestadoService,
    private _snackBar: MatSnackBar) { }

  onNavigate(): void {
    this.router.navigate(['/servico-prestado/lista']);
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 2000 });
  }

  onSubmit(): void {
    this.servico.data = this.formatarData(this.servico.data);
    this.servicoService.salvarServico(this.servico).subscribe((secesso) => {
      this.onNavigate();
      this.openSnackBar("Serviço registrado com sucesso.", "X")
    }, (error) => {
      this.openSnackBar("Erro", "X");
    })
  }

  ngOnInit(): void {
    this.clienteService.listarClienteParaOServico().subscribe(response => this.clientes = response);
  }

  private formatarData(data: string): string {
    if (data.length === 8) {
      return `${data.substring(4, 8)}-${data.substring(2, 4)}-${data.substring(0, 2)}`;
    }

    return data;
  }
}
