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
      <el-row :gutter="20"  v-loading="tabLoading">
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
                  :width="cols.label == '序号' ? '70px' : ''"
                >
                </el-table-column>
                <el-table-column
                  v-if="classifyActiveIndex == 2"
                  label="图片"
                  prop="picture"
                >
                  <template slot-scope="scope">
                    <el-image
                      v-if="scope.row.imgSrc"
                      class="tab_img"
                      :src="scope.row.imgSrc"
                      :preview-src-list="[scope.row.imgSrc]">
                    </el-image>
                  </template>
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
        <el-form-item v-for="item in editTabCols" :label="item.label" :prop="item.prop" :key="item.prop" :label-width="formLabelWidth">
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
        <el-checkbox v-for="checkItem in editTabCols" :label="checkItem.label"></el-checkbox>
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
      tabLoading:false,
      classifyActiveIndex: 1,
      tableCols: [
        // {field: 'date', title: '日期'},
        // {field: 'name', title: '姓名'},
        // {field: 'address', title: '地址'},
      ],
      editTabCols:[],
      tableData: [
        //   {
        //   date: '2016-05-02',
        //   name: '王小虎',
        //   address: '上海市普陀区金沙江路 1518 弄'
      ],
      enterText: '',
      dialogFormVisible: false,
      form: {

      },
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

        this.tabLoading = true
        firstPage.queryScene(param).then(res => {
          // let res = {"code":200,"msg":"操作成功","data":[{"role":"assistant","content":"[\n    {\n        \"序号\": 1,\n        \"旁白\": \"镜头拉近\",\n        \"描述\": \"广州市内繁忙的街道上，人来人往，车水马龙。\",\n        \"镜头类型\": \"大景\",\n        \"持换时间\": 5,\n        \"详细图⽚内容\": \"街道上车水马龙，人来人往，有车有人，热闹非凡。\"\n    },\n    {\n        \"序号\": 2,\n        \"旁白\": \"深度镜头\",\n        \"描述\": \"镜头深入到人群中，一个青年男子穿过人群，匆匆走过。\",\n        \"镜头类型\": \"中景\",\n        \"持换时间\": 3,\n        \"详细图⽚内容\": \"一个青年男子，身穿白色T恤，黑色牛仔裤，匆匆走过人群。\"\n    },\n    {\n        \"序号\": 3,\n        \"旁白\": \"镜头切换\",\n        \"描述\": \"切换到一个年轻女孩，正在路边等待着什么。\",\n        \"镜头类型\": \"中景\",\n        \"持换时间\": 3,\n        \"详细图⽚内容\": \"一个年轻女孩，穿着一袭白色连衣裙，站在路边，焦急地等着什么。\"\n    },\n    {\n        \"序号\": 4,\n        \"旁白\": \"镜头推进\",\n        \"描述\": \"青年男子远远看见了年轻女孩，加快了脚步。\",\n        \"镜头类型\": \"远景\",\n        \"持换时间\": 3,\n        \"详细图⽚内容\": \"青年男子加快了脚步，远远地看见了年轻女孩。\"\n    },\n    {\n        \"序号\": 5,\n        \"旁白\": \"镜头切换\",\n        \"描述\": \"切换到年轻女孩的表情，她看见了青年男子，露出了微笑。\",\n        \"镜头类型\": \"中景\",\n        \"持换时间\": 3,\n        \"详细图⽚内容\": \"年轻女孩看见了青年男子，露出了微笑。\"\n    },\n    {\n        \"序号\": 6,\n        \"旁白\": \"镜头推进\",\n        \"描述\": \"青年男子跑到年轻女孩面前，两人相视而笑。\",\n        \"镜头类型\": \"中景\",\n        \"持换时间\": 3,\n        \"详细图⽚内容\": \"青年男子跑到年轻女孩面前，两人相视而笑。\"\n    },\n    {\n        \"序号\": 7,\n        \"旁白\": \"镜头切换\",\n        \"描述\": \"切换到两人手牵手漫步在广州塔附近的草坪上的画面。\",\n        \"镜头类型\": \"大景\",\n        \"持换时间\": 5,\n        \"详细图⽚内容\": \"两人手牵手漫步在广州塔附近的草坪上，周围的景色很美。\"\n    },\n    {\n        \"序号\": 8,\n        \"旁白\": \"镜头推进\",\n        \"描述\": \"两人坐在草坪上，拍照留念。\",\n        \"镜头类型\": \"中景\",\n        \"持换时间\": 3,\n        \"详细图⽚内容\": \"两人坐在草坪上，拍照留念。\"\n    },\n    {\n        \"序号\": 9,\n        \"旁白\": \"镜头切换\",\n        \"描述\": \"切换到两人在广州塔上共度浪漫时光的画面。\",\n        \"镜头类型\": \"大景\",\n        \"持换时间\": 5,\n        \"详细图⽚内容\": \"两人在广州塔上共度浪漫时光，眺望着远处的美景。\"\n    },\n    {\n        \"序号\": 10,\n        \"旁白\": \"镜头推进\",\n        \"描述\": \"两人在塔顶手牵手，虽然人群熙熙攘攘，但彼此感觉到的只有对方。\",\n        \"镜头类型\": \"中景\",\n        \"持换时间\": 5,\n        \"详细图⽚内容\": \"两人在塔顶手牵手，周围人群熙熙攘攘，但彼此感觉到的只有对方。\"\n    }\n]"}]}

          let tabCols = [];
          let editCols = []
          let tabData = []
          let formParam = {};
          if(res.data && res.data.length) {
            let data = res.data[0].content
            if(data) {
              data = JSON.parse(data)

              if(data && data.length) {
                for(let x in data[0]) {
                  tabCols.push({prop:x,label:x})
                  formParam[x] = ''
                  if(x != '序号') {
                    editCols.push({prop:x,label:x})
                  }
                }

                for(let x=0;x<data.length;x++) {
                  data[x].imgSrc = ''
                  // data[x].imgSrc = 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201801%2F11%2F20180111195541_u5SWV.thumb.1000_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1687686395&t=50aea0a429593c05ae58ff3141df0027'
                }

                tabData = data;
              }
              this.tableCols = tabCols
              this.tableData = tabData
              this.editTabCols = editCols
            }
          }
        }).catch(() => {
          this.tableCols = []
          this.tableData = []
          this.editTabCols = []
        }).finally(() => {
          this.tabLoading = false
        })
      }
    },
    showEdit(data) {
      this.editData = data
      this.dialogFormVisible = true
      for(let x=0;x<this.editTabCols.length;x++) {
        this.$set(this.form, this.editTabCols[x].prop, data[this.editTabCols[x].prop]);
      }
    },
    confirmEdit() {
      this.tableData.map(item => {
        if(item['序号'] == this.editData['序号']) {
          for(let x in this.form) {
            item[x] = this.form[x]
          }
        }
      })
      this.dialogFormVisible = false
    },
    showSubmit(data) {
      this.checkList = []
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

        this.tabLoading = true;
        firstPage.querySd(params).then(res => {
          for(let i=0;i<this.tableData.length;i++) {
            if(this.tableData[i]['序号'] == this.editData['序号']) {
              if(res.data.images) {
                this.tableData[i].imgSrc = 'data:image/png;base64,'+res.data.images[0]
              }
            }
          }
        }).finally(() => {
          this.tabLoading = false;
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

                .el-table .tab_img {
                  width: 100px;
                  max-height: 100px;
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

