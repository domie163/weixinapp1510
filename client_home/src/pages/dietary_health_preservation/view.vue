<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','health_title') || $check_field('add','health_title') || $check_field('set','health_title')" label="养生标题" name="health_title">
                            <uni-easyinput type="text" v-model="form['health_title']" v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','health_title')) || (!form['dietary_health_preservation_id'] && $check_field('add','health_title'))" :disabled="disabledObj['health_title_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','health_title')">
                  {{ form['health_title'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','type_of_health_preservation') || $check_field('add','type_of_health_preservation') || $check_field('set','type_of_health_preservation')" label="养生类型" name="type_of_health_preservation">
                            <uni-easyinput type="text" v-model="form['type_of_health_preservation']" v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','type_of_health_preservation')) || (!form['dietary_health_preservation_id'] && $check_field('add','type_of_health_preservation'))" :disabled="disabledObj['type_of_health_preservation_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','type_of_health_preservation')">
                  {{ form['type_of_health_preservation'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','cover_photo') || $check_field('add','cover_photo') || $check_field('set','cover_photo')" label="封面图片" name="cover_photo">
                    <!-- 修改权限 -->
                <view class="diy_field diy_img" v-if="form['cover_photo'] && $check_field('set','cover_photo')">
                  <image v-if="disabledObj['cover_photo_isDisabled']" :src="$fullUrl(form['cover_photo'])" />
                  <image v-if="!disabledObj['cover_photo_isDisabled']" :src="$fullUrl(form['cover_photo'])" @click="change_img('cover_photo')" />
                </view>
                <!-- 添加权限 -->
                <view class="diy_field diy_img" v-else-if="!form['cover_photo'] && $check_field('add','cover_photo')">
                  <view v-if="disabledObj['cover_photo_isDisabled']" class="btn_add_img">
                    <text>+</text>
                  </view>
                  <view v-if="!disabledObj['cover_photo_isDisabled']" class="btn_add_img" @click="change_img('cover_photo')">
                    <text>+</text>
                  </view>
                </view>
                <!-- 查询权限 -->
                <view class="diy_field diy_img" v-else-if="$check_field('get','cover_photo')">
                  <image :src="$fullUrl(form['cover_photo'])" />
                </view>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','release_date') || $check_field('add','release_date') || $check_field('set','release_date')" label="发布日期" name="release_date">
                    <uni-datetime-picker v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','release_date')) || (!form['dietary_health_preservation_id'] && $check_field('add','release_date'))" v-model="form['release_date']" type="date" :disabled="disabledObj['release_date_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','release_date')">
                  {{ form['release_date'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','health_preservation_methods') || $check_field('add','health_preservation_methods') || $check_field('set','health_preservation_methods')" label="养生方法" name="health_preservation_methods">
                    <uni-easyinput type="textarea" v-model="form['health_preservation_methods']" v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','health_preservation_methods')) || (!form['dietary_health_preservation_id'] && $check_field('add','health_preservation_methods'))" :disabled="disabledObj['health_preservation_methods_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','health_preservation_methods')">
                  {{ form['health_preservation_methods'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','health_preservation_plan') || $check_field('add','health_preservation_plan') || $check_field('set','health_preservation_plan')" label="养生方案" name="health_preservation_plan">
                    <uni-easyinput type="textarea" v-model="form['health_preservation_plan']" v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','health_preservation_plan')) || (!form['dietary_health_preservation_id'] && $check_field('add','health_preservation_plan'))" :disabled="disabledObj['health_preservation_plan_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','health_preservation_plan')">
                  {{ form['health_preservation_plan'] }}
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
      field: "dietary_health_preservation_id",
      url_add: "~/api/dietary_health_preservation/add?",
      url_set: "~/api/dietary_health_preservation/set?",
      url_get_obj: "~/api/dietary_health_preservation/get_obj?",
      url_upload: "~/api/dietary_health_preservation/upload?",

      query: {
        "dietary_health_preservation_id": 0,
      },

      form: {
            "health_title":  '', // 养生标题
                    "type_of_health_preservation":  '', // 养生类型
                    "cover_photo":  '', // 封面图片
                    "release_date": this.$toTime(new Date().getTime(), "yyyy-MM-dd"),
                    "health_preservation_methods":  '', // 养生方法
                    "health_preservation_plan":  '', // 养生方案
                                "dietary_health_preservation_id": 0, // ID
                
              },
          disabledObj:{
                        "health_title_isDisabled": false,
                                "type_of_health_preservation_isDisabled": false,
                                "cover_photo_isDisabled": false,
                                "release_date_isDisabled": false,
                                "health_preservation_methods_isDisabled": false,
                                "health_preservation_plan_isDisabled": false,
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
            url: _self.$fullUrl('/api/dietary_health_preservation/upload?'),
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
            url: _self.$fullUrl('/api/dietary_health_preservation/upload?'),
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
                                      if (this.form["release_date"] && JSON.stringify(this.form["release_date"]).indexOf("-")===-1) {
        this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]), "yyyy-MM-dd")
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
                                      if (this.form["release_date"] && JSON.stringify(this.form["release_date"]).indexOf("-")===-1) {
        this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
      }
                        },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/dietary_health_preservation/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/dietary_health_preservation/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/dietary_health_preservation/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/dietary_health_preservation/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/dietary_health_preservation/view','get');
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
