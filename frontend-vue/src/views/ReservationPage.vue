<template>
  <!-- Koriste v-on odreagovacemo na submit pozivom login -->

  <div>
 <Navbar/> 
 
 <h1> Reservations </h1>
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
          <th>expired </th>
                 <th>Outdated </th>
                <th>Delete</th>
        </tr>
    </thead>
    <tbody>
      
        <!-- v-for sluzi da kreira HTML element <tr> za svaki element u nizu users-->
        
        <tr v-bind:key="reservation.id" v-for="reservation in reservedTickets">
          <td>{{ reservation.id }}</td>
          <td>{{ reservation.ticket.company.companyName }}</td>
          <td>{{ reservation.ticket.oneWay }}</td>
          <td>{{reservation.ticket.departDate}}</td>
          <td>{{reservation.ticket.returnDate}}</td>
          <td>{{reservation.ticket.flightId}} </td>
          <td>{{reservation.ticket.count}}</td>
          <td>{{reservation.expired}}</td>
         <td>{{reservation.outDated}}</td>
          <td @click="reservationDelete(reservation.id)"><button id="reserve-btn" >Delete</button>     </td>
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
        reservedTickets:[],
    };
  },
  methods: {
 
 
    reservationDelete(reservationId) {
            console.log(reservationId);
            UserClient.reservationDelete(reservationId,this);

  
  },

  },
    created: function(){
      console.log("this.reservedTickets");
        UserClient.getUsersReservations(this, localStorage.getItem("userName"));
        console.log(this.reservedTickets);
      console.log('created: vsad');
        
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