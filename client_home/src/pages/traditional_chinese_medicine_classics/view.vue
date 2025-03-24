<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','name_of_classics') || $check_field('add','name_of_classics') || $check_field('set','name_of_classics')" label="典籍名称" name="name_of_classics">
                            <uni-easyinput type="text" v-model="form['name_of_classics']" v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','name_of_classics')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','name_of_classics'))" :disabled="disabledObj['name_of_classics_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','name_of_classics')">
                  {{ form['name_of_classics'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','types_of_classics') || $check_field('add','types_of_classics') || $check_field('set','types_of_classics')" label="典籍类型" name="types_of_classics">
                            <uni-easyinput type="text" v-model="form['types_of_classics']" v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','types_of_classics')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','types_of_classics'))" :disabled="disabledObj['types_of_classics_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','types_of_classics')">
                  {{ form['types_of_classics'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','author_name') || $check_field('add','author_name') || $check_field('set','author_name')" label="作者名称" name="author_name">
                            <uni-easyinput type="text" v-model="form['author_name']" v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','author_name')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','author_name'))" :disabled="disabledObj['author_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','author_name')">
                  {{ form['author_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','picture_of_classics') || $check_field('add','picture_of_classics') || $check_field('set','picture_of_classics')" label="典籍图片" name="picture_of_classics">
                    <!-- 修改权限 -->
                <view class="diy_field diy_img" v-if="form['picture_of_classics'] && $check_field('set','picture_of_classics')">
                  <image v-if="disabledObj['picture_of_classics_isDisabled']" :src="$fullUrl(form['picture_of_classics'])" />
                  <image v-if="!disabledObj['picture_of_classics_isDisabled']" :src="$fullUrl(form['picture_of_classics'])" @click="change_img('picture_of_classics')" />
                </view>
                <!-- 添加权限 -->
                <view class="diy_field diy_img" v-else-if="!form['picture_of_classics'] && $check_field('add','picture_of_classics')">
                  <view v-if="disabledObj['picture_of_classics_isDisabled']" class="btn_add_img">
                    <text>+</text>
                  </view>
                  <view v-if="!disabledObj['picture_of_classics_isDisabled']" class="btn_add_img" @click="change_img('picture_of_classics')">
                    <text>+</text>
                  </view>
                </view>
                <!-- 查询权限 -->
                <view class="diy_field diy_img" v-else-if="$check_field('get','picture_of_classics')">
                  <image :src="$fullUrl(form['picture_of_classics'])" />
                </view>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','appendix_to_classic_books') || $check_field('add','appendix_to_classic_books') || $check_field('set','appendix_to_classic_books')" label="典籍附件" name="appendix_to_classic_books">
                    <!-- 查询权限 -->
                <view class="diy_field diy_file" v-if="$check_field('get','appendix_to_classic_books')">
                  <button @click="download_file($fullUrl(form['appendix_to_classic_books']))" class="btn" v-if="form['appendix_to_classic_books']">下载文件</button>
                </view>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','introduction_to_classical_books') || $check_field('add','introduction_to_classical_books') || $check_field('set','introduction_to_classical_books')" label="典籍简介" name="introduction_to_classical_books">
                    <uni-easyinput type="textarea" v-model="form['introduction_to_classical_books']" v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','introduction_to_classical_books')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','introduction_to_classical_books'))" :disabled="disabledObj['introduction_to_classical_books_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','introduction_to_classical_books')">
                  {{ form['introduction_to_classical_books'] }}
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
      field: "traditional_chinese_medicine_classics_id",
      url_add: "~/api/traditional_chinese_medicine_classics/add?",
      url_set: "~/api/traditional_chinese_medicine_classics/set?",
      url_get_obj: "~/api/traditional_chinese_medicine_classics/get_obj?",
      url_upload: "~/api/traditional_chinese_medicine_classics/upload?",

      query: {
        "traditional_chinese_medicine_classics_id": 0,
      },

      form: {
            "name_of_classics":  '', // 典籍名称
                    "types_of_classics":  '', // 典籍类型
                    "author_name":  '', // 作者名称
                    "picture_of_classics":  '', // 典籍图片
                    "appendix_to_classic_books":  '', // 典籍附件
                    "introduction_to_classical_books":  '', // 典籍简介
                                "traditional_chinese_medicine_classics_id": 0, // ID
                
              },
          disabledObj:{
                        "name_of_classics_isDisabled": false,
                                "types_of_classics_isDisabled": false,
                                "author_name_isDisabled": false,
                                "picture_of_classics_isDisabled": false,
                                "appendix_to_classic_books_isDisabled": false,
                                "introduction_to_classical_books_isDisabled": false,
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
            url: _self.$fullUrl('/api/traditional_chinese_medicine_classics/upload?'),
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
            url: _self.$fullUrl('/api/traditional_chinese_medicine_classics/upload?'),
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
        bl = this.$check_action('/traditional_chinese_medicine_classics/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/traditional_chinese_medicine_classics/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/traditional_chinese_medicine_classics/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/traditional_chinese_medicine_classics/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/traditional_chinese_medicine_classics/view','get');
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
