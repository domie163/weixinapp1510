<template>
	<view class="diy_details diy_div_traditional_chinese_medicine_classics">
		<view v-if="Object.keys(obj).length!==0" class="warp">
			<view class="container">
				<view class="row">
					<view v-if="$check_field('get','name_of_classics')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>典籍名称:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["name_of_classics"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','types_of_classics')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>典籍类型:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["types_of_classics"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','author_name')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>作者名称:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["author_name"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','picture_of_classics')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>典籍图片:</span>
						</view>
							<view class="diy_field diy_img">
							<image style="width:100%;height:auto;" :src="$fullUrl(obj['picture_of_classics'])" mode="widthFix"></image>
						</view>
						</view>
					<view v-if="$check_field('get','appendix_to_classic_books')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>典籍附件:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["appendix_to_classic_books"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','introduction_to_classical_books')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>典籍简介:</span>
						</view>
							<view class="diy_field diy_desc">
							<text>
								{{ obj["introduction_to_classical_books"] }}
							</text>
						</view>
						</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import mixin from "@/libs/mixins/component.js";
	export default {
		mixins: [mixin],
		props: {
			obj_goods: {
				type: Object,
				default(){
					return {}
				}
			},
			query:{
				type: Object,
				default(){
					return {
						traditional_chinese_medicine_classics_id: 0
					}
				}
			}
		},
		data() {
			return {
				obj_goods_type: {},
				obj: {},
									}
		},
		methods: {
			async get_obj_goods_type() {
				var res = await this.$get("~/api/goods_type/get_obj", {
					name: this.obj_goods.type
				})

				if (res.result.obj) {
					this.obj_goods_type = res.result.obj
				} else {
					console.log("没有请求到商品分类");
				}
			},
			async get_obj_by_goods() {
				var {
					source_table,
					source_field
				} = this.obj_goods_type
				var {
					source_id
				} = this.obj_goods
				var query = {}
				query[source_field] = source_id
				this.$get("~/api/" + source_table + "/get_obj", {}, (res) => {
					if (res.result.obj) {
						this.obj = res.result.obj
					} else {
						console.log("没有请求到商品分类");
					}
				})
			},
			async get_obj_by_id(){
				var res = await this.$get("~/api/traditional_chinese_medicine_classics/get_obj", {
					traditional_chinese_medicine_classics_id:this.query.traditional_chinese_medicine_classics_id
				})

				if (res.result && res.result.obj) {
					this.obj = res.result.obj
				} else {
					console.log("没有请求到商品分类");
				}
			},
								},
		created() {
								},
		async onLoad(){
			if (Object.keys(this.obj_goods).length !== 0) {
				await this.get_obj_goods_type();
				await this.get_obj_by_goods();
			} else if (this.query["traditional_chinese_medicine_classics_id"] !==0) {
				await this.get_obj_by_id();
			}
		}
	}
</script>

<style scoped>
	.div_traditional_chinese_medicine_classics_details {}

	.details {
		background-color: #fff;
		margin-bottom: 0.5rem;
		padding: 1rem;
		font-size: 10px;
	}

	.item {
		display: flex;
		padding: 0.2rem 0;
		border-bottom: 1px solid #eee;
	}

	.left_text {
		flex: 0 0 25%;
	}

	.right_text {
		flex: 0 0 75%;
	}
	.edit_nav{
		text-align: center;
		background-color: #fff;
		padding: 0.3rem;
		margin: 0.1rem 1rem;
		border: 1px solid #eee;
		border-radius: 0.5rem;
	}
</style>
