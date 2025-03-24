<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','health_title') || $check_field('add','health_title') || $check_field('set','health_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="养生标题" prop="health_title">
												<el-input id="health_title" v-model="form['health_title']" placeholder="请输入养生标题"
							  v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','health_title')) || (!form['dietary_health_preservation_id'] && $check_field('add','health_title'))" :disabled="disabledObj['health_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','health_title')">{{form['health_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','type_of_health_preservation') || $check_field('add','type_of_health_preservation') || $check_field('set','type_of_health_preservation')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="养生类型" prop="type_of_health_preservation">
												<el-input id="type_of_health_preservation" v-model="form['type_of_health_preservation']" placeholder="请输入养生类型"
							  v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','type_of_health_preservation')) || (!form['dietary_health_preservation_id'] && $check_field('add','type_of_health_preservation'))" :disabled="disabledObj['type_of_health_preservation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','type_of_health_preservation')">{{form['type_of_health_preservation']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo') || $check_field('add','cover_photo') || $check_field('set','cover_photo')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图片" prop="cover_photo">
								<el-upload :disabled="disabledObj['cover_photo_isDisabled']" id="cover_photo" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo"
						:show-file-list="false" v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','cover_photo')) || (!form['dietary_health_preservation_id'] && $check_field('add','cover_photo'))">
						<img v-if="form['cover_photo']" :src="$fullUrl(form['cover_photo'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_photo')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_photo'])" :preview-src-list="[$fullUrl(form['cover_photo'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','release_date') || $check_field('add','release_date') || $check_field('set','release_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布日期" prop="release_date">
								<el-date-picker :disabled="disabledObj['release_date_isDisabled']" v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','release_date')) || (!form['dietary_health_preservation_id'] && $check_field('add','release_date'))" id="release_date"
						v-model="form['release_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_date')">{{form['release_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','health_preservation_methods') || $check_field('add','health_preservation_methods') || $check_field('set','health_preservation_methods')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="养生方法" prop="health_preservation_methods">
								<el-input type="textarea" id="health_preservation_methods" v-model="form['health_preservation_methods']" placeholder="请输入养生方法"
						v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','health_preservation_methods')) || (!form['dietary_health_preservation_id'] && $check_field('add','health_preservation_methods'))" :disabled="disabledObj['health_preservation_methods_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','health_preservation_methods')">{{form['health_preservation_methods']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','health_preservation_plan') || $check_field('add','health_preservation_plan') || $check_field('set','health_preservation_plan')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="养生方案" prop="health_preservation_plan">
								<el-input type="textarea" id="health_preservation_plan" v-model="form['health_preservation_plan']" placeholder="请输入养生方案"
						v-if="user_group === '管理员' || (form['dietary_health_preservation_id'] && $check_field('set','health_preservation_plan')) || (!form['dietary_health_preservation_id'] && $check_field('add','health_preservation_plan'))" :disabled="disabledObj['health_preservation_plan_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','health_preservation_plan')">{{form['health_preservation_plan']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

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
										"release_date":  '', // 发布日期
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


	
	
			
	
						/**
			 * 上传封面图片
			 * @param {Object} param 图片参数
			 */
			upload_cover_photo(param){
						this.uploadFile(param.file, "cover_photo");
					},
	
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
								        if (this.form["release_date"].indexOf("-")===-1){
          this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
        }
									$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


											        if(this.form["release_date"]=="0000-00-00"){
          this.form["release_date"] = null;
        }
				if(parseInt(this.form["release_date"]) > 9999){
					this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
				}
								

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																								return msg;
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
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
														},
	}
</script>

<style>
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

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
