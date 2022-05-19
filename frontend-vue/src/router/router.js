import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import Login from '../views/Login'
import MainUser from '../views/MainUser'
import admin from '../views/MainAdmin'
import TicketTable from '../components/TicketTable'
import AirCompany from '../views/AirCompany'
import ChangeTicket from '../views/ChangeTicket'
import ReservationPage from '../views/ReservationPage'
//import gridTest from '../components/gridTest'
Vue.use(VueRouter);

let router = new VueRouter({
  
    routes: [
      {
        path: '/login',// ako hocemo da dodamo url parametar: /login/:id
        name: 'login',
        component: Login,
        meta: { 
          requiresAuth: false
        }
      },
      {
        path: '/changeTicket',// ako hocemo da dodamo url parametar: /login/:id
        name: 'changeTkt',
        component: ChangeTicket,
        meta: { 
          requiresAuth: false
        }
      },
      {
        path: '/ticketChange',// ako hocemo da dodamo url parametar: /login/:id
        name: 'ticketChange',
        component: ChangeTicket,
        meta: { 
          requiresAuth: false
        }
      },
      {
        path: '/aircmpny',// ako hocemo da dodamo url parametar: /login/:id
        name: 'aircmpny',
        component: AirCompany,
        meta: { 
          requiresAuth: false
        }
      },
      {
        path: '/reservations',// ako hocemo da dodamo url parametar: /login/:id
        name: 'resPage',
        component: ReservationPage,
        meta: { 
          requiresAuth: false
        }
      },
      {
        path: '/admin',// ako hocemo da dodamo url parametar: /login/:id
        name: 'admin',
        component: admin,
        meta: { 
          requiresAuth: false
        }
      },
      {
        path: '/tt',// ako hocemo da dodamo url parametar: /login/:id
        name: 'tt',
        component: TicketTable,
        meta: { 
          requiresAuth: false
        }
      },
      {
        path: '/user',// ako hocemo da dodamo url parametar: /login/:id
        name: 'mainUser',
        component: MainUser,
        meta: { 
          requiresAuth: false
        }
      },
      
      {
        path: '/',
        name: 'home',
        component: Home,
        meta: { 
          requiresAuth: true
        },
        
        
      }]
  })
  
  export default router