import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import Grid from 'gridjs-vue'
import VueSimpleAlert from "vue-simple-alert";


import 'material-design-icons-iconfont/dist/material-design-icons.css'
Vue.config.productionTip = false
Vue.use(Grid)
Vue.use(VueSimpleAlert);
/**
 * Ovde kreiramo root Vue instancu. Ona nam predstavlja sponu
 * izmedju html-a (public/index.html) i root Vue komponente (App.vue).
 * 
 * Vue instanci prosledjujemo konfiguracioni objekat.
 * Moguce konfiguracije mozete naci na https://vuejs.org/v2/api/#Options-Data
 * 
 * Prosledili smo komponentu koju zelimo da renderujemo nakon sto smo je importovali na liniji 2. 
 * Komponenta ce biti renderovana na mestu HTML elementa sa id-jem id='app'.
 */ 
new Vue({
  router, // router u drugim komponentama dohvatamo pomocu this.$router
  render: h => h(App),
}).$mount('#app')
