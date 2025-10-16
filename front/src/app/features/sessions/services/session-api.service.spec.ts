import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { SessionApiService } from './session-api.service';
import { result } from 'cypress/types/lodash';
import { Observable } from 'rxjs';
import { Session } from '../interfaces/session.interface';

describe('SessionsService', () => {
  let service: SessionApiService;
  let infoSessionMocke: Session;

  beforeEach(() => {

    infoSessionMocke = {
      id: 0,
      name: "nameMocké",
      description: "test",
      date: new Date("20-24-2025"),
      teacher_id: 1,
      users: [5],
      createdAt: new Date("20-24-2025"),
      updatedAt: new Date("20-24-2025")
    };

    TestBed.configureTestingModule({
      imports:[
        HttpClientModule
      ]
    });
    service = TestBed.inject(SessionApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  
  it("verifie que la fonction retourne bien un observable définit", ()=>{
    service.all().subscribe((resultat)=>{
      expect(resultat).toBeInstanceOf(Observable);
    });
  });

  it("verifie que la fonction retourne bien un observable définit", ()=>{
    service.create(infoSessionMocke).subscribe((resultat)=>{
      expect(resultat).toBeInstanceOf(Observable);
    });
  });

  it("verifie que la fonction retourne bien un observable définit", ()=>{
    service.delete("1").subscribe((resultat)=>{
      expect(resultat).toBeInstanceOf(Observable);
    });
  });

  it("verifie que la fonction retourne bien un observable définit", ()=>{
    service.detail("1").subscribe((resultat)=>{
      expect(resultat).toBeInstanceOf(Observable);
    });
  });

  it("verifie que la fonction retourne bien un observable définit", ()=>{
    service.participate("3", "1").subscribe((resultat)=>{
      expect(resultat).toBeInstanceOf(Observable);
    });
  });

  it("verifie que la fonction retourne bien un observable définit", ()=>{
    service.unParticipate("3", "1").subscribe((resultat)=>{
      expect(resultat).toBeInstanceOf(Observable);
    });
  });

  it("verifie que la fonction retourne bien un observable définit", ()=>{
    service.update("123", infoSessionMocke).subscribe((resultat)=>{
      expect(resultat).toBeInstanceOf(Observable);
    });
    
  });
});
