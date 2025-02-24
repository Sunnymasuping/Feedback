<template>
  <div>
    <div style="border-bottom: 1px solid rgb(128,128,128); margin-bottom: 10px; padding-bottom: 5px; clear: both">
      <el-button
          type="primary"
          size="mini"
          @click="beforeOpenAdd">新增
      </el-button>
    </div>
    <div>
      <el-table :data="noticeTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="title" label="标题" align="center"></el-table-column>
        <el-table-column prop="content" label="内容" show-overflow-tooltip></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="220px" align="center"></el-table-column>
        <el-table-column label="操作" width="300px" align="center">
          <template slot-scope="scope">
            <el-button
                v-if="true"
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-popconfirm title="确定删除吗？" @confirm="removeNotice(scope.row.id)">
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
        title="添加公告信息"
        :visible.sync="dialogVisible"
        width="30%"
        center>
      <div>
        <div>
          <el-form :model="noticeForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="标题：" prop="title" required>
              <el-input v-model="noticeForm.title" ></el-input>
            </el-form-item>
            <el-form-item label="内容：" prop="content" required>
              <el-input type="textarea" v-model="noticeForm.content" rows="4"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="addNotice()">保 存</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="修改公告信息"
        :visible.sync="dialogEditVisible"
        width="50%"
        center>
      <div>
        <div>
          <el-form :model="noticeForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form :model="noticeForm" :rules="rules" size="small" ref="userForm" label-width="100px">
              <el-form-item label="标题：" prop="title" required>
                <el-input v-model="noticeForm.title" ></el-input>
              </el-form-item>
              <el-form-item label="内容：" prop="content" required>
                <el-input type="textarea" v-model="noticeForm.content" rows="4"></el-input>
              </el-form-item>
            </el-form>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogEditVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="editNotice()">保 存</el-button>
        </span>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'noticeList',
  data(){
    return {
      noticeTableData: [],
      dialogVisible: false,
      dialogEditVisible: false,
      dialogRegisterVisible: false,
      imageUrl: null,
      noticeForm: Object.assign({}, this.defaultNoticeForm),
      defaultNoticeForm: {
        title: '',
        content: '',
      },
      rules: {
        title: [
          {required: true, message: '请输入公告标题'},
        ],
        content: [
          {required: true, message: '请输入公告内容'},
        ],
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
      request.get("/notice",
          {
            params: {
              pageNum: this.pageParam.pageNum,
              pageSize: this.pageParam.pageSize
            }
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //进入主页面
              this.noticeTableData = response.data;
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
      this.noticeForm = Object.assign({}, this.defaultNoticeForm);
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
      this.noticeForm = row;
      console.log(this.noticeForm)
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
    removeNotice(id) {
      request.post("/notice?method=remove&id=" + id)
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
    addNotice() {
      request.post("/notice?method=add", this.noticeForm)
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
    editNotice() {
      request.post("/notice?method=edit", this.noticeForm)
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