import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/Login'
import AdminIndex from '@/components/admin/AdminIndex'
import AdminPaking from '@/components/admin/AdminPaking'
import AdminUser from '@/components/admin/AdminUser'
import AdminManger from '@/components/admin/AdminManger'
import AdminOperator from '@/components/admin/AdminOperator'
// Actor经理
import ManagerIndex from '@/components/manager/ManagerIndex'
import PriceManage from '@/components/manager/PriceManage'
import SellManage from '@/components/manager/SellManage'
//Actor操作员
import OperatorIndex from '@/components/operator/OperatorIndex'
import CreateCard from '@/components/operator/CreateCard'
import SearchCard from '@/components/operator/SearchCard'

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
    {
      // 经理管理页面
      path: '/ManagerIndex',
      redirect: '/ManagerIndex/PriceManage',
      name: 'ManagerIndex',
      component: ManagerIndex,
      children: [{
        //价格管理
          path: '/ManagerIndex/PriceManage',
          name: 'PriceManage',
          component: PriceManage
        },
        //销售管理
        {
          path: '/ManagerIndex/SellManage/:lotName',
          name: 'SellManage',
          component: SellManage
        },
      ]
    },
    {
      // 操作员管理页面
      path: '/OperatorIndex',
      redirect: '/OperatorIndex/CreateCard',
      name: 'OperatorIndex',
      component: OperatorIndex,
      children: [{
        //创建新卡
          path: '/OperatorIndex/CreateCard',
          name: 'CreateCard',
          component: CreateCard
        },
        //停车卡查询
        {
          path: '/OperatorIndex/SearchCard',
          name: 'SearchCard',
          component: SearchCard
        },
      ]
    },
  ]
})
