<template>
  <!-- Koriste v-on odreagovacemo na submit pozivom login -->
  <div style="text-align:center" >
    <h1>Login</h1>
    <!-- Ispis url parametra -->
    <!-- <h2>URL param: {{urlParamId}}</h2> -->
  <form v-on:submit="login">
    <!-- Pomocu v-model vezujemo vrednost polja za username u data sekciji -->
    <input
      type="text"
      v-model="username"
      placeholder="Enter username"
    >
    <p> 
    </p>
    <input
      type="password"
      v-model="password"
      placeholder="Enter password"
    >
     <p> 
    </p>
    <input type="submit" value="Login" >

  </form>


  </div>
</template>

<script>


import UserClient from "../clients/user-client.js"

export default {
  name: "Login",
  
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login(e) {
      // Sprecavamo default-no ponasanje forme
      e.preventDefault();

      // Prosledjujemo Login komponentu (this) radi redirekcije na glavnu stranicu
   
   
      UserClient.login(this.username, this.password, this);
    
      // Vracamo sve vrednosti na staro
      this.username = "",
      this.password = ""
        
      // Sprecavamo default-no ponasanje forme
      return false;
    }
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
h1 {
  padding: 60px;
  text-align: center;
  background: #1abc9c;
  color: white;
  font-size: 30px;
}

</style>