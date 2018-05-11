import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/Login'
import AdminIndex from '@/components/admin/AdminIndex'
import AdminPaking from '@/components/admin/AdminPaking'
import AdminUser from '@/components/admin/AdminUser'
import AdminManger from '@/components/admin/AdminManger'
import AdminOperator from '@/components/admin/AdminOperator'

Vue.use(Router)

export default new Router({
  routes: [{
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/AdminIndex',
      redirect: '/AdminPaking',
      name: 'AdminIndex',
      component: AdminIndex,
      children: [{
          path: '/AdminPaking',
          name: 'AdminPaking',
          component: AdminPaking
        },
        {
          path: '/AdminUser',
          redirect: '/AdminManger',
          name: 'AdminUser',
          component: AdminUser,
          children: [{
              path: '/AdminManger',
              name: 'AdminManger',
              component: AdminManger
            },
            {
              path: '/AdminOperator',
              name: 'AdminOperator',
              component: AdminOperator
            }
          ]
        }
      ]
    },
  ]
})
