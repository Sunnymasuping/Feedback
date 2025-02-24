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
        教工号：
        <el-input type="text" size="small" v-model="queryParams.no" clearable style="width: 200px; margin-right: 20px"></el-input>
        教师姓名：
        <el-input type="text" size="small" v-model="queryParams.name" clearable style="width: 200px"></el-input>
        <el-button plain size="small" @click="search">查询</el-button>
      </div>
    </div>
    <div>
      <el-table :data="teacherTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="no" label="教工号" width="120px" align="center"></el-table-column>
        <el-table-column prop="name" label="教师名称" align="center" ></el-table-column>
        <el-table-column prop="gender" label="性别" width="150px" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.gender == 0">女</span>
            <span v-else>男</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="150px" align="center"></el-table-column>
        <el-table-column prop="birthday" label="出生年月" width="150px" align="center"></el-table-column>
        <el-table-column prop="title" label="职称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="nativePlace" label="籍贯" show-overflow-tooltip></el-table-column>
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
                @click="addTeacherCourse(scope.$index, scope.row)">任课
            </el-button>
            <el-popconfirm title="确定删除吗？" @confirm="removeTeacher(scope.row.id)" style="margin-left: 10px">
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
        title="添加教师信息"
        :visible.sync="dialogVisible"
        width="30%"
        center>
      <div>
        <div>
          <el-form :model="teacherForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="教工号：" prop="no" required>
              <el-input v-model="teacherForm.no" ></el-input>
            </el-form-item>
            <el-form-item label="教师姓名：" prop="name" required>
              <el-input v-model="teacherForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="性别：" prop="gender" required>
              <el-radio v-model="teacherForm.gender" :label="1">男</el-radio>
              <el-radio v-model="teacherForm.gender" :label="0">女</el-radio>
            </el-form-item>
            <el-form-item label="手机号：" prop="phone" required>
              <el-input v-model="teacherForm.phone" ></el-input>
            </el-form-item>
            <el-form-item label="出生年月：" prop="birthday" required>
              <el-date-picker
                  v-model="teacherForm.birthday"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="职称：" prop="title" required>
              <el-input  v-model="teacherForm.title" ></el-input>
            </el-form-item>
            <el-form-item label="籍贯：" prop="nativePlace" required>
              <el-input  v-model="teacherForm.nativePlace" ></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password" required>
              <el-input  v-model="teacherForm.password" show-password></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="addTeacher()">保 存</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="修改教师信息"
        :visible.sync="dialogEditVisible"
        width="50%"
        center>
      <div>
        <div>
          <el-form :model="teacherForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="教工号：" prop="no" required>
              <el-input v-model="teacherForm.no" ></el-input>
            </el-form-item>
            <el-form-item label="教师姓名：" prop="name" required>
              <el-input v-model="teacherForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="性别：" prop="gender" required>
              <el-radio v-model="teacherForm.gender" :label="1">男</el-radio>
              <el-radio v-model="teacherForm.gender" :label="0">女</el-radio>
            </el-form-item>
            <el-form-item label="手机号：" prop="phone" required>
              <el-input v-model="teacherForm.phone" ></el-input>
            </el-form-item>
            <el-form-item label="出生年月：" prop="birthday" required>
              <el-date-picker
                  v-model="teacherForm.birthday"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="职称：" prop="title" required>
              <el-input  v-model="teacherForm.title" ></el-input>
            </el-form-item>
            <el-form-item label="籍贯：" prop="nativePlace" required>
              <el-input  v-model="teacherForm.nativePlace" ></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password" required>
              <el-input  v-model="teacherForm.password" show-password></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogEditVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="editTeacher()">保 存</el-button>
        </span>
    </el-dialog>


    <el-dialog
        title="课程信息"
        :visible.sync="dialogCourseVisible"
        width="80%"
        center>
      <div>
        <div style="border-bottom: 1px solid rgb(128,128,128); margin-bottom: 10px; padding-bottom: 5px; clear: both">
          <div style="display: inline-block; width: 50px; height: 27px"></div>
          <div style="float: right;">
            课程名称：
            <el-input type="text" size="small" v-model="queryParamsCourse.name" clearable style="width: 200px"></el-input>
            <el-button plain size="small" @click="searchCourse">查询</el-button>
          </div>
        </div>
        <div>
          <el-table :data="courseTableData" style="width: 100%">
            <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
            <el-table-column prop="name" label="课程名称"  align="center"></el-table-column>
            <el-table-column prop="credit" label="学分" align="center" ></el-table-column>
            <el-table-column prop="textbook" label="教材名称" align="center"></el-table-column>
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
              :current-page="pageParamCourse.pageNum"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="pageParamCourse.pageSize"
              layout="total, sizes, prev, pager, next"
              :total="pageParamCourse.total">
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
  name: 'teacherList',
  data(){
    return {
      courseTableData: [],
      teacherTableData: [],
      dialogVisible: false,
      dialogEditVisible: false,
      dialogCourseVisible: false,
      imageUrl: null,
      teacherForm: Object.assign({}, this.defaultTeacherForm),
      defaultTeacherForm: {
        no: '',
        name: '',
        gender: 1,
        phone: '',
        birthday: '',
        title: '',
        nativePlace: '',
        password: ''
      },
      rules: {
        no: [
          {required: true, message: '请输入教工号'},
        ],
        name: [
          {required: true, message: '请输入教师姓名'},
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
        title: [
          {required: true, message: '请输入职称'},
        ],
        nativePlace: [
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
      teacherCourse: {
        teacherId: '',
        courseId: ''
      },
      pageParamCourse: {
        pageNum: 1,
        pageSize: 10,
        total: 10
      },
      queryParamsCourse: {
        name: ''
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
      request.get("/teacher",
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
              this.teacherTableData = response.data;
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
      this.pageParamCourse.pageNum = 1;
      this.getDataCourse();
    },
    getDataCourse() {
      request.get("/course",
          {
            params: {
              pageNum: this.pageParamCourse.pageNum,
              pageSize: this.pageParamCourse.pageSize,
              name: this.queryParamsCourse.name
            }
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //进入主页面
              this.courseTableData = response.data;
              this.pageParamCourse.total = response.total;
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
      this.teacherForm = Object.assign({}, this.defaultTeacherForm);
      this.dialogVisible = true;
    },
    handleEdit(idx, row) {
      this.dialogEditVisible = true;
      this.teacherForm = row;
      console.log(this.teacherForm)
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
    removeTeacher(id) {
      request.post("/teacher?method=remove&id=" + id)
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
    addTeacher() {
      request.post("/teacher?method=add", this.teacherForm)
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
    editTeacher() {
      request.post("/teacher?method=edit", this.teacherForm)
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
    addTeacherCourse(idx, row){
      this.dialogCourseVisible = true;
      this.pageParamCourse.pageNum = 1;
      this.pageParamCourse.pageSize= 10;
      this.queryParamsCourse.name = '';
      this.getDataCourse();
      this.teacherCourse.teacherId = row.id;
    },
    chooseCourse(idx, row){
      this.teacherCourse.courseId = row.id;
      request.post("/teacherCourse?method=add", this.teacherCourse)
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