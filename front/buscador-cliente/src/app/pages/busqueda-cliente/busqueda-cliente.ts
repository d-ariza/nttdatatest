import { Component } from '@angular/core';
import { CommonModule, NgIf, NgClass } from '@angular/common';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteService } from '../../services/cliente.service';

declare const bootstrap: any;

@Component({
  selector: 'app-busqueda-cliente',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, NgIf, NgClass],
  templateUrl: './busqueda-cliente.html',
  styleUrl: './busqueda-cliente.scss',
})
export class BusquedaClienteComponent {
  formulario: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private clienteService: ClienteService
  ) {
    this.formulario = this.fb.group({
      tipo: ['', Validators.required],
      numero: ['', [Validators.required, this.validarLongitudNumerica]],
    });
  }

  validarLongitudNumerica(control: AbstractControl): { [key: string]: any } | null {
    const soloNumeros = (control.value || '').replace(/\D/g, '');
    const longitud = soloNumeros.length;
    return longitud >= 8 && longitud <= 11 ? null : { longitudInvalida: true };
  }

  campoInvalido(campo: string): boolean {
    const control = this.formulario.get(campo);
    return !!control && control.invalid && control.touched;
  }

  formatearNumero(event: any): void {
    const valor = event.target.value.replace(/\D/g, '').substring(0, 11);
    const separado = valor.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
    this.formulario.get('numero')?.setValue(separado, { emitEvent: false });
  }

  buscarCliente(): void {
    if (this.formulario.valid) {
      const tipo = this.formulario.value.tipo;
      const numero = this.formulario.value.numero.replace(/\./g, '');
      const ahora = new Date().getTime();

      console.log('Buscando tipo:', tipo);
      console.log('Buscando número:', numero);

      this.clienteService.getCliente(tipo, numero).subscribe({
        next: () => {
          sessionStorage.setItem('tipo', tipo);
          sessionStorage.setItem('numero', numero);
          sessionStorage.setItem('timestamp', ahora.toString());
          this.router.navigate(['/resumen'], { state: { tipo, numero } });
        },
        error: (error) => {
          console.error('Cliente no encontrado o error del backend:', error);
          const modal = new bootstrap.Modal(document.getElementById('errorModal')!);
          modal.show();
        },
      });
    } else {
      this.formulario.markAllAsTouched();
    }
  }
}
