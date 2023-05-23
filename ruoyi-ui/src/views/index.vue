<template>
  <div class="app-container home">
    <div class="classify_wrap">
      <div :class="['classify_cont',classifyActiveIndex == 1? 'classify_active' : '']" @click="classifyActiveIndex = 1">
        <h4>GPT</h4>
        <p>GPT生成文档</p>
      </div>
      <div :class="['classify_cont',classifyActiveIndex == 2? 'classify_active' : '']" @click="classifyActiveIndex = 2">
        <h4>SD</h4>
        <p>stable diffusion 模型</p>
      </div>
    </div>
    <div class="main_wrap">

      <!--      <el-card>-->
      <el-row :gutter="20">
        <el-col :span="8" v-show="classifyActiveIndex == 1">
          <el-card>
            <div class="grid-content bg-purple">
              <h4 class="default_title">输入</h4>
              <el-input
                type="textarea"
                resize="none"
                v-model="enterText"
                placeholder="请输入内容"
              ></el-input>
              <div style="text-align: right">
                <el-button size="small" @click="enterText = ''">清空</el-button>
                <el-button size="small" type="primary" @click="queryData">确定</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="classifyActiveIndex == 1 ? 16 : 24">
          <el-card>
            <div class="grid-content bg-purple">
              <h4 class="default_title">{{classifyActiveIndex == 1 ? '输出' : '模型'}}</h4>
              <div style="text-align: center;margin-top:50px;" v-if="!tableCols.length">
                <img src="../assets/images/empty.png"/>
                <div style="margin-top:10px;">
                  <span style="color:rgb(139 148 159);font-size: 14px;" v-if="classifyActiveIndex == 1">在左侧输入描述，创建你的AI绘画作品</span>
                  <span style="color:rgb(139 148 159);font-size: 14px;" v-if="classifyActiveIndex == 2">当前暂无任何模型，请在GPT中创建</span>
                </div>
              </div>
              <el-table
                v-else
                :data="tableData"
                stripe
                height="100%"
                style="width: 100%">
                <el-table-column
                  v-for="cols in tableCols"
                  :prop="cols.prop"
                  :label="cols.label"
                >
                </el-table-column>
                <el-table-column
                  v-if="classifyActiveIndex == 2"
                  label="图片"
                  prop="picture"
                >
                </el-table-column>
                <el-table-column
                  v-if="classifyActiveIndex == 2"
                  label="操作"
                  width="110">
                  <template slot-scope="scope">
                    <el-button @click="showSubmit(scope.row)" type="text" size="small">生成图片</el-button>
                    <el-button @click="showEdit(scope.row)" type="text" size="small">编辑</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <!--      </el-card>-->
    </div>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item v-for="item in tableCols" :label="item.label" :label-width="formLabelWidth">
          <el-input v-model="form[item.prop]" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmEdit">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="勾选生成图片的条件" :visible.sync="dialogSubmitVisible">
      <el-checkbox-group v-model="checkList">
        <el-checkbox v-for="checkItem in tableCols" :label="checkItem.label"></el-checkbox>
      </el-checkbox-group>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogSubmitVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import firstPage from '@/api/firstPage/index'
export default {
  name: "Index",
  data() {
    return {
      classifyActiveIndex: 1,
      tableCols: [
        // {field: 'date', title: '日期'},
        // {field: 'name', title: '姓名'},
        // {field: 'address', title: '地址'},
      ],
      tableData: [
        //   {
        //   date: '2016-05-02',
        //   name: '王小虎',
        //   address: '上海市普陀区金沙江路 1518 弄'
        // }, {
        //   date: '2016-05-04',
        //   name: '王小虎',
        //   address: '上海市普陀区金沙江路 1517 弄'
        // }, {
        //   date: '2016-05-01',
        //   name: '王小虎',
        //   address: '上海市普陀区金沙江路 1519 弄'
        // }, {
        //   date: '2016-05-03',
        //   name: '王小虎',
        //   address: '上海市普陀区金沙江路 1516 弄'
        // }
      ],
      enterText: '',
      dialogFormVisible: false,
      form: {},
      formLabelWidth: '120px',
      dialogSubmitVisible: false,
      checkList: [],
      editData:{},
    };
  },
  methods: {
    queryData() {
      if (this.enterText == '') {
        this.$message({
          message: '请输入查询内容',
          type: 'warning'
        });
      } else {
        let param = {
          prompt:this.enterText
        }

        firstPage.queryScene(param).then(res => {
          let tabCols = [];
          let tabData = []
          let formParam = {};
          if(res.data && res.data.length) {
            let data = res.data[0].content
            if(data) {
              data = JSON.parse(data)

              if(data && data.length) {
                tabData = data;
                for(let x in data[0]) {
                  tabCols.push({prop:x,label:x})
                  formParam[x] = ''
                }
              }
              this.tableCols = tabCols
              this.tableData = tabData
            }
          }
        })
      }
    },
    showEdit(data) {
      this.dialogFormVisible = true
      for (let x in data) {
        this.form[x] = data[x]
      }
      console.log(this.tableCols)
      console.log(this.form)
    },
    confirmEdit() {
      console.log(this.form)
      this.dialogFormVisible = false
    },
    showSubmit(data) {
      this.editData = data
      this.dialogSubmitVisible = true;
    },
    confirmSubmit() {
      if (!this.checkList.length) {
        this.$message({
          message: '至少选择一个条件',
          type: 'warning'
        });
      } else {
        this.dialogSubmitVisible = false;
        let contArr = [];
        if(this.checkList && this.checkList.length) {
          for(let i=0;i<this.checkList.length;i++) {
            contArr.push(this.editData[this.checkList[i]])
          }
        }
        contArr = contArr.join(',')
        let params = {
          prompt:contArr
        }
        firstPage.querySd(params).then(res => {
          console.log(res)
        })
      }

    }
  }
};
</script>

<style scoped lang="scss">
.app-container {
  height: 100%;

  .classify_wrap {
    margin-bottom: 10px;

    .classify_cont {
      display: inline-block;
      vertical-align: middle;
      padding: 16px;
      width: 300px;
      border: 1px solid #ccc;
      border-radius: 4px;
      margin-right: 15px;
      cursor: pointer;


      h4 {
        font-size: 16px;
        font-weight: bold;
        color: #101010;
        margin: 5px 0px;
      }

      p {
        font-size: 12px;
        color: #666;
      }
    }

    .classify_active {
      border: 1px solid #6691e8;
      background-color: rgba(102, 145, 232, 0.1);
      box-shadow: 2px 2px 5px #3398E14C;

      h4 {
        color: #4b86ff;
      }

      p {
        color: #6691e8;
      }
    }
  }

  .main_wrap {
    height: calc(100% - 100px);

    ::v-deep {

      .el-row {
        height: 100%;

        .el-col {
          height: 100%;

          .el-card {
            height: 100%;

            .el-card__body {
              height: 100%;

              .grid-content {
                height: 100%;

                .default_title {
                  font-weight: bold;
                  font-size: 14px;
                  margin-bottom: 10px;
                  color:#333333;
                }

                .el-textarea {
                  height: calc(100% - 65px);
                  margin-bottom: 10px;

                  .el-textarea__inner {
                    height: 100%;
                  }
                }

                .el-table .el-table__header-wrapper th {
                  background-color: #e3effb;
                }

              }
            }
          }
        }
      }
    }
  }
}
</style>

