import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import {  FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { expect } from '@jest/globals';
import { SessionService } from 'src/app/services/session.service';
import { SessionApiService } from '../../services/session-api.service';

import { FormComponent } from './form.component';
import { ActivatedRoute, ActivatedRouteSnapshot, Router } from '@angular/router';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { TeacherService } from 'src/app/services/teacher.service';
import { of } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { Session } from '../../interfaces/session.interface';

describe('FormComponent', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;
  let activatedRouteMock: any;
  let matSnackBarMock: any;
  let sessionApiServiceMock: any;
  let sessionServiceMock: any;
  let teacherServiceMock: any;
  let routerMock: any;
  let sessionMockDetail: any;


  beforeEach(() => {

    sessionServiceMock = {
      sessionInformation: {
      admin: true 
    }
    };

    routerMock = {
      url: "/sessions/update/1",  
      navigate: jest.fn()
    };

    sessionMockDetail = {
      name: "Session de yoga mocké",
      date: "2025-09-25",
      teacher_id: "123",
      description: "Super session mocké!"
    };

    sessionApiServiceMock = {
      detail: jest.fn().mockReturnValue(of(sessionMockDetail)),
      create: jest.fn().mockReturnValue(of(0)),
      update: jest.fn().mockReturnValue(of(0)),
    };

    activatedRouteMock = {
      snapshot: {
        paramMap: {
          get: jest.fn().mockReturnValue("1")
        }
      }
    };

    teacherServiceMock = {
      all: jest.fn().mockReturnValue(of(0))
    };

    matSnackBarMock = {
      open: jest.fn()
    };

     TestBed.configureTestingModule({

      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatCardModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule, 
        MatSnackBarModule,
        MatSelectModule,
        BrowserAnimationsModule
      ],
      providers: [
        { provide: SessionService, useValue: sessionServiceMock },
        { provide: SessionApiService, useValue: sessionApiServiceMock },
        { provide: TeacherService, useValue: teacherServiceMock },
        { provide: MatSnackBar, useValue: matSnackBarMock },
        { provide: ActivatedRoute, useValue: activatedRouteMock },
        { provide: Router, useValue: routerMock },
       
      ],
      declarations: [FormComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

// test en cas de UPDATE
  
  it("verification que le component est bien créé", ()=>{
    expect(component).toBeTruthy();
  });

  it("verification si initForm initialise bien les anciennes donnés du form pour update ", ()=>{
    
    expect(component.onUpdate).toBe(true);

    component.ngOnInit();

    expect(component.sessionForm?.hasError("required"));
    expect(component.sessionForm?.value).toEqual(sessionMockDetail);
  
  });


//début de admin modifié en false:
  it("verifie que si l'user n'est pas admin il est bien redirigé vers /session", ()=>{
    
    sessionServiceMock.sessionInformation.admin = false;
    
    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    component.ngOnInit();

    expect(routerMock.navigate).toHaveBeenCalledWith(["/sessions"]);
  
// fin de admin modifié en false
  });

  // test si l'user admin n'est bien pas redirigé
  it("ne redirige pas si l'user est admin", () => {

    routerMock.navigate.mockClear();

    component.ngOnInit();
    expect(routerMock.navigate).not.toHaveBeenCalled();

  });

  it("verifie si les données sont bien chargé dans le form UPDTAE", ()=>{
     
    // test si le form contient bien ces donnés à modifier
    const sessionInfoMock = {
      name: "Session de yoga mocké",
      date: "2025-09-25",
      teacher_id: "123",
      description: "Super session mocké!"
    };

    component.sessionForm?.setValue(sessionInfoMock);

    const espionUpdate = jest.spyOn(sessionApiServiceMock, "update").mockReturnValue(of(sessionInfoMock));

    component.submit()
    
    expect(espionUpdate).toHaveBeenCalledWith("1", sessionInfoMock);

  });


  it("verifie si onUpdate est bien true si l'url contient update", ()=>{
    expect(component.onUpdate).toBe(true);
    expect(sessionApiServiceMock.detail).toHaveBeenCalled();

    component.submit();
    expect(sessionApiServiceMock.update).toHaveBeenCalled();
    
  });


  
// test en cas de CREATE
  it("verifie si onUpdate est bien false si l'url ne contient pas update", ()=>{
    
    routerMock.url = "/sessions/create";
    
    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    expect(component.onUpdate).toBe(false); 
    expect(component.sessionForm).toBeDefined();

    // test du form pour CREATE
    const form = component.sessionForm!;
    expect(form.value).toEqual({
      name: '',
      date: '',
      teacher_id: '',
      description: ''
    });

    // Vérifier les validateurs
    expect(form.get('name')?.hasError('required')).toBe(true);
    expect(form.get('date')?.hasError('required')).toBe(true);

    const sessionInfoMock = {
      name: "Session de yoga mocké",
      date: "2025-09-25",
      teacher_id: "123",
      description: "Super session mocké!"
    };

    component.sessionForm?.setValue(sessionInfoMock);

    const espionCreate = jest.spyOn(sessionApiServiceMock, "create").mockReturnValue(of(component.sessionForm?.value));

    component.submit();
    expect(sessionApiServiceMock.create).toHaveBeenCalled();
    
    expect(espionCreate).toHaveBeenCalledWith(sessionInfoMock);
  });


  
});




