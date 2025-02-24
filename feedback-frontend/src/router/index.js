import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Layout,
    children: [
      {
        path: '',
        name: 'home',
        component: () => import('../views/HomeView.vue')
      },
      {
        path: '/course',
        name: 'course',
        component: () => import('../views/CourseList.vue')
      },
      {
        path: '/teacher',
        name: 'teacher',
        component: () => import('../views/TeacherList.vue')
      },
      {
        path: '/student',
        name: 'student',
        component: () => import('../views/StudentList.vue')
      },
      {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/AdminList.vue')
      },
      {
        path: '/index',
        name: 'index',
        component: () => import('../views/IndexList.vue')
      },
      {
        path: '/teacherCourse',
        name: 'teacherCourse',
        component: () => import('../views/TeacherCourseList.vue')
      },
      {
        path: '/studentCourse',
        name: 'studentCourse',
        component: () => import('../views/StudentCourseList.vue')
      },
      {
        path: '/myCourse',
        name: 'myCourse',
        component: () => import('../views/MyCourseList.vue')
      },
      {
        path: '/myTeachingCourse',
        name: 'myTeachingCourse',
        component: () => import('../views/MyTeachingCourseList.vue')
      },
      {
        path: '/studentIndex',
        name: 'studentIndex',
        component: () => import('../views/StudentIndexList.vue')
      },{
        path: '/notice',
        name: 'notice',
        component: () => import('../views/NoticeList.vue')
      },
    ]
  }, {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
