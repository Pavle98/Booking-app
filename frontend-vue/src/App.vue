<!-- Template je HTML parce koda koje ce biti ubaceno u DOM. 
Posto se ova komponenta renderuje u main.js, ovaj HTML kod ce zavrsiti umetnut u div sa id-jem app.
Template mora da ima samo jedan root HTML element! -->
<template>
  <!-- root element -->
  <div id="app">
    
    <!-- Sluzi da izrenderuje komponentu za datu putanju -->
    <router-view></router-view>
    
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {
  
  },
  
  data() {
    return {
      
    };
  },
  /* methods: Funkcije dostupne ovoj komponenti cak i u template-u. 
  Kao i u data sekciji ovim metodama mozemo pristupiti sa this.*/
  methods: {

  },
  /* created: ovde stavljamo kod koji ce se izvrsiti cim se stranica i komponente ucitaju
   */
  created() {
    if(window.localStorage.getItem('jwt') == undefined){
       //  this.$router.push({name: 'login'})
        // ako hocemo da redirektujemo sa nekim url parametrom, trebace nam za avionske kompanije
        // this.$router.push({name: 'login', params: { id: 123}})
      }
  }
}

</script>

<style>

</style>
