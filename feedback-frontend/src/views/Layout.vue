<template>
  <div>
    <!--头部-->
    <el-container>
      <el-header style="background-color:#669d84;display: flex; align-items: center; justify-content: space-between;">
        <div style="display: flex; align-items: center;">
          <img src="@/assets/book.png" style="width: 30px;">
          <span style="font-size: 20px; margin-left: 10px; color: #ffffff;">评教管理系统</span>
        </div>
        <el-dropdown :hide-on-click="false" style="margin-right: 20px" @command="handleCommand" >
          <span class="el-dropdown-link">
            {{loginUser.name}}
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
    </el-container>
    <!--侧边-->
    <el-container>
      <!--侧边-->
      <el-aside width="230px" style="background-color: #85b6a2; overflow: hidden; min-height: 93.5vh">
        <el-menu default-active="$route.path" router background-color="#85b6a2" text-color="#ffffff" active-text-color="#ffd04b">
          <el-menu-item index="/">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>

          <el-submenu index="1" v-if="loginUser.role == 'admin'">
            <template slot="title">
              <i class="el-icon-document"></i>
              <span>课程管理</span>
            </template>
            <el-menu-item index="course">
              <i class="el-icon-notebook-2"></i>
              <span>课程列表</span>
            </el-menu-item>
            <el-menu-item index="teacherCourse">
              <i class="el-icon-user"></i>
              <span>任课列表</span>
            </el-menu-item>
            <el-menu-item index="studentCourse">
              <i class="el-icon-user"></i>
              <span>选课列表</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="2" v-if="loginUser.role == 'admin'">
            <template slot="title">
              <i class="el-icon-document"></i>
              <span>用户管理</span>
            </template>
            <el-menu-item index="teacher">
            <i class="el-icon-user"></i>
            <span>教师列表</span>
            </el-menu-item>
              <el-menu-item index="student">
                <i class="el-icon-user"></i>
                <span>学生列表</span>
              </el-menu-item>
              <el-menu-item index="admin">
                <i class="el-icon-user"></i>
                <span>管理员列表</span>
              </el-menu-item>
          </el-submenu>

          <el-menu-item index="index" v-if="loginUser.role == 'admin'">
            <i class="el-icon-user"></i>
            <span>评教指标列表</span>
          </el-menu-item>
          <el-menu-item index="notice" v-if="loginUser.role == 'admin'">
            <i class="el-icon-user"></i>
            <span>系统公告设置</span>
          </el-menu-item>
          <el-menu-item index="myTeachingCourse" v-if="loginUser.role == 'teacher'">
            <i class="el-icon-user"></i>
            <span>我的任课</span>
          </el-menu-item>
          <el-menu-item index="studentIndex" v-if="loginUser.role == 'teacher'">
            <i class="el-icon-user"></i>
            <span>课程评教列表</span>
          </el-menu-item>
          <el-menu-item index="myCourse" v-if="loginUser.role == 'student'">
            <i class="el-icon-user"></i>
            <span>我的课程</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <!--中心-->
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "Layout",
  data() {
    return {
      loginUser: null
    }
  },
  created() {
    this.getLoginUserInfo();//文件一创建，就调用后续的getloginuserinfor的方法
  },
  methods: {
    getLoginUserInfo(){
      let loginUserJson = window.sessionStorage.getItem('loginUser');
      this.loginUser = JSON.parse(loginUserJson);
    },//拿到登录的用户，拿到权限，true才会渲染界面
    handleCommand(command) {
      if (command == 'logout'){
        window.sessionStorage.removeItem("loginUser")
        let role = this.loginUser.role;
        this.$router.push("login");
      }
    }
  }
}
</script>

<style>
.el-menu{
  border-right: none !important;
}
.el-dropdown-link {
  cursor: pointer;
  color: white;
}
</style>