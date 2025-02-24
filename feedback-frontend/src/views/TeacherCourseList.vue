<template>
  <div>
    <div style="border-bottom: 1px solid rgb(128,128,128); margin-bottom: 10px; padding-bottom: 5px; clear: both">
      <div style="display: inline-block; width: 50px; height: 27px"></div>
      <div style="float: right;">
        教师名称：
        <el-input type="text" size="small" v-model="queryParams.teacherName" clearable style="width: 200px"></el-input>
        课程名称：
        <el-input type="text" size="small" v-model="queryParams.courseName" clearable style="width: 200px"></el-input>
        <el-button plain size="small" @click="search">查询</el-button>
      </div>
    </div>
    <div>
      <el-table :data="teacherCourseTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="teacherNo" label="教工号"  align="center"></el-table-column>
        <el-table-column prop="teacherName" label="教师名称"  align="center"></el-table-column>
        <el-table-column prop="courseName" label="课程名称" align="center"></el-table-column>
        <el-table-column prop="createTime" label="任课时间"  align="center"></el-table-column>
        <el-table-column label="操作" width="300px" align="center">
          <template slot-scope="scope">
            <el-popconfirm title="确定取消吗？" @confirm="removeTeacherCourse(scope.row.id)">
              <el-button
                  v-if="true"
                  slot="reference"
                  size="mini"
                  type="danger">取消任课
              </el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
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
  name: 'teacherCourseList',
  data(){
    return {
      teacherCourseTableData: [],
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
    this.getData();
  },
  methods: {
    getLoginUserInfo(){
      let userInfo = window.localStorage.getItem('userInfo');
      this.userInfo = JSON.parse(userInfo);
      console.log("roomList")
      console.log(this.userInfo)
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
              teacherName: this.queryParams.teacherName,
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
    },
    removeTeacherCourse(id) {
      request.post("/teacherCourse?method=remove&id=" + id)
          .then(response => {
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //重新拉取数据
              this.getData();
              this.$message({
                showClose: true,
                message: response.msg,
                type: 'success'
              });
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
    }
  }
}
</script>

<style scoped>

</style>