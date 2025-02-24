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
        内容：
        <el-input type="text" size="small" v-model="queryParams.content" clearable style="width: 200px"></el-input>
        <el-button plain size="small" @click="search">查询</el-button>
      </div>
    </div>
    <div>
      <el-table :data="indexTableData" style="width: 100%">
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <el-table-column prop="content" label="指标内容" align="center"></el-table-column>
        <el-table-column prop="createtime" label="创建时间" align="center" ></el-table-column>
        <el-table-column label="操作" width="300px" align="center">
          <template slot-scope="scope">
            <el-button
                v-if="true"
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-popconfirm title="确定删除吗？" @confirm="removeIndex(scope.row.id)">
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
        title="添加指标信息"
        :visible.sync="dialogVisible"
        width="30%"
        center>
      <div>
        <div>
          <el-form :model="indexForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="指标内容：" prop="content" required>
              <el-input type="textarea" v-model="indexForm.content" rows="4"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="addIndex()">保 存</el-button>
        </span>
    </el-dialog>

    <el-dialog
        title="修改指标信息"
        :visible.sync="dialogEditVisible"
        width="50%"
        center>
      <div>
        <div>
          <el-form :model="indexForm" :rules="rules" size="small" ref="userForm" label-width="100px">
            <el-form-item label="指标内容：" prop="content" required>
              <el-input type="textarea" v-model="indexForm.content" rows="4"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogEditVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" @click="editIndex()">保 存</el-button>
        </span>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'indexList',
  data(){
    return {
      indexTableData: [],
      dialogVisible: false,
      dialogEditVisible: false,
      dialogRegisterVisible: false,
      imageUrl: null,
      indexForm: Object.assign({}, this.defaultIndexForm),
      defaultIndexForm: {
        content: ''
      },
      rules: {
        content: [
          {required: true, message: '请输入指标内容'},
        ]
      },
      pageParam: {
        pageNum: 1,
        pageSize: 10,
        total: 10
      },
      queryParams: {
        content: ''
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
      request.get("/index",
          {
            params: {
              pageNum: this.pageParam.pageNum,
              pageSize: this.pageParam.pageSize,
              content: this.queryParams.content
            }
          })
          .then(response => {
            console.log(response)
            // 请求成功，获取响应数据
            if (response.code == 200) {
              //进入主页面
              this.indexTableData = response.data;
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
      this.indexForm = Object.assign({}, this.defaultIndexForm);
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
      this.indexForm = row;
      console.log(this.indexForm)
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
    removeIndex(id) {
      request.post("/index?method=remove&id=" + id)
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
    addIndex() {
      request.post("/index?method=add", this.indexForm)
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
    editIndex() {
      request.post("/index?method=edit", this.indexForm)
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