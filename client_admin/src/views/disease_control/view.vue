<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','title_name') || $check_field('add','title_name') || $check_field('set','title_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="标题名称" prop="title_name">
												<el-input id="title_name" v-model="form['title_name']" placeholder="请输入标题名称"
							  v-if="user_group === '管理员' || (form['disease_control_id'] && $check_field('set','title_name')) || (!form['disease_control_id'] && $check_field('add','title_name'))" :disabled="disabledObj['title_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title_name')">{{form['title_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','disease_type') || $check_field('add','disease_type') || $check_field('set','disease_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="疾病类型" prop="disease_type">
												<el-input id="disease_type" v-model="form['disease_type']" placeholder="请输入疾病类型"
							  v-if="user_group === '管理员' || (form['disease_control_id'] && $check_field('set','disease_type')) || (!form['disease_control_id'] && $check_field('add','disease_type'))" :disabled="disabledObj['disease_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','disease_type')">{{form['disease_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','symptom_response') || $check_field('add','symptom_response') || $check_field('set','symptom_response')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="症状反应" prop="symptom_response">
												<el-input id="symptom_response" v-model="form['symptom_response']" placeholder="请输入症状反应"
							  v-if="user_group === '管理员' || (form['disease_control_id'] && $check_field('set','symptom_response')) || (!form['disease_control_id'] && $check_field('add','symptom_response'))" :disabled="disabledObj['symptom_response_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','symptom_response')">{{form['symptom_response']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','recommended_measure') || $check_field('add','recommended_measure') || $check_field('set','recommended_measure')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="建议措施" prop="recommended_measure">
								<el-input type="textarea" id="recommended_measure" v-model="form['recommended_measure']" placeholder="请输入建议措施"
						v-if="user_group === '管理员' || (form['disease_control_id'] && $check_field('set','recommended_measure')) || (!form['disease_control_id'] && $check_field('add','recommended_measure'))" :disabled="disabledObj['recommended_measure_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','recommended_measure')">{{form['recommended_measure']}}</div>
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
												$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


												

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
