<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','title_name') || $check_field('add','title_name') || $check_field('set','title_name')" label="标题名称" name="title_name">
                            <uni-easyinput type="text" v-model="form['title_name']" v-if="user_group === '管理员' || (form['disease_control_id'] && $check_field('set','title_name')) || (!form['disease_control_id'] && $check_field('add','title_name'))" :disabled="disabledObj['title_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','title_name')">
                  {{ form['title_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','disease_type') || $check_field('add','disease_type') || $check_field('set','disease_type')" label="疾病类型" name="disease_type">
                            <uni-easyinput type="text" v-model="form['disease_type']" v-if="user_group === '管理员' || (form['disease_control_id'] && $check_field('set','disease_type')) || (!form['disease_control_id'] && $check_field('add','disease_type'))" :disabled="disabledObj['disease_type_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','disease_type')">
                  {{ form['disease_type'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','symptom_response') || $check_field('add','symptom_response') || $check_field('set','symptom_response')" label="症状反应" name="symptom_response">
                            <uni-easyinput type="text" v-model="form['symptom_response']" v-if="user_group === '管理员' || (form['disease_control_id'] && $check_field('set','symptom_response')) || (!form['disease_control_id'] && $check_field('add','symptom_response'))" :disabled="disabledObj['symptom_response_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','symptom_response')">
                  {{ form['symptom_response'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','recommended_measure') || $check_field('add','recommended_measure') || $check_field('set','recommended_measure')" label="建议措施" name="recommended_measure">
                    <uni-easyinput type="textarea" v-model="form['recommended_measure']" v-if="user_group === '管理员' || (form['disease_control_id'] && $check_field('set','recommended_measure')) || (!form['disease_control_id'] && $check_field('add','recommended_measure'))" :disabled="disabledObj['recommended_measure_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','recommended_measure')">
                  {{ form['recommended_measure'] }}
                </text>
                  </uni-forms-item>


            </uni-forms>
            <view class="form_button">
              <button size="mini" type="primary" @click="submit()" class="primary_btn">提交</button>
              <button size="mini" @click="cancel()">取消</button>
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import mixin from "@/libs/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      field: "disease_control_id",
      url_add: "~/api/disease_control/add?",
      url_set: "~/api/disease_control/set?",
      url_get_obj: "~/api/disease_control/get_obj?",
      url_upload: "~/api/disease_control/upload?",

      query: {
        "disease_control_id": 0,
      },

      form: {
            "title_name":  '', // 标题名称
                    "disease_type":  '', // 疾病类型
                    "symptom_response":  '', // 症状反应
                    "recommended_measure":  '', // 建议措施
                                "disease_control_id": 0, // ID
                
              },
          disabledObj:{
                        "title_name_isDisabled": false,
                                "disease_type_isDisabled": false,
                                "symptom_response_isDisabled": false,
                                "recommended_measure_isDisabled": false,
                                  },
                                                                                                      }
  },
  methods: {
    changeLog(v,value){
      this.form[value] = v
    },
    /**
     * 上传文件
     * @param {Object} param文件参数
     */
    change_file(key_name){
      var _self = this;
      // 选择图像方法
      uni.chooseFile({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/disease_control/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 上传图片
     * @param {Object} param文件参数
     */
    change_img(key_name){
      var _self = this;
      _self.upload_img_flag = false
      // 选择图像方法
      uni.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/disease_control/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 获取对象后获取缓存表单
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_before(param){
      var form = uni.db.get("form");
      if (form) {
        delete(form.examine_state)
        delete(form.examine_reply)
        this.obj = uni.push(this.obj ,form);
        this.form = uni.push(this.form ,form);
      }
      var arr = []
      for (let key in form) {
        arr.push(key)
      }
      for (var i=0;i<arr.length;i++){
        this.disabledObj[arr[i] + '_isDisabled'] = true
      }
                                      uni.db.del("form");
      return param;
    },
        
            
            
            
    
    /**
     * 获取对象之后
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_after(json, func){
                                    },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/disease_control/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/disease_control/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/disease_control/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/disease_control/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/disease_control/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
                                  },
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.form_button{
  padding-bottom: 15px;
  display: flex;
}
.form_button button{
  width: 40%;
}
.query_select{
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline:none;
}

.query_option{
  width: 100%;
}

.btn_add_img{
  color: #D3D3D3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text{
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.form_button {
  padding-bottom: 15px;
  display: flex;
}
.form_button button {
  width: 40%;
}
.query_select {
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline: none;
}

.query_option {
  width: 100%;
}

.btn_add_img {
  color: #d3d3d3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text {
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
/*新样式*/
.uni-forms{
padding-top:1rem;
}
.uni-forms-item {
	padding: 6px 10px;
    background: #f8f6fc;
}
.uni-forms .is-input-border{
	border: 0;
}
.container{
	    -webkit-box-shadow: 0px 0px 0px #888888;
	    box-shadow: 0px 0px 0px #888888;
}
.form_button .primary_btn{
		background-color: #22B8B8;
		color: #FFFFFF;
	}
</style>
