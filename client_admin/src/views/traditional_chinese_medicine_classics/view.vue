<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','name_of_classics') || $check_field('add','name_of_classics') || $check_field('set','name_of_classics')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="典籍名称" prop="name_of_classics">
												<el-input id="name_of_classics" v-model="form['name_of_classics']" placeholder="请输入典籍名称"
							  v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','name_of_classics')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','name_of_classics'))" :disabled="disabledObj['name_of_classics_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_classics')">{{form['name_of_classics']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','types_of_classics') || $check_field('add','types_of_classics') || $check_field('set','types_of_classics')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="典籍类型" prop="types_of_classics">
												<el-input id="types_of_classics" v-model="form['types_of_classics']" placeholder="请输入典籍类型"
							  v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','types_of_classics')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','types_of_classics'))" :disabled="disabledObj['types_of_classics_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','types_of_classics')">{{form['types_of_classics']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','author_name') || $check_field('add','author_name') || $check_field('set','author_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="作者名称" prop="author_name">
												<el-input id="author_name" v-model="form['author_name']" placeholder="请输入作者名称"
							  v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','author_name')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','author_name'))" :disabled="disabledObj['author_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','author_name')">{{form['author_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','picture_of_classics') || $check_field('add','picture_of_classics') || $check_field('set','picture_of_classics')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="典籍图片" prop="picture_of_classics">
								<el-upload :disabled="disabledObj['picture_of_classics_isDisabled']" id="picture_of_classics" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_picture_of_classics"
						:show-file-list="false" v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','picture_of_classics')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','picture_of_classics'))">
						<img v-if="form['picture_of_classics']" :src="$fullUrl(form['picture_of_classics'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','picture_of_classics')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['picture_of_classics'])" :preview-src-list="[$fullUrl(form['picture_of_classics'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','appendix_to_classic_books') || $check_field('add','appendix_to_classic_books') || $check_field('set','appendix_to_classic_books')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="典籍附件" prop="appendix_to_classic_books">
												<div v-if="disabledObj['appendix_to_classic_books_isDisabled']">
						<div v-if="$check_field('get','appendix_to_classic_books')">
							<el-button type="primary" @click="$download($fullUrl(form['appendix_to_classic_books']),form['appendix_to_classic_books'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','appendix_to_classic_books')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','appendix_to_classic_books'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_appendix_to_classic_books" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','appendix_to_classic_books')">
							<el-button type="primary" @click="$download($fullUrl(form['appendix_to_classic_books']),form['appendix_to_classic_books'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','introduction_to_classical_books') || $check_field('add','introduction_to_classical_books') || $check_field('set','introduction_to_classical_books')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="典籍简介" prop="introduction_to_classical_books">
								<el-input type="textarea" id="introduction_to_classical_books" v-model="form['introduction_to_classical_books']" placeholder="请输入典籍简介"
						v-if="user_group === '管理员' || (form['traditional_chinese_medicine_classics_id'] && $check_field('set','introduction_to_classical_books')) || (!form['traditional_chinese_medicine_classics_id'] && $check_field('add','introduction_to_classical_books'))" :disabled="disabledObj['introduction_to_classical_books_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','introduction_to_classical_books')">{{form['introduction_to_classical_books']}}</div>
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


	
	
			
	
			
	
						/**
			 * 上传典籍图片
			 * @param {Object} param 图片参数
			 */
			upload_picture_of_classics(param){
						this.uploadFile(param.file, "picture_of_classics");
					},
	
	
						/**
			 * 上传典籍附件
			 * @param {Object} param 文件参数
			 */
			upload_appendix_to_classic_books(param){
						this.uploadFile(param.file, "appendix_to_classic_books");
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
