import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from 'src/app/dto/Clientes';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.scss'],
})
export class ClientesListaComponent implements OnInit, AfterViewInit {
  clientes: Cliente[] = [];
  displayedColumns: string[] = ['ID', 'nome', 'CPF', 'data', 'edit', 'delete'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  dataSource: MatTableDataSource<Cliente> = new MatTableDataSource<Cliente>(
    this.clientes
  );

  constructor(private service: ClientesService) {}

  ngOnInit(): void {
    this.service
      .listarCliente()
      .subscribe((response) => (this.dataSource.data = response));
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
}
