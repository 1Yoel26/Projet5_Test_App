import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { expect } from '@jest/globals';

import { RegisterComponent } from './register.component';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';


describe("test 2 cas lorsque le form est envoyé : test  en cas de succès ou d'erreur", ()=>{
  
  let registerComponent: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let authServiceMock: any;
  let routerMock: any;
  let onError: boolean;

  beforeEach(()=>{

    authServiceMock = {
      register: jest.fn()
    };

    routerMock = {
      navigate: jest.fn()
    };

    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [RegisterComponent],
      providers: [
        {provide: AuthService, useValue: authServiceMock},
        {provide: Router, useValue: routerMock}
      ]
    }).compileComponents;

    fixture = TestBed.createComponent(RegisterComponent);
    registerComponent = fixture.componentInstance;
    fixture.detectChanges();

  });

  // test en cas de succès d'envois du form
  it("test si le form est envoyé, et si la fonction register est bien appelé, et si le router est bien appelé avec '/login' en cas de succès.", ()=>{
    
    // creation des donnes fictives du compte à enregistrer:
     registerComponent.form.setValue({
      email: 'test@example.com',
      firstName: 'John',
      lastName: 'Doe',
      password: 'password123'
     });


     // creation du service fictive qui renvois un enregistrement réussi:
     authServiceMock.register.mockReturnValue(of(0));

    //appel de la vrai fonction submit pour tester:
    registerComponent.submit();

    // verification si register() à bien été appelé avec les bonnes données:
    expect(authServiceMock.register).toHaveBeenCalledWith({
      email: 'test@example.com',
      firstName: 'John',
      lastName: 'Doe',
      password: 'password123'
    });


    // verification que l'utilisateur est bien redirigé vers login:
    expect(routerMock.navigate).toHaveBeenCalledWith(['/login']);

  });



  // test en cas d'erreur d'envois du form
  it("test si le form n'est pas envoyé si onError = true.", ()=>{

    // creation des donnes fictives du compte à enregistrer:
     registerComponent.form.setValue({
      email: 'test@example.com',
      firstName: 'John',
      lastName: 'Doe',
      password: 'password123'
     });


     // creation du service fictive qui renvois un enregistrement qui échoue:
     authServiceMock.register.mockReturnValue(throwError(() => new Error("Erreur d'enregistrement")));

    //appel de la vrai fonction submit pour tester:
    registerComponent.submit();

    expect(registerComponent.onError).toBe(true);
    
  });  

  // test automatique d'angular pour tester que le composant peut bien être crée:
  it('should create', () => {
    expect(registerComponent).toBeTruthy();
  });
  

});