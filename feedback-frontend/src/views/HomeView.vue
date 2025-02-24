<template>
  <div>
    <div class="head">
      <h3>系统公告</h3>
    </div>
    <div class="content">
      <ul>
        <li v-for="item in noticeTableData">
          <div style="display: flex; justify-content: left">
            <div class="title" @click="openDetail(item)">{{ item.title }}</div>
            <span class="createTime">{{ item.createTime }}</span>
          </div>
        </li>
      </ul>
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
        :title="notice.title"
        :visible.sync="dialogVisible"
        width="30%"
        center>
      <div>
        <div>{{notice.content}}</div>
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
  name: 'HomeView',
  data() {
    return {
      dialogVisible: false,
      noticeTableData: [],
      notice: '',
      pageParam: {
        pageNum: 1,
        pageSize: 10,
        total: 10
      },
    }
  },
  created() {
    this.getData();
  },
  methods: {
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
    openDetail(item) {
      this.notice = item;
      this.dialogVisible = true;
    }
  }
}
</script>

<style>
ul li {
  margin: 10px;
  cursor: pointer;
}

.title {
  display: inline-block;
  overflow: hidden;
  width: 400px;
}

.createTime {
  color: #545C64;
  font-size: 14px;
}
</style>