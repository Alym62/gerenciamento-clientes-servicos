import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { ClientesService } from 'src/app/core/services/clientes.service';
import { Cliente } from 'src/app/shared/models/dto/Clientes';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.scss'],
})
export class ClientesListaComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  clientes: Cliente[] = [];
  displayedColumns: string[] = ['ID', 'nome', 'CPF', 'data', 'edit', 'delete'];

  paginaAtual: number = 0;
  tamanhoPagina: number = 5;
  totalElementos!: number;

  dataSource: MatTableDataSource<Cliente> = new MatTableDataSource<Cliente>(
    this.clientes
  );

  constructor(
    private service: ClientesService,
    private _snackBar: MatSnackBar
  ) { }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 7000 });
  }

  carregarDados(): void {
    this.service
      .listarCliente(this.paginaAtual, this.tamanhoPagina)
      .subscribe((response) => {
        this.dataSource.data = response.content;
        this.totalElementos = response.totalElements;
      });
  }

  alterarPagina(event: any) {
    this.paginaAtual = event.pageIndex;
    this.tamanhoPagina = event.pageSize;
    this.carregarDados();
  }

  onRemoveCliente(id: number): void {
    this.service.deletarCliente(id).subscribe(
      (refresh) => {
        window.location.reload();

        this.openSnackBar('Cliente excluído com sucesso.', 'X');
      },
      (error) => {
        this.openSnackBar('Erro ao excluir cliente (RN).', 'X');
      }
    );
  }

  ngOnInit(): void {
    this.carregarDados();
  }
}
