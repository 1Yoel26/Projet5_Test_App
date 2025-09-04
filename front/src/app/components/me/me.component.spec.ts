import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { SessionService } from 'src/app/services/session.service';

import { MeComponent } from './me.component';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { of } from 'rxjs';

describe('MeComponent', () => {
  let component: MeComponent;
  let fixture: ComponentFixture<MeComponent>;

  const mockSessionService = {
    sessionInformation: {
      admin: true,
      id: 1
    }
  }
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MeComponent],
      imports: [
        MatSnackBarModule,
        HttpClientModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ],
      providers: [{ provide: SessionService, useValue: mockSessionService }],
    })
      .compileComponents();

    fixture = TestBed.createComponent(MeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});




describe("test sur le NgOnInit", ()=>{

  let meComponentTest: MeComponent;
  let sessionServiceTest: Partial<SessionService>;
  let userServiceTest: Partial<UserService>;

  beforeEach(()=>{

    // initialisation d'une sessionService fictive pour le test:
    sessionServiceTest = {
      sessionInformation: {id: 1} as SessionInformation
    };

    // initialisation d'un userService fictive pour le test:
    userServiceTest = {
      getById: jest.fn().mockReturnValue(of({id: 1}))
    };


    meComponentTest = new MeComponent(
      {} as any,
      sessionServiceTest as SessionService,
      {} as any,
      userServiceTest as UserService
    );
  });


  it("Verification de l'appel de la fonction, et de la modification de la propriété user", ()=>{

    // execution de la fonction init() sur le composant de test:
    meComponentTest.ngOnInit();

    expect(userServiceTest.getById).toHaveBeenCalledWith("1");

    expect(meComponentTest.user).toEqual({id: 1});
  
  });

});


describe("test sur la fonction delete", ()=>{

  let meComponentTest: MeComponent;
  let sessionServiceTest: Partial<SessionService>;
  let userServiceTest: Partial<UserService>;

  beforeEach(()=>{

    // initialisation d'une sessionService fictive pour le test:
    sessionServiceTest = {
      sessionInformation: {id: 1} as SessionInformation
    };

    // initialisation d'un userService fictive pour le test:
    userServiceTest = {
      delete: jest.fn().mockReturnValue(of({}))
    };


    meComponentTest = new MeComponent(
      {} as any,
      sessionServiceTest as SessionService,
      {} as any,
      userServiceTest as UserService
    );
  });


  it("Verification de l'appel de la fonction", ()=>{

    // execution de la fonction init() sur le composant de test:
    meComponentTest.delete();

    // verification que la fonction du service a bien été appelé:
    expect(userServiceTest.delete).toHaveBeenCalledWith("1");
  });


  

});





describe("test sur la fonction back", ()=>{

  let meComponentTest: MeComponent;
  

  beforeEach(() => {
    meComponentTest = new MeComponent(
      {} as any,
      {} as any,
      {} as any,
      {} as any,
       );
  });


  it("test sur la fonction back", ()=>{

    // écoute de la fonction Back() native
    const espionSurFonction = jest.spyOn(window.history, "back");

    // appel de la fonction Back() sur le composant de test:
    meComponentTest.back();

    // verification si la fonction back() à bien été appelé au moins une fois:
    expect(espionSurFonction).toHaveBeenCalled();
      
  })

});
