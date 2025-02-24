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
        学号：
        <el-input type="text" size="small" v-model="queryParams.no" clearable style="width: 200px; margin-right: 20px"></el-input>
        学生姓名：
        <el-input type="text" size="small" v-model="queryParams.name" clearable style="width: 200px"></el-input>
        <el-button plain size="small" @click="search">查询</el-button>
      </div>
    </div>
    <div>
      <el-table :data="studentTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="no" label="学号" width="120px" align="center"></el-table-column>
        <el-table-column prop="name" label="学生姓名" align="center" ></el-table-column>
        <el-table-column prop="gender" label="性别" width="150px" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.gender == 0">女</span>
            <span v-else>男</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="150px" align="center"></el-table-column>
        <el-table-column prop="birthday" label="出生年月" width="150px" align="center"></el-table-column>
        <el-table-column prop="classname" label="班级" show-overflow-tooltip></el-table-column>
        <el-table-column prop="nativeplace" label="籍贯" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="300px" align="center">
          <template slot-scope="scope">
            <el-button
                v-if="true"
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
                v-if="true"
                size="mini"
                @click="addStudentCourse(scope.$index, scope.row)">选课
            </el-button>
            <el-popconfirm title="确定删除吗？" @confirm="removeStudent(scope.row.id)" style="margin-left: 10px">
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
        title="添加学生信息"
        :visible.sync="dialogVisible"
        width="30%"
        center>
      <div>
        <div>
          <el-form :model="studentForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="学号：" prop="no" required>
              <el-input v-model="studentForm.no" ></el-input>
            </el-form-item>
            <el-form-item label="学生姓名：" prop="name" required>
              <el-input v-model="studentForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="性别：" prop="gender" required>
              <el-radio v-model="studentForm.gender" :label="1">男</el-radio>
              <el-radio v-model="studentForm.gender" :label="0">女</el-radio>
            </el-form-item>
            <el-form-item label="手机号：" prop="phone" required>
              <el-input v-model="studentForm.phone" ></el-input>
            </el-form-item>
            <el-form-item label="出生年月：" prop="birthday" required>
              <el-date-picker
                  v-model="studentForm.birthday"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="班级：" prop="classname" required>
              <el-input  v-model="studentForm.classname" ></el-input>
            </el-form-item>
            <el-form-item label="籍贯：" prop="nativeplace" required>
              <el-input  v-model="studentForm.nativeplace" ></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password" required>
              <el-input  v-model="studentForm.password" show-password></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="addStudent()">保 存</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="修改学生信息"
        :visible.sync="dialogEditVisible"
        width="50%"
        center>
      <div>
        <div>
          <el-form :model="studentForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="学号：" prop="no" required>
              <el-input v-model="studentForm.no" ></el-input>
            </el-form-item>
            <el-form-item label="学生姓名：" prop="name" required>
              <el-input v-model="studentForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="性别：" prop="gender" required>
              <el-radio v-model="studentForm.gender" :label="1">男</el-radio>
              <el-radio v-model="studentForm.gender" :label="0">女</el-radio>
            </el-form-item>
            <el-form-item label="手机号：" prop="phone" required>
              <el-input v-model="studentForm.phone" ></el-input>
            </el-form-item>
            <el-form-item label="出生年月：" prop="birthday" required>
              <el-date-picker
                  v-model="studentForm.birthday"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="班级：" prop="classname" required>
              <el-input  v-model="studentForm.classname" ></el-input>
            </el-form-item>
            <el-form-item label="籍贯：" prop="nativeplace" required>
              <el-input  v-model="studentForm.nativeplace" ></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password" required>
              <el-input  v-model="studentForm.password" show-password></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogEditVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="editStudent()">保 存</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="任课信息"
        :visible.sync="dialogCourseVisible"
        width="80%"
        center>
      <div>
        <div style="border-bottom: 1px solid rgb(128,128,128); margin-bottom: 10px; padding-bottom: 5px; clear: both">
          <div style="display: inline-block; width: 50px; height: 27px"></div>
          <div style="float: right;">
            教师名称：
            <el-input type="text" size="small" v-model="queryParamsTeacherCourse.teacherName" clearable style="width: 200px"></el-input>
            课程名称：
            <el-input type="text" size="small" v-model="queryParamsTeacherCourse.courseName" clearable style="width: 200px"></el-input>
            <el-button plain size="small" @click="searchCourse">查询</el-button>
          </div>
        </div>
        <div>
          <el-table :data="courseTableData" style="width: 100%">
            <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
            <el-table-column prop="teacherNo" label="教工号"  align="center"></el-table-column>
            <el-table-column prop="teacherName" label="教师名称"  align="center"></el-table-column>
            <el-table-column prop="courseName" label="课程名称" align="center"></el-table-column>
            <el-table-column prop="credit" label="学分" align="center" ></el-table-column>
            <el-table-column prop="textbook" label="教材名称" width="150px" align="center"></el-table-column>
            <el-table-column prop="type" label="课程类型" width="150px" align="center"></el-table-column>
            <el-table-column prop="hour" label="学时" width="150px" align="center"></el-table-column>
            <el-table-column prop="description" label="课程描述" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" width="100px" align="center">
              <template slot-scope="scope">
                <el-button
                    v-if="true"
                    size="mini"
                    @click="chooseCourse(scope.$index, scope.row)">选择
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div style="margin-top: 10px">
          <el-pagination
              @size-change="handleSizeChangeCourse"
              @current-change="handleCurrentChangeCourse"
              :current-page="pageParamTeacherCourse.pageNum"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="pageParamTeacherCourse.pageSize"
              layout="total, sizes, prev, pager, next"
              :total="pageParamTeacherCourse.total">
          </el-pagination>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogCourseVisible = false" size="mini">取 消</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'studentList',
  data(){
    return {
      courseTableData: [],
      studentTableData: [],
      dialogVisible: false,
      dialogEditVisible: false,
      dialogCourseVisible: false,
      imageUrl: null,
      studentForm: Object.assign({}, this.defaultStudentForm),
      defaultStudentForm: {
        no: '',
        name: '',
        gender: 1,
        phone: '',
        birthday: '',
        classname: '',
        nativeplace: '',
        password: ''
      },
      rules: {
        no: [
          {required: true, message: '请输入学号'},
        ],
        name: [
          {required: true, message: '请输入学生姓名'},
        ],
        gender: [
          {required: true, message: '请选择性别'},
        ],
        phone: [
          {required: true, message: '请输入手机号'},
        ],
        birthday: [
          {required: true, message: '请输入出生年月'},
        ],
        classname: [
          {required: true, message: '请输入班级'},
        ],
        nativeplace: [
          {required: true, message: '请输入籍贯'},
        ],
        password: [
          {required: true, message: '请输入密码'},
        ]
      },
      pageParam: {
        pageNum: 1,
        pageSize: 10,
        total: 10
      },
      queryParams: {
        no: '',
        name: ''
      },
      studentCourse: {
        studentId: '',
        teacherCourseId: ''
      },
      pageParamTeacherCourse: {
        pageNum: 1,
        pageSize: 10,
        total: 10
      },
      queryParamsTeacherCourse: {
        teacherName: '',
        courseName: ''
      },
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
      request.get("/student",
          {
            params: {
              pageNum: this.pageParam.pageNum,
              pageSize: this.pageParam.pageSize,
              no: this.queryParams.no,
              name: this.queryParams.name
            }
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //进入主页面
              this.studentTableData = response.data;
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
    searchCourse(){
      this.pageParamTeacherCourse.pageNum = 1;
      this.getDataCourse();
    },
    getDataCourse() {
      request.get("/teacherCourse",
          {
            params: {
              pageNum: this.pageParamTeacherCourse.pageNum,
              pageSize: this.pageParamTeacherCourse.pageSize,
              teacherName: this.queryParamsTeacherCourse.teacherName,
              courseName: this.queryParamsTeacherCourse.courseName
            }
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //进入主页面
              this.courseTableData = response.data;
              this.pageParamTeacherCourse.total = response.total;
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
      this.studentForm = Object.assign({}, this.defaultStudentForm);
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
      this.dialogEditVisible = true;
      this.studentForm = row;
      console.log(this.studentForm)
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
    handleCurrentChangeCourse(val) {
      this.pageParamCourse.pageNum = val
      this.getDataCourse()
    },
    handleSizeChangeCourse(val) {
      this.pageParamCourse.pageSize = val
      this.getDataCourse()
    },
    removeStudent(id) {
      request.post("/student?method=remove&id=" + id)
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
    addStudent() {
      request.post("/student?method=add", this.studentForm)
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
    editStudent() {
      request.post("/student?method=edit", this.studentForm)
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
    },
    addStudentCourse(idx, row){
      this.dialogCourseVisible = true;
      //清空分页和查询条件
      this.pageParamTeacherCourse.pageNum = 1;
      this.pageParamTeacherCourse.pageSize= 10;
      this.queryParamsTeacherCourse.teacherName = '';
      this.queryParamsTeacherCourse.courseName = '';

      this.getDataCourse();
      this.studentCourse.studentId = row.id;
    },
    chooseCourse(idx, row){
      this.studentCourse.teacherCourseId = row.id;
      request.post("/studentCourse?method=add", this.studentCourse)
          .then(response => {
            // 请求成功，获取响应数据
            if (response.code == 200) {
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
          }).catch(error => {
        // 请求失败，处理错误
        console.error(error);
      });
    }
  }
}
</script>

<style scoped>

</style>