import Vue from 'vue'
import Router from 'vue-router'
// 登录
import Login from '@/components/login/Login'
// Actor管理员
import AdminIndex from '@/components/admin/AdminIndex'
import AdminPaking from '@/components/admin/AdminPaking'
import AdminUser from '@/components/admin/AdminUser'
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
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/AdminIndex',
      redirect: '/AdminIndex/AdminPaking',
      name: 'AdminIndex',
      component: AdminIndex,
      children: [{
        //停车场管理
          path: '/AdminIndex/AdminPaking',
          name: 'AdminPaking',
          component: AdminPaking
        },
        {
        //用户管理
          path: '/AdminIndex/AdminUser',
          name: 'AdminUser',
          component: AdminUser,
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
});