import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { UserService } from './user.service';

describe('UserService', () => {
  let service: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientModule
      ]
    });
    service = TestBed.inject(UserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("verifie que getById() retourne bien un User", ()=>{

    service.getById("1").subscribe((unUser)=>{
      expect(unUser).toBeDefined();
    });
  });

   it("verifie que delete() retourne bien un observable", ()=>{

    service.delete("1").subscribe((resultatObs)=>{
      expect(resultatObs).toBeDefined();
    });
  });
});
