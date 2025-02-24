<template>
    <div style="height: 100%; ">
      <div style="width: 400px; margin: auto;background-color:rgba(82,145,127,0.4); border-radius: 10px; position: relative; top: 150px">
        <div
            style="width: 100%; height: 100px; font-size: 30px; line-height: 100px; text-align: center; color: #000000">
          欢迎登录-评教系统
        </div>
        <div style="margin-top: 0px; text-align: center;">
          <el-form :model="user" style="padding-bottom: 10px">
            <el-form-item>
              <el-input v-model="user.account" prefix-icon="el-icon-user" style="width: 80%"
                        placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input v-model="user.password" prefix-icon="el-icon-lock" style="width: 80%" placeholder="请输入密码"
                        show-password></el-input>
            </el-form-item>
            <el-form-item>
              <el-select v-model="user.role" placeholder="请选择" style="width: 80%">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 80%; margin-top: 10px" type="primary" @click="login()">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      options: [
        {
          value: 'admin',
          label: '管理员'
        }, {
          value: 'teacher',
          label: '教师'
        }, {
          value: 'student',
          label: '学生'
        }
      ],
      user: {
        no: '',
        role: "admin",
        account: '',
        password: '',
      }
    }
  },
  // 页面加载的时候，做一些事情，在created里面
  created() {
  },
  // 定义一些页面上控件出发的事件调用的方法
  methods: {
    login() {
      //如果不是管理员，则account转为no
      if (this.user.role != 'admin'){
        this.user.no = this.user.account;
      }
      request.post("/login?method=" + this.user.role, this.user).then(res => {
        if (res.code == 200) {
          let loginUser = res.data;
          window.sessionStorage.setItem("loginUser", JSON.stringify(loginUser));
          this.$message({
            message: '登录成功',
            type: 'success'
          });
          this.$router.push("/");
        } else {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'error'
          });
        }
      })
    }
  }
}
</script>

<style>
body{
  margin: 0;
  padding: 0;
}
</style>