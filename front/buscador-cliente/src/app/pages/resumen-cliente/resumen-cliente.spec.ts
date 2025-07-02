import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ResumenClienteComponent } from './resumen-cliente';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ClienteService } from '../../services/cliente.service';

describe('ResumenClienteComponent', () => {
  let component: ResumenClienteComponent;
  let fixture: ComponentFixture<ResumenClienteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClienteService],
    });

    fixture = TestBed.createComponent(ResumenClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
