import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ServicoPrestadoBusca } from 'src/app/dto/ServicoPrestadoBusca';
import { ServicoPrestadoService } from 'src/app/services/servico-prestado.service';

@Component({
  selector: 'app-servico-prestado-lista',
  templateUrl: './servico-prestado-lista.component.html',
  styleUrls: ['./servico-prestado-lista.component.scss']
})
export class ServicoPrestadoListaComponent {
  nome!: string;
  mes!: number;
  meses: number[];
  lista!: ServicoPrestadoBusca[];

  displayedColumns: string[] = ['cliente', 'descricao', 'valor', 'data'];

  dataSource: MatTableDataSource<ServicoPrestadoBusca> = new MatTableDataSource<ServicoPrestadoBusca>(
    this.lista
  );

  constructor(private servicoService: ServicoPrestadoService,
    private _snackBar: MatSnackBar, private router: Router) {
    this.meses = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 7000 });
  }

  onNavigate(): void {
    this.router.navigate(['/servico-prestado/form']);
  }

  consultar(): void {
    this.servicoService.pesquisarServico(this.nome, this.mes).subscribe((response) => {
      this.dataSource.data = response;

      if (this.lista.length <= 0) {
        this.openSnackBar("Não existe nenhum registro de serviço com base nos dados passados.", "X");
      }
    })
  }
}
