<template>
<div>
<h3>Users</h3>

<button id="load-users-btn" v-on:click="loadUsers()">Ucitaj korisnike</button>

<table id="users-tbl">
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
    </thead>
    <tbody>
        <!-- v-for sluzi da kreira HTML element <tr> za svaki element u nizu users-->
        <tr v-bind:key="user.id" v-for="user in users">
          <td>{{ user.username }}</td>
          <td>{{ user.password }}</td>
        </tr>
    </tbody>
</table>
</div>
</template>
<script>
import UserClient from '../clients/user-client.js'
export default {
    name: "UsersTable",
    methods: {

        // Za rucno ucitavanje korisnika
        loadUsers() {
            /* U props primamo korisnike od parent (Home) komponente, 
               Home bi trebao biti vlasnik korisnika, da bi UsersTable
               komponenta sluzila samo za prikaz podataka.
               Zbog toga prosledjujemo parent-a, da bismo direktno u 
               parent-u menjali listu korisnika. 
               Izmena liste users u parent-u poslace nove podatke
               svim child komponentama. */
               
       
        
            UserClient.loadUsers(this.$parent);
          
        }
    },  
    // Za automatsko ucitavanje korisnika
    created() {
        
    if(window.localStorage.getItem('jwt') != undefined){
        this.loadUsers()
      }
      
  },
/* props: To su promenljive koje ova komponenta moze da primi od strane drugih komponenti.
  Ovoj komponenti je prosledjena lista korisnika koristeci v-bind:users od strane Home.vue komponente. 
  Ove promenljive mozemo koristiti u template sekciji*/
  props: ['users']
}
</script>