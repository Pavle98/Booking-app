<template>
  <!-- Koriste v-on odreagovacemo na submit pozivom login -->

  <div>
 <!-- <Navbar/> -->
 <Navbar/>

  <form v-on:submit="MakeUser">
    <!-- Pomocu v-model vezujemo vrednost polja za username u data sekciji -->
    <h1>Create new user</h1>
    <input
      type="text"
      v-model="username"
      placeholder="Username "
    >
    <div>
    <input
      type="password"
      v-model="password"
      placeholder="Enter password"
    >
    </div>

    
      <div>
      <input
      type="text"
      v-model="type"
      placeholder="Type of User"
    ></div>
     
      <div>
    
    <input type="submit" value="MakeUser">
     </div>
  </form>


<form v-on:submit="MakeTicket">
<h1>Create new Ticket</h1>
<!-- https://vuejs.org/v2/guide/forms.html -->
<select v-model="selected" id = "selectedCompany" @change="onChange($event)">
  <option disabled value="Companies">Companies</option>
  <option v-bind:key="company.id" v-for="company in companies">
   
     {{company.companyName}} 
  </option>
</select>

    <div>
    <input
      type="checkbox"
      v-model="oneWay"
      id="checkbox">
<label for="checkbox"></label>
      One way
    
    </div>
    <div>

  <input 
  type="date"
   v-model="departDate"
   >
 departure date
 
    </div>
    
      <div>
    
    
  <input 
  type="date"
   v-model="returnDate"
   >
 return date
    </div>
         <div>
      <select v-model="selected" @change="onChangeTwo($event)">
       
  <option disabled value="Flights">Flights</option>
  <option v-bind:key="flight.id" v-for="flight in flights">
   
     {{flight.flightId}}
  </option>
</select>
    
    </div>
    <div>
       <input
      type="text"
      placeholder="Count"
      v-model="count"
    >
    </div>
    <div>
    
    <input type="submit" value="MakeTicket">
</div>

 </form>
 <div>
<TicketTable/>
 </div>
  </div>
</template>

<script>



import Navbar from '@/components/Navbar'
import UserClient from "../clients/user-client.js"
import TicketTable from "@/components/TicketTable"

export default {
  name: "MakeUser", 
   components: {
    TicketTable,
    Navbar,
  },
  data() {
    return {
            
      username: "",
      password: "",
      type: "",
            
      company:"",
      oneWay: "",
      departDate: "",
      returnDate: "",
      flight:"",
      count: "",

      companies:[],
      flights: [],
    };
  },
  
  methods: { 
    onChange(event) {
              this.company = event.target.value;
          },
           onChangeTwo(event) {
              this.flight = event.target.value;
          },
    
  fetchCompanies(e) {
      e.preventDefault();
    console.log('companiesss');
    UserClient.fetchCompanies(this);
     console.log(this.companies);
      console.log('companiesss');
  },
  fetchFlights(e){
    e.preventDefault();
    UserClient.fetchFlights(this);
  },

    MakeUser(e) {
      // Sprecavamo default-no ponasanje forme
      e.preventDefault();

      // Prosledjujemo Login komponentu (this) radi redirekcije na glavnu stranicu
      UserClient.MakeUser(this.username, this.password, this.type,this);

      console.log('this.type');
      console.log(this.type);
  
      return false;
    },
     MakeTicket(e) {
     
      e.preventDefault();
      
      
      
     
      UserClient.MakeTicket(this.company, this.oneWay, this.departDate, this.returnDate,this.flight,this.count,this);
  
      
      return false;
    },
  },
  created: function(){
    UserClient.fetchCompanies(this);
    UserClient.fetchFlights(this);
  },
  
  
};


</script>

