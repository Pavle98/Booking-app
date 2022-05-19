<template>
  <!-- Koriste v-on odreagovacemo na submit pozivom login -->

  <div>
 <Navbar/> 




<form v-on:submit="changeTicket">
<h1>Change ticket</h1>
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
    
    <input type="submit" value="changeTicket">
</div>
<button id="GoB" v-on:click="GoB()">Ponisti</button>
 </form>
  </div>
</template>

<script>
import UserClient from '../clients/user-client';
import Navbar from '@/components/Navbar' ;
export default {
  components: {
    Navbar,
  },
  data() {
    return {  
           company : "",
          oneWay : "",
          departDate : "",
          returnDate : "",
          flightId : "",
          count : "",
          ticketId : localStorage.getItem("clickedTicket"),
          flights: [],
          companies: [],
    };
  },
  methods: { 
       GoB(){
         this.$router.push({name : "admin"})
        
    },
    changeTicket(){
        console.log("dabadee");
        UserClient.changeTicket(this.company,this.oneWay,this.departDate,this.returnDate,this.flightId,this.count, this.ticketId,this);
        
    },
        onChange(event) {
              this.company = event.target.value;
          },
           onChangeTwo(event) {
              this.flightId = event.target.value;
          },
            fetchCompanies(e) {
     e.preventDefault;
    UserClient.fetchCompanies(this);
     
  },
  fetchFlights(e){
    e.preventDefault();
    UserClient.fetchFlights(this);
  },
},
  created: function(){
    UserClient.fetchCompanies(this);
    UserClient.fetchFlights(this);
  },
  

}
</script>
