describe("MeComponenent", ()=>{

    it("test si la page account contient bien les infos", ()=>{
        
         cy.intercept("POST", "api/auth/login",
            {
                statusCode: 200,
                body:{
                    token: "abc123xyz456token",
                    type: "Bearer",
                    id: 2,
                    username: "johndoe",
                    firstName: "John",
                    lastName: "Doe",
                    admin: true
                }
            }
        ).as("Login");


        cy.intercept("GET", "api/session", {
            body: [ 
                {
                    id: 1,
                    name: "session1",
                    date: "2025-10-10",
                    description: "bla bla bla",
                },

                {
                    id: 2,
                    name: "session2",
                    date: "2025-11-12",
                    description: "bla bla bla2",
                }
            ]
        });


        cy.intercept("GET", "api/user/2", {
            body:{
                id: 1,
                email: "yoga.test@gmail.com",
                lastName: "Dupont",
                firstName: "Camille",
                admin: true,
                password: "mdpTest123!",
                createdAt: new Date("2025-01-15"),
                updatedAt: new Date("2025-10-10")
            }
        });
        
        cy.visit("/login")
        cy.get("input[formControlName=email]").type("yoga.test@gmail.com") 
        cy.get("input[formControlName=password]").type("mdpTest")
        
        cy.contains("Button", "Submit").click() // je suis bien connecté

        cy.contains("Span", "Account").click()

        cy.contains("P", "Name: Camille DUPONT").should("exist");


        
    })  

});