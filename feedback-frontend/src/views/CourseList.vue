<template>
  <div>
    <div style="border-bottom: 1px solid rgb(128,128,128); margin-bottom: 10px; padding-bottom: 5px; clear: both">
      <el-button
          type="primary"
          size="mini"
          @click="beforeOpenAdd">新增
      </el-button>
      <div style="display: inline-block; width: 50px; height: 27px"></div>
      <div style="float: right;">
        课程名称：
        <el-input type="text" size="small" v-model="queryParams.name" clearable style="width: 200px"></el-input>
        <el-button plain size="small" @click="search">查询</el-button>
      </div>
    </div>
    <div>
      <el-table :data="courseTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="name" label="课程名称" width="120px" align="center"></el-table-column>
        <el-table-column prop="credit" label="学分" align="center" ></el-table-column>
        <el-table-column prop="textbook" label="教材名称" width="150px" align="center"></el-table-column>
        <el-table-column prop="type" label="课程类型" width="150px" align="center"></el-table-column>
        <el-table-column prop="hour" label="学时" width="150px" align="center"></el-table-column>
        <el-table-column prop="description" label="课程描述" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="300px" align="center">
          <template slot-scope="scope">
            <el-button
                v-if="true"
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-popconfirm title="确定删除吗？" @confirm="removeCourse(scope.row.id)">
              <el-button
                  v-if="true"
                  slot="reference"
                  size="mini"
                  type="danger">删除
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

    <el-dialog
        title="添加课程信息"
        :visible.sync="dialogVisible"
        width="30%"
        center>
      <div>
        <div>
          <el-form :model="courseForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="课程名称：" prop="name" required>
              <el-input v-model="courseForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="学分：" prop="credit" required>
              <el-input-number v-model="courseForm.credit" :min="0"></el-input-number>
            </el-form-item>
            <el-form-item label="教材名称：" prop="textbook" required>
              <el-input v-model="courseForm.textbook" ></el-input>
            </el-form-item>
            <el-form-item label="课程类型：" prop="type" required>
              <el-input  v-model="courseForm.type" ></el-input>
            </el-form-item>
            <el-form-item label="学时：" prop="hour" required>
              <el-input-number v-model="courseForm.hour" :min="0"></el-input-number>
            </el-form-item>
            <el-form-item label="课程描述：" prop="description" required>
              <el-input type="textarea" v-model="courseForm.description" rows="4"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="addCourse()">保 存</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="修改课程信息"
        :visible.sync="dialogEditVisible"
        width="50%"
        center>
      <div>
        <div>
          <el-form :model="courseForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="课程名称：" prop="name" required>
              <el-input v-model="courseForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="学分：" prop="credit" required>
              <el-input-number v-model="courseForm.credit" :min="0"></el-input-number>
            </el-form-item>
            <el-form-item label="教材名称：" prop="textbook" required>
              <el-input v-model="courseForm.textbook" ></el-input>
            </el-form-item>
            <el-form-item label="课程类型：" prop="type" required>
              <el-input  v-model="courseForm.type" ></el-input>
            </el-form-item>
            <el-form-item label="学时：" prop="hour" required>
              <el-input-number v-model="courseForm.hour" :min="0"></el-input-number>
            </el-form-item>
            <el-form-item label="课程描述：" prop="description" required>
              <el-input type="textarea" v-model="courseForm.description" rows="4"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogEditVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="editCourse()">保 存</el-button>
        </span>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'courseList',
  data(){
    return {
      courseTableData: [],
      dialogVisible: false,
      dialogEditVisible: false,
      dialogRegisterVisible: false,
      imageUrl: null,
      courseForm: Object.assign({}, this.defaultCourseForm),
      defaultCourseForm: {
        name: '',
        description: '',
        credit: 0,
        textbook: '',
        type: '',
        hour: 0
      },
      rules: {
        name: [
          {required: true, message: '请输入课程名称'},
        ],
        description: [
          {required: true, message: '请输入课程描述'},
        ],
        credit: [
          {required: true, message: '请输入学分'},
        ],
        textbook: [
          {required: true, message: '请输入教材'},
        ],
        type: [
          {required: true, message: '请输入课程类型'},
        ],
        hour: [
          {required: true, message: '请输入学时'},
        ]
      },
      pageParam: {
        pageNum: 1,
        pageSize: 10,
        total: 10
      },
      queryParams: {
        name: ''
      }
    }
  },
  created() {//文件初始化，一打开则会触发这些方法
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
      request.get("/course",
          {
            params: {
              pageNum: this.pageParam.pageNum,
              pageSize: this.pageParam.pageSize,
              name: this.queryParams.name
            }//将分页参数和查询发送给后端
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {//判断后端传输的数据是否为200，是则成功拿回给tabledate
              //进入主页面
              this.courseTableData = response.data;
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
    beforeOpenAdd() {
      //每次打开添加或编辑对话框时，清空表单内容
      this.courseForm = Object.assign({}, this.defaultCourseForm);
      this.dialogVisible = true;
    },
    handleFileUpload(event) {
      this.uploadFile = event.target.files[0];
      // 创建一个FormData对象
      const formData = new FormData();
      // 将文件添加到FormData对象中
      formData.append('image', this.uploadFile);
      // 发送POST请求
      axios.post('/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
          .then(response => {
            console.log(response)
            if (response.data.code == 200) {
              this.roomForm.roomPic = response.data.accessUrl
              this.$message({
                showClose: true,
                message: response.data.msg,
                type: 'success'
              });
            } else {
              this.$message({
                showClose: true,
                message: response.data.msg,
                type: 'error'
              });
            }
          })
          .catch(error => {
            // 处理错误
            console.error(error);
          });
    },
    handleEdit(idx, row) {
      this.dialogEditVisible = true;//显示编辑对话框
      this.courseForm = row;
      console.log(this.courseForm)
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
    removeCourse(id) {
      request.post("/course?method=remove&id=" + id)
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
    },
    addCourse() {
      request.post("/course?method=add", this.courseForm)
          .then(response => {
            // 请求成功，获取响应数据
            if (response.code == 200) {
              this.dialogVisible = false
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
    },
    editCourse() {
      request.post("/course?method=edit", this.courseForm)
          .then(response => {
            // 请求成功，获取响应数据
            if (response.code == 200) {
              this.dialogEditVisible = false;
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