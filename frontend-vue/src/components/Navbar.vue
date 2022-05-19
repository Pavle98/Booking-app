<template>

  <nav class = "intro">

    <v-app-bar flat>
         <v-spacer></v-spacer>
      <v-toolbar-title class="text-uppercase grey--text">
        <span >  {{username}} </span>
        <span>{{type}} </span>
      </v-toolbar-title>
      
     
        <i class="material-icons" @click="goToReservations()" v-if="a">
        subject
</i>

        <button @click="logout()">Sign out</button>
      
    </v-app-bar>

  </nav>
</template>

<script>
export default {
  data() {
    return {
      type:  localStorage.getItem("type"),
      username:  localStorage.getItem("userName"),
      a : true
    }
  },
  methods:{
    goToReservations(){
        this.$router.push({name: 'resPage'})
    },
    logout(){
      localStorage.setItem("type", "");
            localStorage.setItem("userName","");
            localStorage.setItem('jwt', "");
       this.$router.push({name: 'login'})
    }
  },
  mounted(){
    if(localStorage.getItem("type")==='admin'){
      this.a = false;
    }else{
      this.a = true;
    }
    console.log(this.a);
  }
}
</script>
<style>
.intro {
  background-color: yellow;
  float: right;
}
</style>