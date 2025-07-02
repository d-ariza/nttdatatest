import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BusquedaClienteComponent } from './busqueda-cliente';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ClienteService } from '../../services/cliente.service';
import { CommonModule } from '@angular/common';
import { NgIf, NgClass } from '@angular/common';

describe('BusquedaClienteComponent', () => {
  let component: BusquedaClienteComponent;
  let fixture: ComponentFixture<BusquedaClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientTestingModule,
        NgIf,
        NgClass
      ],
      providers: [ClienteService]
    }).compileComponents();

    fixture = TestBed.createComponent(BusquedaClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('debería aceptar números entre 8 y 11 dígitos', () => {
    const control = { value: '12345678' } as any;
    expect(component.validarLongitudNumerica(control)).toBeNull();

    const control2 = { value: '12345678901' } as any;
    expect(component.validarLongitudNumerica(control2)).toBeNull();
  });

  it('debería rechazar si tiene menos de 8 dígitos', () => {
    const control = { value: '1234567' } as any;
    expect(component.validarLongitudNumerica(control)).toEqual({ longitudInvalida: true });
  });

  it('debería rechazar si tiene más de 11 dígitos', () => {
    const control = { value: '123456789012' } as any;
    expect(component.validarLongitudNumerica(control)).toEqual({ longitudInvalida: true });
  });
});
