describe("session test", ()=>{
    it("test pour la creation d'une session", ()=>{
        cy.visit("/login")

        cy.intercept("POST", "api/auth/login", {
            body:{
                id: 1,
                email: "test@test.fr",
                firstName: "FirstName",
                lastName: "LastName",
                admin: true
            }
        })

        cy.intercept("GET", "api/session", {
            body:{

            }
        }).as("session")

        cy.intercept("GET", "api/teacher", {
           body: 
           [
            {
            
            id: 1,
            lastName: "LastName1",
            firstName: "FirstName1",
            createdAt: "2025-10-14",
            updatedAt: "2025-12-14" 
           },
            {
            
            id: 1,
            lastName: "LastName2",
            firstName: "FirstName2",
            createdAt: "2025-10-14",
            updatedAt: "2025-12-14" 
           } 
        ]
        }).as("teacher")

    
    cy.intercept("POST", "api/session", {
        statusCode: 201,
        body:{
                name: "string",
                date: "2025-12-14",
                teacher_id: 1,
                description: "string"
            }
        }).as("session")

        cy.get('input[formControlName=email]').type("yoga@studio.com")
        cy.get('input[formControlName=password]').type(`${"testMdp"}{enter}{enter}`)

        cy.url().should('include', '/session')

        cy.contains('button', 'Create').click();

        cy.get("input[formControlName=name]").type("name")
        cy.get("input[formControlName=date]").type("2024-10-25")
        cy.get("mat-select[formControlName=teacher_id]").click()

        cy.get('mat-option').contains('FirstName1 LastName1').click();


        cy.get("textarea[formControlName=description]").type("description")

        cy.contains("button", "Save").click()

        cy.url().should("include", "/session")

    });



     it("test pour la modification d'une session", ()=>{
        cy.visit("/login")

        cy.intercept("POST", "api/auth/login", {
            body:{
                id: 1,
                email: "test@test.fr",
                firstName: "FirstName",
                lastName: "LastName",
                admin: true
            }
        })

        cy.intercept("GET", "api/session", {
            body:[
                {
                    id: 1,
                    name: "session1",
                    date: "2025-10-10",
                    description: "bla bla bla",
            },

               {
                    id: 2,
                    name: "session2",
                    date: "2024-10-10",
                    description: "bla bla bla2",
            }
        ]
        }).as("session")

        cy.intercept("GET", "api/teacher", {
           body: 
           [
            {
            
            id: 1,
            lastName: "LastName1",
            firstName: "FirstName1",
            createdAt: "2025-10-14",
            updatedAt: "2025-12-14" 
           },
            {
            
            id: 1,
            lastName: "LastName2",
            firstName: "FirstName2",
            createdAt: "2025-10-14",
            updatedAt: "2025-12-14" 
           } 
        ]
        }).as("teacher")


    cy.intercept("POST", "api/session", {
        statusCode: 201,
        body:{
                name: "string",
                date: "2025-12-14",
                teacher_id: 1,
                description: "string"
            }
        }).as("session")

        cy.intercept("GET", "api/session/1", {
            body: {
                id: 1,
                name: 'Cours de yoga du matin',
                description: 'Session douce et relaxante',
                date: '2025-12-14',
                teacher_id: 1,
                users: [1, 2],
                createdAt: '2025-10-09',
                updatedAt: '2025-10-09'
            }
        }).as("session")

        cy.get('input[formControlName=email]').type("yoga@studio.com")
        cy.get('input[formControlName=password]').type(`${"testMdp"}{enter}{enter}`)

        cy.url().should('include', '/session')

        
        cy.contains('button', 'Edit').click();

        cy.get("input[formControlName=name]").should("have.value", "Cours de yoga du matin");


    });



    it("test pour suppression d'une session", ()=>{
        cy.visit("/login")

        cy.intercept("POST", "api/auth/login", {
            statusCode: 200,
            body:{
                token: "mocked-jwt-token-12345",
                type: "Bearer",
                id: 1,
                username: "adminUser",
                firstName: "John",
                lastName: "Doe",
                admin: true
            }
        }).as("SessionInformation")

        
        // les sessions:
        cy.intercept("GET", "api/session", {
            body:[
                {
                    id: 1,
                    name: "session1",
                    date: "2025-10-10",
                    description: "bla bla bla",
            },

               {
                    id: 2,
                    name: "session2",
                    date: "2024-10-10",
                    description: "bla bla bla2",
            }
        ]
        }).as("session")

        // le detail d'une session:
        cy.intercept("GET", "api/session/1", {
            statusCode: 200,
            body: {
                id: 1,
                name: 'Cours de yoga du matin',
                description: 'Session douce et relaxante',
                date: '2025-12-14',
                teacher_id: 1,
                users: [1, 2],
                createdAt: '2025-10-09',
                updatedAt: '2025-10-09'
            }
        }).as("sessionDetail")


        // suppression
        // le detail d'une session:
        cy.intercept("DELETE", "api/session/1", {
            statusCode: 200,
            body: {}
        }).as("deleteSession")

        cy.get('input[formControlName=email]').type("yoga@studio.com")
        cy.get('input[formControlName=password]').type(`${"testMdp"}{enter}{enter}`)
        
        cy.contains("Button", "Detail").click()

        cy.contains("Button", "Delete").click()

        cy.url().should('include', '/sessions')

    });


});

