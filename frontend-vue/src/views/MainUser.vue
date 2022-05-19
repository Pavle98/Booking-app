<template>
  <!-- Koriste v-on odreagovacemo na submit pozivom login -->

  <div>
  <Navbar/>

  <form v-on:submit="searchTickets">
    <!-- Pomocu v-model vezujemo vrednost polja za username u data sekciji -->
    <input
      type="text"
      placeholder="Departure point"
      v-model="STdepartureCity"
    >
    <div>
    <input
      type="text"
      placeholder="Destination point"
        v-model="STdestinationCity"
    >
    </div>
      <div>

  <input type="date" v-model="STdepartDate">
 departure date
    </div>
      <div>

  <input type="date" v-model="STreturnDate">
 return date
    </div>
    <div>
    <input
      type="checkbox"
      
      id="checkbox" v-model="SToneWay">
<label for="checkbox">{{ checked }}</label>
      One way
    
    </div>
    <div>
    <input
      type="checkbox"
      
      id="checkbox" v-model="STtwoWay">
<label for="checkbox">{{ checked }}</label>
      Both ways
    
    </div>
      <div>
    
    <input type="submit" value="Search">
</div>
    <p>Message is: {{ username }}</p>
  </form>
  <div>

  </div>
 
     <TicketTable/>

  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import UserClient from "../clients/user-client.js"
import TicketTable from "@/components/TicketTable"
export default {
  // name: "SearchTickets",
  components: {
    TicketTable,
    Navbar
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
      
          
    };
  },
  methods: { 
    goToChangeTicket(ticketId){
        localStorage.setItem("clickedTicket", ticketId);
        this.$router.push('/ticketChange')
    },
    goToAirCompany(company) {
      console.log(company.id)
      localStorage.setItem("clickedCompany", company.id);
      console.log(localStorage.getItem("clickedCompany"));
      this.$router.push('/aircmpny');
    
  },
    reserveTicket(ticketId) {
      console.log(localStorage.getItem("userName"));
      UserClient.reserveTicket(this,localStorage.getItem("userName"),ticketId);
      
      this.$router.push('/aircmpny');
    
  },

  ticketDelete(ticketId) {
      console.log(ticketId);
      UserClient.ticketDelete(ticketId);
    
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