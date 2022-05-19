<template>
<div>
<h3>Tickets</h3>

<button id="fetch-tickets-btn" v-on:click="fetchData()">Ucitaj tabelu</button>

 <div>
<table id="tickets-tbl">
    <thead>
        <tr>
          <th>ID</th>
          <th>Company</th>
            <th>One-way</th>   
            
                 <th>Depart</th>
                <th>Return</th>
                <th>FlightID</th>
                <th>Count</th>
                <th>reserve</th>
        </tr>
    </thead>
    <tbody>
      
        <!-- v-for sluzi da kreira HTML element <tr> za svaki element u nizu users-->
        
        <tr v-bind:key="ticket.ticketId" v-for="ticket in realTickets">
      
          <td>{{ ticket.ticketId }} </td>
          <td @click="goToAirCompany(ticket.company)">{{ ticket.company.companyName }}</td> 
          <td>{{ ticket.oneWay }}</td>          
          <td>{{ticket.departDate}}</td>
          <td>{{ticket.returnDate}}</td>
          <td>{{ticket.flightId}} </td>
          <td>{{ticket.count}}</td>
           <td @click="reserveTicket(ticket.ticketId)"><button id="reserve-btn" >Reserve</button>     </td>
          <td @click="ticketDelete(ticket.ticketId)"><button id="delete-btn" >Delete</button>     </td>
           <td @click ="goToChangeTicket(ticket.ticketId)"><button id="change-btn" >Change</button>     </td>
         <!-- <button id="fetch-tickets-btn" v-on:click="fetchData()">Ucitaj tabelu</button> -->
        </tr>
    </tbody>
</table>
<button id="previous-btn" v-on:click="pagination(2)" >Previous</button> 
<button id="next-btn" v-on:click="pagination(1)" >Next</button> 
  </div>
</div>
</template>
<script>
import TableClient from '../clients/table-client.js'
import UserClient from "../clients/user-client.js"
export default {
    name: "UsersTable",
     components: {
    
  },
  data() {
    return {
      STdepartureCity:"",
          STdestinationCity: "",
          STdepartDate: "",
          STreturnDate: "",
          SToneWay: "",
          STtwoWay: "",
          searchedTickets: [],
          realTickets: [],
          companyTickets: [],
          
    };
  },
    methods: {
      
      fetchData(){
        console.log('eiiioo');
        TableClient.fetchData(this.$parent);
        console.log(this.tickets);
        console.log('eiiioo2');
        UserClient.pagination(this, 1, this.searchedTickets);
      },
      
      goToChangeTicket(ticketId){
        localStorage.setItem("clickedTicket", ticketId);
        this.$router.push('/ticketChange')
    },

    goToAirCompany(company) {
       console.log("company")
      console.log(company.id)
     
      localStorage.setItem("CompanyName", company.companyName);
      localStorage.setItem("clickedCompany", company.id);
      console.log(localStorage.getItem("clickedCompany"));
      this.$router.push('/aircmpny');
    
  },

    reserveTicket(ticketId) {
      console.log(localStorage.getItem("userName"));
      UserClient.reserveTicket(this,localStorage.getItem("userName"),ticketId);
      
    
  },


  ticketDelete(ticketId) {
      console.log(ticketId);
      UserClient.ticketDelete(ticketId,this);
      
  },

    searchTickets(){
      
      UserClient.searchTickets(this.STdepartureCity,this.STdestinationCity,this.STdepartDate
      ,this.STreturnDate,this.SToneWay,this.STtwoWay,this);
      console.log(this.searchedTickets);
     
     
    },

    pagination(foward){
      
      console.log("rorl");
      console.log(foward);
      UserClient.pagination(this, foward, this.searchedTickets);
      
      
    },


  },
    

    
    // Za automatsko ucitavanje korisnika
   
/* props: To su promenljive koje ova komponenta moze da primi od strane drugih komponenti.
  Ovoj komponenti je prosledjena lista korisnika koristeci v-bind:users od strane Home.vue komponente. 
  Ove promenljive mozemo koristiti u template sekciji*/

};
</script>