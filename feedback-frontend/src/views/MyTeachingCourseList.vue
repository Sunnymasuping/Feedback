<template>
  <div>
    <div style="border-bottom: 1px solid rgb(128,128,128); margin-bottom: 10px; padding-bottom: 5px; clear: both">
      <div style="display: inline-block; width: 50px; height: 27px"></div>
      <div style="float: right;">
        课程名称：
        <el-input type="text" size="small" v-model="queryParams.courseName" clearable style="width: 200px"></el-input>
        <el-button plain size="small" @click="search">查询</el-button>
      </div>
    </div>
    <div>
      <el-table :data="teacherCourseTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="courseName" label="课程名称" align="center"></el-table-column>
        <el-table-column prop="avgScore" label="课程评分" width="150px" align="center">
          <template  slot-scope="scope">
            <div v-if="scope.row.avgScore == 0" class="cell">暂无评分</div>
            <div v-else class="cell">{{scope.row.avgScore}}</div>
          </template>
        </el-table-column>
        <el-table-column prop="credit" label="学分" width="150px" align="center"></el-table-column>
        <el-table-column prop="textbook" label="教材名称" align="center"></el-table-column>
        <el-table-column prop="type" label="课程类型" width="150px" align="center"> </el-table-column>
        <el-table-column prop="hour" label="学时" width="150px" align="center"></el-table-column>
        <el-table-column prop="description" label="课程描述"  show-overflow-tooltip></el-table-column>
      </el-table>
    </div>
    <div style="margin-top: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageParam.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageParam.pageSize"
          layout="total, sizes, prev, pager, next"
          :total="pageParam.total">
      </el-pagination>
    </div>


  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'myTeachingCourseList.vue',
  data(){
    return {
      teacherCourseTableData: [],
      loginUser: '',
      pageParam: {
        pageNum: 1,
        pageSize: 10,
        total: 10
      },
      queryParams: {
        teacherName: '',
        courseName: ''
      }
    }
  },
  created() {
    this.getLoginUserInfo();
    this.getData();
  },
  methods: {
    getLoginUserInfo() {
      let loginUserJson = window.sessionStorage.getItem('loginUser');
      this.loginUser = JSON.parse(loginUserJson);
    },
    search(){
      this.pageParam.pageNum = 1;
      this.getData();
    },
    getData() {
      request.get("/teacherCourse",
          {
            params: {
              pageNum: this.pageParam.pageNum,
              pageSize: this.pageParam.pageSize,
              teacherId: this.loginUser.id,
              courseName: this.queryParams.courseName
            }
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //进入主页面
              this.teacherCourseTableData = response.data;
              this.pageParam.total = response.total;
            } else {
              this.$message({
                showClose: true,
                message: response.msg,
                type: 'error'
              });
            }
          })
          .catch(error => {
            // 请求失败，处理错误
            console.error(error);
          });
    },
    handleCurrentChange(val) {
      this.pageParam.pageNum = val
      console.log(this.pageParam);
      this.getData()
    },
    handleSizeChange(val) {
      this.pageParam.pageSize = val
      console.log(this.pageParam);
      this.getData()
    }
  }
}
</script>

<style scoped>

</style>