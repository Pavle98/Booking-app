import axios from 'axios'

const baseUrl = "http://localhost:8080/rest/users";

let UserClient = {
    loadUsers(comp) {  
      axios.get(baseUrl, {
          headers: {
              "Accept": "application/json",
              "Content-Type": "application/json",
              "Authorization": "Bearer " + localStorage.jwt
            },
        }).then((response) => {
         
          // Pregazimo korisnike u Home komponenti
          console.log('nani');
          console.log(response.data);
          comp.users = JSON.parse(JSON.stringify(response.data));          
        }, (error) => {
          console.log("An error occured:");
          console.log(error);
        });
    },

    login(username, password, comp) {  
      axios.get(baseUrl + '/login', {
            params: {
             
              username: username,
              password: password,
              
            },
            
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
              },
          }).then((response) => {
            // Podaci sa servera
            
            console.log(JSON.parse(JSON.stringify(response.data)));
            // Status code
            if(JSON.parse(JSON.stringify(response.data)) === 'You cant leave fields blank!'){
              comp.$alert("Please check your credentials, something went wrong");
            }
            console.log(JSON.parse(JSON.stringify(response.status)));
            localStorage.setItem("type", response.data.type);
            localStorage.setItem("userName", response.data.username);
            localStorage.setItem('jwt', JSON.parse(JSON.stringify(response.data.JWTToken)))
            if(localStorage.getItem("type") === "admin"){
              comp.$router.push({name: 'admin'}); //promeni u admin
            }else{
              comp.$router.push({name: 'mainUser'}); //promeni u mainUser
            }
            
          }, (error) => {
            console.log("An error occured:");
            console.log(error);
          });
      },

    MakeUser(username, password, type,comp){
      axios.get(baseUrl + '/admin', {
        params: {
          username: username,
          password: password,
          type : type
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
      }).then((response) => {
        // Podaci sa servera
        if(JSON.parse(JSON.stringify(response.data)) === 'You cant leave any of the fields blank!'){
          comp.$alert("Please fill all fields, something went wrong");
        }
        if(JSON.parse(JSON.stringify(response.data)) === 'Username is already taken'){
          comp.$alert("Username is already taken");
        }
        console.log(JSON.parse(JSON.stringify(response.data)));
        // Status code
        console.log(JSON.parse(JSON.stringify(response.status)));
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },

    MakeTicket(company, oneWay,departDate,returnDate,flight,count,comp){
      axios.get(baseUrl + '/adminTickt', {
        params: {
          company: company,
          oneWay: oneWay,
          departDate: departDate,
          returnDate: returnDate,
          flight: flight,
          count: count,

        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
      }).then((response) => {
        // Podaci sa servera
        if(JSON.parse(JSON.stringify(response.data)) === 'Return date cant be before depart date!'){
          comp.$alert("Mistake! Return date is before depart date");
        }
        if(JSON.parse(JSON.stringify(response.data)) === 'Fields cant be blank!'){
          comp.$alert("Mistake! Dont leave blank fields");
        }
        console.log(JSON.parse(JSON.stringify(response.data)));
        // Status code
        console.log(JSON.parse(JSON.stringify(response.status)));
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });

    },
    fetchCompanies(comp){
      axios.get(baseUrl + '/adminCompanies', {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
            
          },
      }).then((response) => {
        
        console.log("esss");
        console.log(JSON.parse(JSON.stringify(response.data)));  
       comp.companies = JSON.parse(JSON.stringify(response.data));     
       console.log(JSON.parse(JSON.stringify(response.data)));     
       
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
    
    fetchFlights(comp){
      axios.get(baseUrl + '/adminFlights', {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
          },
      }).then((response) => {
        
        
        console.log(JSON.parse(JSON.stringify(response.data)));  
       comp.flights = JSON.parse(JSON.stringify(response.data));     
       console.log(JSON.parse(JSON.stringify(response.data)));     
       
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
    getCompanyTickets(comp, companyId){
     
      axios.get(baseUrl + '/cmpnyTickets', {
        params:{
          companyId : companyId,
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
          },
      }).then((response) => {
        console.log("oijsa");
        console.log(JSON.parse(JSON.stringify(response.data)));   
       comp.companyTickets = JSON.parse(JSON.stringify(response.data))
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });

    },
    reserveTicket(comp, username, ticketId){
     
      axios.get(baseUrl + '/tktReserve', {
        params:{
          username : username,
          ticketId : ticketId,
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
      }).then((response) => {

        if(JSON.parse(JSON.stringify(response.data)) === 'you are not user, you cant reserve a ticket!'){
          comp.$alert("you are not user, you cant reserve a ticket!");
        }
        console.log("oijsa");
        console.log(JSON.parse(JSON.stringify(response.data)));   
       comp.companyTickets = JSON.parse(JSON.stringify(response.data))
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });

    },
    getUsersReservations(comp, username){
     
      axios.get(baseUrl + '/userres', {
        params:{
          username : username,
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
      }).then((response) => {
        console.log("userreservations");
        console.log(JSON.parse(JSON.stringify(response.data)));   
       comp.reservedTickets = JSON.parse(JSON.stringify(response.data))
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });

    },
    

    getTicketData(comp, ticketId){
      axios.get(baseUrl + '/cmpnyTickets', {
        params:{
          ticketId : ticketId,
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
          },
      }).then((response) => {
        console.log("oijsa");
        console.log(JSON.parse(JSON.stringify(response.data)));   
       comp.ticket = JSON.parse(JSON.stringify(response.data))
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
    pagination(comp, nextOrPrevious){
      axios.get(baseUrl + '/pagination', {
        params:{
          nextOrPrevious : nextOrPrevious,

        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
          },
         
      }).then((response) => {
        
        console.log(JSON.parse(JSON.stringify(response.data)));   
       comp.realTickets = JSON.parse(JSON.stringify(response.data))
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
    ticketDelete(ticketId,comp){
      axios.get(baseUrl + '/tktDelete', {
        params:{
          ticketId : ticketId,

        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
         
      }).then((response) => {
        
        console.log(JSON.parse(JSON.stringify(response.data)));   
        if(JSON.parse(JSON.stringify(response.data)) ==="You cant delete ticket because you are not admin!"){
          comp.$alert("you are not admin, you cant delete a ticket!");
        }
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
    renameCompany(company, newName){
      axios.get(baseUrl + '/rnmCompany', {
        params:{
          company : company,
          newName: newName,
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
         
      }).then((response) => {
        
        console.log(JSON.parse(JSON.stringify(response.data)));   
      
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
    createCompany(newCompanyName){
      axios.get(baseUrl + '/cr8cmp', {
        params:{
          newCompanyName: newCompanyName,
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
         
      }).then((response) => {
        
        console.log(JSON.parse(JSON.stringify(response.data)));   
      
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
    deleteCompany(deletedCompanyName){
      axios.get(baseUrl + '/dltComp', {
        params:{
          deletedCompanyName: deletedCompanyName,
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
         
      }).then((response) => {
        
        console.log(JSON.parse(JSON.stringify(response.data)));   
      
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },


    changeTicket(company,oneWay,departDate,returnDate,flightId,count,ticketId, comp){
      axios.get(baseUrl + '/tktChange', {
        params:{
          company : company,
          oneWay : oneWay,
          departDate : departDate,
          returnDate : returnDate,
          flightId : flightId,
          count : count,
          ticketId : ticketId,

        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
         
      }).then((response) => {
        if(JSON.parse(JSON.stringify(response.data)) ==="you are not admin, you cant change tickets!"){
          comp.$alert("you are not admin, you cant change tickets!");
        }
        if(JSON.parse(JSON.stringify(response.data)) ==="Fields cant be blank!"){
          comp.$alert("Fields cant be blank!");
        }
        if(JSON.parse(JSON.stringify(response.data)) ==="Return date cant be before depart date!"){
          comp.$alert("Return date cant be before depart date!");
        }
        console.log(JSON.parse(JSON.stringify(response.data)));   
      
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },  


      reservationDelete(reservationTicketsId, comp){
      axios.get(baseUrl + '/rsvDelete', {
        params:{
          reservationTicketsId: reservationTicketsId,
        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
         
      }).then((response) => {
       
        if(JSON.parse(JSON.stringify(response.data)) ==="you cant delete it now"){
          comp.$alert("the last date for deleting it has passed");
        }
        console.log(JSON.parse(JSON.stringify(response.data)));   
      
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
    

    searchTickets(STdepartureCity, STdestinationCity
      ,STdepartDate,STreturnDate,SToneWay, STtwoWay,comp){
      axios.get(baseUrl + '/srchTkt', {
        params: {
          STdepartureCity: STdepartureCity,
          STdestinationCity: STdestinationCity,
          STdepartDate: STdepartDate,
          STreturnDate: STreturnDate,
          SToneWay: SToneWay,
          STtwoWay: STtwoWay,

        },
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.jwt
          },
      }).then((response) => {
        
        console.log(JSON.parse(JSON.stringify(response.data)));    
       console.log(JSON.parse(JSON.stringify(response.data)));     
       comp.searchedTickets = JSON.parse(JSON.stringify(response.data));
       return JSON.parse(JSON.stringify(response.data));
        
      }, (error) => {
        console.log("An error occured:");
        console.log(error);
      });
    },
}


export default UserClient
