import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { SessionService } from './session.service';
import { SessionInformation } from '../interfaces/sessionInformation.interface';

describe('SessionService', () => {
  let service: SessionService;
  let infoLoginMocke: SessionInformation;

  beforeEach(() => {

    infoLoginMocke = {
    token: "tokenMocké",
    type: "typeMocké",
    id: 1,
    username: "userMocké",
    firstName: "firstNameMocké",
    lastName: "lastNameMocké",
    admin: true,
  }

    TestBed.configureTestingModule({});
    service = TestBed.inject(SessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("test si login met bien à jour this.isLogged en true", ()=>{
    service.logIn(infoLoginMocke);
    expect(service.isLogged).toBe(true);
    expect(service.sessionInformation).toEqual(infoLoginMocke);
  });

  
  it("test si logout met bien à jour this.isLogged en false", ()=>{
    service.logOut();
    expect(service.isLogged).toBe(false);
  });
  
it("test si le $isLogged retourne bien false par defaut", ()=>{
  service.$isLogged().subscribe((resultat)=>{
    expect(resultat).toBe(false);
  });
});
});
