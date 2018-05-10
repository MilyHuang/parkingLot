import Vue from 'vue'
import Router from 'vue-router'
import AdminIndex from '@/components/admin/AdminIndex'
import AdminPaking from '@/components/admin/AdminPaking'
import AdminUser from '@/components/admin/AdminUser'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    name: 'AdminIndex',
    component: AdminIndex,
    children: [{
        path: '/AdminPaking',
        name: 'AdminPaking',
        component: AdminPaking
      },
      {
        path: '/AdminUser',
        name: 'AdminUser',
        component: AdminUser
      }
    ]
  }]
})
