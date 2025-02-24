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
        账号：
        <el-input type="text" size="small" v-model="queryParams.account" clearable style="width: 200px; margin-right: 20px"></el-input>
        管理员姓名：
        <el-input type="text" size="small" v-model="queryParams.name" clearable style="width: 200px"></el-input>
        <el-button plain size="small" @click="search">查询</el-button>
      </div>
    </div>
    <div>
      <el-table :data="adminTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="account" label="账号" width="120px" align="center"></el-table-column>
        <el-table-column prop="name" label="管理员姓名" align="center" ></el-table-column>
        <el-table-column prop="gender" label="性别" width="150px" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.gender == 0">女</span>
            <span v-else>男</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="150px" align="center"></el-table-column>
        <el-table-column prop="birthday" label="出生年月" width="150px" align="center"></el-table-column>
        <el-table-column prop="nativeplace" label="籍贯" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="300px" align="center">
          <template slot-scope="scope">
            <el-button
                v-if="true"
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-popconfirm title="确定删除吗？" @confirm="removeAdmin(scope.row.id)">
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
        title="添加管理员信息"
        :visible.sync="dialogVisible"
        width="30%"
        center>
      <div>
        <div>
          <el-form :model="adminForm" :rules="rules" size="small" ref="userForm" label-width="110px">
            <el-form-item label="账号：" prop="account" required>
              <el-input v-model="adminForm.account" ></el-input>
            </el-form-item>
            <el-form-item label="管理员姓名：" prop="name" required>
              <el-input v-model="adminForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="性别：" prop="gender" required>
              <el-radio v-model="adminForm.gender" :label="1">男</el-radio>
              <el-radio v-model="adminForm.gender" :label="0">女</el-radio>
            </el-form-item>
            <el-form-item label="手机号：" prop="phone" required>
              <el-input v-model="adminForm.phone" ></el-input>
            </el-form-item>
            <el-form-item label="出生年月：" prop="birthday" required>
              <el-date-picker
                  v-model="adminForm.birthday"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="籍贯：" prop="nativeplace" required>
              <el-input  v-model="adminForm.nativeplace" ></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password" required>
              <el-input  v-model="adminForm.password" show-password></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="addAdmin()">保 存</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="修改管理员信息"
        :visible.sync="dialogEditVisible"
        width="50%"
        center>
      <div>
        <div>
          <el-form :model="adminForm" :rules="rules" size="small" ref="userForm" label-width="110px">
            <el-form-item label="账号：" prop="account" required>
              <el-input v-model="adminForm.account" ></el-input>
            </el-form-item>
            <el-form-item label="管理员姓名：" prop="name" required>
              <el-input v-model="adminForm.name" ></el-input>
            </el-form-item>
            <el-form-item label="性别：" prop="gender" required>
              <el-radio v-model="adminForm.gender" :label="1">男</el-radio>
              <el-radio v-model="adminForm.gender" :label="0">女</el-radio>
            </el-form-item>
            <el-form-item label="手机号：" prop="phone" required>
              <el-input v-model="adminForm.phone" ></el-input>
            </el-form-item>
            <el-form-item label="出生年月：" prop="birthday" required>
              <el-date-picker
                  v-model="adminForm.birthday"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="籍贯：" prop="nativeplace" required>
              <el-input  v-model="adminForm.nativeplace" ></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password" required>
              <el-input  v-model="adminForm.password" show-password></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogEditVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="editAdmin()">保 存</el-button>
        </span>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'adminList',
  data(){
    return {
      adminTableData: [],
      dialogVisible: false,
      dialogEditVisible: false,
      dialogRegisterVisible: false,
      imageUrl: null,
      adminForm: Object.assign({}, this.defaultAdminForm),
      defaultAdminForm: {
        account: '',
        name: '',
        gender: 1,
        phone: '',
        birthday: '',
        nativeplace: '',
        password: ''
      },
      rules: {
        account: [
          {required: true, message: '请输入账号'},
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
        account: '',
        name: ''
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
      request.get("/admin",
          {
            params: {
              pageNum: this.pageParam.pageNum,
              pageSize: this.pageParam.pageSize,
              account: this.queryParams.account,
              name: this.queryParams.name
            }
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //进入主页面
              this.adminTableData = response.data;
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
      this.adminForm = Object.assign({}, this.defaultAdminForm);
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
      this.adminForm = row;
      console.log(this.adminForm)
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
    removeAdmin(id) {
      request.post("/admin?method=remove&id=" + id)
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
    addAdmin() {
      request.post("/admin?method=add", this.adminForm)
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
    editAdmin() {
      request.post("/admin?method=edit", this.adminForm)
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