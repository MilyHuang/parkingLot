import Vue from 'vue'
import Router from 'vue-router'
// 登录
import Login from '@/components/login/Login'
import UserLogin from '@/components/login/UserLogin'
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
import SearchBill from '@/components/operator/SearchBill'
import SearchBillDetail from '@/components/operator/SearchBillDetail'
//User
import UserIndex from '@/components/user/UserIndex'
import UserInfo from '@/components/user/UserInfo'
import UserBill from '@/components/user/UserBill'
import UserBillDetail from '@/components/user/UserBillDetail'
import ParkingManage from '@/components/user/ParkingManage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/UserLogin',
      name: 'UserLogin',
      component: UserLogin
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
        //操作员查询用户停车卡
        {
          path: '/OperatorIndex/SearchCard',
          name: 'SearchCard',
          component: SearchCard
        },
        //操作员查询用户账单
        {
          path: '/OperatorIndex/SearchBill',
          name: 'SearchBill',
          component: SearchBill
        },
        //操作员查询用户账单详情
        {
          path: '/OperatorIndex/SearchBillDetail/:billNum',
          name: 'SearchBillDetail',
          component: SearchBillDetail
        }
      ]
    },
    {
      // 用户页面
      path: '/UserIndex',
      redirect: '/UserIndex/UserBill',
      name: 'UserIndex',
      component: UserIndex,
      children: [{
        //个人信息
          path: '/UserIndex/UserInfo',
          name: 'UserInfo',
          component: UserInfo
          
        },
        {
          //个人账单
          path: '/UserIndex/UserBill',
          name: 'UserBill',
          component: UserBill
        },
        {
          //账单详情
          path: '/UserIndex/UserBillDetail/:billNum',
          name: 'UserBillDetail',
          component: UserBillDetail
        },
        //停车管理
        {
          path: '/UserIndex/ParkingManage',
          name: 'ParkingManage',
          component: ParkingManage
        }
      ]
    }
  ]
});