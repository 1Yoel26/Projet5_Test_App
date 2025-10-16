describe("Register", ()=>{
    it("test si le register fonctionne", ()=>{
        cy.visit('/register')

        cy.intercept("POST", "/api/auth/register", {
            body:{
                id: 1,
                email: "test@test.fr",
                firstName: "FirstName",
                lastName: "LastName",
                admin: true
            }
         })

         cy.get('input[formControlName=email]').type("yoga@studio.com")
         cy.get('input[formControlName=firstName]').type("testFirstName")
         cy.get('input[formControlName=lastName]').type("testLastName")
         cy.get('input[formControlName=password]').type(`${"testMdp"}{enter}{enter}`)

         cy.url().should('include', '/login')
    })


    it("test si le register ne redirige pas en cas d'erreur", ()=>{
        cy.visit('/register')

        cy.intercept("POST", "/api/auth/register", {
            statusCode: 400,
            body: {
                error: 'Registration failed'
            }
         })

         cy.get('input[formControlName=email]').type("yoga@studio.com")
         cy.get('input[formControlName=firstName]').type("testFirstName")
         cy.get('input[formControlName=lastName]').type("testLastName")
         cy.get('input[formControlName=password]').type(`${"testMdp"}{enter}{enter}`)

         cy.get('.error').should('be.visible');
         
         cy.url().should('include', '/register')
    })
    
});