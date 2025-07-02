import { Component, OnInit } from '@angular/core';
import { CommonModule, NgIf } from '@angular/common';
import { Cliente, ClienteService } from '../../services/cliente.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-resumen-cliente',
  standalone: true,
  imports: [CommonModule, NgIf],
  templateUrl: './resumen-cliente.html',
  styleUrl: './resumen-cliente.scss',
})
export class ResumenClienteComponent implements OnInit {
  cliente: Cliente | null = null;
  cargando: boolean = true;
  error: boolean = false;

  constructor(
    private router: Router,
    private clienteService: ClienteService
  ) {}

  ngOnInit(): void {
    const tipo = sessionStorage.getItem('tipo');
    const numero = sessionStorage.getItem('numero');
    const timestampStr = sessionStorage.getItem('timestamp');

    const ahora = new Date().getTime();
    const vencimientoMs = 10 * 60 * 1000; // limpiar la storage a los 10 minutos
    const timestamp = timestampStr ? parseInt(timestampStr, 10) : 0;

    if (!tipo || !numero || !timestampStr || ahora - timestamp > vencimientoMs) {
      this.limpiarSession();
      this.error = true;
      this.cargando = false;
      return;
    }

    this.clienteService.getCliente(tipo, numero).subscribe({
      next: (data) => {
        this.cliente = data;
        this.cargando = false;
      },
      error: (err) => {
        console.error('Error al obtener cliente:', err);
        this.error = true;
        this.cargando = false;
      },
    });
  }

  volver(): void {
    this.limpiarSession();
    this.router.navigate(['/']);
  }

  private limpiarSession(): void {
    sessionStorage.removeItem('tipo');
    sessionStorage.removeItem('numero');
    sessionStorage.removeItem('timestamp');
  }
}
