import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';
import { expect } from '@jest/globals';

import { AppComponent } from './app.component';
import { SessionService } from './services/session.service';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { AuthService } from './features/auth/services/auth.service';


describe('AppComponent', () => {

  let routerMock: any;
  let sessionServiceMock: any;
  let authServiceMock: any;
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async () => {
    
    sessionServiceMock = {
      logOut: jest.fn(),
      $isLogged: jest.fn().mockReturnValue(of(true))
    };

    routerMock = {
      navigate: jest.fn()
    };

    // service moké vide car aucune fonction de ce service n'est utilisé ds ce component
    authServiceMock = {};

    await TestBed.configureTestingModule({
      imports: [],
      providers: [
        {provide: SessionService, useValue: sessionServiceMock},
        {provide: Router, useValue: routerMock},
        {provide: AuthService, useValue: authServiceMock}
      ],

      declarations: [
        AppComponent
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(AppComponent);

    component = fixture.componentInstance;

    fixture.detectChanges();
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

  it("test si logout est bien appelé et si ça redirige bien vers l'accueil", ()=>{
    component.logout();

    expect(sessionServiceMock.logOut).toBeCalled();
    expect(routerMock.navigate).toBeCalled();
  });
}); 
