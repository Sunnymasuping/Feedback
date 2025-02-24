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
      <el-table :data="studentIndexTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="studentName" label="学生姓名" align="center"></el-table-column>
        <el-table-column prop="courseName" label="课程名称" align="center"></el-table-column>
        <el-table-column prop="score" label="课程评分" width="150px" align="center"></el-table-column>
        <el-table-column prop="createTime" label="评教时间"  align="center"></el-table-column>
        <el-table-column label="操作" width="100px" align="center">
          <template slot-scope="scope">
            <el-button
                v-if="true"
                size="mini"
                type="text"
                @click="openDetail(scope.$index, scope.row)">详情
            </el-button>
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

    <el-dialog
        title="评教详情"
        :visible.sync="dialogVisible"
        width="80%"
        center>
      <div>
        <el-table :data="indexTableData" style="width: 100%">
          <el-table-column prop="content" label="指标内容" ></el-table-column>
          <el-table-column label="评分" align="center">
            <template slot-scope="scope">
              <el-form :model="scope.row">
                <el-radio-group v-model="scope.row.score" disabled>
                  <el-radio :label="60">差</el-radio>
                  <el-radio :label="75">合格</el-radio>
                  <el-radio :label="85">良好</el-radio>
                  <el-radio :label="100">优秀</el-radio>
                </el-radio-group>
              </el-form>
            </template>
          </el-table-column>
        </el-table>
        <el-form >
          <el-form-item label="评教内容：" prop="content">
            <el-input type="textarea" v-model="content" rows="4" disabled></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'studentIndexList.vue',
  data(){
    return {
      dialogVisible: false,
      studentIndexTableData: [],
      loginUser: '',
      pageParam: {
        pageNum: 1,
        pageSize: 10,
        total: 10
      },
      queryParams: {
        teacherName: '',
        courseName: ''
      },
      indexTableData: [],
      content: null,
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
      request.get("/studentIndex?method=list",
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
              this.studentIndexTableData = response.data;
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
    openDetail(idx, row){
      request.get("/studentIndex?method=detail",
          {
            params: {
              studentCourseId: row.id
            }
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //进入主页面
              this.indexTableData = response.data;
              this.content = row.content;
              this.dialogVisible = true;
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