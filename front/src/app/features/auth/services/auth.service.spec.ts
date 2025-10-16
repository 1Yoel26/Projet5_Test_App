import { ComponentFixture, TestBed } from "@angular/core/testing";
import { AuthService } from "./auth.service";
import { HttpClient } from "@angular/common/http";
import { RegisterRequest } from "../interfaces/registerRequest.interface";
import { expect } from '@jest/globals';
import { of } from "rxjs";
import { LoginRequest } from "../interfaces/loginRequest.interface";

describe("AuthService", ()=>{
    let service: AuthService;
    let httpClientMock: any;
    let infoRegisterMock: RegisterRequest;
    let infoLoginMock: LoginRequest;

    beforeEach(()=>{

        httpClientMock = {
            post: jest.fn().mockReturnValue(of(0)),
        };

        infoRegisterMock = {
            email: "test@test.fr",
            firstName: "mockPrenom",
            lastName: "mockNom",
            password: "1234"
        };

        infoLoginMock = {
            email: "test@test.fr",
            password: "1234"
        };

        TestBed.configureTestingModule({
            providers: [
                AuthService,
                {provide: HttpClient, useValue: httpClientMock}
            ]
        });

        service = TestBed.inject(AuthService);
    });

    it("Verifie que qd register() est apelé c'est bien un une methode post qui est appelé du HttpClientMock", ()=>{
        service.register(infoRegisterMock);
        expect(httpClientMock.post).toHaveBeenCalled();

        service.login(infoLoginMock);
        expect(httpClientMock.post).toHaveBeenCalled();
        
    });
});