import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { TeacherService } from './teacher.service';

describe('TeacherService', () => {
  let service: TeacherService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientModule
      ]
    });
    service = TestBed.inject(TeacherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("test de la fonction all()", ()=>{
    service.all().subscribe((lesTeachers)=>{
      expect(lesTeachers).toBeDefined();
    });

    service.detail("1").subscribe((unTeacher)=>{
      expect(unTeacher).toBeDefined();
    });
    
  });
});
