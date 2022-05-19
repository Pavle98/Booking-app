<template>
  <div class="home">
    <label>JWT Token: {{jwt}} </label><br>
    
      <!-- v-bind child komponenti prosledjuje podatke iz parent komponente -->
      <UsersTable v-bind:users="this.users"/>
      <TicketTable v-bind:tickets="this.tickets"/>
      
      
  </div>
</template>
<script>
import UsersTable from '../components/UsersTable.vue'
import TicketTable from '../components/TicketTable.vue'


export default {
  name: 'home',
  components: {
    
    UsersTable,
    TicketTable,
    
    
  },
  // Sluzi za vrednosti koje se mogu menjati u toku rada aplikacije
  // Ukoliko se token promeni, computed metoda jwt ce odmah vratiti novu vrednost
  computed: {
    jwt(){
      return window.localStorage.getItem("jwt");
    }
  },
  /* data: Mora biti funkcija koja vraca objekat. 
  Taj objekat ce sadrzati podatke koje ce ova komponenta koristiti. 
  Svi podaci zaregistrovani u data sekciji mogu se pristupiti sa this npr. this.users */
  data() {
    return {
      users: [],
      tickets: [],
    
    }
  },
  
}
</script>