import axios from 'axios'

const baseUrl = "http://localhost:8080/rest/users";

let TableClient = {

    fetchData(comp) {  
      axios.get(baseUrl + '/table', {
        
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
              },
          }).then((response) => {
            
            
            comp.tickets = JSON.parse(JSON.stringify(response.data));     
          //comp tickets dobija prave vrednosti
            console.log(comp.tickets);     
          
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
          
          
          comp.companies = JSON.parse(JSON.stringify(response.data));     
          
          
         // console.log(comp.companies);     
          return comp.companies
          
        }, (error) => {
          console.log("An error occured:");
          console.log(error);
        });
      }
}



export default TableClient
