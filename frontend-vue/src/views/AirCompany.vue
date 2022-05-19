<template>
  <!-- Koriste v-on odreagovacemo na submit pozivom login -->

  <div>
  <Navbar/>

 <h1>  {{cName}}</h1>

 <button v-on:click =deleteCompany() id="Delete ">Delete Company</button>

<h2> </h2>


 <form v-on:submit="renameCompany">
<input
      type="text"
      placeholder="Rename Company"
      v-model="newName"
    >
      <div>
    
    <input type="submit" value="Rename company">
     </div>


   </form>

   <form v-on:submit="createCompany">
<input
      type="text"
      placeholder="new Company Name"
      v-model="newCompanyName"
    >
      <div>
    
    <input type="submit" value="Make Company">
     </div>


   </form>
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
        
        <tr v-bind:key="ticket.ticketId" v-for="ticket in companyTickets">
          <td>{{ ticket.ticketId }}</td>
          <td>{{ ticket.company.companyName }}</td>
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
  </div>
 
     

  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import UserClient from "../clients/user-client.js"

export default {
  name: "companyTickets",
  components: {
    Navbar
  },
  data() {
    return {
       cName:  localStorage.getItem("CompanyName"),
        companyTickets:[],
        newName: "",
        newCompanyName: "",
        deletedCompanyName: "",
    };
  },
  methods: {
 
     getCompanyTickets() {
      
    
    UserClient.getCompanyTickets(this, localStorage.getItem("clickedCompany"));
     console.log(this.companyTickets);
  },
   renameCompany() {
    
    UserClient.renameCompany(localStorage.getItem("clickedCompany"), this.newName);
   
  },

  createCompany() {
  
    
    UserClient.createCompany(this.newCompanyName);
  
  },

  deleteCompany() {
   
    
    UserClient.deleteCompany(localStorage.getItem("clickedCompany"));
  
  },
      reserveTicket(ticketId) {
      console.log(localStorage.getItem("userName"));
      UserClient.reserveTicket(this,localStorage.getItem("userName"),ticketId);
      
    
  },
   ticketDelete(ticketId) {
      console.log(ticketId);
      UserClient.ticketDelete(ticketId,this);
      
  },
  goToChangeTicket(ticketId){
        localStorage.setItem("clickedTicket", ticketId);
        this.$router.push('/ticketChange')
    },

  },
    created: function(){
      console.log('created: vsad');
        UserClient.getCompanyTickets(this,localStorage.getItem("clickedCompany") );
        console.log(this.companyTickets);
  }
  // Ovako mozemo pokupiti url parametar.
  // computed: {
  //   urlParamId(){
  //     return this.$route.params.id;
  //   }
  // }
};
</script>

<style scoped>
body {
  text-align: center;  
}
</style>